CREATE TABLE device_alarm
(
    id BIGINT(18) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    device_id BIGINT(12) NOT NULL COMMENT '学校设备id',
    alarm_information VARCHAR(255) COMMENT '报警信息',
    alarm_time DATETIME COMMENT '报警时间',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '设备报警表'
;
