package com.mcookies.qxy.biz.schoolcontacts;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.SLabel.SLabelDBO;
import com.mcookies.qxy.common.SLabel.SLabelPVO;
import com.mcookies.qxy.common.SLabel.SLabelService;
/**
 * 教工标签管理
 * @author macBookTang
 *
 */
@Controller
public class StaffLabelController extends MyControllerSupport {
	@Resource
	protected SLabelService SLabelService_;

	/**
	 * 教工标签新增接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/label", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean labelPOST(String token, @RequestBody SLabelPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			//校验标签是否存在
			List<SLabelDBO> rlist = (List<SLabelDBO>)SLabelService_.doSelectData(pvo);
			if(rlist!=null&&rlist.size()>0){
				result.setInfo("新增失败，标签名称重复");
				result.setStatus(2);
			}else{
				//新增
				pvo.setIsUse(1);
				SLabelService_.doInsert(pvo);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 *  教工标签修改接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/label", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean labelPUT(String token, @RequestBody SLabelPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			//校验标签是否存在
			SLabelDBO param = new SLabelDBO();
			param.setLabelName(pvo.getLabelName());
			List<SLabelDBO> rlist = (List<SLabelDBO>)SLabelService_.doSelectData(param);
			if(rlist!=null&&rlist.size()>0){
				for(SLabelDBO tmp:rlist){
					if(tmp.getLabelId()!=pvo.getLabelId()){
						result.setInfo("新增失败，标签名称重复");
						result.setStatus(2);
						return result;
					}
				}
			}
			//编辑
			SLabelService_.doUpdate(pvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	
	/**
	 *  教工标签修改接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/label", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean labelDELETE(String token, @RequestBody SLabelPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			//删除
			int flag = SLabelService_.doDelete(pvo);
			if(flag!=1){
				result.setInfo("删除失败，标签id无效");
				result.setStatus(2);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
}
