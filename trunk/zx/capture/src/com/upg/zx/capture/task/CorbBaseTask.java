package com.upg.zx.capture.task;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.apache.log4j.Logger;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upg.zx.capture.bean.Contain;
import com.upg.zx.capture.bean.ForCodeCompany;
import com.upg.zx.capture.bean.HtmlCaptureRsJob;
import com.upg.zx.capture.bean.YzCode;
import com.upg.zx.capture.client.YzmCapture;
import com.upg.zx.capture.util.HttpClientUtil;
import com.upg.zx.domain.capture.Capture;
import com.upg.zx.domain.capture.exception.CaptureException;
import com.upg.zx.domain.capture.service.IAppService;
import com.upg.zx.domain.response.CorpBaseRes;
import com.upg.zx.web.utils.StringUtil;
import com.zxlh.comm.async.service.AsyncService;

public class CorbBaseTask {

	private ICacheService cache;

	// 打码失败队列key
	private final String FOR_CODE_FAILD_KEY = "FOR:CODE:FAILD";
	// 网页抓取队列key
	private final String HTML_CAPTURE_KEY = "COMPANY:CAP";
	// 网页抓取任务等待队列
	private final String HTML_CAPTURE_JOBS_KEY = "COMPANY:INFO:JOBS";

	// 信号量
	protected Semaphore clientSemaphore = new Semaphore(15);

	private Capture capture;
	// 抓取任务返回超时时间，默认一个小时
	private long jobTimeOut = 3600000L;

	private AsyncService asyncService;

	private YzmCapture yzmCapture;

	private int forCodeFaild;

	public void setForCodeFaild(int forCodeFaild) {
		this.forCodeFaild = forCodeFaild;
	}

	private Logger logger = Logger.getLogger(this.getClass());

	public void setCache(ICacheService cache) {
		this.cache = cache;
	}

	public void setCapture(Capture capture) {
		this.capture = capture;
	}

	public void setAsyncService(AsyncService asyncService) {
		this.asyncService = asyncService;
	}

