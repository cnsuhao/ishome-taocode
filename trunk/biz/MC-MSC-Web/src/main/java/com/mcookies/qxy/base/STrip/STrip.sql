CREATE TABLE s_trip
(
    id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    term_id BIGINT(12) NOT NULL COMMENT '学期id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    tid BIGINT(12) NOT NULL COMMENT '教师id',
    content VARCHAR(255) COMMENT '内容',
    date DATETIME COMMENT '日期',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '行程表'
;
