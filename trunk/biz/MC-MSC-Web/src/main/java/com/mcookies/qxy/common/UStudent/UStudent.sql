CREATE TABLE u_student
(
    student_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '学生id（自增）',
    uid BIGINT(12) NOT NULL COMMENT '用户id',
    sid BIGINT(12) NOT NULL COMMENT '学校id',
    number BIGINT(12) NOT NULL COMMENT '学号',
    student_name VARCHAR(30) NOT NULL COMMENT '学生姓名',
    phone VARCHAR(50) COMMENT '联系电话',
    method TINYINT(1) COMMENT '认证方式',
    openid VARCHAR(100) COMMENT 'openid',
    wechatname VARCHAR(100) COMMENT '微信名',
    wechathead VARCHAR(200) COMMENT '微信头像',
    city VARCHAR(50) COMMENT '城市',
    gender VARCHAR(50) COMMENT '性别',
    status TINYINT(1) COMMENT '状态',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (student_id)
) COMMENT '学生表'
;
