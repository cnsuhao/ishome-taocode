CREATE TABLE tk_loginer
(
    token VARCHAR(200) NOT NULL COMMENT 'TOKEN',
    json TEXT() COMMENT '用户ID',
PRIMARY KEY (token)
) COMMENT '当前登录用户Token表'
;
