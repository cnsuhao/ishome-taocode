CREATE TABLE ceci_analysis_template
(
    id NUMBER(20) NOT NULL COMMENT '主键ID',
    key_reg VARCHAR(128) COMMENT '基本信息key正则',
    val_reg VARCHAR(128) COMMENT '基本信息值的正则',
    data_row NUMBER(11) COMMENT '数据启始行',
    title_row NUMBER(11) COMMENT '标题行',
    data_col_count NUMBER(11) COMMENT '数据列数',
    tab_id VARCHAR(32) COMMENT '标签ID',
    tag VARCHAR(32) COMMENT '标签名称',
    selector VARCHAR(128) COMMENT '选择器',
    type VARCHAR(2) COMMENT '类型(1:基本信息解析,2:表格解析)',
    request_info_id NUMBER(20) COMMENT '请求信息id',
    xml_code VARCHAR(16) COMMENT '转换bean对应的xml',
    clazz VARCHAR(255) COMMENT '对应的实体类',
    template_type VARCHAR(12) COMMENT '模版类别',
PRIMARY KEY (id)
) COMMENT '网页解析模版'
