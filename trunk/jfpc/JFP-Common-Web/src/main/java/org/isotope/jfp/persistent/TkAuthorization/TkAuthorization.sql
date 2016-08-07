CREATE TABLE tk_authorization
(
    authorizer_refresh_token VARCHAR(200) NOT NULL COMMENT 'TOKEN',
    uid BIGINT(12) COMMENT '用户ID',
    sid BIGINT(12) COMMENT '学校ID',
    type TINYINT(1) COMMENT '类别',
    tid VARCHAR(200) COMMENT '第三方id',
    start_time DATETIME COMMENT '授权时间',
    end_time DATETIME COMMENT '授权终了时间',
    create_time DATETIME COMMENT '登录时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (authorizer_refresh_token)
) COMMENT '第三方授权Token表'
;
