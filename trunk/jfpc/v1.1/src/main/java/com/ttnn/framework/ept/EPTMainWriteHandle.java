package com.ttnn.framework.ept;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttnn.framework.ept.bean.EPTDataBean;
import com.ttnn.framework.ept.bean.template.BookBean;
import com.ttnn.framework.ept.bean.template.CellBean;
import com.ttnn.framework.ept.bean.template.OnceCellBean;
import com.ttnn.framework.ept.bean.template.PojoBean;
import com.ttnn.framework.ept.bean.template.RepeatCellBean;
import com.ttnn.framework.ept.bean.template.SheetBean;
import com.ttnn.framework.ept.container.DefaultDataContainer;
import com.ttnn.framework.ept.util.Message;
import com.ttnn.framework.ept.util.Template;
import com.ttnn.framework.support.impl.MyBusinessSupportImpl;

/**
 * Excel 读取操作，数据保存到缓存
 * 
 * @author Fcy
 * @see <ept-config.xml>
 * @since 1.2.3
 */
public class EPTMainWriteHandle extends MyBusinessSupportImpl implements SEPTConstants {

	private Logger logger_ = LoggerFactory.getLogger(EPTMainReadHandle.class);

	private String ticketId_;
	/**
	 * 系统配置参数定义
	 */
	private static DefaultDataContainer Config_ = DefaultDataContainer.getInstance();
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
	 * 文件对应的模版名称
	 */
	private String templateFileNme_;
	/**
	 * 调用模版类名称
	 */
	private Class<?> Class_;

