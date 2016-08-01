CREATE TABLE student_rfid
(
    id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    student_id BIGINT(12) NOT NULL COMMENT '学生id',
    rfid BIGINT(12) NOT NULL COMMENT '学生卡id',
    is_effective TINYINT(1) COMMENT '是否有效',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time BIGINT(12) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '学生一卡通关联表'
;
