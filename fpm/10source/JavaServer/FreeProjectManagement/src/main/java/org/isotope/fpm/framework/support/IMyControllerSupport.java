package org.isotope.fpm.framework.support;

import org.isotope.fpm.framework.page.bean.PageVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * RESTFul画面交互基本操作<br>
 * 定义通用8个操作方法
 * @see <IMySQLMapperSupport>
 * @since 0.1 2012-7-13
 * @version 0.1
 */
public interface IMyControllerSupport {

	/**
	 * 数据一览<br>
	 *   GET /books → index 
	 * @param paramBean
	 * @return
	 * @see IMySQLMapperSupport#findAll(MyBeanSupport)
	 */
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public ModelAndView index(MyBeanSupport paramBean) ;

	/**
	 * 分页显示<br>
	 *   GET /books/show → show 
	 * @param paramPageModel
	 * @return
	 * @see IMySQLMapperSupport#showPage(PageVO)
	 */
	@RequestMapping(value = "/show",method = RequestMethod.GET)
	public ModelAndView show(PageVO paramPageModel);

	/**
	 * 插入一条记录<br>
	 *   POST /books → create
	 * @param paramBean
	 * @see IMySQLMapperSupport#doInsert(MyBeanSupport)
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(MyBeanSupport paramBean) ;

	/**
	 * 查询记录<br> 				
  	 * GET /books/123 → view 
	 * @param paramBean
	 * @see IMySQLMapperSupport#doSelect(MyBeanSupport)
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ModelAndView view(MyBeanSupport paramBean) ;
	
	/**
	 * 更新一条记录<br>
	 *   PUT /books/123 → update 
	 * @param paramBean
	 * @see IMySQLMapperSupport#doUpdate(MyBeanSupport)
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ModelAndView update(MyBeanSupport paramBean) ;

	/**
	 * 删除一条记录<br>
	 *   DELETE /books/123 → remove 
	 * @param paramBean
	 * @see IMySQLMapperSupport#doDelete(MyBeanSupport)
	 */
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ModelAndView remove(MyBeanSupport paramBean) ;

	
	////////////////////////////////////////////////////////////////////////
	/**
	 * 批量删除记录<br>
	 *   DELETE /books/delete → delete 
	 * @param paramBean
	 * @see IMySQLMapperSupport#batchDelete(java.util.List)
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.DELETE)
	public ModelAndView delete(PageVO paramPageModel);
	/**
	 * 批量插入记录<br>
	 *   POST /books/insert → insert 
	 * @param paramBean
	 * @see IMySQLMapperSupport#batchInsert(java.util.List)
	 */
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public ModelAndView insert(PageVO paramPageModel);

}
