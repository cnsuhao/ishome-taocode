package com.ttnn.framework.ept;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.ttnn.common.ISSystemConstants;
import com.ttnn.framework.ept.bean.config.ConfigBean;
import com.ttnn.framework.ept.bean.config.ContainerBean;
import com.ttnn.framework.ept.bean.config.ConvertBean;
import com.ttnn.framework.ept.bean.config.MessageBean;
import com.ttnn.framework.ept.bean.config.ValidateBean;
import com.ttnn.framework.ept.container.DefaultDataContainer;
import com.ttnn.framework.ept.util.Template;

/**
 * Excel操作工具，基于POI
 * @since 0.1
 * @version 0.1 2012-7-19
 */
@SuppressWarnings("unchecked")
public class EPTFactoryBean implements SEPTConstants,ISSystemConstants{

	private DefaultDataContainer DefaultDataContainer_;
	private Logger logger_ = LoggerFactory.getLogger(EPTFactoryBean.class);

	public EPTFactoryBean(){
		DefaultDataContainer_ = DefaultDataContainer.getInstance();
	}
	
	/**
	 * 配置文件（EPT-config.xml）初期化读取
	 * 
	 * @return
	 * @throws Exception
	 */
	public void initialization() {
		try {
			logger_.info("配置文件读取初期化...{}", configLocation);
			ConfigBean _ConfigBean = new ConfigBean();
			HashMap<String, ContainerBean> _ContainerBeans = new HashMap<String, ContainerBean>();
			HashMap<String, MessageBean> _MessageBeans = new HashMap<String, MessageBean>();
			HashMap<String, ValidateBean> _ValidateBeans = new HashMap<String, ValidateBean>();
			HashMap<String, ConvertBean> _ConvertBeans = new HashMap<String, ConvertBean>();
			XMLInputFactory factory = XMLInputFactory.newInstance();
			File _config = configLocation.getFile();
			Reader fileReader = new FileReader(_config);
			XMLEventReader reader = factory.createXMLEventReader(fileReader);
			logger_.debug(""+_config);
			logger_.debug("配置文件读取初期化...成功");
			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				logger_.debug("===>>>>>>>>>>>>"+event);
				if (event.isStartElement()) {
					StartElement element = (StartElement) event;
					// System.out.println("Start Element: " +
					// element.getName());
					if (ConfigBean.CONFIGS.equals(element.getName().toString())) {
						loadBaseConfig(_ConfigBean,element);
					}
					if (ContainerBean.CONFIG_CONTAINER.equals(element.getName()
							.toString())) {
						ContainerBean cb = loadContainer(element);
						_ContainerBeans.put(cb.getName(), cb);
					}
					if (ConvertBean.CONFIG_CONVERT
							.equals(element.getName().toString())) {
						ConvertBean cb = loadConvert(element);
						_ConvertBeans.put(cb.getName(), cb);
					}
					if (MessageBean.CONFIG_MESSAGE
							.equals(element.getName().toString())) {
						MessageBean mb = loadMessage(element);
						_MessageBeans.put(mb.getName(), mb);
					}
					if (ValidateBean.CONFIG_VALIDATE.equals(element.getName()
							.toString())) {
						ValidateBean vb = loadValidate(element);
						_ValidateBeans.put(vb.getName(), vb);
					}
				}
//				if (event.isEndElement()) {
//					 EndElement element = (EndElement) event;
//					 System.out.println("End element:" + element.getName());
//				}
				// if (event.isCharacters()) {
				// Characters characters = (Characters) event;
				// System.out.println("Text: " + characters.getData());
				// }
			}
			
			//保存配置文件
			DefaultDataContainer_.setConfigBean(_ConfigBean);
			DefaultDataContainer_.setContainerBeans(_ContainerBeans);
			DefaultDataContainer_.setConvertBeans(_ConvertBeans);
			DefaultDataContainer_.setMessageBeans(_MessageBeans);
			DefaultDataContainer_.setValidateBeans(_ValidateBeans);
			
			//日志输出
			logger_.debug( _ConfigBean.toString());
			logger_.debug( _ContainerBeans.toString());
			logger_.debug(_ConvertBeans.toString());
			logger_.debug( _MessageBeans.toString());
			logger_.debug(_ValidateBeans.toString());
			
			logger_.debug("配置文件读取...完成");

			//加载模版文件
			Template t;
			for (Resource templateLocation : templateLocations) {
				t = new Template(templateLocation.getFile());
				if(t.init())
					DefaultDataContainer_.addTemplate(templateLocation.getFilename().replaceAll(FILE_EXTENSION_NAME_EPT, EMPTY), t);
			}
			
			logger_.info("模版文件读取...完成");
		} catch (Exception e) {
			logger_.debug("配置文件读取...失败！！");
			e.printStackTrace();
		}
	}

	private ConfigBean loadBaseConfig(ConfigBean configBean,StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;		
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator
				.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			Template.saveFieldValue(configBean, name, value);
		}
		logger_.debug("配置文件读取...基本定义读取成功,{}", configBean.toString());
		return configBean;
	}

	private ContainerBean loadContainer(StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		ContainerBean cb = new ContainerBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator
				.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			Template.saveFieldValue(cb, name, value);
		}
		logger_.debug("配置文件读取...containers定义读取成功,{}", cb.toString());
		return cb;
	}

	private ConvertBean loadConvert(StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		ConvertBean cb = new ConvertBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator
				.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			Template.saveFieldValue(cb, name, value);
		}
		logger_.debug("配置文件读取...converts定义读取成功,{}", cb.toString());
		return cb;
	}

	private MessageBean loadMessage(StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		MessageBean mb = new MessageBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator
				.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			Template.saveFieldValue(mb, name, value);
		}
		logger_.debug("配置文件读取...messages定义读取成功,{}", mb.toString());
		return mb;
	}

	private ValidateBean loadValidate(StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		ValidateBean vb = new ValidateBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator
				.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			Template.saveFieldValue(vb, name, value);
		}
		logger_.debug("配置文件读取...validators定义读取成功,{}", vb.toString());
		return vb;
	}
	
	/**
	 * 基本配置文件
	 */
	private Resource configLocation;
	/**
	 * 数据表文件
	 */
	private Resource[] templateLocations;
	public Resource getConfigLocation() {
    	return configLocation;
    }
	public void setConfigLocation(Resource configLocation) {
    	this.configLocation = configLocation;
    }
	public Resource[] getTemplateLocations() {
    	return templateLocations;
    }
	public void setTemplateLocations(Resource[] templateLocations) {
    	this.templateLocations = templateLocations;
    }
	
	
}
