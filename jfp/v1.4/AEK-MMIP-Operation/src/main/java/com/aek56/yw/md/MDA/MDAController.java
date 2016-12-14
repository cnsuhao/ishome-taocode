package com.aek56.yw.md.MDA;


import javax.annotation.Resource;

import org.jfpc.framework.helper.MessagePageHelper;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aek56.atm.master.MDA_CPXXX.MDA_CPXXXDBO;
import com.aek56.atm.master.MDA_CPXXX.MDA_CPXXXService;
import com.aek56.atm.master.MDB_CPXFLXX.MDB_CPXFLXXService;

/**
 * 产品线主数据维护
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MDAController extends MyControllerSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(MDAController.class);

    /**
     * 产品线主数据表
     */
    @Resource
    protected MDA_CPXXXService MDA_CPXXXService_;
    
    /**
     * 产品线分类主数据
     */
    @Resource
    protected MDB_CPXFLXXService MDB_CPXFLXXService_;
    
    /**
     * 产品线事务类
     */
    @Resource
    protected MDABusiness MDABusiness_;

    /**
     * 查询产品线列表
     * 
     * @param pageCurrent
     * @param pageLimit
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-10-20
     */
    @RequestMapping(value = "/3911100", method = RequestMethod.POST)
    public ModelAndView m3911100post(MDA_CPXXXDBO param, String pageCurrent,
            String pageLimit) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MDAController#m3911100post()!");
            logger.debug("param:{}, pageCurrent:{}, pageLimit:{}", param,
                    pageCurrent, pageLimit);
        }

        // 设置分页参数
        int pageNo = StringUtils.isEmpty(pageCurrent) ? 1 : Integer
                .parseInt(pageCurrent);
        int pageSize = StringUtils.isEmpty(pageLimit) ? DEFAULT_PAGELIMIT
                : Integer.parseInt(pageLimit);
        
        super.pageModel.setPageCurrent(pageNo);
        super.pageModel.setPageLimit(pageSize);
        
        super.pageModel.setFormParamBean(param);

        this.MDABusiness_.doSelectPage(super.pageModel, false);

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MDAController#m3911100post()!");
        }
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("yw/md/MDA/MDA01");
        view.addObject(PAGE, super.pageModel);
        view.addObject(SEARCH, param);
        return view;
    }

    /**
     * 跳转到添加或编辑页面（查询一条记录）
     *
     * @param pukid 产品线ID
     * @return
     * @Author:zhengxw
     * @Date:2014-12-1
     */
    @RequestMapping(value = "/39111001", method = RequestMethod.POST)
    public ModelAndView m39111001post(String pukid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MDAController#m39111001post()!");
            logger.debug("pukid:{}", pukid);
        }

        MDAPvo rs = new MDAPvo();
        if (!StringUtils.isEmpty(pukid)) {
            rs = this.MDABusiness_.getCpxAndCpflByID(pukid);
        } 

        if (logger.isDebugEnabled()) {
            logger.debug("rs:{}", rs);
            logger.debug("Exit MDAController#m39111001post()!");
        }
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("yw/md/MDA/MDA02");
        view.addObject(USER_DATA, rs);
        return view;
    }

    /**
     * 添加或编辑产品线保存
     * 
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-11-29
     */
    @RequestMapping(value = "/39111002", method = RequestMethod.POST)
    public ModelAndView m39111002post(MDAPvo param) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MDAController#m39111002post()!");
            logger.debug("param:{}", param);
        }

        boolean isError = false;
        String message = null;
        try {
            if (StringUtils.isEmpty(param.getPuk())) {
                message = super.messageModel.getLocalMessage("391110021");
                this.MDABusiness_.doInsertCpx(param);                
            } else {
                message = super.messageModel.getLocalMessage("391110023");
                this.MDABusiness_.doUpdateCpx(param);
            }
        } catch (Exception e) {
            isError = true;
            if (StringUtils.isEmpty(param.getPuk())) {
                message = super.messageModel.getLocalMessage("391110022");
            } else {
                message = super.messageModel.getLocalMessage("391110024");
            }
            logger.error("MDAController#m39111002post() is error! ", e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Exit MDAController#m39111002post()!");
        }

        return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB,
                message, "/3911100", isError);
    }
    
    /**
     * 删除规格
     *
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-1
     */
    @RequestMapping(value = "/39111003/{pukid}", method = RequestMethod.POST)
    public ModelAndView m39111003post(@PathVariable String pukid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MDAController#m39111003post()!");
            logger.debug("pukid:{}", pukid);
        }

        boolean isError = false;
        String message = super.messageModel.getLocalMessage("391110031");
        try {
        	MDA_CPXXXDBO param = new MDA_CPXXXDBO();
            param.setPuk(pukid);
            this.MDA_CPXXXService_.toDelete(param);
        } catch (Exception e) {
            isError = true;
            message = super.messageModel.getLocalMessage("391110032");
            logger.error("MDAController#m39108003post() is error! ", e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Exit MDAController#m39111003post()!");
        }
        return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB,
                message, "/3911100", isError);
    }
}
