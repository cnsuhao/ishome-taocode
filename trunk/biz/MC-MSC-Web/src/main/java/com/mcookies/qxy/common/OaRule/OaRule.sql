CREATE TABLE oa_rule
(
    oarule_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '规则id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    oatags_id BIGINT(12) COMMENT '标签id',
    oarule_name VARCHAR(100) COMMENT '规则名称',
    serial_number TINYINT(2) COMMENT '规则序号',
    adopt_type VARCHAR(1) COMMENT '规则通过方式',
    tids DATETIME COMMENT '审核人列表',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (oarule_id)
) COMMENT 'OA规则表'
;
