package com.mcookies.qxy.biz.app;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.News.NewsService;
import com.mcookies.qxy.common.User.UserDBO;

/**
 * 手机端-新闻展示
 * 
 * @author linyh
 *
 */
@Controller
public class NewsDisplayController extends MyControllerSupport {

	@Resource
	protected NewsService newsService;

	/**
	 * 新闻查询接口 /qxy/newlist/column=[col_id]&page=[page]&size=[size]&token=[token]
	 * 
	 * 新闻列表查询接口返回了当前的新闻列表，以时间倒序，显示指定时间内的新闻内容列表供管理员查看（注意：栏目设置为暂停的栏目下的新闻不可见，
	 * 接口关联查询要加上），需要根据token中的角色教师tid或学生家长id获取可查看新闻列表（需要到新闻表中对比新闻可读范围比对）
	 * 返回所有有效的新闻栏目，所有新闻均为已审核通过新闻； 需要使用Token验证查询者身份
	 */
//	@RequestMapping(value = "/newlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
//	@ResponseBody
//	public RESTResultBean newsListGET(@RequestParam(required = false) String token,
//			@RequestParam(required = false) Long columnId, @RequestParam(defaultValue = "1") int page,
//			@RequestParam(defaultValue = "12") int size) {
//		RESTResultBean result = new RESTResultBean();
//		try {
//			if (doCheckToken(token) == false) {
//				return tokenFail();
//			}
//			if (cid == null) {
//				throw new IllegalArgumentException("cid不能为空");
//			}
//			Map<String, Object> data = new HashMap<String, Object>();
//			ClassDBO clazz = new ClassDBO();
//			clazz.setCid(cid);
//			clazz = (ClassDBO) classService.doRead(clazz);
//			if (clazz == null) {
//				throw new IllegalArgumentException("cid所对应的班级不存在");
//			}
//			ClassTeacherDBO classTeacher = new ClassTeacherDBO();
//			classTeacher.setCid(cid);
//			UTeacherDBO leader = (UTeacherDBO) uTeacherService.findClassLeader(classTeacher);
//			ClassCourseDBO classCourse = new ClassCourseDBO();
//			classCourse.setCid(cid);
//			List<ClassCoursePVO> classCourses = (List<ClassCoursePVO>) classCourseService.doSelectData(classCourse);
//			for (ClassCoursePVO classCoursePVO : classCourses) {
//				if (leader != null && classCoursePVO.getTid1() != null 
//						&& classCoursePVO.getTid1() == leader.getTid()) {
//					classCoursePVO.setIsLeader(1);
//				} else {
//					classCoursePVO.setIsLeader(0);
//				}
//			}
//			
//			// 分页
//			String userId = getLoginer().getUserId();
//			NewsDBO paramBean = new NewsDBO();
//			paramBean.setColumnId(columnId);
//			paramBean.setIsAudit(1);
//			paramBean.setIsUse(1);
//			paramBean.setNewsReader(userId);
//			pageModel.setPageCurrent(page);
//			pageModel.setPageLimit(size);
//			pageModel.setFormParamBean(paramBean);
//			pageModel.setOrderby("publish_time desc");
//			pageModel = newsService.doSelectPage(pageModel);
//			
//			data.put("cid", cid);
//			data.put("className", clazz.getClassName());
//			data.put("leaderName", leader != null ? leader.getTeacherName() : "");
//			data.put("count", classCourses.size());
//			data.put("course", classCourses);
//			result.setData(data);
//		} catch (Exception e) {
//			result.setInfo("查询失败，" + e.getMessage());
//			result.setStatus(1);
//		}
//
//		return result;
//	}

	/**
	 * 轮播新闻查询接口 /qxy/homenews/list/token=[token]
	 */
	@RequestMapping(value = "/homenews", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean homeNewsGET(@RequestParam String token, @RequestBody UserDBO user) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}

			Long userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
}
