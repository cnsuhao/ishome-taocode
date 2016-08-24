CREATE TABLE log_loginer
(
    puk VARCHAR(40) NOT NULL COMMENT '系统数据唯一识别ID（固定主键）',
    account VARCHAR(40) COMMENT '登录账户名',
    uid BIGINT(12) COMMENT '用户ID',
    user_type TINYINT(1) DEFAULT '0' COMMENT '用户种类',
    is_admin TINYINT(1) DEFAULT '1' COMMENT '超级管理员',
    ip_adress VARCHAR(40) COMMENT 'IP地址',
    client_type VARCHAR(4) COMMENT '客户端种类',
    sid BIGINT(12) COMMENT '学校ID',
    product_id VARCHAR(32) DEFAULT 'QXY' COMMENT '产品ID',
    act_type TINYINT(1) DEFAULT '0' COMMENT '动作类别',
    create_time DATETIME COMMENT '登录时间',
    creator BIGINT(12) DEFAULT '0' COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) DEFAULT '0' COMMENT '最后更新者',
PRIMARY KEY (puk)
) COMMENT '用户登录日志'
;
