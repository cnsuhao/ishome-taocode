CREATE TABLE device_tag
(
    id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    name VARCHAR(255) COMMENT '地点名字',
    device_id BIGINT(12) NOT NULL COMMENT '学校设备id',
    username VARCHAR(40) COMMENT '设备账号',
    password VARCHAR(128) COMMENT '设备密码',
    last_login_time DATETIME COMMENT '最近一次登陆时间',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '设备标签表'
;
