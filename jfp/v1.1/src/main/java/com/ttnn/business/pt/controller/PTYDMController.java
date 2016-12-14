package com.ttnn.business.pt.controller;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSB03Service;
import com.ttnn.business.pt.service.PTCP01Service;
import com.ttnn.business.pt.service.PTCP02Service;
import com.ttnn.business.pt.service.PTCP03Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("PTYDM")
/** 系统公告*/
public class PTYDMController extends MyControllerSupportImpl {

    @Resource
    protected CSSB03Service cssb03Service_;


	@Override
	public MyServiceSupportImpl getService() {
		return cssb03Service_;
	}
	
	@Override	
    public Logger getLogger(){
        return LoggerFactory.getLogger(PTYDMController.class);
    }
	/**
   	 * 页面视图
   	 */
	 @Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("PT/PTYDM1");
	} 
    
	 @Override
	 @RequestMapping(value = "/H.go")
	 public ModelAndView home(CSPVOSupport paramBean){
		 	getLogger().debug("paramBean" + paramBean);
	   		ModelAndView pageMAV = getModelAndView();
	   		pageMAV.setViewName("PT/PTYDM1");
	   		getLogger().debug("pageMAV" + pageMAV);
	   		return pageMAV;
	 }
		
		/**
		 * 插入一条记录
		 * 跳转到添加画面
		 * @param paramBean
		 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
		 */
		@RequestMapping(value = "/C.go")
		public ModelAndView toInsert(CSPVOSupport paramBean) {
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("paramBean===>>>" + paramBean);
			pageMAV.setViewName("PT/PTYDM2");		
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}
		/**
		 * 插入一条记录
		 * 
		 * @param paramBean
		 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
		 */
		@RequestMapping(value = "/I.go")
		public ModelAndView doInsert(CSPVOSupport paramBean) {
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("paramBean===>>>" + paramBean);
			int result = getService().doInsert(paramBean);
			if (result == 1) {
				pageMAV.addObject("message", paramBean);
			} else if (result == -1) {
				pageMAV.addObject("message", "failure");
			} else {
				pageMAV.addObject("message", "failure");
			}
			String url = "redirect:/PTYDM/F.go";
			pageMAV = new ModelAndView(url);
			return pageMAV;
		}
		/**
		 * 跳转到检索画面
		 * 
		 * @param paramBean
		 * @see ISSQLDaoSupport#doRead(CSPVOSupport)
		 */
		@RequestMapping(value = "/S.go")
		public ModelAndView toSearch(CSPVOSupport paramBean) {
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("paramBean===>>>" + paramBean);
			pageMAV.setViewName("PT/PTYDM3");
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
//			PageVO paramPageModel = new PageVO();
//			paramPageModel.setPageData(paramBean);
//			getService().doSelectPage(paramPageModel)
			pageMAV.addObject(paramBean);
			pageMAV.addObject("parambean1", getService().doRead(paramBean));
			PageVO formParamPageModel = new PageVO();
			formParamPageModel.setPageLimit(Integer.MAX_VALUE);
			List<FrameworkDataBean> list = (List<FrameworkDataBean>)getService().doSelectPage(formParamPageModel);
			pageMAV.addObject("list", list);
			pageMAV.setViewName("PT/PTYDM2");
			getLogger().debug("pageMAV===>>>" + pageMAV);
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
			pageVO.setPageData(paramBean);
			pageMAV.addObject(pageVO);
			getService().doSelectPage(pageVO);
			pageMAV.setViewName("PT/PTYDM1");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}
		/**
		 * 删除一条记录
		 * method = RequestMethod.POST
		 * @param paramBean
		 * @see ISSQLDaoSupport#doBatchDelete(java.util.List)
		 */
		@RequestMapping(value = "/D.go")
		public ModelAndView doDelete(CSPVOSupport paramBean) {
			ModelAndView pageMAV = getModelAndView();
			getLogger().debug("paramBean===>>>" + paramBean);
			int result = getService().doDelete(paramBean);
			if (result > 0) {
				pageMAV.addObject("message", "success");
			} else if (result <= 0) {
				pageMAV.addObject("message", "删除失败");
			} else {
				pageMAV.addObject("message", "删除失败");
			}
			String url = "redirect:/PTYDM/F.go";
			pageMAV = new ModelAndView(url);
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
			System.out.println(result);
			PageVO paramPageModel = new PageVO();
			paramPageModel.setPageLimit(Integer.MAX_VALUE);
			List<FrameworkDataBean> list = (List<FrameworkDataBean>)getService().doSelectPage(paramPageModel);
			pageMAV.addObject("list", list);
			pageMAV.setViewName("PT/PTYDM1");
			getLogger().debug("pageMAV===>>>" + pageMAV);
			return pageMAV;
		}
}
