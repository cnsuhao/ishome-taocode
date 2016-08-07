package com.mcookies.qxy.biz.system;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.Class.ClassDBO;
import com.mcookies.qxy.common.Class.ClassPVO;
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherPVO;
import com.mcookies.qxy.common.ClassTeacher.ClassTeacherService;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelDBO;
import com.mcookies.qxy.common.SGradeLabel.SGradeLabelService;
import com.mcookies.qxy.common.STerm.STermDBO;
import com.mcookies.qxy.common.STerm.STermService;
/**
 * 班级设定
 * @author macBookTang
 *
 */
@Controller
public class ClassSettingController extends MyControllerSupport {
	@Resource
	protected STermService STermService_;
	@Resource
	protected SGradeLabelService SGradeLabelService_;
	@Resource
	protected ClassService ClassService_;
	@Resource
	protected ClassTeacherService ClassTeacherService_;
	
	/**
	 * 学校学期年级班级查询接口（通用）
	 * @param type
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/termgradeclass", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean termgradeclassGET(String type,String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			Integer isuse = null;
			if(type==null||"".equals(type)||"0".equals(type)){
				isuse = 1;
			}else{
				isuse = null;
			}
			JSONObject data = new JSONObject();
			//获得学期列表
			STermDBO stermtmp = new STermDBO();
			stermtmp.setIsUse(isuse);
			stermtmp.setSid(schoolId);
			List<STermDBO> terms = (List<STermDBO>)STermService_.doSelectData(stermtmp);
			data.put("count", terms.size());
			JSONArray termsjson = new JSONArray();
			if(terms!=null){
				for(STermDBO tmp:terms){
					JSONObject termjson = new JSONObject();
					termjson.put("termId", tmp.getTermId());
					termjson.put("termName", tmp.getTermName());
					termjson.put("isDefault", tmp.getIsDefault());
					termjson.put("isUse", tmp.getIsUse());
					//获取该学期下的班级
					ClassDBO cdbo = new ClassDBO();
					cdbo.setTermId(tmp.getTermId());
					cdbo.setSid(schoolId);
					List<ClassDBO> classlist = (List<ClassDBO>)ClassService_.doSelectData(cdbo);
					List<Long> gradeIds = new LinkedList<Long>();
					if (classlist != null && classlist.size()>0) {
						for (ClassDBO temp : classlist) {
							if (!gradeIds.contains(temp.getGradeId())) {
								gradeIds.add(temp.getGradeId());
							}
						}
						//循环获取年级
						JSONArray gradelsjson = new JSONArray();
						for(Long gradeid:gradeIds){
							SGradeLabelDBO sgradtmp = new SGradeLabelDBO();
							sgradtmp.setGradeId(gradeid);
							sgradtmp = (SGradeLabelDBO) SGradeLabelService_.doRead(sgradtmp);
							JSONObject gjson = new JSONObject();
							gjson.put("grade_id", sgradtmp.getGradeId());
							gjson.put("grade_name", sgradtmp.getGradeName());
							gjson.put("is_use", sgradtmp.getIsUse());
							//获取该年级下的所有班级
							ClassDBO ctmp = new ClassDBO();
							ctmp.setGradeId(gradeid);
							ctmp.setTermId(tmp.getTermId());
							List<ClassDBO> clisttmp = (List<ClassDBO>)ClassService_.doSelectData(ctmp);
							JSONArray classjson = new JSONArray();
							for(ClassDBO tmp1:clisttmp){
								JSONObject jtmp = new JSONObject();
								jtmp.put("cid", tmp1.getCid());
								jtmp.put("className", tmp1.getClassName());
								classjson.add(jtmp);
							}
							gjson.put("class", classjson);
							gradelsjson.add(gjson);
						}
						termjson.put("grade", gradelsjson);
					}
					termsjson.add(termjson);
				}
			}
			data.put("term", termsjson);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 学校班级查询接口
	 * @param cpvo
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/class/term", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classSermGET(ClassPVO cpvo,Integer page,Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(cpvo.getToken()) == false) {
				return tokenFail();
			}
			// 获取当前学校ID
			Long schoolId = getLoginer().getSchoolId();
			//获取编辑列表
			if(size==null||size==0){
				size = 12;
			}
			if(page==null||page==0){
				page = 1;
			}
			cpvo.setSid(schoolId);
			
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(cpvo);
			ClassService_.doSelectPageClass(pageModel);
			List<ClassPVO> clist = (List<ClassPVO>)pageModel.getPageListData();
			JSONObject json = new JSONObject();
			json.put("page",page);
			json.put("size",size);
			json.put("count", pageModel.getResultCount());
			json.put("class", clist);
			result.setData(json);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	
	/**
	 * 学校班级新增接口
	 * @param cpvo
	 * @return
	 */
	@RequestMapping(value = "/class", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classPOST(@RequestBody ClassPVO cpvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(cpvo.getToken()) == false) {
				return tokenFail();
			}
			//根据配置的数量，循环新建班级
			Integer count = cpvo.getCount();
			//获取现有的班级数
			ClassDBO dbo = new ClassDBO();
			dbo.setGradeId(cpvo.getGradeId());
			dbo.setTermId(cpvo.getTermId());
			List<ClassPVO> clist =(List<ClassPVO>)ClassService_.doSelectData(dbo);
			int cnum = 0;
			if(clist!=null){
				cnum = clist.size();
			}
			String cname = "班";
			for(int i=0;i<count;i++){
				while(true){
					cnum++;
					String tmpname = cnum+cname;
					//判断该班级是否已经存在
					ClassDBO tmpdbo = new ClassDBO();
					tmpdbo.setGradeId(cpvo.getGradeId());
					tmpdbo.setTermId(cpvo.getTermId());
					tmpdbo.setClassName(tmpname);
					tmpdbo.setYear(cpvo.getYear());
					List<ClassPVO> rlist = (List<ClassPVO>)ClassService_.doSelectData(tmpdbo);
					if(rlist.size()==0){
						//添加此班级
						ClassService_.doInsert(tmpdbo);
						break;
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
	 * 学校班级修改接口
	 * @param cpvo
	 * @return
	 */
	@RequestMapping(value = "/class", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classPUT(@RequestBody ClassPVO cpvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(cpvo.getToken()) == false) {
				return tokenFail();
			}
			//获取班级的完整信息
			ClassDBO classinfo = (ClassDBO)ClassService_.doRead(cpvo);
			if(classinfo==null){
				result.setInfo("该班级不存在");
				result.setStatus(2);
				return result;
			}else{
				//查询 修改后的 班级名称是否已经存在
				ClassDBO tmp = new ClassDBO();
				tmp.setTermId(classinfo.getTermId());
				tmp.setGradeId(cpvo.getGradeId());
				tmp.setClassName(cpvo.getClassName());
				List<ClassDBO> rlist =(List<ClassDBO>)ClassService_.doSelectData(tmp);
				if(rlist.size()>0){
					result.setInfo("修改班级失败，班级已经存在");
					result.setStatus(2);
				}else{
					//编辑
					ClassService_.doUpdate(cpvo);
				}
			}
			
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	/**
	 * 学校班级删除接口
	 * @param cpvo
	 * @return
	 */
	@RequestMapping(value = "/class", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classDELETE(@RequestBody ClassPVO cpvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(cpvo.getToken()) == false) {
				return tokenFail();
			}
			ClassService_.doDelete(cpvo);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	/**
	 * 学校班级班主任指定接口
	 * @param pvo
	 * @return
	 */
	@RequestMapping(value = "/class/leader", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean classLeaderPUT(@RequestBody ClassTeacherPVO pvo) {
		RESTResultBean result = new RESTResultBean();
		try {
			//token校验
			if (doCheckToken(pvo.getToken()) == false) {
				return tokenFail();
			}
			//班主任标准清除
			ClassTeacherDBO dbo = new ClassTeacherDBO();
			dbo.setCid(pvo.getCid());
			dbo.setIsLeader(0);
			ClassTeacherService_.doUpdateAll(dbo);
			//设置新的版主人
			ClassTeacherService_.doUpdateAll(pvo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
}
