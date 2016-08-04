CREATE TABLE u_teacher_ext
(
    tid BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '教师id',
    job_number VARCHAR(255) COMMENT '教师工号',
    gender TINYINT(1) COMMENT '教师性别',
    date_of_birth VARCHAR(255) COMMENT '出生年月',
    start_work_t VARCHAR(255) COMMENT '开始工作时间',
    card_type TINYINT(1) COMMENT '证件类型',
    card_number VARCHAR(255) COMMENT '证件号码',
    home_address VARCHAR(255) COMMENT '家庭住址',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (tid)
) COMMENT '教师信息扩展表'
;
