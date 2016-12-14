package org.jfpc.framework.support;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 画面控制层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.2.1 2014/11/05
 * @version 0.1.0 2014/2/8
 */
public class SuperControllerSupport extends MyBusinessSupport implements ISSessionSupport {

	private static final Logger logger = LoggerFactory.getLogger(SuperControllerSupport.class);

	// 登录相关
	// @Resource
	// protected LoginService loginService;

	/**
	 * 页面视图
	 */
	public MyModelAndViewSupport getModelAndView(String pageId) {
		return new MyModelAndViewSupport(pageId);
	}

	/**
	 * 页面视图
	 */
	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("");
	}

	/**
	 * 登录拦截
	 * 
	 * @param response
	 */
	@ModelAttribute
	public boolean doCheckLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 拦截校验
		if (init(request, response, session)==false) {
			// 初始化失败
			goBack(request, response, false);
			return false;
		}

		setSessionAttribute(RANDOM_CODE, session.getAttribute(RANDOM_CODE));
		setLoginerBean((LoginerBean) session.getAttribute(CONSTANT_LOGINER));

		if (logger.isDebugEnabled())
			logger.error("CONSTANT_LOGINER///////CONSTANT_LOGINER///>>>>>>=====" + session.getAttribute(CONSTANT_LOGINER));
		
		return loadUserInfo(request, response, session);//加载用户信息
	}
	
	/**
	 * 初始化拦截
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public boolean init(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			
//			HandlerExecutionChain chain = handlerMapping.getHandler(request);   
//	        ModelAndView model = handlerAdapter.handle(request, response,   
//	                chain.getHandler()
	                
			//初始化
			pageModel.config();
			// 定义sessionID
			setSessionid(session.getId());
			// 获得 用户Token
			String token = request.getParameter(CONSTANT_USER_TOKEN);
			// 从参数里面获得
			if (EmptyHelper.isEmpty(token)) {
				// 从缓存里面获得
				token = (String) session.getAttribute(CONSTANT_USER_TOKEN);
				if (EmptyHelper.isEmpty(token)) {
					return false;
				}
			}
			//打印日志
			if (logger.isDebugEnabled())
				logger.error("Token///////////////>>>>>>=====" + token);
			// 判断TOKEN有效性
			LoginerBean loginer = new LoginerBean();
			loginer.setToken(token);
			return loginService.doCheckToken(loginer);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	/**
	 * 异常返回
	 * @param request
	 * @param response
	 * @param json
	 */
	public void goBack(HttpServletRequest request, HttpServletResponse response,boolean json) {
		try {
			// 销毁session
			request.getSession().invalidate();
			// JSON拦截器
			if(json){
				RESTResultBean rs = new RESTResultBean();
				rs.setCode("9");
				rs.setMessage("9");//登录失效，请重新登录
				OutputStream out = response.getOutputStream();
				out.write(JSONObject.fromObject(rs).toString().getBytes("UTF-8"));
				out.flush();
			}

			// 标准拦截器
			{
				response.sendError(HttpServletResponse.SC_FORBIDDEN);//403
				response.sendRedirect(SYSTEM_ROOT);
			}
			// 形式： sendError(int errnum)说明：用来向客户端发送错误信息，这对调试程序有很大帮助。常用的常量级错误代码有：
			// SC_CONTINUE， 状态码是100，表示客户端无法连接。
			// SC_SWITHING_PROTOCOLS,状态码是101，表示服务器正向报头中注明的协议切换。
			// SC_OK，状态码是200.表示请求被成功处理。
			// SC_CREATED，状态码是201，表示请求被成功处理，并在服务器方创建了一个新的资源。
			// SC_ACCEPTED，状态码是202，表示请求正在被处理，但尚未完成。
			// SC_NON_AUTHORITATIVE_INFORMATION，状态码是203，表示客户端所表达的mate信息并非来自服务器。
			// SC_NO_CONTENT，状态码是204，表示请求被成功处理，但没有新的信息返回。
			// SC_RESET_CONTENT,状态码是205，表示导致请求被发送的文档视图应该重置。
			// SC_PARTIAL_CONTENT,状态码是206，表示服务器已经完成对资源的GET请求。
			// SC_MULTI_CHOICES，状态码是300，表示对应于一系列表述的被请求资源都有明确的位置。
			// SC_MOVED_PERMANENTLY，状态码是301，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中应当使用一个新的URL.
			// SC_MOVED_TEMPORARILY,状态码是302，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中仍使用原来的URL.
			// SC_SEE_OTHER,状态码是303，表示请求的响应可以在一个不同的URL中找到。
			// SC_NOT_MODIFIED，状态码是304，表示一个有条件的GET操作发现资源可以利用，且没有被改变。
			// SC_USE_PROXY，状态码是305，表示被请求的资源必须通过特定位置的代理来访问。
			// SC_BAD_REQUEST，状态码是400，表示客户发出的请求句法不正确。
			// SC_UNAUTHORIZED,状态码是401，表示请求HTTP认证。
			// SC_PAYMENT_REQUIRED，状态码是402，表示为以后的使用保留。
			// SC_FORBIDDEN，状态码是403，表示服务器明白客户的请求，但拒绝响应。
			// SC_NOT_FAND，状态码是404，表示所请求的资源不可用。
			// SC_METHOD_NOT_ALLOWED,状态码是405，表示在请求行中标示的方法不允许对请求URL所标明的资源使用。
			// SC_NOT_ACCEPTTABLE,状态码是406，表示被请求的资源只能响应实体，而且此符合请求所发送的可接受头部域的实体的确包含不可接受的内容。
			// SC_PHOXY_AUTHENTICATION_REQUIRED,状态码是407，表示客户端必须先向代理验证。
		} catch (Exception e) {
		}
	}
	

	/**
	 * 返回拦截
	 * @param request
	 * @param response
	 */
	public void goBack(HttpServletRequest request, HttpServletResponse response) {
		goBack(request, response, false);
	}
	
	/**
	 * 菜单用户和企业基本信息
	 * 
	 * @param request
	 * @param response
	 */
	public boolean loadUserInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return true;
	}
}
