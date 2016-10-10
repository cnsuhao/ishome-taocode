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
import com.mcookies.qxy.common.STerm.STermPVO;
import com.mcookies.qxy.common.STerm.STermService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherPVO;
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
	 * 班级列表查询接口 /classlist/term=[term_id]&page=[page]&
	 * size=[size]&tid=[tid]&token=[token]
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/classlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classListGET(ClassPVO pvo, Integer page, Integer size,Long tid) {
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
			STermPVO term = new STermPVO();
			term.setTermId(pvo.getTermId());
			term.setSid(pvo.getSid());
			term = (STermPVO) sTermService.findByTermId(term);
			
			if (term == null) {
				throw new IllegalArgumentException("termId所对应的学期不存在");
			}
			if(tid != null){
				UTeacherDBO teacher= new UTeacherDBO();
				teacher.setTid(tid);
				uTeacherService.doRead(teacher);
				if(teacher == null){
					throw new IllegalArgumentException("tid所对应的教师不存在");
				}
				
				pvo.setTid(tid);
			}
			pvo.setTermId(term.getTermId());
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(pvo);
			classService.doSelectPageClass2(pageModel);

			data.put("term", term.getTermName());
			data.put("tid", pvo.getTid());
			data.put("page", pageModel.getPageCurrent());
			data.put("size", pageModel.getPageLimit());
			data.put("count", pageModel.getResultCount());
			data.put("class", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 班级列表搜索接口 /qxy/classlist?cid=[cid]&token=[token]
	 */
	@RequestMapping(value = "/classlist/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classListSearchGET(ClassPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			pvo = classService.findOneByCid(pvo);
			result.setData(pvo);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
}
