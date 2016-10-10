CREATE TABLE school_weixin_group
(
    sid BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '学校id',
    gid BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户组id',
    parentid BIGINT(12) COMMENT '上级用户组ID',
    group_no VARCHAR(40) COMMENT '用户组编号',
    group_name VARCHAR(200) NOT NULL COMMENT '用户组名称',
    openid VARCHAR(200) COMMENT '用户组openId',
    wx_id VARCHAR(200) COMMENT '微信ID',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (sid , gid)
) COMMENT '学校微信企业号用户组表'
;
