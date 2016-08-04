CREATE TABLE news_column
(
    column_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '栏目id（自增）',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    title VARCHAR(80) NOT NULL COMMENT '栏目名称',
    auth TINYINT(1) COMMENT '访问权限',
    is_check TINYINT(1) COMMENT '是否需要审核',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (column_id)
) COMMENT '栏目表'
;
