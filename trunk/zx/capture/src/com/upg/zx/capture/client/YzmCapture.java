package com.upg.zx.capture.client;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.codec.binary.Base64;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.upg.zx.capture.bean.Contain;
import com.upg.zx.capture.bean.YzCode;
import com.upg.zx.capture.util.HttpClientUtil;
import com.upg.zx.capture.util.PicGrey;
import com.upg.zx.domain.capture.service.IAppService;
import com.upg.zx.web.utils.StringUtil;

/**
 * 获取验证码
 * 
 * @author lujf
 * 
 */
public class YzmCapture {
	// 缓存
	private ICacheService cache;
	// 验证码失败多少次
	private int count;
	// 验证码失败key
	public static String YZM_FAIL_KEY = "_fail";

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	public void setCache(ICacheService cache) {
		this.cache = cache;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// 获取验证码
	public YzCode getYzm(String area, byte[] bitmap, String subJobId) {

		int fail_size = 0;
		YzCode yzm = null;

		String failstr = (String) cache.getObject(subJobId + this.YZM_FAIL_KEY, false);

		if (EmptyHelper.isNotEmpty(failstr)) {
			fail_size = Integer.parseInt(failstr);
		}
		// 失败超过次数以及打码区域

		int maxFail;

		if (Contain.MAX_RETRY_FORYZM.get(area) != null) {

			maxFail = Contain.MAX_RETRY_FORYZM.get(area);

		} else {
			maxFail = count;
		}

		if (StringUtil.isContainInArray(Contain.NO_CAPTURE_AREA, area) || fail_size > maxFail) {
			// 人工打码
			return getYzmByArtificialCode(bitmap, subJobId);
		}
		// 自动打码
		yzm = this.getYzmByAuto(area, bitmap, subJobId);
		return yzm;

	}

	/**
	 * 人工获取验证码
	 * 
	 * @param bitmap
	 * @param subJobId
	 * @return
	 */
	public YzCode getYzmByArtificialCode(byte[] bitmap, String subJobId) {
		// key:subJobId
		YzCode yzCode = new YzCode();
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("jobid", subJobId);
			map.put("img", Base64.encodeBase64String(bitmap));
			HttpClientUtil.postRequest(Contain.ARTIFICIAL_SERVICE_HOST + IAppService.ARTIFICIAL_CODE, map);
			for (int i = 0; i < 30; i++) {
				String code = (String) cache.getObject(subJobId, false);
				if (EmptyHelper.isNotEmpty(code)) {
					yzCode.setCode(code);
					break;
				}
				Thread.sleep(1000L);

			}
		} catch (Exception e) {
			log.error("人工打码异常：" + e.getMessage());
		}
		yzCode.setRs("1");
		return yzCode;

	}

	/**
	 * 自动获取验证码
	 * 
	 * @param area
	 * @param bitmap
	 * @return
	 */
	public YzCode getYzmByAuto(String areaCode, byte[] image_Str, String subJobId) {
		String code = "";
		YzCode yzCode = new YzCode();
		try {
			PicGrey flt = null;
			// 江西不需要生成验证码识别器
			String failstr = (String) cache.getObject(subJobId + this.YZM_FAIL_KEY, false);
			// 设置人工打码次数
			if (EmptyHelper.isNotEmpty(failstr)) {
				cache.putObject(subJobId + this.YZM_FAIL_KEY, "" + (Integer.parseInt(failstr) + 1), 900, false);

			} else {
				cache.putObject(subJobId + this.YZM_FAIL_KEY, "" + 1, 900, false);
			}

			if (!"360000".equals(areaCode)) {
				flt = new PicGrey(image_Str, null);
			}
			// if (areaCode.equals("110000")) {
			// code = flt.getBJCode(); //
			// } else
			if (areaCode.equals("120000")) {
				code = flt.getTJCode(); //

			} else if (areaCode.equals("130000")) {
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
				return null;
			}
		} catch (Exception e) {
			log.error("自动打码异常:" + e.getMessage());
		}
		yzCode.setCode(code);
		yzCode.setRs("0");
		return yzCode;
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
			rs = ((Double) engine.eval(buf.toString())).intValue();
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
			return "";
		}
		return rs + "";
	}

}
