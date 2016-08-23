CREATE TABLE s_message
(
    id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    type TINYINT(1) COMMENT '消息类型',
    object TINYINT(1) COMMENT '对象类型',
    tid BIGINT(12) COMMENT '教师id',
    teacher_name BIGINT(12) COMMENT '教师姓名',
    parent_id BIGINT(12) COMMENT '家长id',
    parent_name VARCHAR(40) COMMENT '家长姓名',
    phone VARCHAR(32) COMMENT '手机号',
    sending_time VARCHAR(24) COMMENT '发送时间',
    message VARCHAR(255) COMMENT '短信内容',
    isreceipt TINYINT(1) COMMENT '是否需要回执',
    status TINYINT(1) COMMENT '状态',
    create_time DATETIME COMMENT '登录时间',
    creator BIGINT(12) DEFAULT '0' COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) DEFAULT '0' COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '短信消息记录表'
;
