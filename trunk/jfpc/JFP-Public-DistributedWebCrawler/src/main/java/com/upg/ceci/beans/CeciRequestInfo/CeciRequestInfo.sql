CREATE TABLE ceci_request_info
(
    id NUMBER(20) NOT NULL COMMENT '主键ID',
    area_code VARCHAR(12) COMMENT '区域码',
    host VARCHAR(1000) COMMENT '域名',
    rurl VARCHAR(1000) COMMENT '请求地址',
    is_cookie NUMBER(4) COMMENT '是否需要cookie',
    cookie_domen VARCHAR(320) COMMENT 'cookie域',
    cookie_path VARCHAR(120) COMMENT 'cookie路径',
    mode_type VARCHAR(32) COMMENT '模块类型',
    mode_type_desc VARCHAR(320) COMMENT '模块说明',
    request_type VARCHAR(64) COMMENT '请求方式(get,post)',
    info_type VARCHAR(2) COMMENT '信息类型',
    params VARCHAR(1000) COMMENT '参数',
    sub_id VARCHAR(2) COMMENT '子类请求ID',
PRIMARY KEY (id)
) COMMENT '抓取地址配置'
