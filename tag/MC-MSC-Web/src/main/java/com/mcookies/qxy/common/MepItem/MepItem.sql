CREATE TABLE mep_item
(
    mep_item_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '德育个人项目id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    mep_item_name VARCHAR(50) NOT NULL COMMENT '德育个人项目名称',
    mep_item_explain VARCHAR(50) COMMENT '德育个人项目说明',
    rule_num VARCHAR(1) NOT NULL COMMENT '规则数量',
    initial_score VARCHAR(10) NOT NULL COMMENT '初始分',
    interval_score VARCHAR(10) NOT NULL COMMENT '间隔分',
    auth TINYINT(1) DEFAULT '0' COMMENT '权限',
    tids TEXT() COMMENT '可见教师集合',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (mep_item_id)
) COMMENT '德育个人项目表'
;
