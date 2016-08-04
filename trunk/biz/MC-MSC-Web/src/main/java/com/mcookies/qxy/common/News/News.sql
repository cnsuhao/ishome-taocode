CREATE TABLE news
(
    news_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '新闻id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    column_id BIGINT(12) NOT NULL COMMENT '栏目id',
    title VARCHAR(50) NOT NULL COMMENT '新闻标题',
    content TEXT COMMENT '内容',
    is_top TINYINT(1) COMMENT '是否置顶',
    is_pic TINYINT(1) COMMENT '是否包含图片',
    pic VARCHAR(255) COMMENT '幻灯图片',
    news_type TINYINT(1) COMMENT '新闻阅读范围',
    news_reader TEXT COMMENT '新闻可读教师',
    news_classer TEXT COMMENT '新闻可读班级',
    publish_time VARCHAR(24) COMMENT '发布时间',
    is_audit TINYINT(1) COMMENT '审核状态',
    num BIGINT(11) COMMENT '阅读次数',
    author BIGINT(12) COMMENT '作者',
    is_homenews TINYINT(1) COMMENT '是否为首页轮播',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (news_id)
) COMMENT '新闻表'
;
