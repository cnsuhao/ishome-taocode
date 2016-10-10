CREATE TABLE results_tag
(
    results_tag_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '成绩标签id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    term_id BIGINT(12) NOT NULL COMMENT '学期id',
    results_tag_name VARCHAR(50) NOT NULL COMMENT '成绩标签名称',
    results_tag_explain VARCHAR(50) COMMENT '成绩标签说明',
    start_time VARCHAR(24) COMMENT '考试开始时间',
    end_time VARCHAR(24) COMMENT '考试结束时间',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (results_tag_id)
) COMMENT '成绩标签表'
;
