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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.NewsColumn.NewsColumnDBO;
import com.mcookies.qxy.common.NewsColumn.NewsColumnService;

/**
 * 校园新闻-栏目管理
 * 
 * @author linyh
 *
 */
@Controller
public class ColumnManageController extends MyControllerSupport {

	@Resource
	protected NewsColumnService newsColumnService;

	/**
	 * 新闻栏目查询接口 /column/type=[type]&token=[token]
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/column", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
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
			result.setInfo("查询失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}

	/**
	 * 新闻栏目新增接口 /column
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/column", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean columnPOST(String token, @RequestBody NewsColumnDBO newsColumn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if (StringUtils.isEmpty(newsColumn.getTitle())) {
				throw new IllegalArgumentException("栏目名字不能为空");
			}
			NewsColumnDBO condition = new NewsColumnDBO();
			condition.setTitle(newsColumn.getTitle());
			List<NewsColumnDBO> newsColumns = (List<NewsColumnDBO>) newsColumnService.doSelectData(condition);
			if (newsColumns.size() > 0) {
				result.setInfo("新增失败，重复的栏目名");
				result.setStatus(2);
				return result;
			}
			if (newsColumn.getIsUse() == null) {
				newsColumn.setIsUse(1);
			}
			if (newsColumn.getIsCheck() == null) {
				newsColumn.setIsCheck(1);
			}
			newsColumnService.doInsert(newsColumn);
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
	 * 新闻栏目修改及停用(启用)接口 /column
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/column", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean columnPUT(String token, @RequestBody NewsColumnDBO newsColumn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (newsColumn.getColumnId() == null) {
				throw new IllegalArgumentException("栏目id不能为空");
			}
			NewsColumnDBO condition = new NewsColumnDBO();
			condition.setColumnId(newsColumn.getColumnId());
			List<NewsColumnDBO> newsColumns = (List<NewsColumnDBO>) newsColumnService.doSelectData(condition);
			if (newsColumns.size() == 0) {
				result.setInfo("启用/停用/修改失败，该栏目不存在");
				result.setStatus(3);
				return result;
			}
			// 查询名称是否重复
			// 先判断是否已经改名
			if (!StringUtils.isEmpty(newsColumn.getTitle())
					&& !newsColumns.get(0).getTitle().equals(newsColumn.getTitle())) {
				// 改了名
				condition = new NewsColumnDBO();
				condition.setTitle(newsColumn.getTitle());
				newsColumns = (List<NewsColumnDBO>) newsColumnService.doSelectData(condition);
				if (newsColumns.size() > 0) {
					result.setInfo("修改失败，重复的栏目名");
					result.setStatus(2);
					return result;
				}
			}
			newsColumnService.doUpdate(newsColumn);
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
	 * 新闻栏目删除接口 /column
	 */
	@RequestMapping(value = "/column", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean columnDELETE(String token, @RequestBody NewsColumnDBO newsColumn) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			// 查询是否存在
			if (newsColumn.getColumnId() == null) {
				throw new IllegalArgumentException("栏目id不能为空");
			}
			NewsColumnDBO condition = new NewsColumnDBO();
			condition.setColumnId(newsColumn.getColumnId());
			condition = (NewsColumnDBO) newsColumnService.doRead(condition);
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
			result.setInfo("删除失败，" + e.getMessage());
			result.setStatus(1);
		}

		return result;
	}
}
