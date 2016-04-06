package org.isotope.jfp.search.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.framework.search.SQLService;
import org.isotope.jfp.framework.search.TableService;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 分布式网络爬虫服务端 Distributed Web Crawler
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class DataIndexController {
	@Resource
	SQLService sql;
	@Resource
	TableService table;

	@RequestMapping(value = "/DIC", method = RequestMethod.GET)
	public ModelAndView creatDataIndex(HttpServletRequest request
			, String T    //基于表创建索引名字
			, String I    //基于SQL创建索引名字
			, String from //起点
			, String size //尺寸
			, String st //开始日期
			, String et //终了日期
			) throws Exception {
		ModelAndView model = new ModelAndView("DWC/index");
		//基于表进行操作
		if(EmptyHelper.isNotEmpty(T))
			table.creatIndexByTable(T,from,size);
		//基于SQL语句操作
		else if(EmptyHelper.isNotEmpty(I)){
			sql.setStarttime(st);
			sql.setEndtime(et);
			sql.creatIndexBySQL(I,from,size);
		}
		return model;
	}


}
