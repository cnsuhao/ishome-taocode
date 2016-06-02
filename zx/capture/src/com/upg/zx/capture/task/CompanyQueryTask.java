package com.upg.zx.capture.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
 
import com.upg.zx.capture.util.CorpSearchName;
import com.upg.zx.capture.util.HttpClientUtil;
import com.upg.zx.capture.util.PropertyHelper;
import com.upg.zx.web.utils.StringUtil;

public class CompanyQueryTask {
	
	public String corpSearchCorpWZQY[] = null;
	public static int seqCorpWZQY = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpWZQY"));
	
	final String queryUrl = "http://www.wzyrz.cn/Zheng/corp/queryCompanyByName";
	
	final String queryCorpById = "http://www.wzyrz.cn/Zheng/corp/queryDetailById";
	
	public String queryService() throws Exception{
		
		Random random = new Random();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Integer hour = calendar.get(calendar.HOUR_OF_DAY);

		if ( hour < 5 ) {
			return "";
		}
		else if ( hour < 7 ) {
			int sleepValue = 30 + random.nextInt(50); 
			Thread.sleep(sleepValue * 1000);
		}
		else if (hour < 8 ){
			int sleepValue = 5 + random.nextInt(30); 
			Thread.sleep(sleepValue * 1000);
		}
		else if (hour < 9 ){
			int sleepValue = 5 + random.nextInt(20); 
			Thread.sleep(sleepValue * 1000);
		}
		else if (hour > 18 ){
			int sleepValue = 5 + random.nextInt(35); 
			Thread.sleep(sleepValue * 1000);
		}
		else if( hour > 21 ) {
			int sleepValue = 35 + random.nextInt(50); 
			Thread.sleep(sleepValue * 1000);
		}
		else if( hour > 23 ) {
			return "";
		}
		
		String name = getCorpSearchCorp();
		String html = HttpClientUtil.getRequest(queryUrl+"?corp_name="+URLEncoder.encode(name,"utf-8"));
		if(html != null && !"".equals(html)){
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String,Object> data_map = objectMapper.readValue(html, Map.class);
			Map<String,Object> date = (Map<String,Object>) data_map.get("data");
			List<Map<String,Object>> list  = (List<Map<String,Object>>)date.get("list");
			for(Map<String,Object>map : list){
				String corpId = (String)map.get("corp_id");
				String area_code = (String)map.get("res_date");
				if("330000".equals(area_code) || "310000".equals(area_code) || "320000".equals(area_code)){
					queryCorpById(corpId);
				}
				if (random.nextInt(3) == 2) break;
			}
		}
		return html;
	}
	
	public static void main(String[] arg) throws Exception{
		CompanyQueryTask task = new CompanyQueryTask();
		task.queryService();
	}
	
	public void initial() {
		readCorpFile();
	}
	
	
	
	/**
	 * 获取详细信息
	 * @param corpId
	 * @throws IOException
	 */
	public void queryCorpById(String corpId) throws Exception{
		String html = HttpClientUtil.getRequest(queryCorpById+"?corp_id="+corpId);
	}
	
	
	private void readCorpFile(){
			try { 
	            String encoding="UTF-8"; 
	            File file=new File("CorpWZQY.txt"); 
	            if(file.isFile() && file.exists()){ //判断文件是否存在 
	                InputStreamReader read = new InputStreamReader( new FileInputStream(file),encoding);//考虑到编码格式 
	                BufferedReader bufferedReader = new BufferedReader(read); 
	                String lineTxt = null, corpName=null;
	                ArrayList arrayList = new ArrayList();
	                while((lineTxt = bufferedReader.readLine()) != null){ 
	                	try{
	                		if(lineTxt.indexOf("区")!=-1 && (lineTxt.indexOf("区") < lineTxt.length() -2) ){
		    						corpName=lineTxt.substring(lineTxt.indexOf("区")+1, lineTxt.indexOf("区")+5);
		    					}
	                		else if(lineTxt.indexOf("县")!=-1){
	    						corpName=lineTxt.substring(lineTxt.indexOf("县")+1, lineTxt.indexOf("县")+4);
	    					}else if(lineTxt.indexOf("市")!=-1 && (lineTxt.indexOf("市") < lineTxt.length() -2) ){
	    						corpName=lineTxt.substring(lineTxt.indexOf("市")+1, lineTxt.indexOf("市")+3);
	    					}
	    					else if(lineTxt.indexOf("省")!=-1 && (lineTxt.indexOf("省") < lineTxt.length() -2) ){
	    						corpName=lineTxt.substring(lineTxt.indexOf("省")+1, lineTxt.indexOf("省")+4);
	    					}
	    					else{
	    						if (lineTxt.length() < 4)
	    							corpName = lineTxt;
	    						else {
	    							corpName = lineTxt.substring(1, 4);
	    							if ("（".equals( lineTxt.substring(2, 3)) ) {
	    								corpName = lineTxt.substring(3, 5);
	    							}
	    							else if ("（".equals( lineTxt.substring(3, 4)) ) {
	    								corpName = lineTxt.substring(1, 3);
	    							}
	    						}
	    					}
	                	} catch (Exception e) { 
	            	        System.out.println("读取文件内容出错"); 
	            	        e.printStackTrace(); 
	            	    }

						arrayList.add(corpName);
	                } 

	                corpSearchCorpWZQY = new String[arrayList.size()];
	                for(int k=0; k<arrayList.size(); k++){   
	                	corpSearchCorpWZQY[k]=(String)arrayList.get(k);   
	                } 
			    }else{ 
			        System.out.println("找不到指定的文件"); 
			    } 
		    } catch (Exception e) { 
		        System.out.println(e.toString()); 
		        e.printStackTrace(); 
		    } 
        }
	
	public String getCorpSearchCorp() {
        String corpName = null;
        boolean isSearched = false;
        
        try {
			do {
				if ( seqCorpWZQY == corpSearchCorpWZQY.length) {
					System.out.println("it  has been got to the WZQY end, return null. seqCorp: " + seqCorpWZQY);
					return "";
				}
				isSearched = false;
    			System.out.println("seqCorpWZQY:   " + seqCorpWZQY);
    			PropertyHelper.writeProperties("seqCorpWZQY", String.valueOf(seqCorpWZQY+1) );

    			corpName = corpSearchCorpWZQY[seqCorpWZQY]; 
    			
    			// check whether the name has already been searched 
    			for(int i = 0; i < seqCorpWZQY; i++ ){
    				if ( corpName.equals(corpSearchCorpWZQY[i]) ) {
    					isSearched = true;
    					seqCorpWZQY += 1;
    					System.out.println(corpName + "  has already been searched, get next one.");
    					break;
    				}
    			}
	        }while(isSearched);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

        seqCorpWZQY = seqCorpWZQY +1;
		return corpName;
	}
}
