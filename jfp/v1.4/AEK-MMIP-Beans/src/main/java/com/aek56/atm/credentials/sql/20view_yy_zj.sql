CREATE OR REPLACE VIEW view_yy_zj AS
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商营业执照' AS k03_zjlb,'1' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgt1_yyzz g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商经营许可证' AS k03_zjlb,'2' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgt2_jyxkz g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商工商税务登记证' AS k03_zjlb,'3' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgt3_gsswdjz g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商经销授权书' AS k03_zjlb,'4' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgt4_jxsqs g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商销售人员委托书' AS k03_zjlb,'5' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgt5_xsrywts g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商售后服务承诺书' AS k03_zjlb,'6' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgt6_shfwcns g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商进口商检报告' AS k03_zjlb,'7' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgt7_jksjbg g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1

	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k03_zjbh,'厂家营业执照' AS k04_zjlb,'8' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgt8_cjyyzz g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k03_zjbh,'厂家生产许可证' AS k04_zjlb,'9' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgt9_cjscxkz g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p03_yyid as p01_yyid,g.cc1,g.puk,g.p01_gysid as k01_gysid,g.f01_zczzwmc AS k03_zjbh,'厂家医疗器械注册证' AS k04_zjlb,'a' AS ppp,g.f06_fzdwmc AS f01_fzdwmc,g.f07_fzrq AS f02_fzrq,g.f08_yxnx AS f03_yxnx,g.f09_yxksrq AS f04_yxksrq,g.f10_yxzzrq AS f05_yxzzrq,g.f11_shzt AS f06_shzt,g.bbb FROM mgta_cjylqxzcz g, myy0_jbxx m WHERE g.p03_yyid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k03_zjbh,'厂家耗材商品3C认证' AS k04_zjlb,'b' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgtb_cjhcsprz g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.p01_yyid,g.cc1,g.puk,g.k01_gysid,g.k03_zjbh,'厂家消毒产品卫生许可证' AS k04_zjlb,'c' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgtc_cjxdcpwsxkz g, myy0_jbxx m WHERE g.p01_yyid = m.puk AND g.ddd != 1
