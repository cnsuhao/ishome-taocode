
[TOC]

## 千校云接口文档更新说明



##千校云Token方案说明



标签： 接口 Token 说明

---

### login.1.1文档更新说明
> 更新时间：2016-07-09

### login.2 Token方案总体说明
#### login.2.1 Token方案的基本功能
	Token方案主要的功能是为了保证接口安全有效的运行，防止恶意程序对接口的大并发刷新。Token接口从设计上说具备以下主要功能：
1. 用户身份确认：通过token认证，确认用户是系统的合法用户。
2. 用户权限确认：通过token认证，确认用户是否具备访问本接口的权力。未来通过token的身份识别来判断用户界面上能使用哪些功能菜单。
3. 用户身份变换：通过token查询接口，为后台的其他接口提供token对应的用户身份tid，uid，type（教师还是家长），sid，以及家长对应的学生student_id和班级cid等基本信息
4. 用户登录认证：通过登录接口（通过使用username和password登录。或者通过openid登录）验证用户是否能正常登录。返回用户的持久token

#### login.2.2 Token方案接口的组成
    token方案的接口由三部分组成，分别是token刷新接口，token验证接口，登录接口
- token刷新接口包括：token刷新接口
- token验证接口包括：token验证接口，token状态查询接口
- 登录接口包括：用户名密码登录接口，OpenID登录接口

#### login.2.3 Token方案的数据结构
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


#### login.2.4 Token方案的实现
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
    
### login.3. Token方案接口说明
#### login.3.1 token刷新接口
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

#### login.3.2 token验证接口
> 验证接口通过接口捕捉器统一调用，不再使用单独的验证接口。验证接口返回用户的基本登录信息；

#### login.3.3 token状态查询接口
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



### login.4 学校相关接口
#### login.4.1 学校列表查询接口
> 可以获取系统中所有的学校列表，不需要token验证或者使用特殊的游客token；
> 该接口主要目的为系统管理员或者用户登录选择学校时使用，需要学校名字和id；
``` json
Url:        /qxy/school/list?type=[type]
Method:     GET
Header:     Content-type:application/json
Parameter:
    - 'type':    参数不传或者为0-仅返回有效的学校，1-返回所有学校
Response:  
    - `status`：      0->成功
				      1->获取失败 	
    - 'sid':          学校id
    - 'schoolName':   学校名字
    - 'isUse':        是否启用0-停用，1-启用
    - 'count':        学校数量
```
> **返回结果示例：**
``` json
{
    "status": 0,
    "data": {
        "count":"10",
        "school":[
	        {
		        "sid":"1",
		        "isUse":"1",
		        "schoolName":"英才中学"
	        },
	        {
		        "sid":"2",
		        "isUse":"1",
		        "schoolName":"博文中学"
	        },
	        {
		        "sid":"3",
		        "isUse":"1",
		        "schoolName":"南阳中学"
	        }
        ]
    }
}
```

