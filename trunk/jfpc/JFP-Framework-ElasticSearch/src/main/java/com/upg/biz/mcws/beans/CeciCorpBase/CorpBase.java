package com.upg.biz.mcws.beans.CeciCorpBase;

import java.util.Date;



public class CorpBase {
	
	
	/**
	 * 是否关注
	 */
	private boolean attentionFlag;
	/**
	 * 备案是否有变更(0-没有变更，1-有变更)
	 */
	private int countChange;
	
	/**
	 * 负面是否有变更(0-没有变更，1-有变更)
	 */
	private int countNegative;
	/**
	 * 诉讼是否有变更(0-没有变更，1-有变更)
	 */
	private int countLawsuit;
	
	
	/**
	 * 变动日期
	 */
	private Date t_date ;
	
	/**
	 * 变动日期
	 */
	private String t_dateString ;
	
	/**
	 * 企业编号
	 */
	private Long corp_id ;
	/**
	 * 企业编号String
	 */
	/**
	
	 * 控制人编号
	 */
	private Long controller ;
	/**
	 * 企业名称
	 */
	private String corp_name ;
	/**
	 * 企业法人
	 */
	private String artificial_person ;
	/**
	 * 营业执照号码
	 */
	private String corp_bln ;
	/**
	 * 公司类型
	 */
	private String corp_type ;
	/**
	 * 经营范围
	 */
	private String corp_bs ;
	/**
	 * 注册资本（万元）
	 */
	private Double corp_rc ;
	/**
	 * 实收资本
	 */
	private Double corp_pc ;
	/**
	 * 货币代码
	 */
	private String currency_code ;
	/**
	 * 企业成立日期
	 */
	private Date corp_edate ;
	/**
	 * 注册登记日期
	 */
	private String corp_edateString ;
	/**
	 * 注册登记日期
	 */
	
	private Date corp_rdate ;
	/**
	 * 注册登记到期日
	 */
	private String corp_rdateString ;
	/**
	 * 注册登记到期日
	 */
	private Date corp_ddate ;
	/**
	 * 登记机关
	 */
	private String corp_ddateString ;
	/**
	 * 登记机关
	 */
	private String register_org ;
	/**
	 * 核准日期
	 */
	private Date approval_date ;
	/**
	 * 核准日期
	 */
	private String approval_dateString ;
	/**
	 * 核准状态
	 */
	private String approval_status ;
	/**
	 * 行业分类
	 */
	private String industry_category ;
	/**
	 * 组织机构代码
	 */
	private String org_id ;
	/**
	 * 税务登记证号
	 */
	private String tax_id ;
	/**
	 * 工商总局企业ID
	 */
	private String company_ID ;
	/**
	 * 注册地址
	 */
	private String corp_addr ;
	/**
	 * 浙江企业id
	 */
	private String zhejiang_id ;
 

 
	/**
	 * 抓取日期
	 */
	private Date get_date ;
	
	/**
	 * 抓取日期
	 */
	private String get_dateString ;
	
	
	private String res_date;
	//注销日期
	private Date canc_date;

	/**
	 * 热度(关注总数)
	 */
	private int attentionCount;
	
	/**
	 * 赞总数
	 */
	private int agreeCount;
	
	/**
	 * 踩总数
	 */
	private int disagreeCount;
	
	/**
	 * 赞踩标志(0-无状态,1-赞,2-踩)
	 */
	private int agreeFlag;
	
	//量子ID
	private String quantumId;
	
