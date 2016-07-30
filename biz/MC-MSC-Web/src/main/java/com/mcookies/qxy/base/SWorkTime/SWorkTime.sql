CREATE TABLE s_work_time
(
    work_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '作息时间模板id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    work_name VARCHAR(100) COMMENT '作息时间模板名称',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (work_id)
) COMMENT '作息时间模板'
;
