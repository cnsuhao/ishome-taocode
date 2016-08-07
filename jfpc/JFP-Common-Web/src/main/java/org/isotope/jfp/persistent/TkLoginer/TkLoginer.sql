CREATE TABLE tk_loginer
(
    authorizer_access_token VARCHAR(200) NOT NULL COMMENT 'TOKEN',
    json TEXT() COMMENT '用户ID',
PRIMARY KEY (authorizer_access_token)
) COMMENT '第三方授权Token表'
;
