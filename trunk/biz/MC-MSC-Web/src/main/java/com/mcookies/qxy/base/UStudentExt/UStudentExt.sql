CREATE TABLE u_student_ext
(
    student_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '学生id',
    photo VARCHAR(200) COMMENT '学籍照片',
    nation VARCHAR(20) COMMENT '民族',
    native_place VARCHAR(200) COMMENT '籍贯',
    card_type VARCHAR(50) COMMENT '证件类型',
    card_number VARCHAR(100) COMMENT '证件号码',
    is_overseas TINYINT(1) COMMENT '是否港澳台侨胞',
    political VARCHAR(100) COMMENT '政治面貌',
    fith VARCHAR(100) COMMENT '宗教信仰',
    health VARCHAR(100) COMMENT '健康状况',
    blood VARCHAR(10) COMMENT '血型',
    date_of_school VARCHAR(50) COMMENT '入学日期',
    date_of_birth VARCHAR(50) COMMENT '出生日期',
    student_type VARCHAR(100) COMMENT '学生类别',
    residence_type VARCHAR(100) COMMENT '户口类型',
    residence_address VARCHAR(255) COMMENT '户口地址',
    is_use TINYINT(1) COMMENT '是否启用',
    create_time VARCHAR(24) COMMENT '创建时间',
    creator BIGINT(12) COMMENT '创建者',
    update_time VARCHAR(24) COMMENT '更新时间',
    updator BIGINT(12) COMMENT '最后更新者',
PRIMARY KEY (student_id)
) COMMENT '学生信息扩展表'
;