	public void listTask() {
		try {
			if (clientSemaphore.tryAcquire(500L, TimeUnit.MILLISECONDS)) {
				ForCodeCompany forCodeCompany = getForCodeCompany();
				if (forCodeCompany != null
						&& EmptyHelper.isNotEmpty(forCodeCompany.getCorpName())) {
					try {
						asyncService.runTask(this, "corpList",
								new Object[] { forCodeCompany }, null, null,
								5000, true);
					} catch (InterruptedException e) {
						logger.error("异步调用forCodeCompany失败!");
					}
				}else{
					clientSemaphore.release();
				}
			}else{
				logger.warn("最多只能同时开启15个线程");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setYzmCapture(YzmCapture yzmCapture) {
		this.yzmCapture = yzmCapture;
	}

	public long getJobTimeOut() {
		return jobTimeOut;
	}
	static String server = "http://172.16.2.214:80";
	
	public void setJobTimeOut(long jobTimeOut) {
		this.jobTimeOut = jobTimeOut;
	}
	public HttpHost loadProxy() {
		HttpHost httpHostProxy = null;
		try {
			String rs = HttpClientUtil.getRequest(server + "/Zheng/proxy/newProtool");
			JSONObject js = JSONObject.parseObject(rs);
			JSONObject proxy = JSONObject.parseObject(js.getString("data"));
			System.out.println(proxy);
			httpHostProxy = new HttpHost(proxy.getString("hostName"), proxy.getIntValue("port"));
			
		} catch (Exception e) {
			System.out.println("loadProxy error===>>>" + e.getMessage());
		}
		return httpHostProxy;
	}
	
	/**
	 * 打码功能实现
	 * 
	 * @param forCodeCompany
	 */
	public void corpList(ForCodeCompany forCodeCompany) {
		CorpBaseRes corpBaseRes = null;
		UUID uuid = UUID.randomUUID();
		try {
			
			if(StringUtil.isContainInArray(Contain.NO_CAPTURE_INFOAREA,
					forCodeCompany.getAreaCode())){
				return;
			}
			// 如果 抓取失败次数大于指定次数或区域码在需要人工获取列表以及详细信息区域列表中,推送到人工获取所有信息接口
			if (forCodeCompany.getFailCount() > this.forCodeFaild) {
				StringBuffer buf = new StringBuffer();
				String jobId = uuid.toString();
				buf.append(jobId);
				buf.append(";");
				buf.append(forCodeCompany.getAreaCode());
				buf.append(";");
				buf.append(forCodeCompany.getCorpName());
				// 推送到人工获取所有信息接口
				cache.offerObjectInList(this.HTML_CAPTURE_KEY, buf.toString(),
						false);
				// 将任务ID放到等待结果队列中
				cache.offerObjectInList(
						this.HTML_CAPTURE_JOBS_KEY,
						new HtmlCaptureRsJob(jobId, forCodeCompany
								.getAreaCode(), new Date()));
				return;
			}
			// //获取代理
			HttpHost proxy = loadProxy();
			
			// //代理设置
			if (proxy != null) {
				CompanyTask.ipAddressForList.set(proxy.getHostName());
				CompanyTask.portForList.set(proxy.getPort());
			}
			CorpBaseRes return_rs = null;
			try {
				// 获取验证码
				corpBaseRes = capture.findCompanyByName(
						forCodeCompany.getCorpName(), null, null,
						forCodeCompany.getAreaCode());
				if (corpBaseRes != null) {
					return_rs = this.subCorpList(forCodeCompany.getCorpName(),
							corpBaseRes.getSessionId(),
							forCodeCompany.getAreaCode(),
							corpBaseRes.getBitmap(), uuid.toString());
				}
			} catch (Exception e) {
				if (e instanceof CaptureException) {
					CaptureException cap = (CaptureException) e;
					if ("IO异常!".equals(cap.getMessage())
							|| "http请求异常!".equals(cap.getMessage())) {
						// 设置失败代理
						logger.error("区域码" + forCodeCompany.getCorpName()								+ "失败");
					}
				}
				// 代理失败添加到打码失败队列
				cache.offerObjectInList(this.FOR_CODE_FAILD_KEY,
						JSON.toJSONString(forCodeCompany), false);
				//System.out.println(e.getMessage());
				return;
			}
			// 数据上传
			if (return_rs != null && return_rs.getHtml() != null &&!"".equals(return_rs.getHtml())) {
				try {
					HttpClientUtil
							.postRequest(Contain.ZX_SERVICE_HOST
									+ IAppService.LIST_UPLOAD_Capture + "?areaCode="
									+ forCodeCompany.getAreaCode(),
									return_rs.getHtml());
					logger.info(forCodeCompany.getCorpName() + "上传成功");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				logger.info("没有相关数据！");
			}
		} finally {
			clientSemaphore.release();
		}
	}

	/**
	 * 验证验证码获取列表
	 * 
	 * @param company
	 * @param jsessionId
	 * @param areaCode
	 * @param image_Str
	 */
	public CorpBaseRes subCorpList(String company, String jsessionId,
			String areaCode, byte[] image_Str, String uuid) {
		YzCode code = yzmCapture.getYzm(areaCode, image_Str, uuid);

		if (code == null || "".equals(code.getCode()) || code.getCode() == null) {
			// 如果是通过人工打码返回的失败的则直接退出
			if (code != null && "1".equals(code.getRs())) {
				return null;
			}
			// 自动打码识别出错,则直接再获取验证码进行识别
			CorpBaseRes corpBaseRes = capture.findCompanyByName(company, null,
					null, areaCode);
			return subCorpList(company, corpBaseRes.getSessionId(), areaCode,
					corpBaseRes.getBitmap(), uuid);
		} else {
			CorpBaseRes corpBaseRes = capture.findCompanyByName(company,
					code.getCode(), jsessionId, areaCode);
			if ("bitmap".equals(corpBaseRes.getType())) {
				// 如果自动打码失败继续打码
				if ("0".equals(code.getRs())) {
					corpBaseRes = this.subCorpList(company,
							corpBaseRes.getSessionId(), areaCode,
							corpBaseRes.getBitmap(), uuid);
				} else {
					// 如果是人工打码失败则直接退出
					logger.warn(company+"人工打码失败");
					return null;
				}
			}
			return corpBaseRes;
		}
	}

	/**
	 * 上传人工获取所有信息队列中的html内容
	 */
	public void forCodeCompanyByArtificialHtmlToUpload() {
		// cache.offerObjectInList(this.HTML_CAPTURE_JOBS_KEY,
		// jobId+","+forCodeCompany.getAreaCode());
		HtmlCaptureRsJob rsJob = (HtmlCaptureRsJob) cache
				.pollFirstObjectInList(this.HTML_CAPTURE_JOBS_KEY);
		if (EmptyHelper.isNotEmpty(rsJob)) {
			String url = (String) cache.getObject(rsJob.getJobId());
			if (EmptyHelper.isNotEmpty(url)) {
				try {
					// 获取html
					String html = HttpClientUtil.getRequest(
							Contain.ARTIFICIAL_SERVICE_HOST + url, null);
					if (html != null && !"".equals(html)) {
						HttpClientUtil.postRequest(Contain.ZX_SERVICE_HOST
								+ IAppService.LIST_UPLOAD + "?areaCode="
								+ rsJob.getAreaCode(), html);
						//System.out.println(html);
					}
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		if (rsJob != null && !rsJob.expire(this.jobTimeOut)) {
			cache.offerObjectInList(this.HTML_CAPTURE_JOBS_KEY, rsJob);
		}
	}

	/**
	 * 获取企业待打码队列中企业
	 * 
	 * @param url
	 * @return
	 * 
	 */
	public ForCodeCompany getForCodeCompany() {
		try {
			String json = HttpClientUtil.getRequest(
					Contain.ARTIFICIAL_SERVICE_HOST
							+ IAppService.GET_FOR_CODE_COMPANY, null);
			if (json != null && !"".equals(json)) {
				JSONObject json_obj = JSONObject.parseObject(json);
				ForCodeCompany forCodeCompany =  JSONObject.toJavaObject(json_obj, ForCodeCompany.class);
				return forCodeCompany;
			}
		} catch (IOException e) {
			logger.error("打码企业获取接口异常:" + e.getMessage());
		}
		return null;
	}

}
