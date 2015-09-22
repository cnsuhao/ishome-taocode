package com.ttnn.business.wm.controller.zk;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.cs.service.CSSPR1Service;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.cs.service.CSSR03Service;
import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.business.wm.biz.WMFZGLService;
import com.ttnn.business.wm.biz.WMZKJGService;
import com.ttnn.common.util.DateUtil;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("WMKZ01")
/** 客户系统管理系统授权*/
public class WMKZ01Controller extends MyControllerSupportImpl {
	@Resource
	protected CSSS01Service CSSS01Service_;

	@Resource
	protected CSSR01Service CSSR01Service_;

	@Resource
	protected WMFZGLService WMFZGLService_;

	@Resource
	protected CSSR03Service CSSR03Service_;// 用户组

	@Resource
	protected CSSPR1Service CSSPR1Service_;// 系统用户所属用户组信息

	@Override
	public MyServiceSupportImpl getService() {
		return CSSS01Service_;
	}

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMKZ01Controller.class);
	}

	@Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("WM/ZK/WMKZK2");
	}

	@Override
	@RequestMapping(value = "/H.go")
	public ModelAndView home(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.setViewName("WM/ZK/WMKZK2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	@RequestMapping(value = "/A.go")
	public ModelAndView home1(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		pageMAV.setViewName("WM/ZK/WMKZK1");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

	@RequestMapping(value = "/B.go")
	public ModelAndView home2(CSPVOSupport paramBean) {
		getLogger().debug("paramBean" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		pageMAV.setViewName("WM/ZK/WMKZK1L");
		getLogger().debug("pageMAV" + pageMAV);
		return pageMAV;
	}

	/**
	 * 通道
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/P.go" )
	public ModelAndView doInsert2(CSPVOSupport paramBean) {
		getLogger().debug("paramBean===>>>" + paramBean);
		ModelAndView pageMAV = getModelAndView();
		paramBean.setEb5(super.getProductId());	
		pageVO.setPageData(paramBean);
        pageVO.setPageCurrent(Integer.MAX_VALUE);
        WMFZGLService_.doSelectPageTD(pageVO);
        pageMAV.addObject("eb5",paramBean.getPuk());	
	    pageMAV.addObject(pageVO);	
	    pageMAV.setViewName("WM/ZK/WMTDFP");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}
	
	/**
	 * 插入一条记录
	 * 
	 * @param paramBean
	 * @throws IOException
	 * @throws IllegalStateException
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@RequestMapping(value = "/C.go")
	public ModelAndView doInsert(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		paramBean.setPuk(PKUtil.getPUKey());

		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

		if (commonsMultipartResolver.isMultipart(request)) {

			MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart(request);
			MultipartFile filea = multipartRequest.getFile("tt");
			if (filea != null) {
				try {
					String filename = paramBean.getPuk() + "." + filea.getOriginalFilename().substring(filea.getOriginalFilename().lastIndexOf(".") + 1, filea.getOriginalFilename().length());
					File file = new File(request.getSession().getServletContext().getRealPath("/") + "/upload/zk/" + filename);
					if (file.getParentFile().isDirectory()) {
						file.mkdirs();
					}
					filea.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				paramBean.setF14(filea.getName());
			}
			MultipartFile filef = multipartRequest.getFile("ff");

			if (filef != null) {
				try {
					String filename = paramBean.getPuk() + "." + filef.getOriginalFilename().substring(filef.getOriginalFilename().lastIndexOf(".") + 1, filef.getOriginalFilename().length());

					filef.transferTo(new File(request.getSession().getServletContext().getRealPath("/") + "/upload/zk/" + filename));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				paramBean.setF14(filea.getName());

			}
		}
		// CSPVOSupport dbParamBean1 = new CSPVOSupport ();
		paramBean.setEb5(super.getProductId());
		// 创建客户基本信息
		int result = getService().doInsert(paramBean);
		if (result == 1) {
			pageMAV.addObject("message", paramBean);
		} else if (result == -1) {
			pageMAV.addObject("message", "failure");
		} else {
			pageMAV.addObject("message", "failure");
		}
		// 用户组画面初期化
		pageVO.setPageLimit(Integer.MAX_VALUE);
		//
		// FrameworkDataBean dbParamBean = new FrameworkDataBean ();
		// dbParamBean.setEb5(paramBean.getPuk());
		pageVO.setPageData(new ArrayList<FrameworkDataBean>());
		// CSSR03Service_.doSelectPage(pageVO);
		// String url = "redirect:F.go";
		// pageMAV = new ModelAndView(url);
		// 获得了用户组分类数据字典
		FrameworkDataBean dbParamBean = new FrameworkDataBean();
		// 定义中分类
		dbParamBean.setK02("DFB.XTFL");
		// 定义产品发行内部识别ID
		dbParamBean.setEb5("TTNN_DFB");
		// 获得 字典数据
		List<FrameworkDataBean> list = getDictionaryDefault(dbParamBean);
		pageMAV.addObject(pageVO);
		// pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.addObject("list1", list);
		// pageMAV.addObject("parambean",paramBean);
		pageMAV.addObject("eb5", paramBean.getPuk());
		pageMAV.setViewName("WM/ZK/WMKZH2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 创建系统和管理员
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@RequestMapping(value = "/CJ.go")
	public ModelAndView doInsert1(HttpServletRequest request, CSPVOSupport paramBean, @RequestParam("ff") MultipartFile file1, @RequestParam("tt") MultipartFile file2) {
		ModelAndView pageMAV = getModelAndView();

		// 授权域名唯一性检查
		// TODO k02
		CSPVOSupport cs = new CSPVOSupport();
		cs.setK02(paramBean.getK02());
		pageVO.setPageData(cs);
		CSSS01Service_.doSelectPage(pageVO);
		if (pageVO.getResultCount() > 0) {
			pageMAV.addObject("message","当前授权域名已经存在");
			pageMAV.addObject(paramBean);
			pageMAV.addObject("parambean1", paramBean);
			pageMAV.setViewName("WM/ZK/WMKZK2");
			return pageMAV;
		}

		// 创建客户ID
		paramBean.setPuk(PKUtil.getPUKey());

		String folderName = request.getSession().getServletContext().getRealPath("/") + "resources/project/" + paramBean.getPuk() + "/logo/";
		String qp = "";
		if (file1 != null && !StringUtils.isNullOrEmpty(file1.getOriginalFilename())) {
			qp = folderName + "QTLogo.jpg";
			File dir = new File(qp);
			dir.getParentFile().mkdirs();
			try {
				file1.transferTo(dir);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String hp = "";
		if (file2 != null && !StringUtils.isNullOrEmpty(file2.getOriginalFilename())) {
			qp = folderName + "HTLogo.jpg";
			File dir = new File(hp);
			dir.getParentFile().mkdirs();
			try {
				file2.transferTo(dir);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 添加管理员
		CSPVOSupport paramBean2 = new CSPVOSupport();
		paramBean2.setPuk(PKUtil.getPUKey());
		paramBean2.setF01(paramBean.getF02());
		paramBean2.setF02("Y");
		paramBean2.setF03(paramBean.getFb3());
		paramBean2.setF04(new Md5PasswordEncoder().encodePassword(paramBean.getFb4(), null));
		paramBean2.setF05("WM.HT");
		paramBean2.setF06("1");
		paramBean2.setEb5(paramBean.getPuk());
		CSSR01Service_.doInsert(paramBean2);

		// 添加客户系统
		paramBean.setF14(qp);
		paramBean.setF15(hp);
		// 保存超级管理员id
		paramBean.setK03(paramBean.getFb3());
		paramBean.setF01("");
		paramBean.setFb3("");
		paramBean.setFb4("");
		CSSS01Service_.doInsert(paramBean);

		// 添加管理员用户组
		CSPVOSupport paramBean3 = new CSPVOSupport();
		paramBean3.setPuk(PKUtil.getPUKey());
		// 用户组编号
		paramBean3.setF02("HT_ADMIN_GROUP");
		// 上级用户组名称
		// 上级用户组ID
		// 用户组名称
		paramBean3.setF02("后台管理用户组");
		// 用户组系统分类
		paramBean3.setF02("WM.HT");
		paramBean3.setEb5(paramBean.getPuk());
		CSSR03Service_.doInsert(paramBean3);

		// 用户授权
		CSPVOSupport paramBean4 = new CSPVOSupport();
		paramBean4.setPuk(paramBean2.getPuk());
		paramBean4.setEb5(paramBean.getPuk());
		paramBean4.setK01(paramBean3.getF09());
		CSSPR1Service_.doInsert(paramBean4);

		// 添加用户组权限DFB_HT_ADMIN_GROUP
		pageMAV = new ModelAndView("redirect:/WMKZ01/F.go");
		return pageMAV;
	}

	/**
	 * 数据一览
	 */
	@Override
	@RequestMapping(value = "/F.go")
	public ModelAndView doFindList(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		// paramBean.setEb5(super.getProductId());
		pageVO.setPageData(paramBean);
		pageVO.setPageCurrent(Integer.parseInt(paramBean.getPageCurrent()));
		CSSS01Service_.doSelectPage(pageVO);
		pageMAV.addObject(pageVO);
		pageMAV.setViewName("WM/ZK/WMKZK1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 分页显示
	 * 
	 * @param paramPageModel
	 * @return
	 * @see ISSQLDaoSupport#doSelectList(PageVO)
	 */
	@Override
	@RequestMapping(value = "/L.go")
	public ModelAndView doSelectPage(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageVO.setPageCurrent(1);
		pageVO.setPageLimit(5);
		pageVO.setPageData(paramBean);
		pageVO = getService().doSelectPage(pageVO);
		pageMAV.addObject("list", pageVO.getPageData());
		pageMAV.addObject("pagecount", pageVO.getPageCount());// 总页数
		pageMAV.addObject("pagecurrent", pageVO.getPageCurrent());// 当前页
		pageMAV.addObject("pagelimit", pageVO.getPageLimit());// 每页记录数
		pageMAV.addObject("totalcount", pageVO.getResultCount());// 总记录数
		pageMAV.setViewName("WM/ZK/WMKZK1");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 查询一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doRead(CSPVOSupport)
	 */
	@RequestMapping(value = "/R.go")
	public ModelAndView doRead(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.addObject(paramBean);
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
		pageMAV.setViewName("WM/ZK/WMKZK2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 编辑超级管理员
	 */
	@RequestMapping(value = "/CG.go")
	public ModelAndView doCG(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		pageVO.setPageData(paramBean);

		List<FrameworkDataBean> as = (List<FrameworkDataBean>) CSSR01Service_.doSelectPage(pageVO).getPageData();
		if (as != null && as.size() > 0)
			pageMAV.addObject("parambean", as.get(0));

		pageMAV.setViewName("WM/ZK/WMKZA2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 准备授权
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doRead(CSPVOSupport)
	 */
	@RequestMapping(value = "/TRX.go")
	public ModelAndView doTRX(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		pageMAV.addObject("parambean1", getService().doRead(paramBean));
		pageMAV.setViewName("WM/ZK/WMKZK5");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 删除一条记录 method = RequestMethod.POST
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
	 */
	@RequestMapping(value = "/D.go")
	public ModelAndView doDelete(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);
		// int result =getService().toDelete(paramBean);
		// int result = getService().doDelete(paramBean);
		int result = getService().toDelete(paramBean);
		if (result > 0) {
			pageMAV.addObject("message", "success");
		} else if (result <= 0) {
			pageMAV.addObject("message", "删除失败");
		} else {
			pageMAV.addObject("message", "删除失败");
		}
		String url = "redirect:F.go";
		pageMAV = new ModelAndView(url);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
		// return new ModelAndView("redirect:WM/ZK/WMKZK1");
	}

	/**
	 * 更新一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/RTO.go")
	public ModelAndView doRTO(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		CSPVOSupport c1 = new CSPVOSupport();
		c1.setPuk(paramBean.getPuk());
		FrameworkDataBean db = getService().doRead(c1);

		paramBean.setUu1(db.getUu1());
		int result = getService().doUpdate(paramBean);
		// pageMAV.setViewName("wm/WMZHY2");
		// PageVO paramPageModel = new PageVO();
		// paramPageModel.setPageData(paramBean);
		// pageMAV.addObject(paramPageModel);
		// List<FrameworkDataBean> list =
		// getService().doFindList(paramPageModel);
		// pageMAV.addObject("list", list);
		// pageMAV.setViewName("WM/ZK/WMKZK1");
		String url = "redirect:F.go";
		pageMAV = new ModelAndView(url);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 更新一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/U.go")
	public ModelAndView doUpdate(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		int result = getService().doUpdate(paramBean);

		String url = "redirect:F.go";
		pageMAV = new ModelAndView(url);
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

	/**
	 * 更新一条记录
	 * 
	 * @param paramBean
	 * @see ISSQLDaoSupport#doUpdate(CSPVOSupport)
	 */
	@RequestMapping(value = "/U1.go")
	public ModelAndView doUpdate1(CSPVOSupport paramBean) {
		ModelAndView pageMAV = getModelAndView();
		getLogger().debug("paramBean===>>>" + paramBean);

		// TODO 更新管理员ID
		// int result1=WMZKJGService_.doUpdate1(paramBean);
		// System.out.println(result1);

		pageMAV.setViewName("WM/ZK/WMKZA2");
		getLogger().debug("pageMAV===>>>" + pageMAV);
		return pageMAV;
	}

}
