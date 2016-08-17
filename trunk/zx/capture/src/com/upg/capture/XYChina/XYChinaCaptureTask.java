package com.upg.capture.XYChina;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.isotope.jfp.framework.net.MyHttpServiceSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.upg.zx.capture.bean.ServiceConfig;

public class XYChinaCaptureTask {
	private  ServiceConfig serviceConfig;

	public  ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public  void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}
	
	 String pathName = "/XYChina/";

	public  String getPathName() {
		return pathName;
	}

	public  void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public  static void main(String[] args) throws Exception {
		String direct = "pathName" +"/"+DateHelper.customTime("yyyyMMdd")  +"/";
		File f = new File(direct);
		System.out.println(f.getPath());
		System.out.println(f.mkdirs());
		// loadCompanyHtml("电子",true);
		// loadKeyWordFile("F:/TYC/ccccc.txt");
		// loadKeyWordService();
	}

	public  void loadKeyWordFile(String fileName) throws Exception {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while (EmptyHelper.isNotEmpty(tempString = reader.readLine())) {
				System.out.println("line " + line + "======>>>>>" + tempString);
				line++;
				if (line < 0)
					continue;
				// 1.数据解析
				{
					System.out.println("");
					loadCompanyHtml(tempString, true);
				}
				// if(line>2000)78387
				// break;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e1) {
				}
			}
		}
	}

	public  void loadKeyWordService() throws Exception {
		MyHttpServiceSupport mhss = new MyHttpServiceSupport();
		// 一次读入一行，直到读入null为文件结束
		// while (true)
		// 1.数据解析
		{
			String tempString = mhss.doHttpGET(serviceConfig.getServiceConfig("keyWordUrl"));
			JSONObject josn = JSONObject.parseObject(tempString);
			System.out.println("======>>>>>" + josn.getString("data"));
			System.out.println("");
			loadCompanyHtml(josn.getString("data"), true);
		}
	}

