CREATE TABLE alarm
(
    alarm_id BIGINT(18) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    cid BIGINT(12) NOT NULL COMMENT '班级id',
    student_id BIGINT(12) NOT NULL COMMENT '学生id',
    date VARCHAR(24) COMMENT '日期',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    device_id BIGINT(12) NOT NULL COMMENT '学校设备id',
    flag BIGINT(1) COMMENT '规定行为状态',
    actual_flag BIGINT(1) COMMENT '实际行为状态',
    is_alarm BIGINT(1) COMMENT '是否报警',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (alarm_id)
) COMMENT '报警表'
;
