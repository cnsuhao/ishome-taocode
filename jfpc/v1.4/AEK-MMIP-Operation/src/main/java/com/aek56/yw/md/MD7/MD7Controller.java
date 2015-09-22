package com.aek56.yw.md.MD7;

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

import com.aek56.atm.master.MD7_CPGGXX.MD7_CPGGXXDBO;
import com.aek56.atm.master.MD7_CPGGXX.MD7_CPGGXXService;

/**
 * 后台维护规格
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MD7Controller extends MyControllerSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(MD7Controller.class);

    /**
     * 规格主数据表
     */
    @Resource
    protected MD7_CPGGXXService MD7_CPGGXXService_;
    
    /**
     * 事务类
     */
    @Resource
    protected MD701Business MD701Business_;

    /**
     * 根据产品ID获取规格列表
     * 
     * @param pageCurrent
     * @param pageLimit
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-10-20
     */
    @RequestMapping(value = "/3910800", method = RequestMethod.POST)
    public ModelAndView m3910800post(MD7_CPGGXXDBO param, String pageCurrent,
            String pageLimit) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MD7Controller#m3910800post()!");
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

        this.MD7_CPGGXXService_.doSelectPage(super.pageModel, false);

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MD7Controller#m3910800post()!");
        }
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("yw/md/MD7/MD701");
        view.addObject(PAGE, super.pageModel);
        view.addObject(SEARCH, param);
        return view;
    }

    /**
     * 跳转到添加或编辑页面（查询一条记录）
     *
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-1
     */
    @RequestMapping(value = "/39108001", method = RequestMethod.POST)
    public ModelAndView m39108001post(String pukid, String k01_cpid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MD7Controller#m39108001post()!");
            logger.debug("pukid:{}", pukid);
        }

        MD7_CPGGXXDBO rs = null;
        if (StringUtils.isEmpty(pukid)) {
            rs = new MD7_CPGGXXDBO();
        } else {
        	MD7_CPGGXXDBO param = new MD7_CPGGXXDBO();
            param.setPuk(pukid);
            rs = (MD7_CPGGXXDBO) this.MD7_CPGGXXService_.doRead(param);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("rs:{}", rs);
            logger.debug("Exit MD7Controller#m39108001post()!");
        }
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("yw/md/MD7/MD702");
        view.addObject(USER_DATA, rs);
        view.addObject("k01_cpid", k01_cpid);
        return view;
    }

    /**
     * 添加或编辑规格保存
     * 
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-11-29
     */
    @RequestMapping(value = "/39108002", method = RequestMethod.POST)
    public ModelAndView m39108002post(MD7_CPGGXXDBO param) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MD7Controller#m39108002post()!");
            logger.debug("param:{}", param);
        }

        boolean isError = false;
        String message = null;
        try {
            int rs = 0;
            if (StringUtils.isEmpty(param.getPuk())) {
                message = super.messageModel.getLocalMessage("391080021");
                rs = this.MD7_CPGGXXService_.doInsert(param);                
            } else {
                message = super.messageModel.getLocalMessage("391080023");
                rs = this.MD7_CPGGXXService_.doUpdate(param);
            }
            if (rs == 0) {
                throw new Exception("update num is 0!");
            }
        } catch (Exception e) {
            isError = true;
            if (StringUtils.isEmpty(param.getPuk())) {
                message = super.messageModel.getLocalMessage("391080022");
            } else {
                message = super.messageModel.getLocalMessage("391080024");
            }
            logger.error("MD7Controller#m39108002post() is error! ", e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MD7Controller#m39108002post()!");
        }

        return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB,
                message, "/3910800", isError);
    }
    
    /**
     * 删除规格
     *
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-1
     */
    @RequestMapping(value = "/39108003/{pukid}", method = RequestMethod.POST)
    public ModelAndView m39108003post(@PathVariable String pukid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MD7Controller#m39108003post()!");
            logger.debug("pukid:{}", pukid);
        }

        boolean isError = false;
        String message = super.messageModel.getLocalMessage("391080031");;
        try {
        	MD7_CPGGXXDBO param = new MD7_CPGGXXDBO();
            param.setPuk(pukid);
            this.MD7_CPGGXXService_.toDelete(param);
        } catch (Exception e) {
            isError = true;
            message = super.messageModel.getLocalMessage("391080032");
            logger.error("MD7Controller#m39108003post() is error! ", e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Exit MD7Controller#m39108003post()!");
        }
        return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB,
                message, "/3910800", isError);
    }
}
