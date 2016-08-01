CREATE TABLE alarm_rule
(
    alarmrule_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '规则id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    alarmrule_name VARCHAR(255) COMMENT '规则名',
    device_id BIGINT(12) NOT NULL COMMENT '学校设备id',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    flag TINYINT(1) COMMENT '行为状态',
    time_type TINYINT(1) COMMENT '生效时间设置类型',
    time_repeat VARCHAR(7) COMMENT '指定重复时间',
    time_date VARCHAR(255) COMMENT '指定特定日期',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (alarmrule_id)
) COMMENT '报警规则表'
;
