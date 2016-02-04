package org.isotope.jfp.dwc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.dwc.business.JobConfig;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author 001745
 *
 */
@Controller
public class GanjiController {
	@Resource
	protected ICacheService mq;

	ArrayList<Integer> pages = new ArrayList<Integer>();
	static int MAX = 63186722;// 63147989
	static int size = 678;

	public static void main(String[] args) throws Exception {
		Jedis jedis = new Jedis("10.10.168.50", 6379);
		jedis.auth("ImxV@ly1D4bBtGwv");

		String target = "QCC";

		// Jedis jedis = new Jedis("127.0.0.1", 6379);

		// jedis.rpush("GJ:COMP:KEY", "铁路");
		// jedis.rpush("GJ:COMP:KEY", "医疗");
		// jedis.rpush("GJ:COMP:KEY", "教育");

		jedis.set(target + ":TASK:INVEL", "30:30");

		loadKeyWordFile(jedis, target);
		loadCompanyFile(jedis, target);

		// jedis.set("GJ:TASK:INVEL", "5000");
	}

	public static void loadKeyWordFile(Jedis jedis, String key) throws Exception {
		File file = new File("D:/corp_name_all_20160130.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while (EmptyHelper.isNotEmpty(tempString = reader.readLine())) {
				// 1.数据解析
				{
					jedis.rpush(key + ":COMP:KEY", tempString);
					System.out.println("line " + line + "======>>>>>" + tempString);
				}
				line++;
				// if(line>500)
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

		System.out.println("处理结束");
	}

	public static void loadCompanyFile(Jedis jedis, String key) throws Exception {
		File file = new File("D:/corp_name_all_20160130.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while (EmptyHelper.isNotEmpty(tempString = reader.readLine())) {
				// 1.数据解析
				{
					jedis.rpush(key + ":COMP:KEY", tempString);
					System.out.println("line " + line + "======>>>>>" + tempString);
				}
				line++;
				// if(line>500)
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

		System.out.println("处理结束");
	}

	boolean stop = false;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView loadHttpProxys(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("index");
		return model;
	}

	@RequestMapping(value = "/K/{key}", method = RequestMethod.GET)
	public ModelAndView capInitKey(HttpServletRequest request, @PathVariable String key) {
		ModelAndView model = new ModelAndView("GJK");
		model.addObject("TASK_INVEL", mq.getObject(key + ":TASK:INVEL", false));

		String comp_key = (String) mq.pollFirstObjectInList(key + ":COMP:KEY", false);
		if (EmptyHelper.isNotEmpty(comp_key)) {
			model.addObject("COMP_KEY", comp_key);
		} else {
			comp_key = (String) mq.pollFirstObjectInList(key + ":COMP:NAME", false);
			if (EmptyHelper.isNotEmpty(comp_key)) {
				model.addObject("COMP_KEY", comp_key);
			} else {
				model.addObject("COMP_KEY", "");
			}
		}
		return model;
	}

	@RequestMapping(value = "/N/{key}", method = RequestMethod.GET)
	public ModelAndView capInitName(HttpServletRequest request, @PathVariable String key) {
		ModelAndView model = new ModelAndView("GJN");
		model.addObject("TASK_INVEL", mq.getObject(key + ":TASK:INVEL", false));

		String comp_url = (String) mq.pollFirstObjectInList(key + ":COMP:LIST", false);
		if (EmptyHelper.isNotEmpty(comp_url)) {
			model.addObject("COMP_URL", comp_url);
		} else {
			model.addObject("COMP_URL", "");
		}

		return model;
	}

	@RequestMapping(value = "/GJ1", method = RequestMethod.POST)
	public ModelAndView capUpload1(HttpServletRequest request, @RequestParam String code, @RequestParam String name) {
		ModelAndView model = new ModelAndView("DWC/09001000");

		mq.offerObjectInList("GJ:COMP:LIST", code + " " + name, false);

		model.addObject(WEB_KEY, "OK");
		return model;
	}

	public static String WEB_KEY = "HTML_RESULT";
	@Resource
	protected JobConfig config;

	@RequestMapping(value = "/GJ2", method = RequestMethod.POST)
	public ModelAndView capUpload2(HttpServletRequest request, @RequestParam String fileName, @RequestParam String file) {
		ModelAndView model = new ModelAndView("DWC/09001000");
		if (!file.isEmpty()) {
			try {
				// 文件存储
				String path = config.getFileSavePath() + DateHelper.currentDate3() + "/";
				File f = new File(path);
				if (f.exists() == false)
					f.mkdirs();
				SaveFileFromInputStream(file, path, fileName + ".html");
				model.addObject(WEB_KEY, "OK");
				// Redis存储
				// model.addObject("FILE_VALUE", new String(bytes, "UTF-8"));
			} catch (Exception e) {
				e.printStackTrace();
				model.addObject(WEB_KEY, "FAIL");
			}
		} else {
			model.addObject(WEB_KEY, "FAIL");
		}
		return model;
	}

	@RequestMapping(value = "/GJ3", method = RequestMethod.POST)
	public ModelAndView capUpload3(HttpServletRequest request, @RequestParam String taskInvel, @RequestParam String key) {
		ModelAndView model = new ModelAndView("DWC/09001000");

		mq.putObject(key + ":TASK:INVEL", taskInvel, 0, false);

		model.addObject(WEB_KEY, "OK");
		return model;
	}

	public void SaveFileFromInputStream(String content, String path, String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(path + fileName);
		Writer out = new OutputStreamWriter(fos, "UTF-8");
		out.write(content);
		out.flush();
		fos.close();
	}

}