#### login.4.2 学校详细信息查询接口
>可以通过学校id获取学校详细信息
>需要token验证身份信息
``` json
Url:        /qxy/school/info?sid=[sid]
Method:     GET
Header:     Content-type:application/json
Parameter:
    - 'sid':      学校的id,可不传此参数，会默认获取token中包含sid的信息，传sid会获取其详细信息
Response:  
    - `status`： 0->成功
				1->获取失败 
                11->token is invalid
                12->token is timeout
                13->Illegal interface calls, not power use interface	
    - 'sid':          学校id
    - 'schoolName':   学校名字
    - 'isUse':        是否启用0-停用，1-启用
    - 'createUid':    创建者uid
    - 'contacts':     创建者uid
    - 'phone':        联系电话
    - 'address':      学校地址
    - 'sCode':        学校识别码
    - 'createTime':   创建时间
```
> **返回结果示例：**
``` json
{
    "status": 0,
    "data": {
        "sid":"10",
        "schoolName":"英才中学",
        "isUse":"1",
        "createUid":"12",
        "contacts":"张校长",
        "phone":"ddd",
        "address":"某某地址",
        "sCode":"STDX"
        "createTime":"2016-8-9 00:00:00"
    }
}
```
#### login.4.3 学校详细信息新增接口
>由系统管理员操作学校新增，其他角色不能处理这项业务，学校名字和学校识别码系统中唯一，不能重复，需要检查；
>需要token验证身份信息
``` json
Url:        /qxy/school?token=[token]
Method:     POST
Header:     Content-type:application/json
Parameter:
    - 'createUid':      创建者uid
    - 'schoolName':     学校名字，系统唯一不能重复
    - 'contacts':       学校联系人
    - 'phone':          联系电话
    - 'address':        学校地址
    - 'sCode':          学校识别码（系统内唯一，预留用作选择学校使用）
    - 'token':          token
示例
{
	"createUid":"1",
	"schoolName":"衡水一中",
	"isUse":"1",
	"contacts":"张三三",
	"phone":"15785685658",
	"address":"河北省衡水市向阳大道",
	"sCode":"HSYZ"
}
Response:  
    - `status`： 0->ok
				1->新增失败，识别码已存在
				2->新增失败，该学校名字已存在 
                11->token is invalid
                12->token is timeout
                13->Illegal interface calls, not power use interface
```

> **返回结果示例：**

``` json
{
    "status": 0,
    "data": {
        "info":"ok"
    }
}
```
#### login.4.4 学校详细信息修改/停用（启用）接口
>需要token验证身份信息
>可以由系统管理员来修改（可以修改其他任何学校管理员的资料），也可以由学校管理员来自己修改自己的资料（仅能修改自己的）
``` json
Url:        /qxy/school?token=[token]
Method:     PUT
Header:     Content-type:application/json
Parameter:
    - 'sid':            学校id  #系统管理员允许传sid修改某个学校，学校管理员不能传sid，只能修改token中的sid的信息
    - 'schoolName':     学校名字，系统唯一不能重复
    - 'isUse':          是否启用0-停用，1-启用，默认新增为启用
    - 'contacts':       学校联系人
    - 'phone':          联系电话
    - 'address':        学校地址
    - 'sCode':          学校识别码（系统内唯一，预留用作选择学校使用）
    - 'token':          token
示例
{
	"sid":"1",                #系统管理员才允许传该参数
	"schoolName":"衡水一中",
	"isUse":"1",
	"contacts":"张三三",
	"phone":"15785685658",
	"address":"河北省衡水市向阳大道",
	"sCode":"HSYZ"
}
Response:  
    - `status`： 0->ok
				1->修改失败，识别码已存在
				2->修改失败，该学校名字已存在 
                11->token is invalid
                12->token is timeout
                13->Illegal interface calls, not power use interface
```
> **返回结果示例：**
``` json
{
    "status": 0,
    "data": {
        "info":"ok"
    }
}
```
#### login.4.5 学校删除接口
>只允许系统管理员使用
>需要token验证身份信息
``` json
Url:        /qxy/school?token=[token]
Method:     DELETE
Header:     Content-type:application/json
Parameter:
    - 'sid':            学校id
    - 'token':          token
示例
{
	"sid":"1"
}
Response:  
    - `status`： 0->ok
				1->删除失败，该学校已经关联了教工
				2->删除失败，该学校已不存在 
                11->token is invalid
                12->token is timeout
                13->Illegal interface calls, not power use interface
```
> **返回结果示例：**

``` json
{
    "status": 0,
    "data": {
        "info":"ok"
    }
}
```
### login.5 用户登录相关接口
#### 5.1 用户名/手机号/邮箱密码登录接口
> 登录接口不需要token验证
> 登录操作需要先选择要登录的学校，然后点击下一步选择账号或者手机号、邮箱方式输入密码登录，登录成功后返回用户token；
``` json
Url:        /qxy/login
Method:     POST
Header:     Content-type:application/json
Parameter:
    - 'sid':         学校id
    - 'account':     用户账号
    - 'email':       用户邮箱
    - 'phone':       用户手机号
    - 'password':    用户密码
示例
{
	"sid":"1",
	"account":"username",
	"email":"eee@qq.com",
	"phone":"15265236523",
	"password":"123456"
}
Response:  
    - `status`：      0->登录成功
				      1->登录失败，用户不在该机构下
				      2->登录失败，账号或密码不正确
```
> **返回结果示例：**
``` json
{
    "status": 0,
    "data": {
        "info":"登录成功",
        "token":"ekcoalsdfjladi323lasdfj23ls3e23"
    }
}
```

