CREATE TABLE u_student_parent
(
    id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    parent_id BIGINT(12) NOT NULL COMMENT '家长id',
    role TINYINT(1) NOT NULL COMMENT '家长角色',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    student_id BIGINT(12) NOT NULL COMMENT '学生id',
    is_default TINYINT(1) COMMENT '是否为缺省学生',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '学生家长关联表'
;
