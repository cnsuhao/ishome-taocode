package org.isotope.boxy;

import java.util.HashMap;

import javax.annotation.Resource;

import org.isotope.boxy.common.AGameBussinessService;
import org.isotope.boxy.entity.CommonEntity.CommonEntityService;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.token.TokenBusinessBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.cache.session.SessionHelper;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.PKHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * 默认首页
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class HomeIndexController implements ISFrameworkConstants {

	@Resource
	ICacheService myCacheService;

	@Resource
	CommonEntityService CommonEntityService_;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() throws Exception {	
		HashMap<String, String> param = new HashMap<String, String>();

        param.put("p_user_name", "zhangsan");
        CommonEntityService_.doCall(param);
		System.out.println(param);
		
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("DDD", "欢迎来到蛋仔的世界：" + DateHelper.currentTimeMillis2());
		model.addObject("userID", PKHelper.creatBarCodeKey());
		
		return model;
	}
	
	@RequestMapping(value = "/{clientTimestamp}", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean doProcess3(@PathVariable String  clientTimestamp, String  roleId,  String serverId,
			 String  bizId, String  encryType,String paramValue) throws Exception {
		TokenBusinessBean btb = new TokenBusinessBean(serverId, bizId, encryType, roleId,clientTimestamp);
		return doprocess(btb,paramValue);
	}

	/**
	 * 
	 * @param token
	 * @param paramValue json string
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{token}/{bizId}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doProcess(@PathVariable String token, @PathVariable String  bizId, String paramValue) throws Exception {
		TokenBusinessBean btb = TokenBusinessBean.build(token);
		btb.setBizId(bizId);
		return doprocess(btb,paramValue);
	}
	
	/**
	 * 接口请求
	 * @param roleId 玩家角色ID
	 * @param serverId 业务模块ID(类标识)
	 * @param bizId    功能识别ID
	 * @param encryType 加密标识
	 * @param paramValue 具体参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{roleId}/{serverId}/{bizId}/{encryType}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doProcess2(
			@PathVariable String  roleId, @PathVariable String serverId,
			@PathVariable String  bizId,@PathVariable String  encryType,String paramValue) throws Exception {
		TokenBusinessBean btb = new TokenBusinessBean(serverId, bizId, encryType, roleId);
		return doprocess(btb,paramValue);
	}
	
	private RESTResultBean doprocess(TokenBusinessBean btb,String paramValue){
		//加载测试用户登录数据
		SessionHelper.loadTestSession();
		RESTResultBean result;
		try {
			AGameBussinessService asgbs = BeanFactoryHelper.getBean(btb.getServerId());
			asgbs.setMyCacheService(myCacheService);
			asgbs.setParamValue(paramValue);
			asgbs.setTokenBean(btb);
			boolean process = asgbs.doProcess();
			// 设定返回值
			result = asgbs.getResult();
			if(process==false && result == null){
				result = new RESTResultBean();//程序BUG场合
				result.setMessage("蛋仔的世界：" + MESSAGE_PROC_WAITING);
				result.setCode(ONE);
			}
			 SessionHelper.getSessionAttribute();
			 SessionHelper.getSessionAttribute();
			 SessionHelper.getSessionAttribute();
			 SessionHelper.getSessionAttribute();
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new RESTResultBean();
			result.setMessage("蛋仔的世界：" + MESSAGE_ERROR_SYNC);
			result.setCode(ONE);
		}
		System.out.println("====>>>>>>>>>"+JSON.toJSONString(result));
		return result;
	}
}
