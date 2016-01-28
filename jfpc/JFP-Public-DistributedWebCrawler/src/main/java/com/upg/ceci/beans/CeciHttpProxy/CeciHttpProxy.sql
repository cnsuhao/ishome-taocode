CREATE TABLE ceci_http_proxy (
  id int(11) NOT NULL DEFAULT '0',
  host varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  port int(6) DEFAULT NULL,
  type varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL,
  auth char(1) COLLATE utf8_unicode_ci DEFAULT '1',
  user varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  pwd varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id)
) COMMENT 'Http代理地址'
