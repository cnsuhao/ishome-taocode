CREATE TABLE s_duty
(
    duty_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '岗位id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    name VARCHAR(80) COMMENT '岗位名称',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (duty_id)
) COMMENT '岗位设置表'
;
