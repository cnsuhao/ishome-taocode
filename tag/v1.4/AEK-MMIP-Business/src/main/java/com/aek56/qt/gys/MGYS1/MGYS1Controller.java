package com.aek56.qt.gys.MGYS1;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aek56.atm.company.MGYS1_JYFW.MGYS1_JYFWDBO;
import com.aek56.atm.company.MGYS1_JYFW.MGYS1_JYFWService;
import com.aek56.atm.master.MD1_QYJYFW.MD1_QYJYFWService;

/**
 * 供应商经营范围管理
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MGYS1Controller extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MGYS1Controller.class);

	/**
	 * 供应商经营范围表
	 */
	@Resource
	protected MGYS1_JYFWService MGYS1_JYFWService_;

	/**
	 * 主数据经营范围字典
	 */
	@Resource
	protected MD1_QYJYFWService MD1_QYJYFWService_;

	/**
	 * 事务类
	 */
	@Resource
	protected MGYS1Business MGYS1Business_;

	/**
	 * 跳转到经营范围（range.jsp）页面
	 * 
	 * @param pukid
	 * @return
	 * @Author:zhengxw
	 * @Date:2014-12-3
	 */
	@RequestMapping(value = "/3101100", method = RequestMethod.POST)
	public ModelAndView m3101100post() {
		MyModelAndViewSupport view = this.getModelAndView();
		view.setViewName("qt/gys/MGAAC/range");
		return view;
	}
	
	//http://127.0.0.1:8800/31011001/123?token=6S1_Y4_S1_T7_E8_M4__8__4__0__1__1__3__9

	/**
	 * 根据父节点ID获取经营范围字典子列表
	 * 
	 * @param treeType
	 *            展示树分类 0:68码树，1：分类树
	 * @return
	 * @Author:zhengxw
	 * @Date:2014-10-20
	 */
	@RequestMapping(value = "/31011001", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m31011001post() {
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MGYS1Controller#m31011001post()!");
		}

		RESTResultBean rs = new RESTResultBean();

		// 获得全部经营范围数据
		HashMap<String,Object> rt = new HashMap<String,Object>();
		rt.put("all", MGYS1Business_.loadAllJYFW());
		MGYS1_JYFWDBO cmp = new MGYS1_JYFWDBO();
		cmp.setP01_gys_qyid(super.getCompanyId());
		List<FrameworkDataBean> list = MGYS1_JYFWService_.doSelectPage(cmp, false);
		
		if(list ==null||list.size()==0)
			rt.put("my", "");
		else
			rt.put("my", list.get(0).getBbb());

		// 获得用户经营范围数据
		rs.setResult(rt);

		return rs;
	}
	
	/**
	 * 根据父节点ID获取经营范围字典子列表
	 * 
	 * @param treeType
	 *            展示树分类 0:68码树，1：分类树
	 * @return
	 * @Author:zhengxw
	 * @Date:2014-10-20
	 */
	@RequestMapping(value = "/31011001/123", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m31011001123post() {
		if (logger.isDebugEnabled()) {
			logger.debug("Enter into MGYS1Controller#m31011001post()!");
		}

		RESTResultBean rs = new RESTResultBean();

		// 获得全部经营范围数据
		HashMap<String,Object> rt = new HashMap<String,Object>();
		rt.put("all", MGYS1Business_.loadAllJYFW123());
		MGYS1_JYFWDBO cmp = new MGYS1_JYFWDBO();
		cmp.setP01_gys_qyid(super.getCompanyId());
		List<FrameworkDataBean> list = MGYS1_JYFWService_.doSelectPage(cmp, false);
		if(list ==null||list.size()==0)
			rt.put("my", "");
		else
			rt.put("my", list.get(0).getBbb());

		// 获得用户经营范围数据
		rs.setResult(rt);

		return rs;
	}

	/**
	 * 保存经营范围
	 * 
	 * @param saveNodeInfos
	 * @return
	 * @Author:zhegnxw
	 * @Date:2014-12-5
	 */
	@RequestMapping(value = "/31011002", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m31011002post(String saveNodeInfos) {
		RESTResultBean rs = new RESTResultBean();
		MGYS1_JYFWDBO cmp = new MGYS1_JYFWDBO();
		cmp.setP01_gys_qyid(super.getCompanyId());
		this.MGYS1_JYFWService_.doDelete(cmp);
		cmp.makePuk();
		cmp.setBbb(saveNodeInfos);
		int rsNum = this.MGYS1_JYFWService_.doInsert(cmp);
		rs.setMessage(rsNum > 0 ? "310110021" : "310110021");
		return rs;
	}
}
