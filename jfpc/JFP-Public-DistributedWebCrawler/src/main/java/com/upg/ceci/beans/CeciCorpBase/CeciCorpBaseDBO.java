package com.upg.ceci.beans.CeciCorpBase;
import java.util.Date;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** */
public class CeciCorpBaseDBO extends MyDataBaseObjectSupport
{
    /** 
     * 企业编号
     */
    private String corpId = null;
 
    /** 
     * 变动日期
     */
    private Date tDate = null;
 
    /** 
     * 控制人编号
     */
    private String controller = null;
 
    /** 
     * 企业名称
     */
    private String corpName = null;
 
    /** 
     * 企业法人
     */
    private String artificialPerson = null;
 
    /** 
     * 营业执照号码
     */
    private String corpBln = null;
 
    /** 
     * 公司类型
     */
    private String corpType = null;
 
    /** 
     * 经营范围
     */
    private String corpBs = null;
 
    /** 
     * 注册资本（万元）
     */
    private Double corpRc = null;
 
    /** 
     * 实收资本
     */
    private String corpPc = null;
 
    /** 
     * 货币代码
     */
    private String currencyCode = null;
 
    /** 
     * 企业成立日期
     */
    private Date corpEdate = null;
 
    /** 
     * 注册地址
     */
    private String corpAddr = null;
 
    /** 
     * 注册登记日期
     */
    private Date corpRdate = null;
 
    /** 
     * 注册登记到期日
     */
    private Date corpDdate = null;
 
    /** 
     * 登记机关
     */
    private String registerOrg = null;
 
    /** 
     * 核准日期
     */
    private Date approvalDate = null;
 
    /** 
     * 核准状态
     */
    private String approvalStatus = null;
 
    /** 
     * 行业分类
     */
    private String industryCategory = null;
 
    /** 
     * 组织机构代码
     */
    private String orgId = null;
 
    /** 
     * 税务登记证号
     */
    private String taxId = null;
 
    /** 
     * 工商总局企业ID
     */
    private String companyId = null;
 
    /** 
     * 浙江企业id
     */
    private String zhejiangId = null;
 
    /** 
     * 抓取日期
     */
    private Date getDate = null;
 
    /** 
     * 数据来源
     */
    private String resDate = null;
 
    /** 
     * 注销日期
     */
    private Date cancDate = null;
 
    /** 
     * 量子id
     */
    private String quantumId = null;
 
    /** 
     * 获取企业编号
     *
     * @return Corp_id 企业编号
     */
    public String getCorpId() {
        return this.corpId;
    }
 
    /** 
     * 获取变动日期
     *
     * @return T_date 变动日期
     */
    public Date getTDate() {
        return this.tDate;
    }
 
    /** 
     * 获取控制人编号
     *
     * @return Controller 控制人编号
     */
    public String getController() {
        return this.controller;
    }
 
    /** 
     * 获取企业名称
     *
     * @return Corp_name 企业名称
     */
    public String getCorpName() {
        return this.corpName;
    }
 
    /** 
     * 获取企业法人
     *
     * @return Artificial_person 企业法人
     */
    public String getArtificialPerson() {
        return this.artificialPerson;
    }
 
    /** 
     * 获取营业执照号码
     *
     * @return Corp_bln 营业执照号码
     */
    public String getCorpBln() {
        return this.corpBln;
    }
 
    /** 
     * 获取公司类型
     *
     * @return Corp_type 公司类型
     */
    public String getCorpType() {
        return this.corpType;
    }
 
    /** 
     * 获取经营范围
     *
     * @return Corp_bs 经营范围
     */
    public String getCorpBs() {
        return this.corpBs;
    }
 
    /** 
     * 获取注册资本（万元）
     *
     * @return Corp_rc 注册资本（万元）
     */
    public Double getCorpRc() {
        return this.corpRc;
    }
 
    /** 
     * 获取实收资本
     *
     * @return Corp_pc 实收资本
     */
    public String getCorpPc() {
        return this.corpPc;
    }
 