//	String[] areas = { "北京", "天津", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西", "山东",
//			"河南", "湖北", "湖南", "广东", "广西", "海南", "重庆", "四川", "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆" };
	
	//AH,LN,XZ,CQ,FJ,GS,GX,HB,HeB,HeN,HLJ,HN,JX,LN,NX,SC,SD,SH,SX1,SX2,TJ,XJ,YN
	String[] areas = { "北京", "山西", "内蒙古", "辽宁", "吉林", "江苏", "浙江",  "福建", "海南", "贵州","西藏", "甘肃", "青海", "宁夏" };
	
	public  void loadCompanyHtml(String key, boolean run) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Requested-With", "XMLHttpRequest");
		headers.put("Accept", "text/plain, */*; q=0.01");
		headers.put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 QQBrowser/9.3.6874.400");
		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

		Map<String, String> param = new HashMap<String, String>();

		param.put("keyword", key);
		param.put("page", "1");
		param.put("searchtype", "0");
		param.put("objectType", "2");
		param.put("areas", "辽宁");
		param.put("creditType", "");
		param.put("dataType", "1");
		param.put("areaCode", "");
		param.put("templateId", "");
		param.put("exact", "0");
		param.put("t", "" + System.currentTimeMillis());

		MyHttpServiceSupport mss = new MyHttpServiceSupport();
		// System.out.println(mss.doHttpGET("http://www.creditchina.gov.cn/search_all#keyword=&searchtype=0&departmentId=&creditType=&areas=&objectType=2&page=1"));
		// {"result":{"start":0,"pageSize":10,"totalCount":50,"results":[{"name":"沈阳铁路分局铁路器材厂","type":0,"idCardOrOrgCode":"210114000022636","area":"辽宁","objectType":2,"goodCount":0,"badCount":0,"dishonestyCount":0,"time":null,"encryStr":"d0NpeTB6NGQ=\n"},{"name":"沈阳铁路华兴物资经销公司","type":0,"idCardOrOrgCode":"210102000091880","area":"辽宁","objectType":2,"goodCount":0,"badCount":1,"dishonestyCount":0,"time":null,"encryStr":"c2ZGc2RkMHo=\n"},{"name":"沈阳铁路枕木防腐厂","type":0,"idCardOrOrgCode":"210111200003997","area":"辽宁","objectType":2,"goodCount":0,"badCount":1,"dishonestyCount":0,"time":null,"encryStr":"UW4xbzY2ezY=\n"},{"name":"鞍山市铁路配件公司","type":0,"idCardOrOrgCode":"210300005087540","area":"辽宁","objectType":2,"goodCount":0,"badCount":0,"dishonestyCount":0,"time":null,"encryStr":"fUtycGx1fX10\n"},{"name":"锦州铁路印刷总厂","type":0,"idCardOrOrgCode":"210700004025764","area":"辽宁","objectType":2,"goodCount":0,"badCount":0,"dishonestyCount":0,"time":null,"encryStr":"UUFsUWV9aTY=\n"},{"name":"鞍山铁路实业总公司","type":0,"idCardOrOrgCode":"210300005098890","area":"辽宁","objectType":2,"goodCount":0,"badCount":0,"dishonestyCount":0,"time":null,"encryStr":"fUNucHZwd0t7\n"},{"name":"大连丰源铁路仓储公司","type":0,"idCardOrOrgCode":"2102111101748","area":"辽宁","objectType":2,"goodCount":0,"badCount":0,"dishonestyCount":0,"time":null,"encryStr":"MkZzLGZxcWho\n"},{"name":"鞍山铁路房产综合厂","type":0,"idCardOrOrgCode":"210300005066867","area":"辽宁","objectType":2,"goodCount":0,"badCount":0,"dishonestyCount":0,"time":null,"encryStr":"ejQ7cWhxc1tx\n"},{"name":"锦州铁路机械厂","type":0,"idCardOrOrgCode":"210700004032324","area":"辽宁","objectType":2,"goodCount":0,"badCount":0,"dishonestyCount":0,"time":null,"encryStr":"ejQ7a2ZrZ3Fx\n"},{"name":"阜新铁路印刷厂","type":0,"idCardOrOrgCode":"210900004018441","area":"辽宁","objectType":2,"goodCount":0,"badCount":0,"dishonestyCount":0,"time":null,"encryStr":"cUN9e2IxbzYy\n"}],"departmentIds":null,"totalPageCount":5,"currentPageNo":1},"totalCount":1416}
		// File file = new File("E:/json.txt");
		// BufferedReader reader = new BufferedReader(new InputStreamReader(new
		// FileInputStream(file), "UTF-8"));
		// String tempString = null;

		// while (EmptyHelper.isNotEmpty(tempString = reader.readLine())) {
		// saveCompanyList(JSONObject.parseObject(tempString));
		// }
		String tempString;
		for (String area : areas) {
			if (run == false)
				return;
			System.out.print(" " + area);
			try {
				param.put("areas", area);
				{
					try {
						param.put("page", "1");
						tempString = mss.doHttpPOST("http://www.creditchina.gov.cn/credit_info_search", param);
						saveCompanyList(JSONObject.parseObject(tempString));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Thread.sleep(5000);
				{
					try {
						param.put("page", "2");
						tempString = mss.doHttpPOST("http://www.creditchina.gov.cn/credit_info_search", param);
						saveCompanyList(JSONObject.parseObject(tempString));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Thread.sleep(5000);
				 {
					try {
						param.put("page", "3");
						tempString = mss.doHttpPOST("http://www.creditchina.gov.cn/credit_info_search", param);
						saveCompanyList(JSONObject.parseObject(tempString));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Thread.sleep(5000);
				 {
					try {
						param.put("page", "4");
						tempString = mss.doHttpPOST("http://www.creditchina.gov.cn/credit_info_search", param);
						saveCompanyList(JSONObject.parseObject(tempString));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Thread.sleep(5000);
				 {
					try {
						param.put("page", "5");
						tempString = mss.doHttpPOST("http://www.creditchina.gov.cn/credit_info_search", param);
						saveCompanyList(JSONObject.parseObject(tempString));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public  void saveCompanyList(JSONObject restlt) {
		JSONArray list = restlt.getJSONObject("result").getJSONArray("results");
		for (Object object : list) {
			JSONObject josn = JSONObject.parseObject(object.toString());
			// System.out.println(josn);
			String strLine = "\r\n" + josn.getString("area") + "    " + josn.getString("name");// +
																								// "
																								// "
																								// +
																								// josn.getString("idCardOrOrgCode")
																								// +
																								// "";
			// System.out.print(strLine);
			appendFile(strLine);
		}
	}

	public  void appendFile(String content) {
		try {
			String direct = pathName +"/" ;//+DateHelper.customTime("yyyyMMdd")  +"/";
			File dir = new File(direct);
			dir.mkdirs();
			// 打开一个随机访问文件流，按读写方式
			FileWriter writer = new FileWriter(direct+ DateHelper.customTime("yyyyMMdd") + ".txt", true);
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
