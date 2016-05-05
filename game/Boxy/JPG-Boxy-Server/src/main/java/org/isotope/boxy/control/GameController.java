package org.isotope.boxy.control;

import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 唯一游戏入口
 * @author ISHome
 * @version 0.0.1
 * @since 3.1.2 2016/4/25
 */
@Controller
public class GameController {

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView("index");
		model.addObject("DDD", "欢迎来到蛋仔的世界：" + DateHelper.currentTimeMillis2());
		return model;
	}
}
