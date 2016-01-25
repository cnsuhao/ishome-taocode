package com.ttnn.common.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.ttnn.framework.bean.FrameworkDataBean;
//import test.ListSource;

public class ExcelUtil {
	
	/*
	 public void write(String filePath) throws Exception {  
	        List<List<String>> dateList = new ListSource().listSource();  
	        HSSFWorkbook wb = new HSSFWorkbook();  
	        HSSFSheet sheet = wb.createSheet("sheet");// 添加sheet  
	        // 表格样式  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 指定单元格居中对齐  
	        // // 边框  
	        // style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);  
	        // style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);  
	        // style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);  
	        // style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);  
	        // //设置字体  
	        // HSSFFont f = wb.createFont();  
	        // f.setFontHeightInPoints((short)10);  
	        // f.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
	        // style.setFont(f);  
	        // //设置列宽  
	        // sheet.setColumnWidth((short)0, (short)9600);  
	        // sheet.setColumnWidth((short)1, (short)4000);  
	        // sheet.setColumnWidth((short)2, (short)8000);  
	        // sheet.setColumnWidth((short)3, (short)8000);  
	  
	        // 在索引0的位置创建第一行  
	  
	        for (int i = 0; i < dateList.size(); i++) {  
	            HSSFRow row = sheet.createRow(i);  
	            List<String> list = dateList.get(i);  
	            for (int j = 0; j < list.size(); j++) {  
	                HSSFCell cell = row.createCell(j);  
	                cell.setCellValue(list.get(j));  
	                cell.setCellStyle(style);  
	            }  
	        }  
	        // 导出文件  
	        FileOutputStream fout = new FileOutputStream(filePath);  
	        wb.write(fout);  
	        fout.close();  
	    }  */
	  
	   

	
	
	public static Workbook  createWorkBook(List<FrameworkDataBean> list)throws Exception{
		 Workbook wb = new HSSFWorkbook();
		 Sheet sheet = wb.createSheet("new sheet"); 
//		 创建表头
		 HSSFRow firstrow = (HSSFRow)sheet.createRow(0);
		 firstrow.createCell(0).setCellValue("收款人姓名");
		 firstrow.createCell(1).setCellValue("收款人银行卡账号");
		 firstrow.createCell(2).setCellValue("开户行");
		 firstrow.createCell(3).setCellValue("金额");
//		 循环创建行
		 for (int i = 0; i < list.size(); i++) {  
	            HSSFRow row = (HSSFRow)sheet.createRow(i+1);   
	            FrameworkDataBean data = list.get(i);
	            HSSFCell cell = row.createCell(0);
	            cell.setCellValue(data.getF03()); 
	            row.createCell(1).setCellValue(data.getF01());
	            row.createCell(2).setCellValue(data.getF02()+data.getFb3()+data.getF04());
	            row.createCell(3).setCellValue(data.getF18());
	            
	        }  
		 
		return wb;
	}
	
