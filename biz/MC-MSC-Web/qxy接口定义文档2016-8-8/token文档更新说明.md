
[TOC]

## 千校云接口文档更新说明



##千校云Token方案说明



标签： 接口 Token 说明

---

### 1.1文档更新说明
> 更新时间：2016-07-09

### 2 Token方案总体说明
#### 2.1 Token方案的基本功能
	Token方案主要的功能是为了保证接口安全有效的运行，防止恶意程序对接口的大并发刷新。Token接口从设计上说具备以下主要功能：
1. 用户身份确认：通过token认证，确认用户是系统的合法用户。
2. 用户权限确认：通过token认证，确认用户是否具备访问本接口的权力。未来通过token的身份识别来判断用户界面上能使用哪些功能菜单。
3. 用户身份变换：通过token查询接口，为后台的其他接口提供token对应的用户身份tid，uid，type（教师还是家长），sid，以及家长对应的学生student_id和班级cid等基本信息
4. 用户登录认证：通过登录接口（通过使用username和password登录。或者通过openid登录）验证用户是否能正常登录。返回用户的持久token

#### 2.2 Token方案接口的组成
    token方案的接口由三部分组成，分别是token刷新接口，token验证接口，登录接口
- token刷新接口包括：token刷新接口
- token验证接口包括：token验证接口，token状态查询接口
- 登录接口包括：用户名密码登录接口，OpenID登录接口

#### 2.3 Token方案的数据结构
    token方案的数据结构包括两部分，数据库部分和redis部分。
    数据库部分包括，用户表，用户教师表，用户家长表，用户学生表，学生班级表等
    Redis部分包括token信息表，用户持久token表，以及当前token表
    Redis的KV结构如下：
    
表一：用户token信息表

Key | Value | 生命期 | 说明
---|---|---|---
base64(uid+type+sid) | authorizer_refresh_token | 无（有数据表示登录过） | 接口调用凭据刷新令牌（在登录的用户具备API权限时，才有此返回值），刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在登录成果时提供，请妥善保存。 一旦丢失，只能让用户重新登录权，才能再次拿到新的刷新令牌

表二：用户当前token表

Key | Value | 生命期 | 说明
---|---|---|---
authorizer_refresh_token | authorizer_access_token | 半年（暂定） |  凭刷新令牌（在登录的用户具备API权限时，才有此返回值），查询当前有效的access token

表三：用户当前token表

Key | Value | 生命期 | 说明
---|---|---|---
authorizer_access_token | json | 7200秒 |  当前有效的access token，查询用户的基本信息

``` json
表三的json格式如下：
{
    "uid":"1234",
    "sid":"2122",
    "type":"0",   #0-教师；1-学生家长
    "teacher":{
        "tid":"212",
        "tname":"张老师"
        "privile":""
    },
    "parent":{
        "pid":"221",
        "pname":"",
        "student_id":"2321",
        "student_name":"xxxx",
        "cid":"213"
    }
｝
```


