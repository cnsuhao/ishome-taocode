package com.upg.zx.capture.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.upg.zx.capture.bean.Contain;
import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.capture.bean.ServiceConfig;
import com.upg.zx.capture.util.Base64SecurityUtils;
import com.upg.zx.capture.util.CorpSearchName;
import com.upg.zx.capture.util.HttpClientUtil;
import com.upg.zx.capture.util.PicGrey;
import com.upg.zx.domain.capture.Capture;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.domain.response.CorpBaseRes;
import com.zxlh.comm.async.service.AsyncService;

/**
 * 离线异步静态抓取
 * 
 * @author 001745
 *
 */
public class CompanyTask {
	private Capture capture;

	private AsyncService asyncService;

	private String userId;

	ServiceConfig serviceConfig;

	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	CorpSearchName corpSearchName;// = CorpSearchName.getInstance();

	public CorpSearchName getCorpSearchName() {
		return corpSearchName;
	}

	public void setCorpSearchName(CorpSearchName corpSearchName) {
		this.corpSearchName = corpSearchName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private Logger logger = Logger.getLogger(this.getClass().getName());
	// 验证码重复次数
	// private static int retryTimes = 0;
	public static ThreadLocal<Integer> retryTimes = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		}
	};

	public void setAsyncService(AsyncService asyncService) {
		this.asyncService = asyncService;
	}

	// JX, AH, JS, SD, FJ ...
	public Integer BJ = 0, TJ = 1, HeB = 2, SX2 = 3, NMG = 4, LN = 5, JL = 6, HLJ = 7, JX = 8, AH = 9, JS = 10, SD = 11,
			FJ = 12, ZJ = 13, SH = 14, GD = 15, GX = 16, HaiN = 17, HeN = 18, HN = 19, HB = 20, CQ = 21, SC = 22,
			GZ = 23, YN = 24, XZ = 25, SX1 = 26, GS = 27, QH = 28, NX = 29, XJ = 30;

	public void init() {
	}

	private int error_count = 0;

	public void setCapture(Capture capture) {
		this.capture = capture;
	}

	public void taskRun() throws Exception {
		System.out.println("任务开启......CompanyTask");
		CorpBase corpBase = this.getCorpBase();
		if (corpBase != null) {
			System.out.println(corpBase.getCorp_name() + "开始抓取 .....");
			// 基本信息的请求信息
			RequestInfo requestInfo = RamCache.requestMap.get(corpBase.getRes_date() + "_" + "4");
			final String corpId = Base64SecurityUtils.encryption(corpBase.getCorp_id().toString());

			String html = capture.getCompanyInfoHtml(corpBase.getCompany_ID(), corpBase.getRes_date(), corpId,
					corpBase.getZhejiang_id());

			System.out.println(corpBase.getCorp_name() + "基本信息抓取结束 .....");

			if (html != null && !"".equals(html)) {
				System.out.println(corpBase.getCorp_name() + "开始上传 .....");
				try {
					final String suburl = serviceConfig.getServiceConfig("submitCompanyUrl") + "?corpId=" + corpId
							+ "&areaCode=" + corpBase.getRes_date() + "&headId=" + requestInfo.getId();
					HttpClientUtil.postRequest(suburl, html);
					System.out.println(corpBase.getCorp_name() + "上传 成功.....");
				} catch (Exception e) {
					error_count++;
					System.out.println(corpBase.getCorp_name() + "上传 失败....." + e.getMessage() + "||" + error_count);
				}

			}
		}
		System.out.println("任务结束......CompanyTask");

	}

	/**
	 * 每秒启动一个任务（任意城市）
	 * 
	 * @throws IOException
	 */
	public void getListTask() throws Exception {
		// 一个网站最多1分钟2次
		final String corpName[] = corpSearchName.getCorpSearchCorp2();

		if (corpName[1] == null) {
			System.out.println("区域：" + corpName[0] + " 企业名称为空。");
			return;
		}

		CorpBaseRes corpBaseRes = null;
		try {
			System.out.println("=====>>>>>名称: " + corpName[1] + "==>>区域码：" + corpName[0] + "==>>访问地址："
					+ RamCache.requestMap.get(corpName[0] + "_" + 1).getRurl() + "=====getImage");
			corpBaseRes = capture.findCompanyByName(corpName[1], null, null, corpName[0]);

		} catch (Exception e) {
			// proxy IP is useless
			corpSearchName.decreaseAreaSeqCorp(corpName[2]);
			return;
		}

		CorpBaseRes return_rs = getList(corpName[1], corpBaseRes.getSessionId(), corpName[0], corpBaseRes.getBitmap());

		if (return_rs != null && return_rs.getHtml() != null && !"".equals(return_rs.getHtml())) {
			String corpjson = "";
			try {
				System.out.println(corpBaseRes.getCorp_name() + "列表开始上传 .....");
				corpjson = HttpClientUtil.postRequest(serviceConfig.getServiceConfig("submitCompanyls") + "?areaCode="
						+ corpName[0] + "&userID=" + userId, return_rs.getHtml());
				logger.info(corpName[0] + corpBaseRes.getCorp_name() + "列表上传");
				System.out.println(corpBaseRes.getCorp_name() + "列表上传成功! .....");
				logger.info(corpBaseRes.getCorp_name() + "||" + corpName[0] + "||" + corpName[1]
						+ RamCache.requestMap.get(corpName[0] + "_" + 1).getRurl());
				// System.out.println(return_rs.getHtml());
			} catch (Exception e) {
				System.out.println(corpBaseRes.getCorp_name() + "列表上传失败! .....");
				e.printStackTrace();
			}

			if (corpjson != null && !"".equals(corpjson) && "110000".equals(corpName[0])) {
				// 获取详细信息
				getBjInfo(corpjson);

			}
		} else {
			System.out.println(corpBaseRes.getCorp_name() + " 为空记录! .....");
			if (("350000".equals(corpName[0])) || ("310000".equals(corpName[0])) || ("430000".equals(corpName[0]))) // 福建、上海、湖南的企业查询比较特殊，不需要回滚
				;
			else {
				;
				// corpSearchName.decreaseAreaSeqCorp(corpName[2]);
			}
		}

	}

	// 北京详细信息获取
	public void getBjInfo(String json_str) {
		if (json_str != null && !"".equals(json_str)) {
			JSONObject jsondata = JSONObject.parseObject(json_str);
			if (jsondata.getJSONObject("data") == null && jsondata.getJSONObject("data").getJSONArray("list") == null) {
				return;
			}
			JSONArray jsonArray = jsondata.getJSONObject("data").getJSONArray("list");
			for (int cp = 0; cp < jsonArray.size(); cp++) {
				JSONObject jsonObj = jsonArray.getJSONObject(cp);
				CorpBase corpBase_cp = JSONObject.toJavaObject(jsonObj, CorpBase.class);
				RequestInfo requestInfo = null;
				if (corpBase_cp.getZhejiang_id() != null && !"".equals(corpBase_cp.getZhejiang_id())) {
					requestInfo = RamCache.requestMap
							.get(corpBase_cp.getRes_date() + "_" + corpBase_cp.getZhejiang_id() + "_" + "4");
				} else {
					requestInfo = RamCache.requestMap.get(corpBase_cp.getRes_date() + "_" + "4");
				}
				String html = "";
				try {
					html = capture.getCompanyInfoHtml(corpBase_cp.getCompany_ID(), corpBase_cp.getRes_date(),
							corpBase_cp.getCorp_id() + "", corpBase_cp.getZhejiang_id());
					final String suburl = serviceConfig.getServiceConfig("submitCompanyUrl") + "?corpId="
							+ corpBase_cp.getCorp_id() + "&areaCode=" + corpBase_cp.getRes_date() + "&headId="
							+ requestInfo.getId();
					HttpClientUtil.postRequest(suburl, html);
				} catch (Exception e) {

				}
			}
		}
	}

	/**
	 * 量子
	 * 
	 * @throws IOException
	 */
	public void getLiangZiListTask() throws Exception {

		CorpBase corpBase = getLiangziCorp(serviceConfig.getServiceConfig("getLianziCompanyUrl"));

		if (corpBase == null) {
			System.out.println("量子企业名称为空。");
			return;
		}
		for (int i = 0; i < 1; i++) {
			try {
				asyncService.runTask(this, "businessyLiangziList", new Object[] { corpBase }, null, null, 5000, true);
			} catch (InterruptedException e) {
				System.out.println("异步调用自动打码失败!");
			}
		}

		// to get the next LiangZi corp immediately
		// getLiangZiListTask();
	}

	/**
	 * 量子数据自动打码业务处理
	 * 
	 * @param corpBase
	 * @throws Exception
	 */
	public void businessyLiangziList(CorpBase corpBase) throws Exception {
		String area = corpSearchName.getAreaByCode(corpBase.getRes_date());
		final String corpName[] = { corpBase.getRes_date(), corpBase.getCorp_name(), area };

		System.out.println("=====>>>>>量子企业名称: " + corpName[1]);
		CorpBaseRes corpBaseRes = null;
		try {
			corpBaseRes = capture.findCompanyByName(corpName[1], null, null, corpName[0]);
		} catch (Exception e) {
			System.out.println("Exception: areaCode " + corpName[0] + "  corp name: " + corpName[1]
					+ "  list is searched failure. ");

			return;
		}

		CorpBaseRes return_rs = getList(corpName[1], corpBaseRes.getSessionId(), corpName[0], corpBaseRes.getBitmap());

		if (return_rs != null && return_rs.getHtml() != null && !"".equals(return_rs.getHtml())) {
			try {
				System.out.println(corpBaseRes.getCorp_name() + "  量子列表开始上传 .....");
				HttpClientUtil.postRequest(
						serviceConfig.getServiceConfig("submitCompanyls") + "?areaCode=" + corpName[0],
						return_rs.getHtml());
				System.out.println(corpBaseRes.getCorp_name() + "  量子列表上传成功! .....");
			} catch (Exception e) {
				System.out.println(corpBaseRes.getCorp_name() + "  量子列表上传失败! .....");
				e.printStackTrace();
			}
		} else {
			System.out.println("areaCode " + corpName[0] + "  corp name: " + corpName[1] + "  list is null. ");
		}
	}

	public void getInfoTask() throws Exception {
		System.out.println("任务开启");
		final CorpBase corpBase = this.getCorpBase();
		if (corpBase != null && !"".equals(corpBase.getCorp_name())) {
			try {

				logger.info(corpBase.getCorp_name() + "||" + corpBase.getRes_date() + "info");
				asyncService.runTask(this, "getInfoTaskCapture", new Object[] { corpBase }, null, null, 5000, true);
			} catch (InterruptedException e) {
				System.out.println("异步调用抓取详细信息失败!");
			}
		}

		System.out.println("任务结束 ");
	}

	public void getInfoTaskCapture(CorpBase corpBase) {
		String areaType = "";

		corpSearchName.initialAreaCode();
		areaType = corpSearchName.getAreaByCode(corpBase.getRes_date());

		if ("BJ".equals(areaType) || "JL".equals(areaType) || "GZ".equals(areaType)) {
			System.out.println("system error: not supported area  " + corpBase.getRes_date());
			return;
		}

		System.out.println("area: " + areaType + ",   " + corpBase.getCorp_name() + "开始抓取 .....");
		// 模板请求地址

		RequestInfo requestInfo = null;
		if (corpBase.getZhejiang_id() != null && !"".equals(corpBase.getZhejiang_id())) {
			requestInfo = RamCache.requestMap.get(corpBase.getRes_date() + "_" + corpBase.getZhejiang_id() + "_" + "4");
		} else {
			requestInfo = RamCache.requestMap.get(corpBase.getRes_date() + "_" + "4");
		}

		final String corpId = Base64SecurityUtils.encryption(corpBase.getCorp_id().toString());

		if ("320000".equals(corpBase.getRes_date())) {
			String[] htmlTemplate_json = null;
			try {
				htmlTemplate_json = capture.getCompanyInfoByHtmlTemplate(corpBase.getCompany_ID(),
						corpBase.getRes_date(), corpId);
			} catch (Exception e) {
				System.out.println("getCompanyInfoByHtmlTemplate exception .....");
			}

			if (htmlTemplate_json != null && htmlTemplate_json[0] != null) {
				System.out.println(corpBase.getCorp_name() + "基本信息抓取结束 .....");

				Map<String, String> param = new HashMap<String, String>();
				param.put("htmlTemplate", htmlTemplate_json[1]);
				param.put("json", htmlTemplate_json[0]);
				param.put("areaCode", corpBase.getRes_date());
				param.put("headId", requestInfo.getId().toString());
				param.put("modeType", requestInfo.getModeType());
				param.put("corpId", corpId);

				System.out.println(corpBase.getCorp_name() + "开始上传 .....");

				try {
					HttpClientUtil.postRequest(serviceConfig.getServiceConfig("postOtherInfoJsUrl"), param);
					System.out.println(corpBase.getCorp_name() + "上传 成功.....");
				} catch (Exception e) {
					error_count++;
					System.out.println(corpBase.getCorp_name() + "上传 失败....." + e.getMessage() + "||" + error_count);
				}
				return;
			}
		}
		long beginTime = System.currentTimeMillis();
		String html = "";
		try {
			html = capture.getCompanyInfoHtml(corpBase.getCompany_ID(), corpBase.getRes_date(), corpId,
					corpBase.getZhejiang_id());
			System.out.println(corpBase.getCorp_name() + "抓取时间" + (System.currentTimeMillis() - beginTime) / 1000);
		} catch (Exception e) {
			System.out.println("getCompanyInfoHtml exception .....");
		}

		System.out.println(corpBase.getCorp_name() + "基本信息抓取结束 .....");

		if (html != null && !"".equals(html)) {
			System.out.println(corpBase.getCorp_name() + "开始上传 .....");
			try {
				final String suburl = serviceConfig.getServiceConfig("submitCompanyUrl") + "?corpId=" + corpId
						+ "&areaCode=" + corpBase.getRes_date() + "&headId=" + requestInfo.getId();
				HttpClientUtil.postRequest(suburl, html);
				logger.info(corpBase.getCorp_name() + "||" + corpBase.getRes_date() + "info_update");
				System.out.println(
						corpBase.getCorp_name() + "上传 成功....." + (System.currentTimeMillis() - beginTime) / 1000);
			} catch (Exception e) {
				error_count++;
				System.out.println(corpBase.getCorp_name() + "上传 失败....." + e.getMessage() + "||" + error_count);
			}
		}
	}

	/**
	 * 自动打码
	 * 
	 * @param company
	 * @param jsessionId
	 * @param areaCode
	 * @param image_Str
	 * @return
	 * @throws Exception
	 */
	public CorpBaseRes getList(String company, String jsessionId, String areaCode, byte[] image_Str) throws Exception {
		if (retryTimes.get() == 5) {
			retryTimes.set(0);
			return null;
		} else
			retryTimes.set(1 + retryTimes.get());

		// 获取验证码
		PicGrey flt = null;
		// 江西不需要生成验证码识别器
		if (!"360000".equals(areaCode)) {
			flt = new PicGrey(image_Str, null);
		}
		String code = "";

		if (areaCode.equals("110000")) {
			code = flt.getBJCode(); //
		} else if (areaCode.equals("120000")) {
			code = flt.getTJCode(); //

		} else if (areaCode.equals("130000")) {
			if (retryTimes.get() > 1)
				return null;

			code = flt.getFJCode(); // same as FJ
		}
		// else if (areaCode.equals("150000")){
		// code = flt.getGDCode(); // same as GD
		// }

		else if (areaCode.equals("140000")) {
			// code = flt.getSX2Code();
			code = flt.getAHCode(); // same as AH
		}
		// else if (areaCode.equals("150000")){
		// code = flt.getGDCode(); // same as GD
		// }
		else if (areaCode.equals("210000")) {

			code = flt.getLNCode(); //

		} else if (areaCode.equals("230000")) {
			code = flt.getAHCode(); // same as AH
		} else if (areaCode.equals("310000")) {
			if (retryTimes.get() > 1)
				return null;

			code = flt.getFJCode(); // same as FJ
		} else if (areaCode.equals("320000")) {
			code = flt.getJSCode();
		}
		// else if (areaCode.equals("330000")){
		// code = flt.getZJCode();
		// }
		else if (areaCode.equals("340000")) {
			code = flt.getAHCode();

		} else if (areaCode.equals("350000")) {
			if (retryTimes.get() > 1)
				return null;

			code = flt.getFJCode();
		} else if (areaCode.equals("360000")) {
			// 跳过打码
			code = "default";
		} else if (areaCode.equals("370000")) {
			code = flt.getSDCode();
			code = technologySdCode(code);

		} else if (areaCode.equals("410000")) {
			code = flt.getAHCode(); // same as AH
		} else if (areaCode.equals("420000")) {
			code = flt.getHBCode();
		} else if (areaCode.equals("430000")) {
			if (retryTimes.get() > 1)
				return null;

			code = flt.getFJCode(); // same as FJ
		}
		// else if (areaCode.equals("440000")){
		// code = flt.getGDCode();
		// }
		else if (areaCode.equals("450000")) {
			code = flt.getAHCode(); // same as AH
		}
		// else if (areaCode.equals("460000")){
		// code = flt.getGDCode(); // same as GD
		// }
		else if (areaCode.equals("500000")) {
			code = flt.getCQCode();
		} else if (areaCode.equals("510000")) {
			code = flt.getSCCode();
		} else if (areaCode.equals("520000")) {
			code = flt.getGZCode();
		} else if (areaCode.equals("530000")) {
			code = flt.getAHCode(); // same as AH
		} else if (areaCode.equals("540000")) {
			// code = flt.getSX2Code(); // almost same as SX2
			code = flt.getAHCode(); // same as AH
		} else if (areaCode.equals("610000")) {
			code = flt.getSCCode(); // same as SC
		} else if (areaCode.equals("620000")) {
			code = flt.getGSCode(); // GS
		} else if (areaCode.equals("630000")) {
			code = flt.getSX2Code(); // same as SX2
		} else if (areaCode.equals("640000")) {
			code = flt.getNXCode(); // same as JX
		} else if (areaCode.equals("650000")) {
			code = flt.getSCCode(); // same as SC
		} else {
			System.out.println(" for company: " + company + " Not supported area: " + code);
			return null;
		}
		System.out.println("code value: " + code);
		CorpBaseRes corpBaseRes = capture.findCompanyByName(company, code, jsessionId, areaCode);
		if ("bitmap".equals(corpBaseRes.getType())) {
			if (("350000".equals(areaCode)) || ("310000".equals(areaCode)) || ("430000".equals(areaCode))) // 福建、上海、湖南的企业查询比较特殊，不需要重试
			{
				System.out.println("自动识别验证码失败，福建、上海、湖南的企业查询比较特殊，不需要重试");
			} else {
				try {
					System.out.println("自动识别验证码失败，模拟人工操作，随机等待1-4秒后去识别下一个验证码");
					Random random = new Random();
					int sleepValue = 1 + random.nextInt(3);
					Thread.sleep(sleepValue * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				corpBaseRes = this.getList(company, corpBaseRes.getSessionId(), areaCode, corpBaseRes.getBitmap());

			}
		}

		retryTimes.set(0);

		return corpBaseRes;
	}

	/**
	 * 计算山东验证码
	 * 
	 * @return
	 */
	public String technologySdCode(String code) {
		StringBuffer buf = new StringBuffer();
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		if (code != null && !"".equals(code)) {
			for (int i = 0; i < code.length(); i++) {
				String word = code.substring(i, i + 1);
				if (word.equals("等"))
					break;
				if (!"".equals(word.trim())) {
					if (Contain.shandDongMap.get(word.trim()) != null) {
						buf.append(Contain.shandDongMap.get(word.trim()));
					}
				}
			}
		}
		int rs;
		try {
			rs = Double.valueOf("" + engine.eval(buf.toString())).intValue();
		} catch (Exception e) {
			System.out.println("TASK Double Exception: " + e.toString());
			return "";
		}
		return rs + "";
	}

	public CorpBase getCorpBase() throws Exception {
		CorpBase corpBase = null;
		String rs = HttpClientUtil.getRequest(serviceConfig.getServiceConfig("getCompanyUrl"));
		if (rs == null || "".equals(rs)) {
			return null;
		} else {
			try {
				JSONObject jsonObject = JSONObject.parseObject(rs);
				corpBase = JSONObject.toJavaObject(jsonObject, CorpBase.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return corpBase;
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public CorpBase getLiangziCorp(String url) throws Exception {
		CorpBase corpBase = null;
		String rs = HttpClientUtil.getRequest(url);
		if (rs == null || "".equals(rs)) {
			return null;
		} else {
			try {
				JSONObject jsonObject = JSONObject.parseObject(rs);
				if (jsonObject.getJSONObject("data") != null) {
					corpBase = JSONObject.toJavaObject(jsonObject.getJSONObject("data"), CorpBase.class);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return corpBase;
	}

	public void saveData(String data, String file) throws IOException {
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			bufferedWriter.write(data);
			bufferedWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedWriter != null) {
				bufferedWriter.flush();
				bufferedWriter.close();
			}
		}
	}

	public void readData(String file) throws IOException {
		BufferedReader read = null;
		try {
			read = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String json = "";
			while ((json = read.readLine()) != null) {
				try {
					System.out.println("开始上传");
					HttpClientUtil.postRequest("https://www.wzyrz.cn/Zheng/corp/uploadCompany", json);
					System.out.println("上传成功");
				} catch (Exception e) {
					// saveData(json, "d:/error.txt");
					// e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (read != null)
				read.close();
		}
	}

}
