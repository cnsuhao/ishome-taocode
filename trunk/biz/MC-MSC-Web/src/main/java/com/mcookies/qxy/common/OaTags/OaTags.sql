CREATE TABLE oa_tags
(
    oatags_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    oatags_name VARCHAR(255) COMMENT '标签名称',
    type TINYINT(1) COMMENT '标签类型',
    cycle VARCHAR(10) COMMENT '审核周期',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (oatags_id)
) COMMENT 'OA标签表'
;
