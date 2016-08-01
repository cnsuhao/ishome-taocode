CREATE TABLE s_term
(
    term_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '学期id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    term_name VARCHAR(80) COMMENT '学期名称',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    is_default TINYINT(1) COMMENT '是否为缺省学期',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (term_id)
) COMMENT '学期设置表'
;
