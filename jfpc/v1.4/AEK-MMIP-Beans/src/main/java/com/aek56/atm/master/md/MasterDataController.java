package com.aek56.atm.master.md;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.support.MyBusinessSupport;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主数据检索
 * @author Administrator
 *
 */
@Controller
public class MasterDataController extends MyBusinessSupport {
	
	@Resource
	MasterDataService MasterDataService_;
	
	/**
	 * 单一数据查看
	 * @param type 主数据类别
	 * @param id 主数据ID
	 * @param token 用户登录标识
	 * @return
	 */
	@RequestMapping(value = "/2001010/{type}/{id}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m2001010POST(
			@PathVariable String type,@PathVariable String id
			) {
		RESTResultBean rs = new RESTResultBean();
		rs.setResult(MasterDataService_.getMaster(Integer.parseInt(type),id));
		// 根据名称，从数据库里面查询最新5条
		return rs;
	}
	
	/**
	 * 单一数据查看
	 * @param type 主数据类别
	 * @param id 主数据ID
	 * @param token 用户登录标识
	 * @return
	 */
	@RequestMapping(value = "/2001020/{type}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m2001020POST(
			@PathVariable String type,
			String p, String l
			) {
		RESTResultBean rs = new RESTResultBean();
		if (EmptyHelper.isEmpty(p)) p ="1";
		if (EmptyHelper.isEmpty(l)) l ="12";
		rs.setResult(MasterDataService_.getMasterData(Integer.parseInt(type),Integer.parseInt(p),Integer.parseInt(l)));
		// 根据名称，从数据库里面查询最新5条
		return rs;
	}
	
	/**
	 * 获得主数据前面10条（简写）
	 * @param q 参数
	 * @param t 类型
	 * @param l 返回记录数目
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/2001030/{type}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m2001030POST(
			@PathVariable String type,
			String q, String l) throws UnsupportedEncodingException{
	    if (StringUtils.isEmpty(q)) {
	        q = "";
	    }
	    
        q = URLDecoder.decode(q, "utf-8");
        
  		RESTResultBean rs = new RESTResultBean();
		if (EmptyHelper.isEmpty(l)) l ="12";
		rs.setResult(MasterDataService_.getTop10(q, Integer.parseInt(type), Integer.parseInt(l)));
		// 根据名称，从数据库里面查询最新5条
		return rs;
	}
	
	/**
	 * 获得主数据前面10条（全部）
	 * @param q 参数
	 * @param t 类型
	 * @param l 返回记录数目
	 * @return
	 */
	@RequestMapping(value = "/2001031/{type}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m2001031POST(
			@PathVariable String type,
			String q, String l) {
		RESTResultBean rs = new RESTResultBean();
		if (EmptyHelper.isEmpty(l)) l ="12";
		rs.setResult(MasterDataService_.getTop101(q, Integer.parseInt(type), Integer.parseInt(l)));
		// 根据名称，从数据库里面查询最新5条
		return rs;
	}
	
	/**
	 * 获得主数据记录数目
	 * @param q 参数
	 * @param t 类型
	 * @param l 返回记录数目
	 * @return
	 */
	@RequestMapping(value = "/2001040", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m2001040POST(
			String t,String l) {
		RESTResultBean rs = new RESTResultBean();
		if (EmptyHelper.isEmpty(l)) l ="12";
		rs.setResult(MasterDataService_.getMasterCount());
		// 根据名称，从数据库里面查询最新5条
		return rs;
	}
	
//	/**
//	 * 获得参数搜索近似产品
//	 * @param q 参数
//	 * @param t 类型
//	 * @param l 返回记录数目
//	 * @return
//	 */
//	@RequestMapping(value = "/2002050", method = RequestMethod.POST)
//	@ResponseBody
//	public RESTResultBean m2001050POST(
//			String t,String l) {
//		RESTResultBean rs = new RESTResultBean();
//		if (EmptyHelper.isEmpty(l)) l ="12";
//		rs.setResult(MasterDataService_.getMaster());
//		// 根据名称，从数据库里面查询最新5条
//		return rs;
//	}	
}
