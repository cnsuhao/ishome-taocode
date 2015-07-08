package org.isotopes.jfp.framework.support.bean;

import java.io.Serializable;

import javax.inject.Named;

import org.isotopes.jfp.framework.common.util.DateUtil;

/**
 * 数据实体超类
 * <p>
 * 需要定义@CacheKeyMethod标签来为实体指定唯一Key值，完成缓存
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 */
@Named
/** 基底共通Bean*/
public class FrameworkDataBean implements Serializable{	
	/**
	 * 表名
	 */
	private String tablename = this.getClass().getSimpleName();
	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	/**
	 * 更新时间戳
	 */
	private String uuu = DateUtil.currentTimeMillis3();
	public final String getUuu() {
		return uuu;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 
     * 系统数据唯一识别ID（固定主键）
     */
    private String puk = "";
 
    /** 
     * 外键1
     */
    private String k01 = "";
 
    /** 
     * 外键2
     */
    private String k02 = "";
 
    /** 
     * 外键3
     */
    private String k03 = "";
 
    /** 
     * 属性1
     */
    private String f01 = "";
 
    /** 
     * 属性2
     */
    private String f02 = "";
 
    /** 
     * 属性3
     */
    private String f03 = "";
 
    /** 
     * 属性4
     */
    private String f04 = "";
 
    /** 
     * 属性5
     */
    private String f05 = "";
 
    /** 
     * 属性6
     */
    private String f06 = "";
 
    /** 
     * 属性7
     */
    private String f07 = "";
 
    /** 
     * 属性8
     */
    private String f08 = "";
 
    /** 
     * 属性9
     */
    private String f09 = "";
 
    /** 
     * 属性10
     */
    private String f10 = "";
 
    /** 
     * 属性11
     */
    private String f11 = "";
 
    /** 
     * 属性12
     */
    private String f12 = "";
 
    /** 
     * 属性13
     */
    private String f13 = "";
 
    /** 
     * 属性14
     */
    private String f14 = "";
 
    /** 
     * 属性15
     */
    private String f15 = "";
 
    /** 
     * 属性16
     */
    private String f16 = "";
 
    /** 
     * 属性17
     */
    private String f17 = "";
 
    /** 
     * 属性18
     */
    private String f18 = "";
 
    /** 
     * 属性19
     */
    private String f19 = "";
 
    /** 
     * 属性20
     */
    private String f20 = "";
 
    /** 
     * 备注说明
     */
    private String bbb = "";
 
    /** 
     * 备用1
     */
    private String fb1 = "";
 
    /** 
     * 备用2
     */
    private String fb2 = "";
 
    /** 
     * 备用3
     */
    private String fb3 = "";
 
    /** 
     * 备用4
     */
    private String fb4 = "";
 
    /** 
     * 备用5
     */
    private String fb5 = "";
 
    /** 
     * 扩展1
     */
    private String eb1 = "";
 
    /** 
     * 扩展2
     */
    private String eb2 = "";
 
    /** 
     * 扩展3
     */
    private String eb3 = "";
 
    /** 
     * 扩展4
     */
    private String eb4 = "";
 
    /** 
     * 扩展5
     */
    private String eb5 = "";
 
    /** 
     * 有效标识
     */
    private String ddd = "";
 
    /** 
     * 创建时间
     */
    private String cc1 = "";
 
    /** 
     * 创建者
     */
    private String cc2 = "";
 
    /** 
     * 更新时间
     */
    private String uu1 = "";
 
    /** 
     * 更新者
     */
    private String uu2 = "";
 
    /** 
     * 获取系统数据唯一识别ID（固定主键）
     *
     * @return PUK 系统数据唯一识别ID（固定主键）
     */
    public String getPuk() {
        return this.puk;
    }
 
    /** 
     * 获取外键1
     *
     * @return K01 外键1
     */
    public String getK01() {
        return this.k01;
    }
 
    /** 
     * 获取外键2
     *
     * @return K02 外键2
     */
    public String getK02() {
        return this.k02;
    }
 
    /** 
     * 获取外键3
     *
     * @return K03 外键3
     */
    public String getK03() {
        return this.k03;
    }
 
    /** 
     * 获取属性1
     *
     * @return F01 属性1
     */
    public String getF01() {
        return this.f01;
    }
 
    /** 
     * 获取属性2
     *
     * @return F02 属性2
     */
    public String getF02() {
        return this.f02;
    }
 
    /** 
     * 获取属性3
     *
     * @return F03 属性3
     */
    public String getF03() {
        return this.f03;
    }
 
    /** 
     * 获取属性4
     *
     * @return F04 属性4
     */
    public String getF04() {
        return this.f04;
    }
 
    /** 
     * 获取属性5
     *
     * @return F05 属性5
     */
    public String getF05() {
        return this.f05;
    }
 
    /** 
     * 获取属性6
     *
     * @return F06 属性6
     */
    public String getF06() {
        return this.f06;
    }
 
    /** 
     * 获取属性7
     *
     * @return F07 属性7
     */
    public String getF07() {
        return this.f07;
    }
 
    /** 
     * 获取属性8
     *
     * @return F08 属性8
     */
    public String getF08() {
        return this.f08;
    }
 
    /** 
     * 获取属性9
     *
     * @return F09 属性9
     */
    public String getF09() {
        return this.f09;
    }
 
    /** 
     * 获取属性10
     *
     * @return F10 属性10
     */
    public String getF10() {
        return this.f10;
    }
 
    /** 
     * 获取属性11
     *
     * @return F11 属性11
     */
    public String getF11() {
        return this.f11;
    }
 
    /** 
     * 获取属性12
     *
     * @return F12 属性12
     */
    public String getF12() {
        return this.f12;
    }
 
    /** 
     * 获取属性13
     *
     * @return F13 属性13
     */
    public String getF13() {
        return this.f13;
    }
 
    /** 
     * 获取属性14
     *
     * @return F14 属性14
     */
    public String getF14() {
        return this.f14;
    }
 
    /** 
     * 获取属性15
     *
     * @return F15 属性15
     */
    public String getF15() {
        return this.f15;
    }
 
    /** 
     * 获取属性16
     *
     * @return F16 属性16
     */
    public String getF16() {
        return this.f16;
    }
 
    /** 
     * 获取属性17
     *
     * @return F17 属性17
     */
    public String getF17() {
        return this.f17;
    }
 
    /** 
     * 获取属性18
     *
     * @return F18 属性18
     */
    public String getF18() {
        return this.f18;
    }
 
    /** 
     * 获取属性19
     *
     * @return F19 属性19
     */
    public String getF19() {
        return this.f19;
    }
 
    /** 
     * 获取属性20
     *
     * @return F20 属性20
     */
    public String getF20() {
        return this.f20;
    }
 
    /** 
     * 获取备注说明
     *
     * @return BBB 备注说明
     */
    public String getBbb() {
        return this.bbb;
    }
 
    /** 
     * 获取备用1
     *
     * @return FB1 备用1
     */
    public String getFb1() {
        return this.fb1;
    }
 
    /** 
     * 获取备用2
     *
     * @return FB2 备用2
     */
    public String getFb2() {
        return this.fb2;
    }
 
    /** 
     * 获取备用3
     *
     * @return FB3 备用3
     */
    public String getFb3() {
        return this.fb3;
    }
 
    /** 
     * 获取备用4
     *
     * @return FB4 备用4
     */
    public String getFb4() {
        return this.fb4;
    }
 
    /** 
     * 获取备用5
     *
     * @return FB5 备用5
     */
    public String getFb5() {
        return this.fb5;
    }
 
    /** 
     * 获取扩展1
     *
     * @return EB1 扩展1
     */
    public String getEb1() {
        return this.eb1;
    }
 
    /** 
     * 获取扩展2
     *
     * @return EB2 扩展2
     */
    public String getEb2() {
        return this.eb2;
    }
 
    /** 
     * 获取扩展3
     *
     * @return EB3 扩展3
     */
    public String getEb3() {
        return this.eb3;
    }
 
    /** 
     * 获取扩展4
     *
     * @return EB4 扩展4
     */
    public String getEb4() {
        return this.eb4;
    }
 
    /** 
     * 获取扩展5
     *
     * @return EB5 扩展5
     */
    public String getEb5() {
        return this.eb5;
    }
 
    /** 
     * 获取有效标识
     *
     * @return DDD 有效标识
     */
    public String getDdd() {
        return this.ddd;
    }
 
    /** 
     * 获取创建时间
     *
     * @return CC1 创建时间
     */
    public String getCc1() {
        return this.cc1;
    }
 
    /** 
     * 获取创建者
     *
     * @return CC2 创建者
     */
    public String getCc2() {
        return this.cc2;
    }
 
    /** 
     * 获取更新时间
     *
     * @return UU1 更新时间
     */
    public String getUu1() {
        return this.uu1;
    }
 
    /** 
     * 获取更新者
     *
     * @return UU2 更新者
     */
    public String getUu2() {
        return this.uu2;
    }
 
    /** 
     * 设置系统数据唯一识别ID（固定主键）
     *
     * @param PUK 系统数据唯一识别ID（固定主键）
     */
    public void setPuk(String PUK) {
        this.puk = PUK;
    }
 
    /** 
     * 设置外键1
     *
     * @param K01 外键1
     */
    public void setK01(String K01) {
        this.k01 = K01;
    }
 
    /** 
     * 设置外键2
     *
     * @param K02 外键2
     */
    public void setK02(String K02) {
        this.k02 = K02;
    }
 
    /** 
     * 设置外键3
     *
     * @param K03 外键3
     */
    public void setK03(String K03) {
        this.k03 = K03;
    }
 
    /** 
     * 设置属性1
     *
     * @param F01 属性1
     */
    public void setF01(String F01) {
        this.f01 = F01;
    }
 
    /** 
     * 设置属性2
     *
     * @param F02 属性2
     */
    public void setF02(String F02) {
        this.f02 = F02;
    }
 
    /** 
     * 设置属性3
     *
     * @param F03 属性3
     */
    public void setF03(String F03) {
        this.f03 = F03;
    }
 
    /** 
     * 设置属性4
     *
     * @param F04 属性4
     */
    public void setF04(String F04) {
        this.f04 = F04;
    }
 
    /** 
     * 设置属性5
     *
     * @param F05 属性5
     */
    public void setF05(String F05) {
        this.f05 = F05;
    }
 
    /** 
     * 设置属性6
     *
     * @param F06 属性6
     */
    public void setF06(String F06) {
        this.f06 = F06;
    }
 
    /** 
     * 设置属性7
     *
     * @param F07 属性7
     */
    public void setF07(String F07) {
        this.f07 = F07;
    }
 
    /** 
     * 设置属性8
     *
     * @param F08 属性8
     */
    public void setF08(String F08) {
        this.f08 = F08;
    }
 
    /** 
     * 设置属性9
     *
     * @param F09 属性9
     */
    public void setF09(String F09) {
        this.f09 = F09;
    }
 
    /** 
     * 设置属性10
     *
     * @param F10 属性10
     */
    public void setF10(String F10) {
        this.f10 = F10;
    }
 
    /** 
     * 设置属性11
     *
     * @param F11 属性11
     */
    public void setF11(String F11) {
        this.f11 = F11;
    }
 
    /** 
     * 设置属性12
     *
     * @param F12 属性12
     */
    public void setF12(String F12) {
        this.f12 = F12;
    }
 
    /** 
     * 设置属性13
     *
     * @param F13 属性13
     */
    public void setF13(String F13) {
        this.f13 = F13;
    }
 
    /** 
     * 设置属性14
     *
     * @param F14 属性14
     */
    public void setF14(String F14) {
        this.f14 = F14;
    }
 
    /** 
     * 设置属性15
     *
     * @param F15 属性15
     */
    public void setF15(String F15) {
        this.f15 = F15;
    }
 
    /** 
     * 设置属性16
     *
     * @param F16 属性16
     */
    public void setF16(String F16) {
        this.f16 = F16;
    }
 
    /** 
     * 设置属性17
     *
     * @param F17 属性17
     */
    public void setF17(String F17) {
        this.f17 = F17;
    }
 
    /** 
     * 设置属性18
     *
     * @param F18 属性18
     */
    public void setF18(String F18) {
        this.f18 = F18;
    }
 
    /** 
     * 设置属性19
     *
     * @param F19 属性19
     */
    public void setF19(String F19) {
        this.f19 = F19;
    }
 
    /** 
     * 设置属性20
     *
     * @param F20 属性20
     */
    public void setF20(String F20) {
        this.f20 = F20;
    }
 
    /** 
     * 设置备注说明
     *
     * @param BBB 备注说明
     */
    public void setBbb(String BBB) {
        this.bbb = BBB;
    }
 
    /** 
     * 设置备用1
     *
     * @param FB1 备用1
     */
    public void setFb1(String FB1) {
        this.fb1 = FB1;
    }
 
    /** 
     * 设置备用2
     *
     * @param FB2 备用2
     */
    public void setFb2(String FB2) {
        this.fb2 = FB2;
    }
 
    /** 
     * 设置备用3
     *
     * @param FB3 备用3
     */
    public void setFb3(String FB3) {
        this.fb3 = FB3;
    }
 
    /** 
     * 设置备用4
     *
     * @param FB4 备用4
     */
    public void setFb4(String FB4) {
        this.fb4 = FB4;
    }
 
    /** 
     * 设置备用5
     *
     * @param FB5 备用5
     */
    public void setFb5(String FB5) {
        this.fb5 = FB5;
    }
 
    /** 
     * 设置扩展1
     *
     * @param EB1 扩展1
     */
    public void setEb1(String EB1) {
        this.eb1 = EB1;
    }
 
    /** 
     * 设置扩展2
     *
     * @param EB2 扩展2
     */
    public void setEb2(String EB2) {
        this.eb2 = EB2;
    }
 
    /** 
     * 设置扩展3
     *
     * @param EB3 扩展3
     */
    public void setEb3(String EB3) {
        this.eb3 = EB3;
    }
 
    /** 
     * 设置扩展4
     *
     * @param EB4 扩展4
     */
    public void setEb4(String EB4) {
        this.eb4 = EB4;
    }
 
    /** 
     * 设置产品ID、业务系统ID
     *
     * @param EB5 扩展5
     */
    public void setEb5(String EB5) {
        this.eb5 = EB5;
    }
 
    /** 
     * 设置有效标识
     *
     * @param DDD 有效标识
     */
    public void setDdd(String DDD) {
        this.ddd = DDD;
    }
 
    /** 
     * 设置创建时间
     *
     * @param CC1 创建时间
     */
    public void setCc1(String CC1) {
        this.cc1 = CC1;
    }
 
    /** 
     * 设置创建者
     *
     * @param CC2 创建者
     */
    public void setCc2(String CC2) {
        this.cc2 = CC2;
    }
 
    /** 
     * 设置更新时间
     *
     * @param UU1 更新时间
     */
    public void setUu1(String UU1) {
        this.uu1 = UU1;
    }
 
    /** 
     * 设置更新者
     *
     * @param UU2 更新者
     */
    public void setUu2(String UU2) {
        this.uu2 = UU2;
    }

	@Override
	public String toString() {
		return "FrameworkDataBean [puk=" + puk + ", k01=" + k01 + ", k02=" + k02 + ", k03=" + k03 + ", f01=" + f01 + ", f02=" + f02 + ", f03=" + f03 + ", f04=" + f04 + ", f05=" + f05 + ", f06=" + f06 + ", f07=" + f07 + ", f08=" + f08 + ", f09=" + f09 + ", f10=" + f10 + ", f11=" + f11 + ", f12=" + f12 + ", f13=" + f13 + ", f14=" + f14 + ", f15=" + f15 + ", f16=" + f16 + ", f17=" + f17 + ", f18=" + f18 + ", f19=" + f19 + ", f20=" + f20 + ", bbb=" + bbb + ", fb1=" + fb1 + ", fb2=" + fb2 + ", fb3=" + fb3 + ", fb4=" + fb4 + ", fb5=" + fb5 + ", eb1=" + eb1 + ", eb2=" + eb2 + ", eb3=" + eb3 + ", eb4=" + eb4 + ", eb5=" + eb5 + ", ddd=" + ddd + ", cc1=" + cc1 + ", cc2=" + cc2 + ", uu1=" + uu1 + ", uu2=" + uu2 + "]";
	}

    
}