	public static Workbook  createWorkBookRB(List<FrameworkDataBean> list)throws Exception{
		 Workbook wb = new HSSFWorkbook();
		 Sheet sheet = wb.createSheet("new sheet"); 
//		 创建表头
		 HSSFRow firstrow = (HSSFRow)sheet.createRow(0);
		 firstrow.createCell(0).setCellValue("编号");
		 firstrow.createCell(1).setCellValue("银行账户");
		 firstrow.createCell(2).setCellValue("开户名");
		 firstrow.createCell(3).setCellValue("开户行");
		 firstrow.createCell(4).setCellValue("分行");
		 firstrow.createCell(5).setCellValue("支行");
		 firstrow.createCell(6).setCellValue("公/私");
		 firstrow.createCell(7).setCellValue("金额");
		 
		 
		 firstrow.createCell(8).setCellValue("币种");
		 firstrow.createCell(9).setCellValue("省");
		 firstrow.createCell(10).setCellValue("市");
		 firstrow.createCell(11).setCellValue("手机号");
		 
		 firstrow.createCell(12).setCellValue("证件类型");
		 firstrow.createCell(13).setCellValue("证件号");
		 firstrow.createCell(14).setCellValue("用户协议号");
		 firstrow.createCell(15).setCellValue("商户订单号");
		 firstrow.createCell(16).setCellValue("备注");
		 
//		 循环创建行
		 for (int i = 0; i < list.size(); i++) {  
	            HSSFRow row = (HSSFRow)sheet.createRow(i+1);   
	            FrameworkDataBean data = list.get(i);
	            HSSFCell cell = row.createCell(0);
	            cell.setCellValue(i+1); 
	            row.createCell(1).setCellValue(data.getF01());
	            
	            row.createCell(2).setCellValue(data.getF03());
	            
	            
	            
	            row.createCell(3).setCellValue(data.getF02());
	            
	            row.createCell(4).setCellValue(data.getFb3());
	            
	            row.createCell(5).setCellValue(data.getF04());
	            
	            
	            row.createCell(6).setCellValue("私");
	            
	            row.createCell(7).setCellValue(data.getF18());
	            
	            row.createCell(8).setCellValue("CNY"); 
	            row.createCell(9).setCellValue(data.getF11());
	            
	            row.createCell(10).setCellValue(data.getF12());
	            
	            row.createCell(11).setCellValue(data.getF09());
	            
	            row.createCell(12).setCellValue("");
	            
	            row.createCell(13).setCellValue("");
	            
	            row.createCell(14).setCellValue("");
	            row.createCell(15).setCellValue("");
	            
	            row.createCell(15).setCellValue("往来款");
	            
	        }  
		 
		return wb;
	}
	
/*	public static void createWorkBook() throws IOException { 
        //创建excel工作簿 
        Workbook wb = new HSSFWorkbook(); 
        //创建第一个sheet（页），命名为 new sheet 
        Sheet sheet = wb.createSheet("new sheet"); 
        //Row 行 
        //Cell 方格 
        // Row 和 Cell 都是从0开始计数的 
         
        // 创建一行，在页sheet上 
        Row row = sheet.createRow((short) 0); 
        // 在row行上创建一个方格 
        Cell cell = row.createCell(0); 
        //设置方格的显示 
        cell.setCellValue(1); 
 
        // Or do it on one line. 
        row.createCell(1).setCellValue(1.2); 
        row.createCell(2).setCellValue("This is a string 速度反馈链接"); 
        row.createCell(3).setCellValue(true); 
 
        //创建一个文件 命名为workbook.xls 
        FileOutputStream fileOut = new FileOutputStream("workbook.xls"); 
        // 把上面创建的工作簿输出到文件中 
        wb.write(fileOut); 
        //关闭输出流 
        fileOut.close(); 
    } 
 
    //使用POI读入excel工作簿文件 
    public static void readWorkBook() throws Exception { 
        // poi读取excel 
        //创建要读入的文件的输入流 
        InputStream inp = new FileInputStream("workbook.xls"); 
         
        //根据上述创建的输入流 创建工作簿对象 
        Workbook wb = WorkbookFactory.create(inp); 
        //得到第一页 sheet 
        //页Sheet是从0开始索引的 
        Sheet sheet = wb.getSheetAt(0); 
        //利用foreach循环 遍历sheet中的所有行 
        for (Row row : sheet) { 
            //遍历row中的所有方格 
            for (Cell cell : row) { 
                //输出方格中的内容，以空格间隔 
                System.out.print(cell.toString() + "  "); 
            } 
            //每一个行输出之后换行 
            System.out.println(); 
        } 
        //关闭输入流 
        inp.close(); 
    } 
 
    public static void main(String[] args) throws Exception { 
        // POITest.createWorkBook(); 
//        POITest.readWorkBook(); 
    	ExcelUtil we = new ExcelUtil();
    	we.write("C:\\Users\\Administrator\\Desktop\\test.xlsx"); 
    } */
} 


