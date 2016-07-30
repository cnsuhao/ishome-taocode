CREATE TABLE class
(
    cid BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '班级id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    name VARCHAR(50) COMMENT '班级名称',
    year VARCHAR(10) COMMENT '入学年份',
    cnum VARCHAR(10) COMMENT '班级编号',
    term_id BIGINT(12) COMMENT '所属学期id',
    grade_id BIGINT(12) COMMENT '年级标签id',
    work_id BIGINT(12) COMMENT '作息时间模板id',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (cid)
) COMMENT '班级表'
;
