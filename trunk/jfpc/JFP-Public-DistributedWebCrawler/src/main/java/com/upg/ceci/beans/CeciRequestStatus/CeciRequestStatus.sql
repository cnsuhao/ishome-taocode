CREATE TABLE ceci_request_status
(
    id NUMBER(20) NOT NULL COMMENT '主键ID',
    area_code VARCHAR(12) COMMENT '区域码',
    conn_status NUMBER(11) COMMENT '请求地址状态',
    last_conn_time DATETIME COMMENT '最后刷新时间',
    last_error_time DATETIME COMMENT '最后失败时间',
    his_error_num NUMBER(12) COMMENT '历史失败次数',
    process_num NUMBER(8) COMMENT '处理记录数',
    process_num_success NUMBER(20) COMMENT '处理成功记录数',
PRIMARY KEY (id)
) COMMENT '抓取地址访问状态'
