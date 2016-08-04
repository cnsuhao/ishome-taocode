CREATE TABLE class_alarm
(
    cid BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '班级id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    alarmrule_id BIGINT(12) NOT NULL COMMENT '规则id',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (cid)
) COMMENT '班级报警规则关联表'
;
