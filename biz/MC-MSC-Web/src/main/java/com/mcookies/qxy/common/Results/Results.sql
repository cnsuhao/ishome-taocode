CREATE TABLE results
(
    results_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '成绩id',
    results_tag_id BIGINT(12) NOT NULL COMMENT '成绩标签id',
    course_id BIGINT(12) NOT NULL COMMENT '课程id',
    cid BIGINT(12) NOT NULL COMMENT '班级id',
    student_id BIGINT(12) NOT NULL COMMENT '学生id',
    student_name VARCHAR(50) COMMENT '学生姓名',
    score VARCHAR(10) NOT NULL COMMENT '分数',
    test_time VARCHAR(24) COMMENT '考试时间',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (results_id)
) COMMENT '成绩表'
;
