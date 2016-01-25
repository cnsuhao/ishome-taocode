1、基本说明
新短信中心代码工程HS-MED-Framework-SMS
 
     1.客户端配置
       <bean class='com.hundsun.med.framework.sms.UserSMSSendServiceImpl '/> 
      开发依赖HS-MED-Framework-Support
     2.服务端配置
       <!-- 短信监听队列 -->
    <bean class='com.hundsun.med.framework.sms.server.monitor.RedisSubscriberThread' init-method='run'/>
    <!-- 短信网关过滤规则定义 -->
    <bean id='SMSGatewayFactory' class='com.hundsun.med.framework.sms.server.SMSGatewayFactory'>
        <property name='defaultGateway' ref='chinamovie' />
        <property name='smsGatewayConfig'>
            <map>   
                <!-- 基于手机号段处理定义 --> 
                <!-- 中国移动 -->                
                <entry key='134;135;136;137;138;139;150;151;152;157;158;159;182;183;184;187;178;188;147;1705'             value='chinamovie' />
                <!-- 中国联通 -->    
                <entry key='130;131;132;145155;156;176;185;186;1709'             value='chinamovie' />    
                <!-- 中国电信 -->    
                <entry key='133;153;177;180;181;189;1349;1700'             value='chinamovie' />                       
            </map>
        </property>
    </bean>    
    <!-- 短信网关定义 –>   这里实现不同的网关定义
    <bean id='chinamovie' class='com.hundsun.med.framework.sms.server.impl.DefaultSMSGatewayImpl' />
2、短信中心调用
依赖工程HS-MED-Framework-Support
配置文件参考《客户端配置》
代码片段
    @Resource
	ISSMSSupport sms;
	boolean phonePush = sms.send("123456", "15057177411", "短信测试");