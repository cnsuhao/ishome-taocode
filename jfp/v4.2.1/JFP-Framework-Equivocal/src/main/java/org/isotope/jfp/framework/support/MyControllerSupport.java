package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.cache.session.SessionHelper;
import org.springframework.web.servlet.ModelAndView;

/**
 * 接口控制层超类<br>
 * 需要用户登录
 * 
 * @author Spook
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @since 3.2.1 2016/08/28
 *
 */
public class MyControllerSupport extends MyTokenCommonSupport {

	/**
	 * 页面返回（token自动添加）
	 * 
	 * @param viewName
	 * @return
	 */
	public ModelAndView getModelAndView(String viewName) {
		ModelAndView model = new ModelAndView(viewName);
		model.addObject("token", SessionHelper.getSessionAttribute().getToken());
		return model;
	}
}
