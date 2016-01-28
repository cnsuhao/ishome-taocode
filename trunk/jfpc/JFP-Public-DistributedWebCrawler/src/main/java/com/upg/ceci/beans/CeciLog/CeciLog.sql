CREATE TABLE ceci_log
(
    id VARCHAR(40) NOT NULL COMMENT '主键ID',
    area_code VARCHAR(12) COMMENT '区域码',
    log_date DATETIME COMMENT '处理日期',
    area_name VARCHAR(40) COMMENT '处理省份名称',
    process_num NUMBER(8) COMMENT '处理记录数',
    process_num_success NUMBER(20) COMMENT '处理成功记录数',
    info_type VARCHAR(8) COMMENT '信息类型',
PRIMARY KEY (id)
) COMMENT '抓取结果日志'
