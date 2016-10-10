CREATE TABLE device_heartbeat
(
    id BIGINT(18) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    device_id BIGINT(12) NOT NULL COMMENT '学校设备id',
    send_time DATETIME COMMENT '心跳时间',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '设备心跳表'
;
