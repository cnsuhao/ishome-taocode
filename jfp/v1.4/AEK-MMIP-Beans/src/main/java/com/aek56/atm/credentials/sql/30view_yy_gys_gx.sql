CREATE OR REPLACE VIEW view_yy_gys_gx AS
	SELECT
	c.puk,c.p01_yyid,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND ppp = '1'),'0') as t01_gys_yyzz,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND ppp = '2'),'0') as t02_gys_jyxkz,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND ppp = '3'),'0') as t03_gys_swdjz,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND ppp = '4'),'0') as t04_gys_jxsqs,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND ppp = '5'),'0') as t05_gys_xsrywts,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND ppp = '6'),'0') as t06_gys_shfwcns,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND ppp = '7'),'0') as t07_cj_yyzz,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND ppp = '8'),'0') as t08_cj_scxkz,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND ppp = '9'),'0') as t09_cj_spzcz,
	
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND datediff(SYSDATE(),g.f05_yxzzrq)>0 AND ppp = '1'),'0') as g01_gys_yyzz,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND datediff(SYSDATE(),g.f05_yxzzrq)>0 AND ppp = '2'),'0') as g02_gys_jyxkz,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND datediff(SYSDATE(),g.f05_yxzzrq)>0 AND ppp = '3'),'0') as g03_gys_swdjz,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND datediff(SYSDATE(),g.f05_yxzzrq)>0 AND ppp = '4'),'0') as g04_gys_jxsqs,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND datediff(SYSDATE(),g.f05_yxzzrq)>0 AND ppp = '5'),'0') as g05_gys_xsrywts,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND datediff(SYSDATE(),g.f05_yxzzrq)>0 AND ppp = '6'),'0') as g06_gys_shfwcns,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND datediff(SYSDATE(),g.f05_yxzzrq)>0 AND ppp = '7'),'0') as g07_cj_yyzz,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND datediff(SYSDATE(),g.f05_yxzzrq)>0 AND ppp = '8'),'0') as g08_cj_scxkz,
	IFNULL((select count(g.puk) as sumNum from view_yy_zj g where g.k01_gysid = c.puk AND g.p01_yyid = c.p01_yyid AND datediff(SYSDATE(),g.f05_yxzzrq)>0 AND ppp = '9'),'0') as g09_cj_spzcz,
	
	ga.f01_qyqc as fb1,ga.f30_lxrxm as fb2,ga.f16_lxdh as fb3,ga.f32_lxrdh as fb4,ga.f19_logo_url as fb5,
	ya.f01_qyqc as eb1,ya.f30_lxrxm as eb2,ya.f16_lxdh as eb3,ya.f32_lxrdh as eb4,ya.f19_logo_url as eb5,
	
	IFNULL(c.f40,'2') as ggg,c.ddd,NOW() as uu1
	
	FROM myy1_yytjgysxx c
	LEFT JOIN mgys0_jbxx ga ON c.puk = ga.puk
	
	LEFT JOIN myy0_jbxx ya ON c.p01_yyid = ya.puk