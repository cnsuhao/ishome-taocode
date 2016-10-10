CREATE TABLE s_calendar
(
    calendar_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    term_id BIGINT(12) NOT NULL COMMENT '学期id',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    content VARCHAR(255) COMMENT '校历事项',
    department VARCHAR(200) COMMENT '责任部门',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (calendar_id)
) COMMENT '校历表'
;