#### login.5.2 手机号短信验证码登录接口
>不需要token验证
>如果忘记密码，可以使用手机短信验证码方式验证登录，验证成功即登录成功后获取token；如果验证成功后反馈用户手机号不存在系统中，则不能登录系统；
``` json
Url:        /qxy/login/captcha
Method:     POST
Header:     Content-type:application/json
Parameter:
    - 'captcha':       用户收到的验证码
    - 'phone':         用户手机号
示例
{
	"phone":"13562563656",
	"captcha":"63656"	
}
Response:  
    - `status`：     0->登录成功
					 1->登录失败，验证码已过期，请重新获取验证码
					 2->登录失败，用户不是系统用户，请联系管理员
```
> **返回结果示例：**
``` json
{
    "status": 0,
    "data": {
        "token":"6kasjdkfjalsdjflajsdlkf58695"
    }
}
```

#### login.5.3 生成短信验证码接口
>不需要token验证
>用户使用手机短信验证码登录系统是，需要点击获取短信验证码获取，点击后会生成验证码，格式为6为随机数字，验证有效期为30分钟，过期将失效，需要重新生成验证码；重新生成验证码需要间隔60s；重新生成验证码后，上一个验证码会失效；
``` json
Url:        /qxy/captcha/create
Method:     POST
Header:     Content-type:application/json
Parameter:
    - 'phone':       用户手机号
示例
{
	"phone":"13562563656"
}
Response:  
    - `status`：     0->验证码生成成功
					 1->1分钟之后才能够重新生成验证码
```
> **返回结果示例：**
``` json
{
    "status": 0,
    "data": {
        "captcha":"658695",
        "key":"12",                   #该验证码信息对应的key
        "phone":"15256235256",
        "effectiveTime":"00:30:00",
        "createTime":"2016-8-11 00:00:00"
    }
}
```
#### login.5.4 忘记密码（手机）接口
>忘记密码功能为用户通过手机短信方式重新设置密码的功能，通过手机号方式重置，系统会发送短信验证码，用户输入验证码验证成功后，即可修改密码；
``` json
Url:        /qxy/forgetPassword
Method:     POST
Header:     Content-type:application/json
Parameter:
    - 'phone':       用户手机号
    - 'captcha':     短信验证码
示例
{
	"phone":"13526523256",             
	"captcha":"63656"	
}
Response:  
    - `status`：    0->验证成功
	                1->验证失败，手机号不是系统用户
```
> **返回结果示例：**
``` json
{
    "status": 0,
    "data": {
        "info":"验证成功",
        "uid":"12"
    }
}
```
#### login.5.5 重置密码接口
>重置密码功能为用户通过手机短信方式重新设置密码的功能，用户通过验证短信验证码的方式进入到重置密码页面，即可修改密码；修改完成后即登录成功；
``` json
Url:        /qxy/resetPassword
Method:     POST
Header:     Content-type:application/json
Parameter:
    - 'repeatPassword':     确认密码
    - 'newPassword':        新密码
    - 'uid':                用户id
示例
{
	"uid":"6", 
	"newPassword":"13526523256",             
	"repeatPassword":"135@qq.com"              
}
Response:  
    - `status`：    0->重置密码成功
	                1->重置密码失败，用户不存在
	                2->重置密码失败，两次输入的密码不一致
```
> **返回结果示例：**
``` json
{
    "status": 0,
    "data": {
        "info":"重置密码成功",
        "token":"6kasjdkfjalsdjflajsdlkf58695"
    }
}
```


#### login.5.5 OpenID登录接口

