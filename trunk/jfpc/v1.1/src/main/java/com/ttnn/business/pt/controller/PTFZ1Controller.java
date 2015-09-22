package com.ttnn.business.pt.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyControllerSupportImpl;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Controller
@RequestMapping("PTFZ1")
/** 系统公告*/
public class PTFZ1Controller extends MyControllerSupportImpl {
	@Resource
    protected CSSS01Service CSSS01Service_;

    @Override
    public MyServiceSupportImpl getService(){
        return CSSS01Service_;
    }
	
	@Override	
    public Logger getLogger(){
        return LoggerFactory.getLogger(PTCPController.class);
    }
	/**
   	 * 页面视图
   	 */
	 @Override
	public ModelAndView getModelAndView() {
		return new ModelAndView("PT/PTFZK1");
	} 
    
	 @Override
	 @RequestMapping(value = "/H.go")
	 public ModelAndView home(CSPVOSupport paramBean){
		 	getLogger().debug("paramBean" + paramBean);
	   		ModelAndView pageMAV = getModelAndView();
	   		pageMAV.setViewName("PT/PTFZK1");
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
			pageMAV.setViewName("PT/PTFZK2");		
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
			String url = "redirect:/PTFZ1/F.go";
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
			pageMAV.setViewName("PT/PTFZK3");
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
			PageVO paramPageModel = new PageVO();
			paramPageModel.setPageData(paramBean);
			pageMAV.addObject(paramBean);
			pageMAV.addObject("parambean1", getService().doRead(paramBean));
			pageMAV.setViewName("PT/PTFZK2");
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
			pageMAV.setViewName("PT/PTFZK1");
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
			String url = "redirect:/PTFZ1/F.go";
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
//			System.out.println(result);
//			PageVO paramPageModel = new PageVO();
//			paramPageModel.setPageData(paramBean);
//			List<FrameworkDataBean> list = getService().doFindList(paramPageModel);
//			pageMAV.addObject("list", list);
//			pageMAV.setViewName("PT/PTCP01R");
//			getLogger().debug("pageMAV===>>>" + pageMAV);
//			return pageMAV;
			String url = "redirect:/PTFZ1/F.go";
			pageMAV = new ModelAndView(url);
			return pageMAV;
		}
}
