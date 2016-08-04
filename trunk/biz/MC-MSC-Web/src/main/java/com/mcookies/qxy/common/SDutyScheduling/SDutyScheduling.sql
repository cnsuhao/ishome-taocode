CREATE TABLE s_duty_scheduling
(
    id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    term_id BIGINT(12) COMMENT '所属学期',
    type TINYINT(1) COMMENT '岗位类型',
    week TINYINT(6) COMMENT '指定该学期的对应周',
    start_time DATETIME COMMENT '周开始日期',
    end_time DATETIME COMMENT '周结束日期',
    date DATETIME COMMENT '指定该学期的对应日期',
    leader_tids BIGINT(12) COMMENT '值日或值周领导id',
    tids BIGINT(12) COMMENT '值日或值周教师id',
    duty_id BIGINT(12) COMMENT '岗位id',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (id)
) COMMENT '岗位人员排班表'
;
