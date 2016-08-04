CREATE TABLE u_teacher
(
    tid BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '教师id（自增）',
    uid BIGINT(12) NOT NULL COMMENT '用户id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    teacher_name VARCHAR(20) NOT NULL COMMENT '教师姓名',
    phone VARCHAR(50) COMMENT '联系电话',
    email VARCHAR(255) COMMENT '邮箱',
    id_type TINYINT(1) COMMENT '用户类型',
    method TINYINT(3) COMMENT '认证方式',
    openid VARCHAR(50) COMMENT 'openid',
    wechatname VARCHAR(100) COMMENT '微信名（不启用）',
    wechathead VARCHAR(200) COMMENT '微信头像',
    city VARCHAR(50) COMMENT '城市',
    gender TINYINT(1) COMMENT '性别',
    status TINYINT(1) COMMENT '状态',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time DATETIME COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time DATETIME COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (tid)
) COMMENT '教师表'
;
