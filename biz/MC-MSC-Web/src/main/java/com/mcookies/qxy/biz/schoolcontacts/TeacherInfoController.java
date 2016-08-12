package com.mcookies.qxy.biz.schoolcontacts;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.security.value.MD5SecurityHelper;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherService;
import com.mcookies.qxy.common.SLabel.SLabelDBO;
import com.mcookies.qxy.common.SLabel.SLabelService;
import com.mcookies.qxy.common.SLabelTeacher.SLabelTeacherDBO;
import com.mcookies.qxy.common.SLabelTeacher.SLabelTeacherService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherPVO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;
import com.mcookies.qxy.common.UTeacherExt.UTeacherExtDBO;
import com.mcookies.qxy.common.UTeacherExt.UTeacherExtService;
import com.mcookies.qxy.common.User.UserDBO;
import com.mcookies.qxy.common.User.UserService;

/**
 * 教工信息
 * 
 * @author macBookTang
 *
 */
@Controller
@Transactional
public class TeacherInfoController extends MyControllerSupport {
	@Resource
	protected UserService UserService_;
	@Resource
	protected UTeacherService UTeacherService_;
	@Resource
	protected UTeacherExtService UTeacherExtService_;
	@Resource
	protected SLabelService SLabelService_;
	@Resource
	protected SLabelTeacherService SLabelTeacherService_;

	@Resource
	protected ClassTeacherService ClassTeacherService_;

