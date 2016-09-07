CREATE TABLE school_weixin
(
    sid BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '学校id',
    app_id VARCHAR(200) NOT NULL COMMENT '应用ID',
    app_secret VARCHAR(200) NOT NULL COMMENT '用用密钥',
    token VARCHAR(200) COMMENT '应用识别Token',
    aes_key VARCHAR(200) COMMENT '加密key',
    partner_id VARCHAR(200) COMMENT '支付商户号',
    partner_key VARCHAR(200) COMMENT '商户支付密钥',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (sid)
) COMMENT '学校微信企业号表'
;
