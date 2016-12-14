package com.ttnn.framework.ept.util;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttnn.framework.ept.SEPTConstants;
import com.ttnn.framework.ept.bean.config.ConfigBean;
import com.ttnn.framework.ept.bean.config.ContainerBean;
import com.ttnn.framework.ept.bean.config.ConvertBean;
import com.ttnn.framework.ept.bean.config.ValidateBean;
import com.ttnn.framework.ept.bean.template.BookBean;
import com.ttnn.framework.ept.bean.template.CellBean;
import com.ttnn.framework.ept.bean.template.OnceCellBean;
import com.ttnn.framework.ept.bean.template.PojoBean;
import com.ttnn.framework.ept.bean.template.RepeatCellBean;
import com.ttnn.framework.ept.bean.template.SheetBean;

/**
 * Excel 读取转换工具模版配置
 * 
 * @see <ept-template.xml>
 * @since 0.1
 * @version 0.1
 */
@SuppressWarnings("unchecked")
public class Template implements SEPTConstants {
	// public final static String TEMPLATE = "/*";

	private Logger logger_ = LoggerFactory.getLogger(Template.class);

	private File templateFile_ = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Template t = new Template(ConfigBean.class, ("template.ept"));
		try {
			t.init();
			System.out.print(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造函数定义
	 * 
	 * @param clazz
	 *            模版文件存在路径下的程序文件
	 * @param templateFileNme
	 *            模版文件名称
	 * @deprecated
	 */

	public Template(Class<?> clazz, String templateFileNme) {
		try {
			templateFile_ = new File(clazz.getResource(templateFileNme).toURI());
		} catch (Exception e) {
			logger_.debug("模版文件读取初期化...失败,{}", "" + clazz.getResource("") + templateFileNme);
			templateFile_ = new File(templateFileNme);
		}
	}
//	//TODO
//	public Template(String templateFileNme) {
//		try {
//			templateFile_ = new File(clazz.getResource(templateFileNme).toURI());
//		} catch (Exception e) {
//			logger_.debug("模版文件读取初期化...失败,{}", "" + clazz.getResource("") + templateFileNme);
//			templateFile_ = new File(templateFileNme);
//		}
//	}
	public Template(File template) {
		templateFile_ = template;
	}

	/**
	 * 配置文件（EPT-template.xml）初期化读取
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean init() throws Exception {
		logger_.info("模版文件读取初期化...{}", templateFile_.toString());
		EPTBookBeans_ = new HashMap<String, BookBean>();
		EPTPojoBeans_ = new HashMap<String, PojoBean>();
		EPTSheetBeans_ = new HashMap<String, SheetBean>();
		EPTOnceCellBeans_ = new HashMap<String, ArrayList<OnceCellBean>>();
		EPTRepeatCellBeans_ = new HashMap<String, ArrayList<RepeatCellBean>>();
		XMLInputFactory factory = XMLInputFactory.newInstance();
		Reader fileReader = new FileReader(templateFile_);
		XMLEventReader reader = factory.createXMLEventReader(fileReader);
		logger_.debug("模版文件读取初期化...成功");
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				StartElement element = (StartElement) event;
				logger_.debug("Start Element: " + element.getName());
				if (TEMPLATE_BOOK.equals(element.getName().toString())) {
					BookBean bb = loadBook(element);
					EPTBookBeans_.put(bb.getName(), bb);
				}
				if (TEMPLATE_SHEET.equals(element.getName().toString())) {
					SheetBean sb = loadSheet(element);
					EPTSheetBeans_.put(sb.getName(), sb);
				}
				if (TEMPLATE_POJO.equals(element.getName().toString())) {
					PojoBean pb = loadPojo(reader, element);
					EPTPojoBeans_.put(pb.getName(), pb);
				}
				if (TEMPLATE_ONCECELL.equals(element.getName().toString())) {
					OnceCellBean ocb = loadOnceCell(reader, element);
					ArrayList<OnceCellBean> cbList = EPTOnceCellBeans_.get(ocb.getSheetName());
					if (cbList == null)
						cbList = new ArrayList<OnceCellBean>();
					cbList.add(ocb);
					EPTOnceCellBeans_.put(ocb.getSheetName(), cbList);
				}
				if (TEMPLATE_REPEATCELL.equals(element.getName().toString())) {
					RepeatCellBean ocb = loadRepeatCell(reader, element);
					ArrayList<RepeatCellBean> cbList = EPTRepeatCellBeans_.get(ocb.getSheetName());
					if (cbList == null)
						cbList = new ArrayList<RepeatCellBean>();
					cbList.add(ocb);
					EPTRepeatCellBeans_.put(ocb.getSheetName(), cbList);
				}
			}
			if (event.isEndElement()) {
				// EndElement element = (EndElement) event;
				// System.out.println("End element:" + element.getName());
			}
			// if (event.isCharacters()) {
			// Characters characters = (Characters) event;
			// System.out.println("Text: " + characters.getData());
			// }
		}
		if (checkCellTemplate() == false) 
			logger_.debug("模版文件CELL定义为空，模版停止使用,{}", templateFile_.toString());
		else
			logger_.info("模版文件读取...完成");
		return true;
	}

	private BookBean loadBook(StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		BookBean bb = new BookBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			saveFieldValue(bb, name, value);
		}
		logger_.debug("模版文件读取...BOOK定义成功");
		return bb;
	}

	private SheetBean loadSheet(StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		SheetBean sb = new SheetBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			saveFieldValue(sb, name, value);
		}
		logger_.debug("模版文件读取...SHEET定义成功,{}", sb.toString());
		return sb;
	}

	private PojoBean loadPojo(XMLEventReader reader, StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		PojoBean pb = new PojoBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			saveFieldValue(pb, name, value);
		}
		// 处理Cell定义
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				element = (StartElement) event;
				if (CONFIG_CONTAINER.equals(element.getName().toString())) {
					pb.getContainerBeanList().add(loadContainer(element));
				}
				if (CONFIG_CONVERT.equals(element.getName().toString())) {
					pb.getConvertBeanList().add(loadConvert(element));
				}
			}
			if (event.isEndElement()) {
				break;
			}
		}
		logger_.debug("模版文件读取...POJO定义成功,{}", pb.toString());
		return pb;
	}

	private ContainerBean loadContainer(StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		ContainerBean cb = new ContainerBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			Template.saveFieldValue(cb, name, value);
		}
		logger_.debug("配置文件读取...CONTAINER定义读取成功,{}", cb.toString());
		return cb;
	}

	private ConvertBean loadConvert(StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		ConvertBean cb = new ConvertBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			Template.saveFieldValue(cb, name, value);
		}
		logger_.debug("配置文件读取...CONVERT定义读取成功,{}", cb.toString());
		return cb;
	}

	private OnceCellBean loadOnceCell(XMLEventReader reader, StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		OnceCellBean ocb = new OnceCellBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			saveFieldValue(ocb, name, value);
		}
		// 处理Cell定义
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				element = (StartElement) event;
				if (TEMPLATE_CELL.equals(element.getName().toString())) {
					ocb.getCellBeanList().add(loadCell(reader, element));
				}
			}
			if (event.isEndElement()) {
				break;
			}
		}
		logger_.debug("模版文件读取...一次性CELL定义成功,{}", ocb.toString());
		return ocb;
	}

	private RepeatCellBean loadRepeatCell(XMLEventReader reader, StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		RepeatCellBean rcb = new RepeatCellBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			saveFieldValue(rcb, name, value);
		}
		// 处理Cell定义
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				element = (StartElement) event;
				if (TEMPLATE_CELL.equals(element.getName().toString())) {
					rcb.getCellBeanList().add(loadCell(reader, element));
				}
			}
			if (event.isEndElement()) {
				break;
			}
		}
		logger_.debug("模版文件读取...重复性CELL定义成功,{}", rcb.toString());
		return rcb;
	}

	private CellBean loadCell(XMLEventReader reader, StartElement element) throws Exception {
		checkCellTemplate = true;
		Attribute attribute;
		QName name;
		String value;
		CellBean cb = new CellBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			saveFieldValue(cb, name, value);
		}
		// 处理Cell定义
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				element = (StartElement) event;
				if (CONFIG_VALIDATE_CELL.equals(element.getName().toString())) {
					cb.getValidateBeanList().add(loadValidate(element));
				}
			}
			if (event.isEndElement()) {
				// if(ValidateBean.VALIDATE_CELL.equals(((EndElement)
				// event).getName()))
				// break;
				if (TEMPLATE_CELL.equals(((EndElement) event).getName().toString())) {
					break;
				}
			}
		}
		logger_.debug("模版文件读取...CELL定义成功,{}", cb.toString());
		return cb;
	}

	private ValidateBean loadValidate(StartElement element) throws Exception {
		Attribute attribute;
		QName name;
		String value;
		ValidateBean vb = new ValidateBean();
		for (Iterator<Attribute> iterator = element.getAttributes(); iterator.hasNext();) {
			attribute = iterator.next();
			name = attribute.getName();
			value = attribute.getValue();
			saveFieldValue(vb, name, value);
		}
		logger_.debug("模版文件读取...VALIDATE定义成功,{}", vb.toString());
		return vb;
	}

	public static void saveFieldValue(Object target, QName fieldName, Object value) throws Exception {
		Field field = target.getClass().getDeclaredField(fieldName.toString());
		field.setAccessible(true);
		Class<?> fieldType = field.getType();
		if (fieldType.equals(int.class)) {
			field.setInt(target, Integer.parseInt(value.toString()));
		} else if (fieldType.equals(float.class)) {
			field.setFloat(target, Float.parseFloat(value.toString()));
		} else if (fieldType.equals(boolean.class)) {
			field.setBoolean(target, Boolean.parseBoolean(value.toString()));
		} else {
			field.set(target, value);
		}
	}

	private HashMap<String, BookBean> EPTBookBeans_;
	private HashMap<String, SheetBean> EPTSheetBeans_;
	private HashMap<String, PojoBean> EPTPojoBeans_;
	// <SHEET名称,<CELL定义>>
	private HashMap<String, ArrayList<OnceCellBean>> EPTOnceCellBeans_;
	// <SHEET名称,<CELL定义>>
	private HashMap<String, ArrayList<RepeatCellBean>> EPTRepeatCellBeans_;

	public String toString() {
		StringBuilder sb = new StringBuilder(2000);
		sb.append("BookBeans===>").append(EPTBookBeans_).append("\nSheetBeans===>").append(EPTSheetBeans_).append("\nPojoBeans===>").append(EPTPojoBeans_).append("\nOnceCellBeans===>").append(EPTOnceCellBeans_).append("\nRepeatCellBeans===>").append(EPTRepeatCellBeans_);
		return sb.toString();
	}

	private boolean checkCellTemplate = false;

	/**
	 * 模版数据校验
	 * 
	 * @return
	 */
	public boolean checkBookTemplate(String bookName) {
		if (EPTBookBeans_.containsKey(bookName) == false)
			return false;
		String bookTemplate = EPTBookBeans_.get(bookName).getTemplate();
		if (null == bookTemplate)
			return false;
		if ("".equals(bookTemplate.trim()))
			return false;
		return true;
	}

	/**
	 * 模版数据校验
	 * 
	 * @return
	 */
	public boolean checkCellTemplate() {
		return checkCellTemplate;
	}

	public HashMap<String, PojoBean> getPojoBeans() {
		return EPTPojoBeans_;
	}

	public HashMap<String, BookBean> getBookBeans() {
		return EPTBookBeans_;
	}

	public HashMap<String, SheetBean> getSheetBeans() {
		return EPTSheetBeans_;
	}

	public HashMap<String, ArrayList<OnceCellBean>> getOnceCellBeans() {
		return EPTOnceCellBeans_;
	}

	public HashMap<String, ArrayList<RepeatCellBean>> getRepeatCellBeans() {
		return EPTRepeatCellBeans_;
	}
}
