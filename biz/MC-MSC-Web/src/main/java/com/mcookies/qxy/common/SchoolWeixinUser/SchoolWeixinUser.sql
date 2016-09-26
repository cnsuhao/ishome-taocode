CREATE TABLE school_weixin_user
(
    pid BIGINT(14) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据id',
    sid BIGINT(12) COMMENT '学校id',
    uid BIGINT(12) COMMENT '用户id',
    uert_type TINYINT(1) DEFAULT '9' COMMENT '用户类别',
    user_name VARCHAR(200) COMMENT '用户昵称',
    openid VARCHAR(200) COMMENT 'openId',
    wx_id VARCHAR(200) COMMENT '微信ID',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (pid)
) COMMENT '学校微信企业号关注用户表'
;
