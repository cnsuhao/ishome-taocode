CREATE TABLE task
(
    task_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '作业id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    course_id BIGINT(12) NOT NULL COMMENT '课程id',
    task_name VARCHAR(50) NOT NULL COMMENT '作业标题',
    content TEXT() COMMENT '作业内容',
    video VARCHAR(255) COMMENT '作业音频',
    pic VARCHAR(255) COMMENT '作业图片',
    task_classer TEXT() COMMENT '作业布置班级',
    start_time VARCHAR(24) COMMENT '作业开始时间',
    end_time VARCHAR(24) COMMENT '作业结束时间',
    is_top TINYINT(1) DEFAULT '0' COMMENT '是否置顶',
    publish_time VARCHAR(24) COMMENT '作业发布时间',
    is_audit TINYINT(1) DEFAULT '1' COMMENT '审核状态',
    num BIGINT(11) COMMENT '阅读次数',
    author BIGINT(12) COMMENT '作者（教师tid）',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (task_id)
) COMMENT '作业表'
;
