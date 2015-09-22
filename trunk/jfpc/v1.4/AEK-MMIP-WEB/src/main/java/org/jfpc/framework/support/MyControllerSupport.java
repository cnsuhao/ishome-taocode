package org.jfpc.framework.support;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jfpc.beans.platform.CS0D1.CS0D1DBO;
import org.jfpc.beans.platform.CS0D1.CS0D1Service;
import org.jfpc.beans.platform.CS2A4.CS2A4DBO;
import org.jfpc.beans.platform.CS2A4.CS2A4Service;
import org.jfpc.beans.platform.MS1A1.MS1A1DBO;
import org.jfpc.beans.platform.MS1A1.MS1A1Service;
import org.jfpc.common.login.LoginService;
import org.jfpc.common.menu.MenuBean;
import org.jfpc.common.menu.MenuService;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.page.PageVOSupport;
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
public class MyControllerSupport extends MyFrameworkSupport implements ISSessionSupport {

	private static final Logger logger = LoggerFactory.getLogger(MyControllerSupport.class);

	@Resource
	protected LoginService LoginService_;
	@Resource
	protected PageVOSupport pageModel;
	//系统菜单
	@Resource
	protected MenuService MenuService_;
	// 企业基本信息
	@Resource
	protected MS1A1Service MS1A1Service_;
	// 内部消息
	@Resource
	protected CS0D1Service CS0D1Service_;
	// 会员应用开通信息
	@Resource
	protected CS2A4Service CS2A4Service_;

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
		//Session共享
		loadSQLSession(request, response);
		
		// 本地保存
		setSessionid(request, response, session);
		setSessionAttribute(RANDOM_CODE, session.getAttribute(RANDOM_CODE));
		setLoginerBean((LoginerBean) session.getAttribute(CONSTANT_LOGINER));

		if (logger.isDebugEnabled())
			logger.error("CONSTANT_LOGINER///////CONSTANT_LOGINER///>>>>>>=====" + session.getAttribute(CONSTANT_LOGINER));

		// 从缓存里面获得
		LoginerBean user = getLoginerBean();
		if (user == null) {
			// 从缓存里面获得
			goBack(request, response);
			return false;
		}

		// 从参数里面获得
		// String token = request.getParameter(CONSTANT_USER_TOKEN);
		// if (StringUtils.isEmpty(token)) {
		// // 获得TOKEN数据
		// if (StringUtils.isEmpty(getToken())) {
		// // 从缓存里面获得
		// goBack(request, response);
		// return false;
		// // // 用URL里面获得
		// // String tokenUrl = request.getRequestURI();
		// // // tokenUrl = "/TAAAAA/ZXXXXX/DXXXXX/FXXXXX";
		// // tokenUrl = tokenUrl.substring(1);
		// // if (tokenUrl.indexOf("/") != 0) {
		// // tokenUrl = tokenUrl.substring(tokenUrl.indexOf("/") + 1);
		// // if (tokenUrl.indexOf("/") > 0) {
		// // tokenUrl = tokenUrl.substring(0, tokenUrl.indexOf("/"));
		// // }
		// // setToken(tokenUrl);
		// // }// 用URL里面获得
		// }// 从参数里面获得
		// }
		// else {
		// setToken(token);
		// }
		if (logger.isDebugEnabled())
			logger.error("Token///////////////>>>>>>=====" + getToken());

		// 判断TOKEN有效性
		if (!doCheckToken(request, response)) {
			goBack(request, response);
		}

