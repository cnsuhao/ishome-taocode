package com.ttnn.framework.ept;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttnn.framework.ept.bean.EPTDataBean;
import com.ttnn.framework.ept.bean.config.ContainerBean;
import com.ttnn.framework.ept.bean.config.ConvertBean;
import com.ttnn.framework.ept.bean.config.ValidateBean;
import com.ttnn.framework.ept.bean.template.CellBean;
import com.ttnn.framework.ept.bean.template.OnceCellBean;
import com.ttnn.framework.ept.bean.template.PojoBean;
import com.ttnn.framework.ept.bean.template.RepeatCellBean;
import com.ttnn.framework.ept.container.DefaultDataContainer;
import com.ttnn.framework.ept.container.IContainer;
import com.ttnn.framework.ept.convert.IConvert;
import com.ttnn.framework.ept.util.Message;
import com.ttnn.framework.ept.util.Template;
import com.ttnn.framework.ept.validate.IValidate;
import com.ttnn.framework.support.impl.MyBusinessSupportImpl;


/**
 * Excel 读取操作，数据保存到缓存
 * 
 * @author Fcy
 * @see <ept-config.xml>
 * @since 1.2.3
 */
public class EPTMainReadHandle extends MyBusinessSupportImpl implements SEPTConstants{

	private Logger logger_ = LoggerFactory.getLogger(EPTMainReadHandle.class);

	private String ticketId_;
	/**
	 * 系统配置参数定义
	 */
	private DefaultDataContainer Config_ = DefaultDataContainer.getInstance();
	/**
	 * 提示信息集合
	 */
	private Message Message_;

	/**
	 * 操作模版定义文件
	 */
	private Template Template_;
	/**
	 * 要操作的客户端文件地址
	 */
	private String targetFileUrl_;
	/**
	 * 调用模版类名称
	 */
	private Class<?> Class_;
	/**
	 * 文件对应的模版名称
	 */
	private String templateFileNme_;

	/**
	 * 构造函数
	 * 
	 * @param ticketId
	 *            操作线程session ID
	 * @param targetFileUrl
	 *            要操作的客户端文件地址
	 * @param templateFileNme
	 *            操作模版定义文件
	 * @param message
	 * @deprecated
	 */
	public EPTMainReadHandle(String ticketId, String targetFileUrl, Class<?> clazz, String templateFileNme, Message message) {
		ticketId_ = ticketId;
		targetFileUrl_ = targetFileUrl;
		Class_ = clazz;
		templateFileNme_ = templateFileNme;
		Message_ = message;
	}
	
	/**
	 * 构造函数
	 * 
	 * @param templateFileNme
	 */
	public EPTMainReadHandle(String ticketId, String targetFileUrl, String templateFileNme, Message message) {
		ticketId_ = ticketId;
		targetFileUrl_ = targetFileUrl;
		templateFileNme_ = templateFileNme;
		Message_ = message;
	}

