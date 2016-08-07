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
import com.mcookies.qxy.common.News.NewsDBO;
import com.mcookies.qxy.common.News.NewsService;
import com.mcookies.qxy.common.NewsColumn.NewsColumnDBO;
import com.mcookies.qxy.common.NewsColumn.NewsColumnService;
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

	/**
	 * 新闻列表查询接口
	 * /qxy/news/column=[col_id]&time=[start_time|end_time]&type=[type]&teacher=
	 * [tid]&page=[page]&size=[size]&token=[token]
	 */
	@RequestMapping(value = "/qxy/news", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsGET(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			String userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 新闻详情查看接口 /qxy/news/detaile/news=[new_id]&token=[token]
	 */
	@RequestMapping(value = "/qxy/news/detaile", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsDetailGET(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			String userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 新闻新增接口 /qxy/news
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qxy/news", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
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
			List<NewsColumnDBO> newsColumns = (List<NewsColumnDBO>) newsColumnService.doSelectData(newsColumn);
			if (newsColumns.size() == 0) {
				throw new IllegalArgumentException("columnId所对应的栏目不存在");
			}
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
	 * 新闻修改\置顶(取消)\审核(驳回)\加入轮播(取消)口 /qxy/news
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qxy/news", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsPUT(@RequestBody NewsDBO news) {
		// TODO: test
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(news.getToken()) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (news.getNewsId() == null) {
				throw new IllegalArgumentException("新闻id不能为空");
			}
			// 由所属栏目判断是否需要审核
			NewsColumnDBO newsColumn = new NewsColumnDBO();
			newsColumn.setColumnId(news.getColumnId());
			List<NewsColumnDBO> newsColumns = (List<NewsColumnDBO>) newsColumnService.doSelectData(newsColumn);
			if (newsColumns.size() == 0) {
				throw new IllegalArgumentException("columnId所对应的栏目不存在");
			}
			newsColumn = newsColumns.get(0);
			if (newsColumn.getIsCheck() == 0) {
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
	 * 新闻删除接口 /qxy/news
	 */
	@RequestMapping(value = "/qxy/news", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
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
	 * 幻灯图片上传接口（暂缺） /qxy/news/upload/pictures
	 */
	@RequestMapping(value = "/qxy/news/upload/pictures", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean newsUploadPicturesPUT(@RequestBody UserDBO user) {
		// TODO
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			String userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}
}
