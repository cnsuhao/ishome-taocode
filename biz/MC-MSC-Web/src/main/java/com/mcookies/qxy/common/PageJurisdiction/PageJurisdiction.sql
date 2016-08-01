CREATE TABLE page_jurisdiction
(
    jurisdiction_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '页面权限id',
    content BIGINT(12) NOT NULL COMMENT '权限内容',
    page_id VARCHAR(10) COMMENT '对应页面',
    identity TINYINT(1) COMMENT '对应身份',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (jurisdiction_id)
) COMMENT '页面权限管理表'
;
