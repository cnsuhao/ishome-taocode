CREATE TABLE class_student
(
    id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    cid BIGINT(12) NOT NULL COMMENT '班级id',
    student_id BIGINT(12) NOT NULL COMMENT '学生id',
    join_time DATETIME COMMENT '加入班级时间',
    exit_time DATETIME COMMENT '退出班级时间',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '班级学生关联表'
;
