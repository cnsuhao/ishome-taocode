CREATE TABLE mec_score
(
    mec_score_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '评分id',
    term_id BIGINT(12) NOT NULL COMMENT '学期id',
    cid BIGINT(12) NOT NULL COMMENT '班级id',
    mec_item_id BIGINT(12) NOT NULL COMMENT '德育项目id',
    scroe_order VARCHAR(2) NOT NULL COMMENT '德育班级评分序号',
    scroe VARCHAR(12) COMMENT '德育班级评分',
    score_time VARCHAR(24) NOT NULL COMMENT '评分时间',
    tid BIGINT(12) NOT NULL COMMENT '评分人（tid）',
    score_pic VARCHAR(255) COMMENT '评分图片',
    score_content VARCHAR(50) COMMENT '评分内容',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (mec_score_id)
) COMMENT '德育班级评分表'
;
