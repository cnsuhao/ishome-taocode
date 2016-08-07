package com.mcookies.qxy.biz.classmanage;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.Class.ClassPVO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.STerm.STermDBO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;

/**
 * 班级管理-学生管理
 * 
 * @author linyh
 *
 */
@Controller
public class ClassManageController extends MyControllerSupport {

	@Resource
	protected ClassService classService;
	@Resource
	protected STermService sTermService;
	@Resource
	protected UTeacherService uTeacherService;
	
	/**
	 * 班级列表查询接口
	 * /qxy/classlist/term=[term_id]&grade=[grade_id]&class=[cid]&page=[page]&
	 * size=[size]&teacher=[tid]&token=[token]
	 */
	@RequestMapping(value = "/qxy/classlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classListGET(ClassPVO pvo, Integer page, Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			Map<String, Object> data = new HashMap<String, Object>();
			if (page == null || page == 0) {
				page = 1;
			}
			if (size == null || size == 0) {
				size = 12;
			}
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(pvo);
			// pageModel.setOrderby("publish_time desc");
			classService.doSelectPageClass2(pageModel);
			
			STermDBO term = new STermDBO();
			term.setTermId(pvo.getTermId());
			term = (STermDBO) sTermService.findByTermId(term);
			if (term == null) {
				throw new IllegalArgumentException("termId所对应的学期不存在");
			}
			for (FrameworkDataBean each: pageModel.getPageListData()) {
				ClassPVO tmp = (ClassPVO) each;
				ClassTeacherDBO tmpTeacher = new ClassTeacherDBO();
				tmpTeacher.setCid(tmp.getCid());
				UTeacherDBO leader = uTeacherService.findClassLeader(tmpTeacher);
				((ClassPVO) each).setLeaderName(leader != null ? leader.getTeacherName() : "");
			}
			data.put("term", term.getTermName());
			data.put("tid", pvo.getTid());
			data.put("page", pageModel.getPageCurrent());
			data.put("size", pageModel.getPageLimit());
			data.put("count", pageModel.getPageCount());
			data.put("class", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
}
