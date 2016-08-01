CREATE TABLE s_course
(
    course_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程id',
    sid BIGINT(12) COMMENT '学校id',
    course_name VARCHAR(80) COMMENT '课程名称',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (course_id)
) COMMENT '课表内容标签表'
;