	/**
	 * 构造函数
	 * 
	 * @param templateFileNme
	 */
	public EPTMainWriteHandle(String ticketId, String targetFileUrl, Class<?> clazz,String templateFileNme, Message message) {
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
	public EPTMainWriteHandle(String ticketId, String targetFileUrl,String templateFileNme, Message message) {
		ticketId_ = ticketId;
		targetFileUrl_ = targetFileUrl;
		templateFileNme_ = templateFileNme;
		Message_ = message;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Message em = new Message("111", new TicketService());
			EPTMainReadHandle emrh = new EPTMainReadHandle("111", "EPT-read.xls", "ept-template.xml", em);
			emrh.init();
			Entry<String, Object> sheetEntry = null;
			HashMap<String, Object> dataContainer = Config_.getWriteDataContainer("111");
			for (Iterator<Entry<String, Object>> iter = dataContainer.entrySet().iterator(); iter.hasNext();) {
				sheetEntry = iter.next();
				dataContainer.put(sheetEntry.getKey(), ((EPTDataBean) sheetEntry.getValue()).getOperationData());
			}
			EPTMainWriteHandle emwh = new EPTMainWriteHandle("111", "EPT-read1.xls", "ept-template.xml", em);
			Config_.addWriteDataContainer("111", dataContainer);
			emwh.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 初期化准备
	 */
	public void init() {

		// 获得配置文件
		Template_ = Config_.getTemplate(templateFileNme_);
		if (Template_ == null || Template_.checkCellTemplate() == false) {
			// ////////////////////////////////////////////
			logger_.info("模版文件定义为空，程序已终止", templateFileNme_);
			Message_.updateTicketErrorMsg("模版文件定义为空，程序已终止，详情参见下方错误信息列表", templateFileNme_);
			return;
		}

		try {

			FileOutputStream fileOutputStream = new FileOutputStream(targetFileUrl_);
			logger_.debug("正在打开文件:", targetFileUrl_);

			// fileInputStream = new FileInputStream(xlsFile);
			// 开始解析EXCEL
			doProcess(fileOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
			logger_.info("上传文件出错，程序已终止", e.getMessage());
			Message_.updateTicketErrorMsg("上传文件出错，程序已终止，详情参见下方错误信息列表", e.getMessage());
		}
	}

	/**
	 * 数据库读取操作
	 */
	@SuppressWarnings("unchecked")
	public boolean readFromDataBase() throws Exception {
		// <SqlMapID,<数据参数（条件||结果）>>
		Map.Entry<String, PojoBean> entry;
		List queryList;
		String namedSqlId = "";
		int count = 0;
		HashMap<String, Object> dataContainer = new HashMap<String, Object>();

		// 获得全部操作数据
		try {
			// 数据从数据库取出
			for (Iterator<Entry<String, PojoBean>> iter = Template_.getPojoBeans().entrySet().iterator(); iter.hasNext();) {
				entry = iter.next();
				namedSqlId = entry.getKey();
				queryList = mySqlSession.selectList(namedSqlId, entry.getValue());
				if (queryList != null && queryList.size() > 0) {
					dataContainer.put(namedSqlId, queryList);
					count = count + queryList.size();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger_.error("SQL操作错误(" + namedSqlId + ")，程序已终止", e);
			Message_.updateTicketErrorMsg("上传文件出错，程序已终止，详情参见下方错误信息列表", e.getMessage());
			return false;
		}
		// 修正缓存数据
		DefaultDataContainer dc = DefaultDataContainer.getInstance();
		dc.addWriteDataContainer(ticketId_, dataContainer);
		return true;
	}

	/**
	 * 开始输出过程
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void doProcess(FileOutputStream fileOutputStream) throws Exception {
		// <SqlMapID,List<数据对象（结果）>>
		HashMap<String, Object> dataContainer = DefaultDataContainer.getInstance().getWriteDataContainer(ticketId_);
		HSSFWorkbook wb = null;
		BookBean bookBean = null;
		// //////////////////（1）创建输出数据文件//////////////////////////
		bookBean = Template_.getBookBeans().get("book");
		// 外部模版校验
		boolean template = Template_.checkBookTemplate(bookBean.getName());
		if (template) {
			// 初始化工作簿
			// //////////////////////////////////////////////
			// 目前不支持多个文件输出，需要时候，开启此处循环处理
			// 参考Template_.getBookBeans()
			// //////////////////////////////////////////////
			wb = new HSSFWorkbook(new FileInputStream(bookBean.getTemplate()));
		} else {
			// 初始化工作簿
			wb = new HSSFWorkbook();
		}

		Long countNum = 0L;
		countNum = processOnceData(dataContainer, template, wb);
		countNum = countNum + processRepeatData(dataContainer, template, wb);

		// //////////////////（2）生成输出数据文件//////////////////////////
		// 判断是否产生提示信息
		// if (Message_.isHaveMessage() == false) {
		// } else {
		// 询问用户是否处理
		wb.write(fileOutputStream);
		// }
	}

	private Long processOnceData(HashMap<String, Object> dataContainer, boolean template, HSSFWorkbook wb) {
		// 按照Sheet定义处理数据 <SHEET名称,<CELL定义>>
		Entry<String, ArrayList<OnceCellBean>> sheetEntry = null;
		Row row = null;
		Cell cell = null;
		HSSFSheet sheet = null;
		Object cellValue = null;
		SheetBean sheetBean = null;
		PojoBean pojoBean = null;
		ArrayList<CellBean> cellBeans;
		List<Object> queryList;
		Long countNum = 0L;
		// 按照Sheet定义读取数据<SHEET名称,<CELL定义>>
		for (Iterator<Entry<String, ArrayList<OnceCellBean>>> iter = Template_.getOnceCellBeans().entrySet().iterator(); iter.hasNext();) {
			sheetEntry = iter.next();
			logger_.debug("输出工作簿==>" + sheetEntry.getKey());
			// 获得当前操作的SHEET定义
			sheetBean = Template_.getSheetBeans().get(sheetEntry.getKey());
			// 操作Sheet页码
			// 提供了外部输出模版
			// 初始化工作表
			if (sheet == null&&template) {
				sheet = wb.getSheetAt(sheetBean.getNumIndex());
			} else {
				sheet = wb.createSheet();
				wb.setSheetName(sheetBean.getNumIndex(), sheetBean.getTitle());
			}
			// ////////////// 写入循环数据////////////////////////
			// 按照行定义读取数据
			for (OnceCellBean onceCellBean : sheetEntry.getValue()) {
				// 获得处理对象
				cellBeans = onceCellBean.getCellBeanList();
				if (cellBeans == null || cellBeans.size() == 0)
					continue;
				// 获得对应pojoBean
				pojoBean = Template_.getPojoBeans().get(onceCellBean.getPojoName());
				queryList = (List<Object>) dataContainer.get(pojoBean.getSqlMap());
				if (queryList == null || queryList.size() == 0)
					continue;
				for (CellBean cellBean : cellBeans) {
					System.out.println("cellBean================" + cellBean);
					// 提供了外部输出模版
					if (template) {
						// 获取操作单元格
						row = sheet.getRow(cellBean.getRowNumber());
						cell = row.getCell(cellBean.getColumnNumber());
					} else {
						// 获取操作单元格
						row = sheet.createRow(cellBean.getRowNumber());
						cell = row.createCell(cellBean.getColumnNumber());
					}
					// 要保存的数据
					Object operateObj = queryList.get(0);
					// Field field = operateObj.getClass().getDeclaredField(
					// cellBean.getDaoName());
					// cellValue = field.get(operateObj);
					cellValue = ((HashMap) operateObj).get(cellBean.getDaoName());
					if (cellValue != null) {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(cellValue.toString());
					}
				}
				// 计算总数据条数
				countNum = countNum + 1;
				logger_.debug("写出一次性数据==>" + countNum + "条");
			}
			// 循环变量初期化
			sheetEntry = null;
			row = null;
			cell = null;
			sheet = null;
			cellValue = null;
			pojoBean = null;
			cellBeans = null;
		}
		return countNum;
	}

	private Long processRepeatData(HashMap<String, Object> dataContainer, boolean template, HSSFWorkbook wb) {
		// 按照Sheet定义处理数据 <SHEET名称,<行号,<CELL定义>>>
		Entry<String, ArrayList<RepeatCellBean>> sheetEntry = null;
		Row row = null;
		Cell cell = null;
		Row rowT = null;
		Cell cellT = null;
		HSSFSheet sheet = null;
		Object cellValue = null;
		CellBean cellBean = null;
		SheetBean sheetBean = null;
		PojoBean pojoBean = null;
		ArrayList<CellBean> cellBeans;
		List<Object> queryList;
		Long countNum = 0L;
		int curLine = -1;
		int startLine = -1;
		// 按照Sheet定义读取数据<SHEET名称,<CELL定义>>
		logger_.debug("处理循环数据读取......");
		for (Iterator<Entry<String, ArrayList<RepeatCellBean>>> iter = Template_.getRepeatCellBeans().entrySet().iterator(); iter.hasNext();) {
			sheetEntry = iter.next();
			logger_.debug("输出工作簿==>" + sheetEntry.getKey());
			// 获得当前操作的SHEET定义
			sheetBean = Template_.getSheetBeans().get(sheetEntry.getKey());
			// 操作Sheet页码
			// 提供了外部输出模版
			// 初始化工作表
			if (sheet == null&&template) {
				sheet = wb.getSheetAt(sheetBean.getNumIndex());
			} else {
				sheet = wb.createSheet();
				wb.setSheetName(sheetBean.getNumIndex(), sheetBean.getTitle());
			}
			// ////////////// 读取数据////////////////////////
			// 按照行定义读取数据
			for (RepeatCellBean repeatCellBean : sheetEntry.getValue()) {
				cellBeans = repeatCellBean.getCellBeanList();
				if (cellBeans == null || cellBeans.size() == 0)
					continue;
				// 获得对应pojoBean
				pojoBean = Template_.getPojoBeans().get(repeatCellBean.getPojoName());
				startLine = curLine = repeatCellBean.getStartRowNumber();
				queryList = (List<Object>) dataContainer.get(pojoBean.getSqlMap());
				if (queryList == null || queryList.size() == 0)
					continue;
				for (int q = 0; q < queryList.size(); q++) {
					// 获得模版
					if (template && rowT == null) {
						rowT = sheet.getRow(startLine);
					}
					// 获取操作单元格
					row = sheet.createRow(curLine);
					for (int i = 0; i < cellBeans.size(); i++) {
						cellBean = cellBeans.get(i);
						// 纵向写入数据
						cell = row.createCell(cellBean.getColumnNumber());
						if (template) {
							cellT = rowT.getCell(cellBean.getColumnNumber());
							cell.setCellType(cellT.getCellType());
							cell.setCellComment(cellT.getCellComment());
							cell.setCellStyle(cellT.getCellStyle());
							cellT = null;
						}
						// 要保存的数据
						Object operateObj = queryList.get(q);
						// Field field =
						// operateObj.getClass().getDeclaredField(
						// cellBean.getDaoName());
						// cellValue = field.get(operateObj);
						cellValue = ((HashMap) operateObj).get(cellBean.getDaoName());
						if (cellValue != null) {
							cell.setCellValue(cellValue.toString());
						}
					}
					curLine = curLine + 1;
				}
				rowT = null;
				cellT = null;
				row = null;
				cell = null;
				logger_.debug("输出循环数据==>" + (curLine - startLine) + "条");

			}
			// 循环变量初期化
			sheetEntry = null;
			row = null;
			cell = null;
			sheet = null;
			cellBean = null;
			cellValue = null;
			pojoBean = null;
			cellBeans = null;
		}
		return countNum;
	}

	/**
	 * 获取单元格的值
	 */
	private void setCellValue(Cell cell, Object cellValue) {
		if (cellValue == null) {
			return;
		}
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING: // 字符串
				cell.setCellValue(cellValue.toString());
				break;
			case Cell.CELL_TYPE_BOOLEAN: // boolean型
				cell.setCellValue(Boolean.parseBoolean(cellValue.toString()));
				break;
			case Cell.CELL_TYPE_NUMERIC: // 数值型
				cell.setCellValue(Double.parseDouble(cellValue.toString()));
				break;
			case Cell.CELL_TYPE_ERROR:
				cell.setCellErrorValue(Byte.parseByte(cellValue.toString()));
				break;
			case Cell.CELL_TYPE_FORMULA:
				cell.setCellFormula(parseFormula(cellValue.toString()));
				break;
			}
		}
	}

	private String parseFormula(String pPOIFormula) {
		final String cstReplaceString = "ATTR(semiVolatile)"; //$NON-NLS-1$  
		StringBuffer result = null;
		int index;

		result = new StringBuffer();
		index = pPOIFormula.indexOf(cstReplaceString);
		if (index >= 0) {
			result.append(pPOIFormula.substring(0, index));
			result.append(pPOIFormula.substring(index + cstReplaceString.length()));
		} else {
			result.append(pPOIFormula);
		}
		return result.toString();
	}

}
