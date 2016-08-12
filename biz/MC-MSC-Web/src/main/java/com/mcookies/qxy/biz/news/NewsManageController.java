package com.mcookies.qxy.biz.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.mcookies.qxy.common.Class.ClassService;
import com.mcookies.qxy.common.News.NewsDBO;
import com.mcookies.qxy.common.News.NewsPVO;
import com.mcookies.qxy.common.News.NewsService;
import com.mcookies.qxy.common.NewsColumn.NewsColumnDBO;
import com.mcookies.qxy.common.NewsColumn.NewsColumnService;
import com.mcookies.qxy.common.UTeacher.UTeacherDBO;
import com.mcookies.qxy.common.UTeacher.UTeacherService;
import com.mcookies.qxy.common.User.UserDBO;

/**
 * 校园新闻-新闻管理
 * 
 * @author linyh
 *
 */
@Controller
public class NewsManageController extends MyControllerSupport {

	@Resource
	protected NewsService newsService;
	@Resource
	protected NewsColumnService newsColumnService;
	@Resource
	protected UTeacherService uTeacherService;
	@Resource
	protected ClassService classService;

	/**
	 * 新闻列表查询接口
	 * /qxy/news?columnId=[colId]&time=[startTime|endTime]&type=[type]&tid=[tid]
	 * &page=[page]&size=[size]&token=[token]
	 */
	@RequestMapping(value = "/news", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsGET(NewsPVO pvo, Integer type, Long tid, Integer page, Integer size) {
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
			if (type == null) {
				type = 0;
			}
			if (type == 1) {
				pvo.setIsAudit(1);
			} else if (type == 2) {
				pvo.setIsAudit(2);
			} else if (type == 3) {
				pvo.setIsAudit(0);
			}
			if (tid != null) {
				pvo.setAuthor(tid);
			}
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(size);
			pageModel.setFormParamBean(pvo);
			newsService.doSelectPageByColumnIdAndType(pageModel);
			
			NewsDBO n = new NewsDBO();
			n.setColumnId(pvo.getColumnId());
			data.put("allnews", newsService.doSelectData(n).size());
			n.setIsAudit(1);
			data.put("publishnews", newsService.doSelectData(n).size());
			data.put("columnName", condition.getTitle());
			data.put("startTime", pvo.getStartTime());
			data.put("endTime", pvo.getEndTime());
			data.put("type", type);
			data.put("page", pageModel.getPageCurrent());
			data.put("size", pageModel.getPageLimit());
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
	 * 新闻详情查看接口 /news/detaile/news=[new_id]&token=[token]
	 */
	@RequestMapping(value = "/news/detaile", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsDetailGET(NewsPVO news) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(news.getToken()) == false) {
				return tokenFail();
			}
			if (news.getNewsId() == null) {
				throw new IllegalArgumentException("newsId不能为空");
			}
			news = (NewsPVO) newsService.findDetailByNewsId(news);
			if (news == null) {
				throw new IllegalArgumentException("newsId对应的新闻不存在");
			}
			//
			news.setNewsReaders((List<UTeacherDBO>) uTeacherService.findNewsReaders(news));
			news.setNewsClassers((List<ClassDBO>) classService.findNewsClassers(news));
			result.setData(news);
		} catch (Exception e) {
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 新闻新增接口 /news
	 */
	@RequestMapping(value = "/news", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsPOST(@RequestBody NewsDBO news) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(news.getToken()) == false) {
				return tokenFail();
			}
			if (StringUtils.isEmpty(news.getTitle())) {
				throw new IllegalArgumentException("新闻标题不能为空");
			}
			NewsColumnDBO newsColumn = new NewsColumnDBO();
			newsColumn.setColumnId(news.getColumnId());
			newsColumn = (NewsColumnDBO) newsColumnService.doRead(newsColumn);
			if (newsColumn == null) {
				throw new IllegalArgumentException("columnId所对应的栏目不存在");
			}
			news.setIsAudit(0);
			newsService.doInsert(news);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("新增失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 新闻修改\置顶(取消)\审核(驳回)\加入轮播(取消)口 /news
	 */
	@RequestMapping(value = "/news", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsPUT(@RequestBody NewsDBO news) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(news.getToken()) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (news.getNewsId() == null) {
				throw new IllegalArgumentException("新闻id不能为空");
			}
			NewsDBO origin = new NewsDBO();
			origin.setNewsId(news.getNewsId());
			origin = (NewsDBO) newsService.doRead(origin);
			if (origin == null) {
				throw new IllegalArgumentException("新闻不存在");
			}
			// 由所属栏目判断是否需要审核
			NewsColumnDBO newsColumn = new NewsColumnDBO();
			newsColumn.setColumnId(news.getColumnId());
			newsColumn = (NewsColumnDBO) newsColumnService.doRead(newsColumn);
			if (newsColumn == null) {
				throw new IllegalArgumentException("columnId所对应的栏目不存在");
			}
			if (newsColumn.getIsCheck() == null || newsColumn.getIsCheck() == 0) {
				throw new IllegalStateException("该新闻不允许修改");
			}
			newsService.doUpdate(news);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("修改失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 新闻删除接口 /news
	 */
	@RequestMapping(value = "/news", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();

		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray newsIds = param.getJSONArray("newsIds");
			for (Object newsId : newsIds) {
				Long tmp = Long.valueOf(newsId.toString());
				// 乐观锁操作
				NewsDBO dbo = new NewsDBO();
				dbo.setNewsId(tmp);
				dbo = (NewsDBO) newsService.doRead(dbo);
				@SuppressWarnings("unused")
				int flag = newsService.doDelete(dbo);
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 幻灯图片上传接口（暂缺） /news/upload/pictures
	 */
	@RequestMapping(value = "/news/upload/pictures", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsUploadPicturesPUT(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
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