    /** 
     * 获取货币代码
     *
     * @return Currency_code 货币代码
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }
 
    /** 
     * 获取企业成立日期
     *
     * @return Corp_edate 企业成立日期
     */
    public Date getCorpEdate() {
        return this.corpEdate;
    }
 
    /** 
     * 获取注册地址
     *
     * @return Corp_addr 注册地址
     */
    public String getCorpAddr() {
        return this.corpAddr;
    }
 
    /** 
     * 获取注册登记日期
     *
     * @return Corp_rdate 注册登记日期
     */
    public Date getCorpRdate() {
        return this.corpRdate;
    }
 
    /** 
     * 获取注册登记到期日
     *
     * @return Corp_ddate 注册登记到期日
     */
    public Date getCorpDdate() {
        return this.corpDdate;
    }
 
    /** 
     * 获取登记机关
     *
     * @return Register_org 登记机关
     */
    public String getRegisterOrg() {
        return this.registerOrg;
    }
 
    /** 
     * 获取核准日期
     *
     * @return Approval_date 核准日期
     */
    public Date getApprovalDate() {
        return this.approvalDate;
    }
 
    /** 
     * 获取核准状态
     *
     * @return Approval_status 核准状态
     */
    public String getApprovalStatus() {
        return this.approvalStatus;
    }
 
    /** 
     * 获取行业分类
     *
     * @return Industry_category 行业分类
     */
    public String getIndustryCategory() {
        return this.industryCategory;
    }
 
    /** 
     * 获取组织机构代码
     *
     * @return Org_id 组织机构代码
     */
    public String getOrgId() {
        return this.orgId;
    }
 
    /** 
     * 获取税务登记证号
     *
     * @return Tax_id 税务登记证号
     */
    public String getTaxId() {
        return this.taxId;
    }
 
    /** 
     * 获取工商总局企业ID
     *
     * @return Company_ID 工商总局企业ID
     */
    public String getCompanyId() {
        return this.companyId;
    }
 
    /** 
     * 获取浙江企业id
     *
     * @return Zhejiang_id 浙江企业id
     */
    public String getZhejiangId() {
        return this.zhejiangId;
    }
 
    /** 
     * 获取抓取日期
     *
     * @return Get_date 抓取日期
     */
    public Date getGetDate() {
        return this.getDate;
    }
 
    /** 
     * 获取数据来源
     *
     * @return Res_date 数据来源
     */
    public String getResDate() {
        return this.resDate;
    }
 
    /** 
     * 获取注销日期
     *
     * @return Canc_date 注销日期
     */
    public Date getCancDate() {
        return this.cancDate;
    }
 
    /** 
     * 获取量子id
     *
     * @return Quantum_id 量子id
     */
    public String getQuantumId() {
        return this.quantumId;
    }
 
    /** 
     * 设置企业编号
     *
     * @param Corp_id 企业编号
     */
    public void setCorpId(String corpid) {
        this.corpId = corpid;
    }
 
    /** 
     * 设置变动日期
     *
     * @param T_date 变动日期
     */
    public void setTDate(Date tdate) {
        this.tDate = tdate;
    }
 
    /** 
     * 设置控制人编号
     *
     * @param Controller 控制人编号
     */
    public void setController(String controller) {
        this.controller = controller;
    }
 
    /** 
     * 设置企业名称
     *
     * @param Corp_name 企业名称
     */
    public void setCorpName(String corpname) {
        this.corpName = corpname;
    }
 
    /** 
     * 设置企业法人
     *
     * @param Artificial_person 企业法人
     */
    public void setArtificialPerson(String artificialperson) {
        this.artificialPerson = artificialperson;
    }
 
    /** 
     * 设置营业执照号码
     *
     * @param Corp_bln 营业执照号码
     */
    public void setCorpBln(String corpbln) {
        this.corpBln = corpbln;
    }
 
