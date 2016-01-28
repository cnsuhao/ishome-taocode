package com.upg.ceci.beans.CeciAnalysisTemplate;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** */
public class CeciAnalysisTemplateDBO extends MyDataBaseObjectSupport
{
    /** 
     * 主键ID
     */
    private String id = null;
    /** 
     * 区域码
     */
    private String areaCode = null;
 
    public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/** 
     * 基本信息key正则
     */
    private String keyReg = null;
 
    /** 
     * 基本信息值的正则
     */
    private String valReg = null;
 
    /** 
     * 数据启始行
     */
    private String dataRow = null;
 
    /** 
     * 标题行
     */
    private String titleRow = null;
 
    /** 
     * 数据列数
     */
    private String dataColCount = null;
 
    /** 
     * 标签ID
     */
    private String tabId = null;
 
    /** 
     * 标签名称
     */
    private String tag = null;
 
    /** 
     * 选择器
     */
    private String selector = null;
 
    /** 
     * 类型(1:基本信息解析,2:表格解析)
     */
    private String type = null;
 
    /** 
     * 请求信息id
     */
    private String requestInfoId = null;
 
    /** 
     * 转换bean对应的xml
     */
    private String xmlCode = null;
 
    /** 
     * 对应的实体类
     */
    private String clazz = null;
 
    /** 
     * 模版类别
     */
    private String templateType = null;
 
    /** 
     * 获取主键ID
     *
     * @return ID 主键ID
     */
    public String getId() {
        return this.id;
    }
 
    /** 
     * 获取基本信息key正则
     *
     * @return Key_reg 基本信息key正则
     */
    public String getKeyReg() {
        return this.keyReg;
    }
 
    /** 
     * 获取基本信息值的正则
     *
     * @return Val_reg 基本信息值的正则
     */
    public String getValReg() {
        return this.valReg;
    }
 
    /** 
     * 获取数据启始行
     *
     * @return Data_row 数据启始行
     */
    public String getDataRow() {
        return this.dataRow;
    }
 
    /** 
     * 获取标题行
     *
     * @return Title_row 标题行
     */
    public String getTitleRow() {
        return this.titleRow;
    }
 
    /** 
     * 获取数据列数
     *
     * @return Data_col_count 数据列数
     */
    public String getDataColCount() {
        return this.dataColCount;
    }
 
    /** 
     * 获取标签ID
     *
     * @return Tab_id 标签ID
     */
    public String getTabId() {
        return this.tabId;
    }
 
    /** 
     * 获取标签名称
     *
     * @return Tag 标签名称
     */
    public String getTag() {
        return this.tag;
    }
 
    /** 
     * 获取选择器
     *
     * @return Selector 选择器
     */
    public String getSelector() {
        return this.selector;
    }
 
    /** 
     * 获取类型(1:基本信息解析,2:表格解析)
     *
     * @return Type 类型(1:基本信息解析,2:表格解析)
     */
    public String getType() {
        return this.type;
    }
 
    /** 
     * 获取请求信息id
     *
     * @return Request_info_id 请求信息id
     */
    public String getRequestInfoId() {
        return this.requestInfoId;
    }
 
    /** 
     * 获取转换bean对应的xml
     *
     * @return Xml_code 转换bean对应的xml
     */
    public String getXmlCode() {
        return this.xmlCode;
    }
 
    /** 
     * 获取对应的实体类
     *
     * @return Clazz 对应的实体类
     */
    public String getClazz() {
        return this.clazz;
    }
 
    /** 
     * 获取模版类别
     *
     * @return Template_type 模版类别
     */
    public String getTemplateType() {
        return this.templateType;
    }
 
    /** 
     * 设置主键ID
     *
     * @param ID 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }
 
    /** 
     * 设置基本信息key正则
     *
     * @param Key_reg 基本信息key正则
     */
    public void setKeyReg(String keyreg) {
        this.keyReg = keyreg;
    }
 
    /** 
     * 设置基本信息值的正则
     *
     * @param Val_reg 基本信息值的正则
     */
    public void setValReg(String valreg) {
        this.valReg = valreg;
    }
 
    /** 
     * 设置数据启始行
     *
     * @param Data_row 数据启始行
     */
    public void setDataRow(String datarow) {
        this.dataRow = datarow;
    }
 
    /** 
     * 设置标题行
     *
     * @param Title_row 标题行
     */
    public void setTitleRow(String titlerow) {
        this.titleRow = titlerow;
    }
 
    /** 
     * 设置数据列数
     *
     * @param Data_col_count 数据列数
     */
    public void setDataColCount(String datacolcount) {
        this.dataColCount = datacolcount;
    }
 
    /** 
     * 设置标签ID
     *
     * @param Tab_id 标签ID
     */
    public void setTabId(String tabid) {
        this.tabId = tabid;
    }
 
    /** 
     * 设置标签名称
     *
     * @param Tag 标签名称
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
 
    /** 
     * 设置选择器
     *
     * @param Selector 选择器
     */
    public void setSelector(String selector) {
        this.selector = selector;
    }
 
    /** 
     * 设置类型(1:基本信息解析,2:表格解析)
     *
     * @param Type 类型(1:基本信息解析,2:表格解析)
     */
    public void setType(String type) {
        this.type = type;
    }
 
    /** 
     * 设置请求信息id
     *
     * @param Request_info_id 请求信息id
     */
    public void setRequestInfoId(String requestinfoid) {
        this.requestInfoId = requestinfoid;
    }
 
    /** 
     * 设置转换bean对应的xml
     *
     * @param Xml_code 转换bean对应的xml
     */
    public void setXmlCode(String xmlcode) {
        this.xmlCode = xmlcode;
    }
 
    /** 
     * 设置对应的实体类
     *
     * @param Clazz 对应的实体类
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
 
    /** 
     * 设置模版类别
     *
     * @param Template_type 模版类别
     */
    public void setTemplateType(String templatetype) {
        this.templateType = templatetype;
    }
 
}