	//是否需要抓取的区域
	private boolean captureArea;
	//工商网络是否正常
	private boolean netWork;
	public boolean isAttentionFlag() {
		return attentionFlag;
	}
	public void setAttentionFlag(boolean attentionFlag) {
		this.attentionFlag = attentionFlag;
	}
	public int getCountChange() {
		return countChange;
	}
	public void setCountChange(int countChange) {
		this.countChange = countChange;
	}
	public int getCountNegative() {
		return countNegative;
	}
	public void setCountNegative(int countNegative) {
		this.countNegative = countNegative;
	}
	public int getCountLawsuit() {
		return countLawsuit;
	}
	public void setCountLawsuit(int countLawsuit) {
		this.countLawsuit = countLawsuit;
	}
	public Date getT_date() {
		return t_date;
	}
	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}
	public String getT_dateString() {
		return t_dateString;
	}
	public void setT_dateString(String t_dateString) {
		this.t_dateString = t_dateString;
	}
	public Long getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(Long corp_id) {
		this.corp_id = corp_id;
	}
	public Long getController() {
		return controller;
	}
	public void setController(Long controller) {
		this.controller = controller;
	}
	public String getCorp_name() {
		return corp_name;
	}
	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}
	public String getArtificial_person() {
		return artificial_person;
	}
	public void setArtificial_person(String artificial_person) {
		this.artificial_person = artificial_person;
	}
	public String getCorp_bln() {
		return corp_bln;
	}
	public void setCorp_bln(String corp_bln) {
		this.corp_bln = corp_bln;
	}
	public String getCorp_type() {
		return corp_type;
	}
	public void setCorp_type(String corp_type) {
		this.corp_type = corp_type;
	}
	public String getCorp_bs() {
		return corp_bs;
	}
	public void setCorp_bs(String corp_bs) {
		this.corp_bs = corp_bs;
	}
	public Double getCorp_rc() {
		return corp_rc;
	}
	public void setCorp_rc(Double corp_rc) {
		this.corp_rc = corp_rc;
	}
	public Double getCorp_pc() {
		return corp_pc;
	}
	public void setCorp_pc(Double corp_pc) {
		this.corp_pc = corp_pc;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public Date getCorp_edate() {
		return corp_edate;
	}
	public void setCorp_edate(Date corp_edate) {
		this.corp_edate = corp_edate;
	}
	public String getCorp_edateString() {
		return corp_edateString;
	}
	public void setCorp_edateString(String corp_edateString) {
		this.corp_edateString = corp_edateString;
	}
	public Date getCorp_rdate() {
		return corp_rdate;
	}
	public void setCorp_rdate(Date corp_rdate) {
		this.corp_rdate = corp_rdate;
	}
	public String getCorp_rdateString() {
		return corp_rdateString;
	}
	public void setCorp_rdateString(String corp_rdateString) {
		this.corp_rdateString = corp_rdateString;
	}
	public Date getCorp_ddate() {
		return corp_ddate;
	}
	public void setCorp_ddate(Date corp_ddate) {
		this.corp_ddate = corp_ddate;
	}
	public String getCorp_ddateString() {
		return corp_ddateString;
	}
	public void setCorp_ddateString(String corp_ddateString) {
		this.corp_ddateString = corp_ddateString;
	}
	public String getRegister_org() {
		return register_org;
	}
	public void setRegister_org(String register_org) {
		this.register_org = register_org;
	}
	public Date getApproval_date() {
		return approval_date;
	}
	public void setApproval_date(Date approval_date) {
		this.approval_date = approval_date;
	}
	public String getApproval_dateString() {
		return approval_dateString;
	}
	public void setApproval_dateString(String approval_dateString) {
		this.approval_dateString = approval_dateString;
	}
	public String getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}
	public String getIndustry_category() {
		return industry_category;
	}
	public void setIndustry_category(String industry_category) {
		this.industry_category = industry_category;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getTax_id() {
		return tax_id;
	}
	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}
	public String getCompany_ID() {
		return company_ID;
	}
	public void setCompany_ID(String company_ID) {
		this.company_ID = company_ID;
	}
	public String getCorp_addr() {
		return corp_addr;
	}
	public void setCorp_addr(String corp_addr) {
		this.corp_addr = corp_addr;
	}
	public String getZhejiang_id() {
		return zhejiang_id;
	}
	public void setZhejiang_id(String zhejiang_id) {
		this.zhejiang_id = zhejiang_id;
	}
	public Date getGet_date() {
		return get_date;
	}
	public void setGet_date(Date get_date) {
		this.get_date = get_date;
	}
	public String getGet_dateString() {
		return get_dateString;
	}
	public void setGet_dateString(String get_dateString) {
		this.get_dateString = get_dateString;
	}
	public String getRes_date() {
		return res_date;
	}
	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}
	public Date getCanc_date() {
		return canc_date;
	}
	public void setCanc_date(Date canc_date) {
		this.canc_date = canc_date;
	}
	public int getAttentionCount() {
		return attentionCount;
	}
	public void setAttentionCount(int attentionCount) {
		this.attentionCount = attentionCount;
	}
	public int getAgreeCount() {
		return agreeCount;
	}
	public void setAgreeCount(int agreeCount) {
		this.agreeCount = agreeCount;
	}
	public int getDisagreeCount() {
		return disagreeCount;
	}
	public void setDisagreeCount(int disagreeCount) {
		this.disagreeCount = disagreeCount;
	}
	public int getAgreeFlag() {
		return agreeFlag;
	}
	public void setAgreeFlag(int agreeFlag) {
		this.agreeFlag = agreeFlag;
	}
	public String getQuantumId() {
		return quantumId;
	}
	public void setQuantumId(String quantumId) {
		this.quantumId = quantumId;
	}
	public boolean isCaptureArea() {
		return captureArea;
	}
	public void setCaptureArea(boolean captureArea) {
		this.captureArea = captureArea;
	}
	public boolean isNetWork() {
		return netWork;
	}
	public void setNetWork(boolean netWork) {
		this.netWork = netWork;
	}
	
}
