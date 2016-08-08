CREATE TABLE class_course
(
    id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    cid BIGINT(12) NOT NULL COMMENT '班级id',
    term BIGINT(8) DEFAULT '1' NOT NULL COMMENT '学期',
    class_time TINYINT(2) NOT NULL COMMENT '课次',
    week_day TINYINT(1) COMMENT '星期',
    use_day DATETIME COMMENT '日期',
    course_id BIGINT(12) COMMENT '课程id',
    course_name VARCHAR(50) COMMENT '课程名称',
    tid BIGINT(12) COMMENT '教师id',
    teacher_name VARCHAR(50) COMMENT '教师姓名',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '班级课程教师关联表'
;
