CREATE TABLE log_attendance
(
    id BIGINT(18) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    rfid BIGINT(12) NOT NULL COMMENT '学生卡id',
    device_id BIGINT(12) NOT NULL COMMENT '学校设备id',
    mark_time VARCHAR(24) NOT NULL COMMENT '打卡时间',
    flag TINYINT(1) NOT NULL COMMENT '行为状态',
    source_json TEXT NOT NULL COMMENT '接收原文日志json',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '考勤日志表'
;
