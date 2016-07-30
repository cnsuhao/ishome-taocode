CREATE TABLE s_work_rule
(
    work_rule_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '作息时间规则id',
    work_id BIGINT(12) COMMENT '作息时间模板id',
    sid BIGINT(12) COMMENT '学校id',
    rule_name VARCHAR(80) COMMENT '作息时间规则名称',
    rule_stage TINYINT(1) COMMENT '规则对应时间段',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (work_rule_id)
) COMMENT '作息时间规则表'
;
