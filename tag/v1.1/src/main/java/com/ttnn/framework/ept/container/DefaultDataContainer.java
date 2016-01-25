package com.ttnn.framework.ept.container;

import java.util.HashMap;


import com.ttnn.framework.ept.bean.EPTDataBean;
import com.ttnn.framework.ept.bean.config.ConfigBean;
import com.ttnn.framework.ept.bean.config.ContainerBean;
import com.ttnn.framework.ept.bean.config.ConvertBean;
import com.ttnn.framework.ept.bean.config.MessageBean;
import com.ttnn.framework.ept.bean.config.ValidateBean;
import com.ttnn.framework.ept.util.Template;

/**
 * EPT数据缓存容器
 * 
 * @see <Config><Template>
 * @since 0.1
 * @version 0.1
 */
public final class DefaultDataContainer {

	public static void main(String[] args) {
		DefaultDataContainer.getInstance();
	}

	private static DefaultDataContainer instance = null;

	private DefaultDataContainer() {
	}

	public static DefaultDataContainer getInstance() {
		if (instance == null) {
			instance = new DefaultDataContainer();
		}
		return instance;
	}

	// 配置文件
	private ConfigBean ConfigBean_ = new ConfigBean();
	private HashMap<String, ConvertBean> ConvertBeans_;
	private HashMap<String, ContainerBean> ContainerBeans_;
	private HashMap<String, MessageBean> MessageBeans_;
	private HashMap<String, ValidateBean> ValidateBeans_;

	public ConfigBean getConfigBean() {
		return ConfigBean_;
	}

	public void setConfigBean(ConfigBean configBean) {
		ConfigBean_ = configBean;
	}

	public HashMap<String, ConvertBean> getConvertBeans() {
		return ConvertBeans_;
	}

	public void setConvertBeans(HashMap<String, ConvertBean> convertBeans) {
		ConvertBeans_ = convertBeans;
	}

	public HashMap<String, ContainerBean> getContainerBeans() {
		return ContainerBeans_;
	}

	public void setContainerBeans(HashMap<String, ContainerBean> containerBeans) {
		ContainerBeans_ = containerBeans;
	}

	public HashMap<String, MessageBean> getMessageBeans() {
		return MessageBeans_;
	}

	public void setMessageBeans(HashMap<String, MessageBean> messageBeans) {
		MessageBeans_ = messageBeans;
	}

	public HashMap<String, ValidateBean> getValidateBeans() {
		return ValidateBeans_;
	}

	public void setValidateBeans(HashMap<String, ValidateBean> validateBeans) {
		ValidateBeans_ = validateBeans;
	}

	/**
	 * 获得当前使用的模版
	 * 
	 * @param clazz
	 *            模版文件存在路径下的程序文件
	 * @param templateID
	 *            模版名称标识，全局唯一
	 * @see <Template>
	 * @return
	 */
	private HashMap<String, Template> Templates_ = new HashMap<String, Template>();
	public void addTemplate(String templateID,Template t) {
		Templates_.put(templateID, t);
	}
	
	/**
	 * 获得模版未定义的场合返回NULL
	 * @param templateID
	 * @see EPTFactoryBean#initialization
	 * @return
	 */
	public Template getTemplate(String templateID) {
		// 返回模版
		return Templates_.get(templateID);
	}
	/**
	 * 
	 * @param clazz
	 *            模版文件存在路径下的程序文件
	 * @param templateFileNme
	 *            模版文件名称
	 * @deprecated
	 */
	public Template getTemplate(Class<?> clazz, String templateID) {

		// 判断是否已经缓存模版
		if (!Templates_.containsKey(templateID)) {
			try {
				// 加载模版
				Template t = new Template(clazz, templateID);
				t.init();
				Templates_.put(templateID, t);
				// 返回模版
				return t;
			} catch (Exception e) {
			}
		}
		// 返回模版
		return Templates_.get(templateID);
	}

	// <SqlMapID,<数据对象（条件||结果）>>
	private HashMap<String, HashMap<String, Object>> WriteDataContainer_ = new HashMap<String, HashMap<String, Object>>();
	private HashMap<String, HashMap<String, EPTDataBean>> ReadDataContainer_ = new HashMap<String, HashMap<String, EPTDataBean>>();

	/**
	 * 
	 * @param ticketId
	 * @return HashMap<SqlMapID,<数据条件>>
	 */
	public HashMap<String, Object> getWriteDataContainer(String ticketId) {
		return WriteDataContainer_.remove(ticketId);
	}

	public void addWriteDataContainer(String ticketId, HashMap<String, Object> writeDataContainer_) {
		WriteDataContainer_.put(ticketId, writeDataContainer_);
	}

	/**
	 * 
	 * @param ticketId
	 * @return HashMap<SqlMapID,<数据对象（条件||结果）>>
	 */
	public HashMap<String, EPTDataBean> getReadDataContainer(String ticketId) {
		return ReadDataContainer_.remove(ticketId);
	}

	public void addReadDataContainer(String ticketId, HashMap<String, EPTDataBean> readDataContainer_) {
		ReadDataContainer_.put(ticketId, readDataContainer_);
	}
}
