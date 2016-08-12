package com.mcookies.qxy.biz.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.News.NewsDBO;
import com.mcookies.qxy.common.News.NewsPVO;
import com.mcookies.qxy.common.News.NewsService;
import com.mcookies.qxy.common.NewsColumn.NewsColumnDBO;
import com.mcookies.qxy.common.NewsColumn.NewsColumnService;
import com.mcookies.qxy.common.UParent.UParentDBO;
import com.mcookies.qxy.common.UParent.UParentService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;
import com.mcookies.qxy.common.User.UserService;

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
	@Resource
	protected NewsColumnService newsColumnService;
	@Resource
	protected UserService userService;
	@Resource
	protected UTeacherService uTeacherService;
	@Resource
	protected UParentService uParentService;

	/**
	 * 新闻查询接口 /qxy/newlist/column=[col_id]&page=[page]&size=[size]&token=[token]
	 * 
	 * 新闻列表查询接口返回了当前的新闻列表，以时间倒序，显示指定时间内的新闻内容列表供管理员查看（注意：栏目设置为暂停的栏目下的新闻不可见，
	 * 接口关联查询要加上），需要根据token中的角色教师tid或学生家长id获取可查看新闻列表（需要到新闻表中对比新闻可读范围比对）
	 * 返回所有有效的新闻栏目，所有新闻均为已审核通过新闻； 需要使用Token验证查询者身份
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/newlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsListGET(NewsPVO pvo, Integer page, Integer size) {
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
			// 查询是否存在
			if (pvo.getColumnId() == null) {
				throw new IllegalArgumentException("栏目id不能为空");
			}
			NewsColumnDBO condition = new NewsColumnDBO();
			condition.setColumnId(pvo.getColumnId());
			condition = (NewsColumnDBO) newsColumnService.doRead(condition);
			if (condition == null) {
				throw new IllegalArgumentException("该栏目不存在");
			}
			Long userId = getLoginer().getUserId();
			if (userId == null) {
				throw new IllegalStateException("获取用户id失败");
			}
			UTeacherDBO teacher = new UTeacherDBO();
			teacher.setUid(userId);
			List<UTeacherDBO> teachers = (List<UTeacherDBO>) uTeacherService.doSelectData(teacher);
			if (teachers.size() > 0) {
				pvo.setTid(teachers.get(0).getTid());
			} else {
				UParentDBO parent = new UParentDBO();
				parent.setUid(userId);
				List<UParentDBO> parents = (List<UParentDBO>) uParentService.doSelectData(parent);
				if (parents.size() > 0) {
					pvo.setParentId(parents.get(0).getParentId());
				} else {
					data.put("page", page);
					data.put("size", size);
					data.put("count", 0);
					data.put("newslist", new ArrayList<NewsDBO>());
					result.setData(data);
				}
			}
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(pvo);
			data.put("page", pageModel.getPageCurrent());
			data.put("size", pageModel.getPageLimit());
			if (pvo.getTid() != null) {
				newsService.doSelectPageByColumnIdAndTid(pageModel);
				result.setInfo("用户为教师");
			} else if (pvo.getParentId() != null){
				newsService.doSelectPageByColumnIdAndParentId(pageModel);
				result.setInfo("用户为学生家长");
			} else {
				data.put("count", 0);
				data.put("newslist", new ArrayList<NewsPVO>());
				result.setData(data);
				result.setInfo("用户既不是教师，也不是学生家长");
				return result;
			}
			
			data.put("count", pageModel.getResultCount());
			data.put("newslist", pageModel.getPageListData());
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 轮播新闻查询接口 /qxy/homenews/list/token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/homenews/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean homeNewsGET(NewsDBO dbo) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(dbo.getToken()) == false) {
				return tokenFail();
			}
			dbo.setIsHomenews(1);
			dbo.setIsAudit(1);
			List<NewsDBO> news = (List<NewsDBO>) newsService.doSelectData(dbo);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("count", news.size());
			data.put("homenews", news);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
}
