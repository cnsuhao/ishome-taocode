package org.isotope.jfp;

import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 默认首页
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class HomeIndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView creatDataIndex() throws Exception {
		ModelAndView model = new ModelAndView("index");
		model.addObject("DDD", "欢迎来到蛋仔的世界：" + DateHelper.currentTimeMillis2());
		return model;
	}


}
