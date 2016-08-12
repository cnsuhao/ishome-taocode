CREATE TABLE u_parent
(
    parent_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '家长id（自增）',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    uid BIGINT(12) NOT NULL COMMENT '用户id',
    parent_name BIGINT(30) NOT NULL COMMENT '家长姓名',
    phone VARCHAR(50) COMMENT '家长电话',
    work_unit VARCHAR(100) COMMENT '工作单位',
    position VARCHAR(50) COMMENT '职位',
    method TINYINT(1) COMMENT '认证方式',
    openid VARCHAR(100) COMMENT 'openid',
    wechatname VARCHAR(100) COMMENT '微信名',
    wechathead VARCHAR(200) COMMENT '微信头像',
    city VARCHAR(50) COMMENT '城市',
    gender VARCHAR(50) COMMENT '性别',
    status TINYINT(1) COMMENT '状态',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (parent_id)
) COMMENT '家长表'
;
