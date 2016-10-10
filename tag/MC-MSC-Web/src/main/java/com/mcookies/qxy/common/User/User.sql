CREATE TABLE user
(
    uid BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id（自增）',
    account VARCHAR(100) NOT NULL COMMENT '登录账号',
    email VARCHAR(100) COMMENT '登录邮箱',
    email_status TINYINT(1) COMMENT '邮箱是否验证',
    phone VARCHAR(50) COMMENT '手机号',
    password VARCHAR(32) COMMENT '密码',
    status TINYINT(1) COMMENT '状态（不启用）',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (uid)
) COMMENT '用户表'
;