	/**
	 * 教工信息查询接口
	 * 
	 * @param tid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/teacher/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean teacherInfoGET(Long tid, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 教师基本信息
			UTeacherDBO teacher = new UTeacherDBO();
			teacher.setTid(tid);
			teacher = (UTeacherDBO) UTeacherService_.doRead(teacher);
			// 扩展信息
			UTeacherExtDBO ext = new UTeacherExtDBO();
			ext.setTid(tid);
			ext = (UTeacherExtDBO) UTeacherExtService_.doRead(ext);
			// 教工标签信息
			UTeacherDBO ut = new UTeacherDBO();
			ut.setTid(tid);
			List<SLabelDBO> labellist = (List<SLabelDBO>) SLabelService_.doselectTeacherLabel(ut);
			JSONObject data = new JSONObject();
			data.put("tid", tid);
			data.put("teacherName", teacher.getTeacherName());
			data.put("gender", teacher.getGender());
			data.put("dateOfBirth", ext.getDateOfBirth());
			data.put("startWorkT", ext.getStartWorkT());
			data.put("jobNumber", ext.getJobNumber());
			data.put("cardType", ext.getCardType());
			data.put("cardNumber", ext.getCardNumber());
			data.put("homeAddress", ext.getHomeAddress());
			data.put("phone", teacher.getPhone());
			data.put("email", teacher.getEmail());
			data.put("isUse", teacher.getIsUse());
			// 标签组
			JSONArray labeljson = new JSONArray();
			if (labellist != null) {
				for (SLabelDBO tmp : labellist) {
					JSONObject jt = new JSONObject();
					jt.put("labelId", tmp.getLabelId());
					jt.put("labelName", tmp.getLabelName());
					labeljson.add(jt);
				}
			}
			data.put("labellist", labeljson);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 教工信息新增接口
	 * 
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/teacher/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean teacherInfoPOST(@RequestBody UTeacherPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			Long uid = null;
			Long tid = null;
			//检查手机号码、邮箱是否被使用
			UTeacherDBO u1 = new UTeacherDBO();
			u1.setPhone(pvo.getPhone());
			List<UTeacherDBO> list1 =(List<UTeacherDBO>)UTeacherService_.doSelectData(u1);
			if(list1!=null&&list1.size()>0){
				result.setInfo("手机号或邮箱已经存在");
				result.setStatus(2);
				return result;
			}
			UTeacherDBO u2 = new UTeacherDBO();
			u2.setEmail(pvo.getEmail());
			List<UTeacherDBO> list2 =(List<UTeacherDBO>)UTeacherService_.doSelectData(u2);
			if(list2!=null&&list2.size()>0){
				result.setInfo("手机号或邮箱已经存在");
				result.setStatus(2);
				return result;
			}
			// 匹配用户表
			UserDBO userp = new UserDBO();
			userp.setPhone(pvo.getPhone());
			List<UserDBO> plist = (List<UserDBO>) UserService_.doSelectData(userp);

			UserDBO usere = new UserDBO();
			usere.setEmail(pvo.getEmail());
			List<UserDBO> elist = (List<UserDBO>) UserService_.doSelectData(usere);
			// 存在则直接获取uid
			if (plist != null && plist.size() > 0) {
				uid = plist.get(0).getUid();
			} else if (elist != null && elist.size() > 0) {
				uid = elist.get(0).getUid();
			} else {
				// 新建
				UserDBO user = new UserDBO();
				user.setAccount(pvo.getPhone());
				user.setEmail(pvo.getEmail());
				user.setEmailStatus(1);
				user.setPhone(pvo.getPhone());
				user.setPassword(MD5SecurityHelper.encrypt("qxy123456"));
				user.setStatus(1);
				UserService_.doInsert(user);
				uid = user.getUid();
			}
			// 插入教师表
			UTeacherDBO tearcher = new UTeacherDBO();
			tearcher.setTeacherName(pvo.getTeacherName());
			tearcher.setUid(uid);
			tearcher.setPhone(pvo.getPhone());
			tearcher.setEmail(pvo.getEmail());
			tearcher.setGender(pvo.getGender());
			tearcher.setIdType(pvo.getIdType());
			tearcher.setStatus(1);
			tearcher.setIsUse(1);
			UTeacherService_.doInsert(tearcher);
			tid = tearcher.getTid();
			// 插入扩展表
			UTeacherExtDBO ext = new UTeacherExtDBO();
			ext.setTid(tid);
			ext.setGender(pvo.getGender());
			ext.setDateOfBirth(pvo.getDateOfBirth());
			ext.setStartWorkT(pvo.getStartWorkT());
			ext.setJobNumber(pvo.getJobNumber());
			ext.setCardType(pvo.getCardType());
			ext.setCardNumber(pvo.getCardNumber());
			ext.setHomeAddress(pvo.getHomeAddress());
			ext.setIsUse(1);
			UTeacherExtService_.doInsert(ext);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 教工信息修改\教工身份停用(启用)接口
	 * 
	 * @param jsonparam
	 * @return
	 */
	@RequestMapping(value = "/teacher/info", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean teacherInfoPUT(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray teacherinfo = param.getJSONArray("teacherinfo");
			if (teacherinfo != null && teacherinfo.size() > 0) {
				for (Object t : teacherinfo) {
					UTeacherPVO pvo = JSONObject.toJavaObject((JSONObject) t, UTeacherPVO.class);
					// 验证手机号是否已经存在
					if (pvo.getPhone() != null && !"".equals(pvo.getPhone())) {
						UTeacherDBO t1 = new UTeacherDBO();
						t1.setPhone(pvo.getPhone());
						List<UTeacherDBO> tlist1 = (List<UTeacherDBO>) UTeacherService_.doSelectData(t1);
						if (tlist1 != null && tlist1.size() > 0) {
							for (UTeacherDBO tmp : tlist1) {
								if (tmp.getTid() != pvo.getTid()) {
									result.setInfo("修改失败，邮箱或手机号已经存在");
									result.setStatus(3);
									return result;
								}
							}
						}
					}
					// 验证邮箱是否已经存在
					if (pvo.getEmail() != null && !"".equals(pvo.getEmail())) {
						UTeacherDBO t2 = new UTeacherDBO();
						t2.setEmail(pvo.getEmail());
						List<UTeacherDBO> tlist2 = (List<UTeacherDBO>) UTeacherService_.doSelectData(t2);
						if (tlist2 != null && tlist2.size() > 0) {
							for (UTeacherDBO tmp : tlist2) {
								if (tmp.getTid() != pvo.getTid()) {
									result.setInfo("修改失败，邮箱或手机号已经存在");
									result.setStatus(3);
									return result;
								}
							}
						}
					}
					UTeacherService_.doUpdate(pvo);
					UTeacherExtDBO ext = new UTeacherExtDBO();
					ext.setTid(pvo.getTid());
					ext.setJobNumber(pvo.getJobNumber());
					ext.setGender(pvo.getGender());
					ext.setDateOfBirth(pvo.getDateOfBirth());
					ext.setStartWorkT(pvo.getStartWorkT());
					ext.setCardType(pvo.getCardType());
					ext.setCardNumber(pvo.getCardNumber());
					ext.setHomeAddress(pvo.getHomeAddress());
					UTeacherExtService_.doUpdate(ext);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 教工信息删除接口
	 * 
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/teacher", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean teacherDELETE(@RequestBody UTeacherPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			Long tid = pvo.getTid();
			// 检查是否存在标签关联
			SLabelTeacherDBO slabel = new SLabelTeacherDBO();
			slabel.setTid(tid);
			List<SLabelTeacherDBO> t1 = (List<SLabelTeacherDBO>) SLabelTeacherService_.doSelectData(slabel);
			if (t1 != null && t1.size() > 0) {
				result.setInfo("删除失败，该教工已经在其他地方使用（包括班级，包括教工标签等关联表中）");
				result.setStatus(3);
				return result;
			}
			// 检查是否存在班级关联
			ClassTeacherDBO ct = new ClassTeacherDBO();
			ct.setTid(tid);
			List<ClassTeacherDBO> ctlist = (List<ClassTeacherDBO>) ClassTeacherService_.doSelectData(ct);
			if (ctlist != null && ctlist.size() > 0) {
				result.setInfo("删除失败，该教工已经在其他地方使用（包括班级，包括教工标签等关联表中）");
				result.setStatus(3);
				return result;
			}

			UTeacherDBO teacher = new UTeacherDBO();
			teacher.setTid(tid);
			UTeacherService_.doDelete(teacher);
			UTeacherExtDBO teacherext = new UTeacherExtDBO();
			teacherext.setTid(tid);
			UTeacherExtService_.doDelete(teacherext);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
}
