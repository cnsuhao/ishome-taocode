package com.mcookies.qxy.biz.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.NewsColumn.NewsColumnDBO;
import com.mcookies.qxy.common.NewsColumn.NewsColumnService;

/**
 * 校园新闻-栏目管理
 * @author linyh
 *
 */
@Controller
public class ColumnManageController extends MyControllerSupport {

	@Resource
	protected NewsColumnService newsColumnService;

	/**
	 * 新闻栏目查询接口 /qxy/column/type=[type]&token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qxy/column", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean columnGET(@RequestParam(required = false) String token,
			@RequestParam(defaultValue = "1") Integer type) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			NewsColumnDBO newsColumn = new NewsColumnDBO();
			if (type == 0) {
				newsColumn.setIsUse(1);
			}
			List<NewsColumnDBO> newsColumns = (List<NewsColumnDBO>) newsColumnService.doSelectData(newsColumn);
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("count", newsColumns.size());
			data.put("column", newsColumns);
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("查询失败: " + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 新闻栏目新增接口 /qxy/column
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qxy/column", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean columnPOST(@RequestBody NewsColumnDBO newsColumn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(newsColumn.getToken()) == false) {
				return tokenFail();
			}
			NewsColumnDBO condition = new NewsColumnDBO();
			condition.setTitle(newsColumn.getTitle());
			List<NewsColumnDBO> newsColumns = (List<NewsColumnDBO>) newsColumnService.doSelectData(condition);
			if (newsColumns.size() > 0) {
				result.setInfo("新增失败，重复的栏目名");
				result.setStatus(2);
				return result;
			}
			Long sid = Long.valueOf(getToken().getSchoolId());
			newsColumn.setSid(sid);
			newsColumnService.doInsert(newsColumn);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("新增失败: " + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 新闻栏目修改及停用(启用)接口 /qxy/column
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qxy/column", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean columnPUT(@RequestBody NewsColumnDBO newsColumn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(newsColumn.getToken()) == false) {
				return tokenFail();
			}
			// 查询是否存在
			NewsColumnDBO condition = new NewsColumnDBO();
			condition.setColumnId(newsColumn.getColumnId());
			List<NewsColumnDBO> newsColumns = (List<NewsColumnDBO>) newsColumnService.doSelectData(condition);
			if (newsColumns.size() == 0) {
				result.setInfo("启用/停用/修改失败，该栏目不存在");
				result.setStatus(3);
				return result;
			}
			// 查询名称是否重复（未去除本身）
			condition = new NewsColumnDBO();
			condition.setTitle(newsColumn.getTitle());
			newsColumns = (List<NewsColumnDBO>) newsColumnService.doSelectData(condition);
			if (newsColumns.size() > 0) {
				result.setInfo("修改失败，重复的栏目名");
				result.setStatus(2);
				return result;
			}
			newsColumnService.doUpdate(newsColumn);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("修改失败: " + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 新闻栏目删除接口 /qxy/column
	 */
	@RequestMapping(value = "/qxy/column", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean columnDELETE(@RequestBody NewsColumnDBO newsColumn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(newsColumn.getToken()) == false) {
				return tokenFail();
			}
			NewsColumnDBO condition = new NewsColumnDBO();
			condition.setColumnId(newsColumn.getColumnId());
			int flag = newsColumnService.doDelete(condition);
			if (flag == 0) {
				result.setInfo("删除失败，该栏目不存在");
				result.setStatus(2);
				return result;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("删除失败: " + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
}
