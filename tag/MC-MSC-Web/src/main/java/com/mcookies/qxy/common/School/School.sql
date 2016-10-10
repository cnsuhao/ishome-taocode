CREATE TABLE school
(
    sid BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '学校id',
    create_uid BIGINT(12) NOT NULL COMMENT '创建人id',
    school_name VARCHAR(30) NOT NULL COMMENT '学校名字',
    contacts VARCHAR(11) COMMENT '联系人',
    phone VARCHAR(50) COMMENT '电话',
    address VARCHAR(50) COMMENT '地址',
    s_code VARCHAR(30) COMMENT '学校识别码(不启用)',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (sid)
) COMMENT '学校表'
;
