package org.isotope.jfp.dwc.control;

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

import org.isotope.jfp.dwc.config.JobConfig;
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
		jedis.set("GJ:TASK:INVEL", "5000");
		// Jedis jedis = new Jedis("127.0.0.1", 6379);

		// jedis.rpush("GJ:COMP:KEY", "铁路");
		// jedis.rpush("GJ:COMP:KEY", "医疗");
		// jedis.rpush("GJ:COMP:KEY", "教育");

		// jedis.set(target + ":TASK:INVEL", "300;300");

		// loadKeyWordFile(jedis, target);
		// loadCompanyFile(jedis, target);
		// loadCompanyNameFile(jedis, target);

		// jedis.set("GJ:TASK:INVEL", "5000");
	}

	public static void loadCompanyNameFile(Jedis jedis, String key) throws Exception {
		File file = new File("F:/QCC-CompanyName/Local2/201602111-CompanyList.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while (EmptyHelper.isNotEmpty(tempString = reader.readLine())) {
				// 1.数据解析
				if (line > 2237) {
					try {
						jedis.rpush(key + ":COMP:LIST", tempString);
						System.out.println("line " + line + "======>>>>>" + tempString);
					} catch (Exception e) {
						jedis = new Jedis("10.10.168.50", 6379);
						jedis.auth("ImxV@ly1D4bBtGwv");
					}
				}
				line++;
				// if(line>10)
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

	public static void loadKeyWordFile(Jedis jedis, String key) throws Exception {
		File file = new File("D:/KeyWord.txt");
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
		File file = new File("F:/corp_name_all_20160131.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while (EmptyHelper.isNotEmpty(tempString = reader.readLine())) {
				// 1.数据解析
				if (line > 12100) {
					jedis.rpush(key + ":COMP:KEY", tempString);
					System.out.println("line " + line + "======>>>>>" + tempString);
				}
				line++;
				if (line > 3000000)
					break;
			}
			reader.close();
		} catch (Exception e) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e1) {
				}
			}
		}
	}

	boolean stop = false;

	@RequestMapping(value = "/KKK", method = RequestMethod.GET)
	public ModelAndView loadHttpProxys(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView("index");

		Jedis jedis = new Jedis("10.10.168.50", 6379);
		jedis.auth("ImxV@ly1D4bBtGwv");

		String target = "QCC";

		try {
			loadKeyWordFile(jedis, target);
		} catch (Exception e1) {
		}

		try {
			loadCompanyFile(jedis, target);
		} catch (Exception e1) {
		}

		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView loadHttpProxys1(HttpServletRequest request) throws Exception {
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

	@RequestMapping(value = "/GJ1/{key}", method = RequestMethod.POST)
	public ModelAndView capUpload1(HttpServletRequest request, @PathVariable String key, @RequestParam String code, @RequestParam String name) {
		ModelAndView model = new ModelAndView("DWC/09001000");

		mq.offerObjectInList(key + ":COMP:LIST", code + " " + name, false);

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