    /** 
     * 设置公司类型
     *
     * @param Corp_type 公司类型
     */
    public void setCorpType(String corptype) {
        this.corpType = corptype;
    }
 
    /** 
     * 设置经营范围
     *
     * @param Corp_bs 经营范围
     */
    public void setCorpBs(String corpbs) {
        this.corpBs = corpbs;
    }
 
    /** 
     * 设置注册资本（万元）
     *
     * @param Corp_rc 注册资本（万元）
     */
    public void setCorpRc(Double corprc) {
        this.corpRc = corprc;
    }
 
    /** 
     * 设置实收资本
     *
     * @param Corp_pc 实收资本
     */
    public void setCorpPc(String corppc) {
        this.corpPc = corppc;
    }
 
    /** 
     * 设置货币代码
     *
     * @param Currency_code 货币代码
     */
    public void setCurrencyCode(String currencycode) {
        this.currencyCode = currencycode;
    }
 
    /** 
     * 设置企业成立日期
     *
     * @param Corp_edate 企业成立日期
     */
    public void setCorpEdate(Date corpedate) {
        this.corpEdate = corpedate;
    }
 
    /** 
     * 设置注册地址
     *
     * @param Corp_addr 注册地址
     */
    public void setCorpAddr(String corpaddr) {
        this.corpAddr = corpaddr;
    }
 
    /** 
     * 设置注册登记日期
     *
     * @param Corp_rdate 注册登记日期
     */
    public void setCorpRdate(Date corprdate) {
        this.corpRdate = corprdate;
    }
 
    /** 
     * 设置注册登记到期日
     *
     * @param Corp_ddate 注册登记到期日
     */
    public void setCorpDdate(Date corpddate) {
        this.corpDdate = corpddate;
    }
 
    /** 
     * 设置登记机关
     *
     * @param Register_org 登记机关
     */
    public void setRegisterOrg(String registerorg) {
        this.registerOrg = registerorg;
    }
 
    /** 
     * 设置核准日期
     *
     * @param Approval_date 核准日期
     */
    public void setApprovalDate(Date approvaldate) {
        this.approvalDate = approvaldate;
    }
 
    /** 
     * 设置核准状态
     *
     * @param Approval_status 核准状态
     */
    public void setApprovalStatus(String approvalstatus) {
        this.approvalStatus = approvalstatus;
    }
 
    /** 
     * 设置行业分类
     *
     * @param Industry_category 行业分类
     */
    public void setIndustryCategory(String industrycategory) {
        this.industryCategory = industrycategory;
    }
 
    /** 
     * 设置组织机构代码
     *
     * @param Org_id 组织机构代码
     */
    public void setOrgId(String orgid) {
        this.orgId = orgid;
    }
 
    /** 
     * 设置税务登记证号
     *
     * @param Tax_id 税务登记证号
     */
    public void setTaxId(String taxid) {
        this.taxId = taxid;
    }
 
    /** 
     * 设置工商总局企业ID
     *
     * @param Company_ID 工商总局企业ID
     */
    public void setCompanyId(String companyid) {
        this.companyId = companyid;
    }
 
    /** 
     * 设置浙江企业id
     *
     * @param Zhejiang_id 浙江企业id
     */
    public void setZhejiangId(String zhejiangid) {
        this.zhejiangId = zhejiangid;
    }
 
    /** 
     * 设置抓取日期
     *
     * @param Get_date 抓取日期
     */
    public void setGetDate(Date getdate) {
        this.getDate = getdate;
    }
 
    /** 
     * 设置数据来源
     *
     * @param Res_date 数据来源
     */
    public void setResDate(String resdate) {
        this.resDate = resdate;
    }
 
    /** 
     * 设置注销日期
     *
     * @param Canc_date 注销日期
     */
    public void setCancDate(Date cancdate) {
        this.cancDate = cancdate;
    }
 
    /** 
     * 设置量子id
     *
     * @param Quantum_id 量子id
     */
    public void setQuantumId(String quantumid) {
        this.quantumId = quantumid;
    }
 
}