#### 2.4 Token方案的实现
-  token系统采用类似微信第三方开发平台的双token模式，即authorizer_refresh_token（授权方的刷新令牌，刷新令牌主要用于“接口调用凭据”的获取和刷新）authorizer_access_token（接口调用凭据）。authorizer_refresh_token由客户端长期保存较长期限有效（暂定半年过期），authorizer_access_token失效的方式为退出登录时注销。重新登录的时候新生成authorizer_access_token。
- authorizer_access_token可以用来查询用户是否登录过，也可以用来刷新authorizer_access_token。authorizer_access_token是系统使用中的“接口调用凭据”，通过该authorizer_access_token前端才能调用后台的功能接口。authorizer_access_token有生存期，每次刷新后7200秒过期。
- 当authorizer_access_token过期失效后（也可在快失效前由前端主动调用刷新接口），前端需要用authorizer_refresh_token调用刷新接口重新获得有效的authorizer_access_token。
- authorizer_access_token在每个接口调用中都会传到服务器进行效验，过期失效的话会在接口中返回失效状态。
- 前端应该缓存authorizer_access_token的生存周期。当authorizer_access_token的有效时间快到的时候，前端应主动刷新authorizer_access_token。
- 用户登录时需要传的参数包括：sid，家长or教师，用户名和密码或者是open_id。
- redis中表三的每条记录都有过期时间，到了时间该记录会自动删除。提前刷新的话可以由接口获取的authorizer_refresh_token在的redis中表二中找到当前的使用的authorizer_access_token显式删除表三中的对应数据，然后重新插入新authorizer_access_token。
- redis中表二有一定生命周期，比如半年一年。当表二中的数据被清除后，用户的authorizer_refresh_token也随之失效，用户将不可能通过使用authorizer_refresh_token来获取新的authorizer_access_token，也就无法再使用系统，也就是说半年之后必须重新登录。这样表二是不会有冗余数据的，随着时间冗余数据也会被清除。
- 当用户登陆的时候，首先会检查redis表一中有没有对应的数据。没有对应的数据，表明是首次使用，直接创建authorizer_refresh_token并返回即可，并同时创建表二数据，表二数据的生命周期为半年（暂定），同时authorizer_access_token内容为空，等待用户调用接口生成该数据。如果已经有对应的数据，取出authorizer_refresh_token（说明该用户已经登录过系统）并检查表二，表二中也有数据说明用户是重复登录，直接返回持久authorizer_refresh_token，并对表二的生命期延长到半年。
- 如Redis表二中没有对应的数据。说明用户已经超过半年或者一年没有登录过系统。那么重新生成一个authorizer_refresh_token，修改表一中的authorizer_refresh_token记录。并重新插入到表二中，设置表二的生命周期为半年，同时返回新的authorizer_refresh_token即可。
    
### 3. Token方案接口说明
#### 3.1 token刷新接口
> token刷新接口接口返回了当前的authorizer_access_token，该API用于在授权方令牌（authorizer_access_token）失效时，可用刷新令牌（authorizer_refresh_token）获取新的令牌。请注意，此处token是2小时刷新一次，开发者需要自行进行token的缓存。

``` json
Url:        /qxy/component?token=[authorizer_refresh_token]
Method:     GET
Header:     Content-type:application/json
Parameter:
    - 'token':   登录时返回的authorizer_refresh_token
Response:
    - `status`：  0->成功
                 1->authorizer_refresh_token无效，需要重新登录
                 11->token is invalid
                 12->token is timeout
    - 'authorizer_access_token':  接口调用时使用的authorizer_access_token
    - 'expires_in':   有效时间
```

> **返回结果示例：**

``` json
{
    "status": 0,
    "data": {
        "authorizer_access_token":"aaUl5s6kAByLwgV0BhXNuIFFUqfrR8vTATsoSHukcIGqJgrc4KmMJ-JlKoC_-NKCLBvuU1cWPv4vDcLN8Z0pn5I45mpATruU0b51hzeT1f8", 
        "expires_in": 7200
    }
}
```

#### 3.2 token验证接口
> 验证接口通过接口捕捉器统一调用，不再使用单独的验证接口。验证接口返回用户的基本登录信息；

#### 3.3 token状态查询接口
> token刷新接口接口返回了当前的authorizer_access_token，该API用于在授权方令牌（authorizer_access_token）失效时，可用刷新令牌（authorizer_refresh_token）获取新的令牌。请注意，此处token是2小时刷新一次，开发者需要自行进行token的缓存。

``` json
Url:        /qxy/component_info?token=[authorizer_access_token]
Method:     GET
Header:     Content-type:application/json
Parameter:
    - 'token':    当前有效的authorizer_access_token
Response:
    - `status`：  0->成功
                  1->authorizer_access_token，需要重新刷新
                  11->token is invalid
                  12->token is timeout
    - 'teacher':  token对应的教师基本信息（如果是教师正常登录的话）
    - 'parent':   token对应的家长基本信息（如果是家长正常登录的话）
```

> **返回结果示例：**

``` json
{
    "status": 0,
    "data": {
        "uid":"1234",
        "sid":"2122",
        "type":"0",   #0-教师；1-学生家长
        "teacher":{
            "tid":"212",
            "tname":"张老师"
            "privile":""
        },
        "parent":{
            "pid":"221",
            "pname":"",
            "student_id":"2321",
            "student_name":"xxxx",
            "cid":"213"
        }
    }
}
```

#### 3.4 用户名密码登录接口







#### 3.5 OpenID登录接口




