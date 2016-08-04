CREATE TABLE page_personal
(
    personal_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    tid BIGINT(12) NOT NULL COMMENT '教工id',
    jurisdiction_id BIGINT(12) COMMENT '页面权限id',
    t_json TEXT COMMENT '权限json',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (personal_id)
) COMMENT '个人权限页面表'
;
