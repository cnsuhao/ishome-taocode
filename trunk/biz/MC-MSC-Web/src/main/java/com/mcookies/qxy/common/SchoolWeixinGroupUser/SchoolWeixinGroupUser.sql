CREATE TABLE school_weixin_group_user
(
    pid BIGINT(14) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据id',
    sid BIGINT(12) COMMENT '学校id',
    gid BIGINT(12) COMMENT '用户组id',
    uid BIGINT(12) COMMENT '用户id',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (pid)
) COMMENT '学校微信企业号用户所属用户组表'
;
