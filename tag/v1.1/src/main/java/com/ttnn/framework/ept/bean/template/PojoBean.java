package com.ttnn.framework.ept.bean.template;

import java.util.ArrayList;


import com.ttnn.framework.ept.SEPTConstants;
import com.ttnn.framework.ept.bean.config.ContainerBean;
import com.ttnn.framework.ept.bean.config.ConvertBean;
import com.ttnn.framework.support.CSPVOSupport;


/**
 * 操作模版POJO配置
 * @see <ept-template.xml>
 * @since 1.1
 * @version 1.3 模版升级
 */
public class PojoBean extends CSPVOSupport implements SEPTConstants{
	
	/**
     * 
     */
    private static final long serialVersionUID = -4994174484296028325L;
	/**
	 * POJO的名称标识
	 */
	private String name;
	/**
	 * Bean的类路径
	 */
	private String beanCalss;
	/**
	 * Dao的类路径
	 */
	private String daoCalss;
	/**
	 * sqlMap的操作名称
	 */
	private String sqlMap;
	/**
	 * SQL操作类型
	 */
	private String operationType;
	/**
	 * 数据存储类型
	 */
	private String parameterType;

	private ArrayList<ContainerBean> containerBeanList = new ArrayList<ContainerBean>();
	private ArrayList<ConvertBean> convertBeanList = new ArrayList<ConvertBean>();

	public ArrayList<ContainerBean> getContainerBeanList() {
		return containerBeanList;
	}

	public void setContainerBeanList(ArrayList<ContainerBean> containerBeanList) {
		this.containerBeanList = containerBeanList;
	}

	public ArrayList<ConvertBean> getConvertBeanList() {
		return convertBeanList;
	}

	public void setConvertBeanList(ArrayList<ConvertBean> convertBeanList) {
		this.convertBeanList = convertBeanList;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String fieldOperationType) {
		operationType = fieldOperationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeanCalss() {
		return beanCalss;
	}

	public void setBeanCalss(String beanCalss) {
		this.beanCalss = beanCalss;
	}

	public String getDaoCalss() {
		return daoCalss;
	}

	public void setDaoCalss(String daoCalss) {
		this.daoCalss = daoCalss;
	}

	public String getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(String sqlMap) {
		this.sqlMap = sqlMap;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	@Override
	public String toString() {
		return "PojoBean [name=" + name + ", beanCalss=" + beanCalss + ", daoCalss=" + daoCalss + ", sqlMap=" + sqlMap + ", operationType=" + operationType + ", parameterType=" + parameterType + ", containerBeanList=" + containerBeanList + ", convertBeanList=" + convertBeanList + "]";
	}
}