	public static void main(String[] args) {
		Message em = new Message("111", new TicketService());
		EPTMainReadHandle emrh = new EPTMainReadHandle("111", "EPT-read.xls", "ept-template.xml", em);
		try {
			emrh.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 初期化准备
	 */
	public boolean init() throws Exception {
		// 模块初始化
		File xlsFile = new File(targetFileUrl_);
		// 校验输入文件名称正确性
		if (!(xlsFile.getName().substring(xlsFile.getName().lastIndexOf(".") + 1)).equalsIgnoreCase("xls")) {
			// {msg:'上传文件出错，程序已终止，详情参见下方错误信息列表'}
			Message_.updateTicketErrorMsg("上传文件出错，程序已终止，详情参见下方错误信息列表", "请选择excel2003格式的数据,后缀名为xls");
			return false;
		}
		if (xlsFile.exists() == false) {
			Message_.updateTicketErrorMsg("文件不存在!", targetFileUrl_);
			return false;
		}

		// 获得配置文件
		Template_ = DefaultDataContainer.getInstance().getTemplate(templateFileNme_);
		if (Template_ == null || Template_.checkCellTemplate() == false) {
			// ////////////////////////////////////////////
			logger_.info("模版文件定义为空，程序已终止", templateFileNme_);
			Message_.updateTicketErrorMsg("模版文件定义为空，程序已终止，详情参见下方错误信息列表", templateFileNme_);
			return false;
		}
		return doProcess(new FileInputStream(xlsFile));
	}

	/**
	 * 开始读取过程
	 * 
	 * @version 1.2
	 * @throws Exception
	 */
	private boolean doProcess(FileInputStream fileInputStream) throws Exception {
		Message_.updateTicketMessage("文件读取开始......", templateFileNme_);
		// 读取excel
		HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
		// <SqlMapID,<数据对象（条件||结果）>>
		HashMap<String, EPTDataBean> defaultDataContainer = new HashMap<String, EPTDataBean>();
		Long countNum = 0L;
		countNum = processOnceData(wb, defaultDataContainer);
		countNum = countNum + processRepeatData(wb, defaultDataContainer);
		Message_.updateTicketMessage("文件读取成功，共读入数据" + countNum + "条", templateFileNme_);
		// 保存全部数据到缓存，并询问用户处理错误信息
		logger_.debug("读取数据DefaultDataContainer==>" + defaultDataContainer);
		Config_.addReadDataContainer(ticketId_, defaultDataContainer);
		// 判断是否产生提示信息
		if (Message_.isHaveMessage() == false) {
			// 没有，直接保存到数据库
			return false;// writeIntoDataBase(null, ticketId_);
		} else {
			// 询问用户是否处理
			// ////////////////////////////////////////
			return false;
		}
	}

	private Long processOnceData(HSSFWorkbook wb, HashMap<String, EPTDataBean> defaultDataContainer) throws Exception {
		// 按照Sheet定义处理数据 <SHEET名称,<CELL定义>>
		Entry<String, ArrayList<OnceCellBean>> sheetEntry = null;
		// 循环变量定义
		Row row = null;
		Cell cell = null;
		HSSFSheet sheet = null;
		FormulaEvaluator evaluator = null;
		CellBean cellBean = null;
		String cellValue = null;
		StringBuilder msgSB = null;
		PojoBean pojoBean = null;
		EPTDataBean data = null;
		ArrayList<CellBean> cellBeans = null;
		HashMap<String, Object> operateObj = null;
		Long countNum = 0L;
		logger_.debug("处理一次数据读取......");
		for (Iterator<Entry<String, ArrayList<OnceCellBean>>> iter = Template_.getOnceCellBeans().entrySet().iterator(); iter.hasNext();) {
			sheetEntry = iter.next();
			logger_.debug("读取工作簿==>" + sheetEntry.getKey());
			// 操作Sheet页码
			sheet = wb.getSheetAt(Template_.getSheetBeans().get(sheetEntry.getKey()).getNumIndex());
			evaluator = wb.getCreationHelper().createFormulaEvaluator();
			// ////////////// 读取数据////////////////////////
			// 按照行定义读取数据
			for (OnceCellBean onceCellBean : sheetEntry.getValue()) {
				// 获得处理对象
				cellBeans = onceCellBean.getCellBeanList();
				if (cellBeans == null || cellBeans.size() == 0)
					continue;
				// 获得对应pojoBean
				pojoBean = Template_.getPojoBeans().get(onceCellBean.getPojoName());
				logger_.debug("pojoBean==================>" + pojoBean);
				data = defaultDataContainer.get(pojoBean.getName());
				if (data == null) {
					data = new EPTDataBean();
				}
				// 获得数据保存对象
				if (data.getOperationData().size() == 0)
					operateObj = new HashMap<String, Object>();
				else
					operateObj = data.getOperationData().removeFirst();
				// 处理一次性数据
				for (int i = 0; i < cellBeans.size(); i++) {
					// 获取操作单元格
					cellBean = cellBeans.get(i);
					row = sheet.getRow(cellBean.getRowNumber());
					cell = row.getCell(cellBean.getColumnNumber());
					if (cell == null)
						continue;
					cellValue = getCellValue(evaluator, cell);
					// 数据校验
					if (checkCellValue(defaultDataContainer, cellBean.getValidateBeanList(), cellBean.getRowNumber(), cellBean.getColumnNumber(), cellValue) == 1) {
						// LOG输出
						msgSB = new StringBuilder(100);
						// ----(引用的单元格；如：A3)
						// ----(该单元格对应的值)
						msgSB.append(cellBean.getName()).append("[").append(cellBean.getRowNumber()).append("]").append("[").append(cellBean.getColumnNumber()).append("]");
						Message_.updateTicketErrorMsg(msgSB.toString(), cellValue);
					}
					// 保存数据
					operateObj.put(cellBean.getDaoName(), cellValue);
				}
				// 保存读取的数据
				ArrayDeque<HashMap<String, Object>> operationData = data.getOperationData();
				operationData.add(operateObj);
				data.setOperationData(operationData);
				data.setOperationType(pojoBean.getOperationType());
				data.setOperationSQLMap(pojoBean.getSqlMap());

				// 保存读取到的数据到基本容器
				defaultDataContainer.put(pojoBean.getName(), data);

				logger_.debug("data==================>" + data);
				// 计算总数据条数
				countNum = countNum + 1;
				logger_.debug("读取一次性数据==>" + countNum + "条");
			}
		}
		return countNum;
	}

	private long processRepeatData(HSSFWorkbook wb, HashMap<String, EPTDataBean> defaultDataContainer) throws Exception {
		// 按照Sheet定义处理数据 <SHEET名称,<行号,<CELL定义>>>
		Entry<String, ArrayList<RepeatCellBean>> sheetEntry = null;
		// 循环变量定义
		Row row = null;
		Cell cell = null;
		HSSFSheet sheet = null;
		FormulaEvaluator evaluator = null;
		CellBean cellBean = null;
		String cellValue = null;
		StringBuilder msgSB = null;
		PojoBean pojoBean = null;
		EPTDataBean data = null;
		ArrayList<CellBean> cellBeans = null;
		HashMap<String, Object> operateObj = null;
		Long countNum = 0L;
		int curLine = -1;
		int startLine = -1;
		// 按照Sheet定义读取数据<SHEET名称,<CELL定义>>
		logger_.debug("处理循环数据读取......");
		for (Iterator<Entry<String, ArrayList<RepeatCellBean>>> iter = Template_.getRepeatCellBeans().entrySet().iterator(); iter.hasNext();) {
			sheetEntry = iter.next();
			logger_.debug("读取工作簿==>" + sheetEntry.getKey());
			// 操作Sheet页码
			sheet = wb.getSheetAt(Template_.getSheetBeans().get(sheetEntry.getKey()).getNumIndex());
			evaluator = wb.getCreationHelper().createFormulaEvaluator();
			// ////////////// 读取数据////////////////////////
			// 按照行定义读取数据
			for (RepeatCellBean repeatCellBean : sheetEntry.getValue()) {
				cellBeans = repeatCellBean.getCellBeanList();
				if (cellBeans == null || cellBeans.size() == 0)
					continue;
				// 获得对应pojoBean
				pojoBean = Template_.getPojoBeans().get(repeatCellBean.getPojoName());
				startLine = curLine = repeatCellBean.getStartRowNumber();
				// 强制读取数据
				while (true) {
					row = sheet.getRow(curLine);
					// 末尾判断
					if (row == null) {
						break;
					}
					// 定义存储
					operateObj = new HashMap<String, Object>(cellBeans.size());
					data = defaultDataContainer.get(pojoBean.getName());
					if (data == null) {
						data = new EPTDataBean();
					}
					// 按行读取数据
					for (int i = 0; i < cellBeans.size(); i++) {
						// 获取操作单元格
						cellBean = cellBeans.get(i);
						cell = row.getCell(cellBean.getColumnNumber());
						if (cell == null)
							continue;
						cellValue = getCellValue(evaluator, cell);
						if (cellValue == null) {
							startLine = startLine + 1;
							break;
						}
						// 数据校验
						if (checkCellValue(defaultDataContainer, cellBean.getValidateBeanList(), cellBean.getRowNumber(), cellBean.getColumnNumber(), cellValue) == 1) {
							// LOG输出
							msgSB = new StringBuilder(100);
							// ----(引用的单元格；如：A3)
							// ----(该单元格对应的值)
							msgSB.append(cellBean.getName()).append("[").append(cellBean.getRowNumber()).append("]").append("[").append(startLine).append("]");
							Message_.updateTicketErrorMsg(msgSB.toString(), cellValue);
						}
						// 保存读取的数据
						{
							operateObj.put(cellBean.getDaoName(), cellValue);
						}
					}
					// 保存到缓存
					if (operateObj.size() > 0) {
						ArrayDeque<HashMap<String, Object>> operationData = data.getOperationData();
						operationData.add(operateObj);
						data.setOperationData(operationData);
						data.setOperationType(pojoBean.getOperationType());
						data.setOperationSQLMap(pojoBean.getSqlMap());
						// 保存读取到的数据到基本容器
						defaultDataContainer.put(pojoBean.getName(), data);
					}
					curLine = curLine + 1;
				}
				logger_.debug("读取循环数据==>" + (curLine - startLine) + "条");

			}
			countNum = countNum + curLine - startLine;
			// 循环变量初期化
			sheetEntry = null;
			row = null;
			cell = null;
			sheet = null;
			evaluator = null;
			cellBean = null;
			cellValue = null;
			msgSB = null;
			pojoBean = null;
		}
		logger_.debug("总共读取数据==>" + countNum + "条");
		return countNum;
	}

	/**
	 * 数据库保存操作
	 */
	public boolean writeIntoDataBase(String ticketId) throws Exception {
		// 定义中间变量
		Map.Entry<String, EPTDataBean> entry;
		String pojoName;
		EPTDataBean sdb;
		PojoBean pojoBean = null;
		IConvert convert = null;
		IContainer container = null;
		int count = 0;
		// 单次处理数据数目
		int commitNumber = Config_.getConfigBean().getCommitNumber();

		// //////////////////////////////////////////////
		// 目前不支持多个文件输出，需要时候，开启此处循环处理
		// 参考Template_.getBookBeans()
		// //////////////////////////////////////////////

		// <SqlMapID,<数据对象（条件&&结果[HashMap<String, Object>]）>>
		HashMap<String, EPTDataBean> intoData = DefaultDataContainer.getInstance().getReadDataContainer(ticketId);
		// 数据保存到数据库<PojoName,EPTSDataBean>
		for (Iterator<Entry<String, EPTDataBean>> iter = intoData.entrySet().iterator(); iter.hasNext();) {
			entry = iter.next();
			pojoName = entry.getKey();
			sdb = entry.getValue();
			// 根据SQLMap保存数据
			for (HashMap<String, Object> mapObj : sdb.getOperationData()) {
				pojoBean = Template_.getPojoBeans().get(pojoName);
				try {
					// 开启数据转换
					for (ConvertBean convertBean : pojoBean.getConvertBeanList()) {
						// 判断是不是自定义的class
						if (StringUtils.isNotEmpty(convertBean.getClassName())) {
							convert = (IConvert) Class.forName(convertBean.getClassName()).newInstance();
						} else {
							// 系统定义
							convertBean = Config_.getConvertBeans().get(convertBean.getName());
							convert = (IConvert) Class.forName(convertBean.getClassName()).newInstance();
						}
						convert.doConvert(pojoBean, mapObj);
					}
					// 开启数据容器
					for (ContainerBean containerBean : pojoBean.getContainerBeanList()) {
						// 判断是不是自定义的class
						if (StringUtils.isNotEmpty(containerBean.getClassName())) {
							convert = (IConvert) Class.forName(containerBean.getClassName()).newInstance();
						} else {
							// 系统定义
							containerBean = Config_.getContainerBeans().get(containerBean.getName());
							container = (IContainer) Class.forName(containerBean.getClassName()).newInstance();
						}
						container.doContainer(pojoBean, mapObj);
					}
					// 操作数据库
					if (EPTDataBean.OPERATION_TYPE_INSERT.equals(sdb.getOperationType()))
						mySqlSession.insert(sdb.getOperationSQLMap(), mapObj);
					else if (EPTDataBean.OPERATION_TYPE_UPDATE.equals(sdb.getOperationType()))
						mySqlSession.update(sdb.getOperationSQLMap(), mapObj);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 保存读取到的单元格数据
	 * 
	 * @param DefaultDataContainer
	 *            数据缓存
	 * @param cell
	 *            单元格信息
	 * @param cellValue
	 *            单元格数据
	 * @return 0:未校验，1：成功，2：失败
	 * @throws Exception
	 */
	private int checkCellValue(HashMap<String, EPTDataBean> DefaultDataContainer, ArrayList<ValidateBean> validateBeanList, int rowNumber, int columNnumber, String cellValue) throws Exception {
		// 判断是否进行数据校验
		if (null == validateBeanList || validateBeanList.size() == 0) {
			return 0;
		}
		// 开始数据校验
		EPTDataBean svb = DefaultDataContainer.get(CONFIG_VALIDATE);
		HashMap<String, Object> validateObj;
		if (svb == null) {
			svb = new EPTDataBean();
			validateObj = new HashMap<String, Object>();
		} else if (svb.getOperationData().size() == 0) {
			validateObj = new HashMap<String, Object>();
		} else {
			validateObj = (HashMap<String, Object>) svb.getOperationData().removeFirst();// 获得当前校验器
		}
		if (validateObj == null) {
			validateObj = new HashMap<String, Object>();
		}
		// 根据定义的校验器进行校验<EPTIValidate>
		IValidate validate = null;
		ValidateBean doValidateBean = null;
		for (ValidateBean validateBean : validateBeanList) {
			// 准备校验器
			if (validate == null) {
				doValidateBean = Config_.getValidateBeans().get(validateBean.getName());
				validate = (IValidate) Class.forName(doValidateBean.getClassName()).newInstance();
			}
			// 数据校验
			if (!validate.doValidate(Message_, rowNumber, columNnumber, cellValue, null)) {
				return 2;
			}
		}// END 校验器循环
		 // 保存校验器
		svb.getOperationData().add(validateObj);
		// DefaultDataContainer.put(ValidateBean.VALIDATE, svb);
		return 1;
	}

	/**
	 * 获取单元格的值
	 */
	private String getCellValue(FormulaEvaluator evaluator, Cell cell) {
		String fieldValue = "";
		if (cell != null) {
			switch (evaluator.evaluateInCell(cell).getCellType()) {
			case Cell.CELL_TYPE_STRING: // 字符串
				fieldValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN: // boolean型
				fieldValue = cell.getBooleanCellValue() + "";
				break;
			case Cell.CELL_TYPE_NUMERIC: // 数值型
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Date date = HSSFDateUtil.getJavaDate(d);
					SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
					fieldValue = sFormat.format(date);
					break;
				} else if ((cell.getNumericCellValue()) > (int) (cell.getNumericCellValue()))
					fieldValue = Double.toString(cell.getNumericCellValue());
				else
					fieldValue = Integer.toString((int) cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_BLANK: // 空
				fieldValue = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				fieldValue = cell.getErrorCellValue() + "";
				break;
			case Cell.CELL_TYPE_FORMULA:
				fieldValue = "";
				break;
			}
		}
		return fieldValue;
	}
}
