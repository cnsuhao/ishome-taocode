package com.aek56.yw.sh.CSM6;

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

import com.aek56.atm.auditing.CSM6_ZCZXX.CSM6_ZCZXXDBO;
import com.aek56.atm.auditing.CSM6_ZCZXX.CSM6_ZCZXXService;
import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXDBO;
import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXService;

/**
 * 后台审核注册证
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class CSM6Controller extends MyControllerSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(CSM6Controller.class);

    /**
     * 注册证主数据表
     */
    @Resource
    protected MDG_ZCZXXService MDG_ZCZXXService_;

    /**
     * 注册证审核表
     */
    @Resource
    protected CSM6_ZCZXXService CSM6_ZCZXXService_;
    
    /**
     * 审核事务类
     */
    @Resource
    protected CSM6Business CSM6Business_;

    /**
     * 注册证审核一览
     * 
     * @param param
     * @param pageCurrent
     * @param pageLimit
     * @return
     * @Author:zhengxw
     * @Date:2014-12-1
     */
    @RequestMapping(value = "/3920700", method = RequestMethod.POST)
    public ModelAndView m3920700post(CSM6_ZCZXXDBO param, String pageCurrent,
            String pageLimit) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into CSM6Controller#m3920700post()!");
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

        this.CSM6Business_.doSelectPage(super.pageModel, false);

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit CSM6Controller#m3920700post()!");
        }
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("yw/md/CSM6/CSM601");
        view.addObject(PAGE, super.pageModel);
        view.addObject(SEARCH, param);
        return view;
    }

    /**
     * 跳转到审核页面
     * 
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-10-20
     */
    @RequestMapping(value = "/39207001/{pukid}", method = RequestMethod.POST)
    public ModelAndView m39207001post(@PathVariable String pukid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into CSM6Controller#m39207001post()!");
            logger.debug("pukid:{}", pukid);
        }

        // 从审核表中获取右侧对比记录
        CSM6_ZCZXXDBO csm6Param = new CSM6_ZCZXXDBO();
        csm6Param.setPuk(pukid);
        CSM6_ZCZXXDBO csm6Rs = (CSM6_ZCZXXDBO) this.CSM6_ZCZXXService_
                .doRead(csm6Param);

        // 从主数据中获取左侧对比记录
        MDG_ZCZXXDBO mdgParam = new MDG_ZCZXXDBO();
        mdgParam.setPuk(csm6Rs.getP01_puk());
        MDG_ZCZXXDBO mdgRs = (MDG_ZCZXXDBO) this.MDG_ZCZXXService_
                .doRead(mdgParam);

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit CSM6Controller#m39207001post()!");
        }
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("yw/md/CSM6/CSM602");
        view.addObject("right", csm6Rs);
         view.addObject("left", mdgRs);
        return view;
    }
    
    /**
     * 审核通过或者失败
     * @param pukid
     * @param type 0：审核通过，1：审核失败
     * @return
     */
    @RequestMapping(value = "/39207002/{type}", method = RequestMethod.POST)
    public ModelAndView m39207002post(CSM6_ZCZXXDBO param, @PathVariable String type) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into CSM6Controller#m39207002post()!");
            logger.debug("param:{}, type:{}", param, type);
        }
        
        String message = super.messageModel.getLocalMessage("392070021");
        boolean isError = false;

        try {
            if (ZERO.equals(type)) {//审核通过
                this.CSM6Business_.throughAudit(param);
            } else { //审核失败
                this.CSM6Business_.donotPassTheAudit(param);
            }
        } catch (Exception e) {
            isError = true;
            message = super.messageModel.getLocalMessage("392070022");
            logger.error("CSM6Controller#m39207002post() is error!", e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit CSM6Controller#m39207002post()!");
        }
        
        return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB,
                message, "/3920700", isError);
    }

}
