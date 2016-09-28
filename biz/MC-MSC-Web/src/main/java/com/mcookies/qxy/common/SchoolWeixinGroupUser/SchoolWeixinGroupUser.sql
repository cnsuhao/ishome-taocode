CREATE TABLE school_weixin_group_user
(
    sid BIGINT(12) DEFAULT '1' NOT NULL COMMENT '学校id',
    gid BIGINT(12) DEFAULT '0' COMMENT '用户组id',
    uid BIGINT(12) DEFAULT '1' NOT NULL COMMENT '用户id',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
    SEQNO VARCHAR2(12) NOT NULL,
PRIMARY KEY (seqno)
) COMMENT '学校微信企业号用户所属用户组表'
;
