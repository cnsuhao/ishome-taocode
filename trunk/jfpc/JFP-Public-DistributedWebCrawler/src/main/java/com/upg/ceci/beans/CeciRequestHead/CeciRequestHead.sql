CREATE TABLE ceci_request_head
(
    id NUMBER(20) NOT NULL COMMENT '主键ID',
    head_name VARCHAR(120) COMMENT '参数名称',
    val VARCHAR(320) COMMENT '参数值',
    request_info_id NUMBER(20) COMMENT '请求信息id',
    type VARCHAR(2) COMMENT '类型(0:头部信息，1：cookie信息)',
PRIMARY KEY (id)
) COMMENT '抓取地址请求头配置'