		loadUserInfo(request, response, session);
		return true;
	}
	
	public boolean loadSQLSession(HttpServletRequest request, HttpServletResponse response) {

		setSessionAttribute(CONSTANT_SQL_SESSION,LoginService_.getMySqlSession());
		
		return false;
	}
	
	/**
	 * Session ID获得
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	// 注释的方法会在此controller每个方法执行前被执行
	public void setSessionid(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 定义sessionID
		setSessionid(session.getId());
	}

	/**
	 * 菜单用户和企业基本信息
	 * 
	 * @param request
	 * @param response
	 */
	public void loadUserInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		LoginerBean user = getLoginerBean();

		if ("1".equals(user.getUserType()) || "2".equals(user.getUserType())) {
			MS1A1DBO cmp = (MS1A1DBO) getSessionAttribute(CONSTANT_COMPANY);
			// 获得企业基本资料
			if (cmp == null) {
				cmp = new MS1A1DBO();
				cmp.setPuk(user.getCompanyId());
				setSessionAttribute(CONSTANT_COMPANY, MS1A1Service_.doRead(cmp));
			}
			// 菜单权限
			{
				if (getSessionAttribute(MENU) == null) // 第一次登录
				{
					List<MenuBean> allMenus = MenuService_.loadMenu("1", StringUtils.EMPTY, user.getUserType());
					// 用户操作菜单
					if (ONE.equals(user.getIsAdmin()) && StringUtils.isNotEmpty(cmp.getGgg())) {
						// 获得用户应用菜单权限
						CS2A4DBO appli = new CS2A4DBO();
						appli.setPuk(super.getLoginerId());
						List<FrameworkDataBean> apps = CS2A4Service_.doSelectPage(appli, false);
						List<MenuBean> myMenus = new ArrayList<MenuBean>();
						for (MenuBean menu : allMenus) {
							for (FrameworkDataBean app : apps) {
								if (menu.getPuk().equals(((CS2A4DBO) app).getK01_yyid())) {
									myMenus.add(menu);
									break;
								}
							}
						}
						setSessionAttribute(MENU, myMenus);
					} else {// 管理员的场合
						setSessionAttribute(MENU, allMenus);
					}
				}
			}
			// 内部消息
			{
				pageModel.config();
				pageModel.setPageCurrent(1);
				pageModel.setPageLimit(5);
				CS0D1DBO nbxx = new CS0D1DBO();
				nbxx.setEb5(getCompanyId());// 接收企业ID
				pageModel.setFormParamBean(nbxx);
				pageModel = CS0D1Service_.doSelectPage(pageModel, false);
				setSessionAttribute("NBXX", pageModel.getPageListData());
				setSessionAttribute("NBXXNUM", pageModel.getResultCount());
				pageModel.config();
			}
			// 一级菜单
			String firstMenuid = request.getParameter("firstMenuid");
			if (StringUtils.isEmpty(firstMenuid)) {
				// 子页面操作场合
				firstMenuid = (String) getSessionAttribute("firstMenuid");
				if (StringUtils.isEmpty(firstMenuid)) {
					setSessionAttribute("firstMenuid", firstMenuid);
					return;
				}
			}
			// 权限判断
			if (!firstMenuid.equals(CONSTANT_USER_MAIN)) {
				boolean clear = true;
				List<MenuBean> myMenus = (List<MenuBean>) getSessionAttribute(MENU);
				for (MenuBean menu : myMenus) {
					if (menu.getPuk().equals(firstMenuid)) {
						clear = false;
						break;
					}
				}
				// 权限不足直接返回
				if (clear) {
					return;
				}
			}
			// 二级菜单
			String secondMenuid = request.getParameter("secondMenuid");
			List<MenuBean> mySecMenus = MenuService_.loadMenu("2", firstMenuid, user.getUserType());

			// 数据缓存
			setSessionAttribute("firstMenuid", firstMenuid);
			setSessionAttribute("secondMenuid", secondMenuid);
			setSessionAttribute("menu2", mySecMenus);
		}
	}

	public void goBack(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 销毁session
			request.getSession().invalidate();
			response.sendRedirect(SYSTEM_ROOT);
			response.sendError(405);
			// 形式： sendError(int errnum
			// )说明：用来向客户端发送错误信息，这对调试程序有很大帮助。常用的常量级错误代码有：
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

	public boolean doCheckToken(HttpServletRequest request, HttpServletResponse response) {
		LoginerBean loginer = new LoginerBean();
		loginer.setToken(getToken());
		boolean result = LoginService_.doCheckToken(loginer);
		// request.setAttribute("loginUrl", loginer.getLoginUrl());
		request.setAttribute("", "");
		return result;
	}

	public boolean doCheckComputer(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return false;
	}
}
