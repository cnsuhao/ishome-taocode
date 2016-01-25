CREATE OR REPLACE VIEW view_gys_zj AS
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商营业执照' AS k03_zjlb,'1' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mga1_yyzz g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商经营许可证' AS k03_zjlb,'2' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mga2_jyxkz g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商工商税务登记证' AS k03_zjlb,'3' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mga3_gsswdjz g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商经销授权书' AS k03_zjlb,'4' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mga4_jxsqs g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商销售人员委托书' AS k03_zjlb,'5' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mga5_xsrywts g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商售后服务承诺书' AS k03_zjlb,'6' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mga6_shfwcns g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k02_zjbh,'供应商进口商检报告' AS k03_zjlb,'7' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mga7_jksjbg g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1

	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k03_zjbh,'厂家营业执照' AS k04_zjlb,'8' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mga8c_yyzz g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k03_zjbh,'厂家生产许可证' AS k04_zjlb,'9' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mga9c_scxkz g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.p01_gysid as k01_gysid,g.f01_zczzwmc AS k03_zjbh,'厂家医疗器械注册证' AS k04_zjlb,'a' AS ppp,g.f06_fzdwmc AS f01_fzdwmc,g.f07_fzrq AS f02_fzrq,g.f08_yxnx AS f03_yxnx,g.f09_yxksrq AS f04_yxksrq,g.f10_yxzzrq AS f05_yxzzrq,g.f11_shzt AS f06_shzt,g.bbb FROM mgaac_ylqxzcz g, mgys0_jbxx m WHERE g.p01_gysid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k03_zjbh,'厂家耗材商品3C认证' AS k04_zjlb,'b' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgabc_hcsprz g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1
	UNION
	SELECT m.f01_qyqc AS fb1,m.f30_lxrxm AS fb2, m.f32_lxrdh AS fb3,g.cc1,g.puk,g.k01_gysid,g.k03_zjbh,'厂家消毒产品卫生许可证' AS k04_zjlb,'c' AS ppp,g.f01_fzdwmc,g.f02_fzrq,g.f03_yxnx,g.f04_yxksrq,g.f05_yxzzrq,g.f06_shzt,g.bbb FROM mgacc_xdcpwsxkz g, mgys0_jbxx m WHERE g.k01_gysid = m.puk AND g.ddd != 1
