package com.mcookies.qxy.biz.annex;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.common.sms.UserSMSSendServiceImpl;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.SMessage.SMessageDBO;
import com.mcookies.qxy.common.SMessage.SMessageService;
import com.mcookies.qxy.common.UParent.UParentDBO;
import com.mcookies.qxy.common.UParent.UParentService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;

/**
 * 消息群发
 * 
 * @author macBookTang
 *
 */
@Controller
@Transactional
public class SMessageController extends MyControllerSupport {
	@Resource
	protected UParentService UParentService_;
	@Resource
	protected UTeacherService UTeacherService_;
	@Resource
	protected UserSMSSendServiceImpl UserSMSSendServiceImpl_;
	@Resource
	protected SMessageService SMessageService_;

	/**
	 * 发送消息接口
	 * 
	 * @param token
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean sendMessagePOST(String token, @RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONObject param = JSONObject.parseObject(jsonparam);
			Integer type = param.getInteger("type");
			String parentIdstr = param.getString("parentId");
			String tidstr = param.getString("tid");
			String message = param.getString("message");
			Integer isReceipt = param.getInteger("isReceipt");
			// 家长信息发送
			if (StringUtils.isEmpty(parentIdstr)) {
				String[] parentIds = parentIdstr.split(";");
				if (parentIds != null && parentIds.length > 0) {
					for (String ptmp : parentIds) {
						Long parentId = Long.parseLong(ptmp);
						// 获取家长的手机号码
						UParentDBO parent = new UParentDBO();
						parent.setParentId(parentId);
						parent = (UParentDBO) UParentService_.doRead(parent);
						if (parent != null) {
							String phone = parent.getPhone();
							// 短信
							if (type == 0) {
								// 发短信
								UserSMSSendServiceImpl_.send(parent.getSid().toString(), phone, message);
							} else if (type == 1) {
								// app推送
							} else if (type == 2) {
								// 微信消息
							}
							// 记录到smessage表中
							SMessageDBO mdbo = new SMessageDBO();
							mdbo.setType(type);
							mdbo.setObject(1);
							mdbo.setParentId(parent.getParentId());
							mdbo.setParentName(parent.getParentName());
							mdbo.setPhone(phone);
							mdbo.setSendingTime(DateHelper.currentTimeMillisCN1());
							mdbo.setMessage(message);
							mdbo.setIsreceipt(isReceipt);
							SMessageService_.doInsert(mdbo);
						}
					}
				}
			}
			// 教工信息发送
			if (StringUtils.isEmpty(tidstr)) {
				String[] tids = parentIdstr.split(";");
				if (tids != null && tids.length > 0) {
					for (String tidtmp : tids) {
						Long tid = Long.parseLong(tidtmp);
						// 获取家长的手机号码
						UTeacherDBO teacher = new UTeacherDBO();
						teacher.setTid(tid);
						teacher = (UTeacherDBO) UTeacherService_.doRead(teacher);
						if (teacher != null) {
							String phone = teacher.getPhone();
							// 短信
							if (type == 0) {
								// 发短信
								UserSMSSendServiceImpl_.send(teacher.getSid().toString(), phone, message);
							}
							// app推送
							else if (type == 1) {
								// 微信消息
							} else if (type == 2) {

							}
							// 记录到smessage表中
							SMessageDBO mdbo = new SMessageDBO();
							mdbo.setType(type);
							mdbo.setObject(0);
							mdbo.setTid(teacher.getTid());
							mdbo.setTeacherName(teacher.getTeacherName());
							mdbo.setPhone(phone);
							mdbo.setSendingTime(DateHelper.currentTimeMillisCN1());
							mdbo.setMessage(message);
							mdbo.setIsreceipt(isReceipt);
							SMessageService_.doInsert(mdbo);
						}
					}
				}
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 短信消息记录查询接口
	 * @param page
	 * @param size
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/message", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean messageGET(Integer page, Integer size,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (size == null || size == 0) {
				size = 12;
			}
			if (page == null || page == 0) {
				page = 1;
			}
			SMessageDBO dbo = new SMessageDBO();
			pageModel.config();
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(dbo);
			SMessageService_.doSelectPage(pageModel);
			JSONObject data = new JSONObject();
			data.put("page", page);
			data.put("size",size);
			data.put("count", pageModel.getResultCount());
			data.put("message", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
}
