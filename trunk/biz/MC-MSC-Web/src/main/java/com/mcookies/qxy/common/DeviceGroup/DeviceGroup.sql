CREATE TABLE device_group
(
    dgroup_id BIGINT(12) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
    sid BIGINT(12) NOT NULL COMMENT 'ѧУid',
    dgroup_name VARCHAR(50) NOT NULL COMMENT '��������',
    dgroup_explain VARCHAR(50) COMMENT '����˵��',
    is_use TINYINT(1) COMMENT '�Ƿ�����',
    create_time VARCHAR(24) COMMENT '����ʱ��',
    creator BIGINT(12) COMMENT '������',
    update_time VARCHAR(24) COMMENT '����ʱ��',
    updator BIGINT(12) COMMENT '��������',
PRIMARY KEY (dgroup_id)
) COMMENT '�豸�����'
;
