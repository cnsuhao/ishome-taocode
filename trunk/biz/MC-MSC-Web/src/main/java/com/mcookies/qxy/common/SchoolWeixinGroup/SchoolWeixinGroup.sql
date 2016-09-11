CREATE TABLE school_weixin_group
(
    pid BIGINT(14) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据id',
    sid BIGINT(12) COMMENT '学校id',
    gid BIGINT(12) COMMENT '用户组id',
    group_no VARCHAR(40) COMMENT '用户组编号',
    group_name VARCHAR(200) COMMENT '用户组名称',
    openid VARCHAR(200) COMMENT '用户组openId',
    school_app_id VARCHAR(200) COMMENT '关注学校企业号ID',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (pid)
) COMMENT '学校微信企业号用户组表'
;
