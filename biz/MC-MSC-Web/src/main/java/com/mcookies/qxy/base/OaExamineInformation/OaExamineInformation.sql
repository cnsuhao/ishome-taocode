CREATE TABLE oa_examine_information
(
    approval_information_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '审核信息id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    type TINYINT(1) COMMENT '标签类型',
    tags_id BIGINT(12) COMMENT '标签id',
    tid BIGINT(12) COMMENT '发起人id',
    content DATETIME COMMENT '待审批内容',
    result TINYINT(1) COMMENT '审核结果',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (approval_information_id)
) COMMENT 'OA审批信息表'
;
