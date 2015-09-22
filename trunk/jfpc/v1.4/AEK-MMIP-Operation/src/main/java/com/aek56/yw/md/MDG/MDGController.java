package com.aek56.yw.md.MDG;

import javax.annotation.Resource;

import org.jfpc.framework.helper.DateHelper;
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

import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXDBO;
import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXService;

/**
 * 后台维护主数据注册证
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MDGController extends MyControllerSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(MDGController.class);

    /**
     * 注册证主数据表
     */
    @Resource
    protected MDG_ZCZXXService MDG_ZCZXXService_;
    
    /**
     * 事务类
     */
    @Resource
    protected MDG01Business MDGBusiness_;

    /**
     * 获取所有注册证列表
     * 
     * @param pageCurrent
     * @param pageLimit
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-10-20
     */
    @RequestMapping(value = "/39118001", method = RequestMethod.POST)
    public ModelAndView m39118001post(MDG_ZCZXXDBO param, String pageCurrent,
            String pageLimit) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MDGController#m39118001post()!");
            logger.debug("param:{}, pageCurrent:{}, pageLimit:{}", param,
                    pageCurrent, pageLimit);
        }

        // 设置分页参数
        int pageNo = StringUtils.isEmpty(pageCurrent) ? 1 : Integer
                .parseInt(pageCurrent);
        int pageSize = StringUtils.isEmpty(pageLimit) ? DEFAULT_PAGELIMIT
                : Integer.parseInt(pageLimit);
        
        // 如果查询标志为空，则默认为查询正常注册证列表
        if (StringUtils.isEmpty(param.getEb5())) {
            param.setEb5(ZERO);
        }
        // 设置当前时间
        param.setF10_yxzzrq(DateHelper.currentTimeMillisCN3());
        
        super.pageModel.setPageCurrent(pageNo);
        super.pageModel.setPageLimit(pageSize);
        
        super.pageModel.setFormParamBean(param);

        this.MDGBusiness_.doSelectPage(super.pageModel, false);

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MDGController#m39118001post()!");
        }
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("yw/md/MDG/MDG01");
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
    @RequestMapping(value = "/391180002", method = RequestMethod.POST)
    public ModelAndView m391180002post(String pukid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MDGController#m391180002post()!");
            logger.debug("pukid:{}", pukid);
        }

        MDG_ZCZXXDBO rs = null;
        if (StringUtils.isEmpty(pukid)) {
            rs = new MDG_ZCZXXDBO();
        } else {
            MDG_ZCZXXDBO param = new MDG_ZCZXXDBO();
            param.setPuk(pukid);
            rs = (MDG_ZCZXXDBO) this.MDG_ZCZXXService_.doRead(param);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("rs:{}", rs);
            logger.debug("Exit MDGController#m391180002post()!");
        }
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("yw/md/MDG/MDG02");
        view.addObject(USER_DATA, rs);
        return view;
    }

    /**
     * 添加或编辑注册证保存方法
     * 
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-11-29
     */
    @RequestMapping(value = "/391180003", method = RequestMethod.POST)
    public ModelAndView m391180003post(MDG_ZCZXXDBO param) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MDGController#m391180003post()!");
            logger.debug("param:{}", param);
        }

        boolean isError = false;
        String message = null;
        try {
            int rs = 0;
            if (StringUtils.isEmpty(param.getPuk())) {
                message = super.messageModel.getLocalMessage("3911800031");
                rs = this.MDG_ZCZXXService_.doInsert(param);
            } else {
                message = super.messageModel.getLocalMessage("3911800033");
                rs = this.MDG_ZCZXXService_.doUpdate(param);
            }
            if (rs == 0) {
                throw new Exception("update num is 0!");
            }
        } catch (Exception e) {
            isError = true;
            if (StringUtils.isEmpty(param.getPuk())) {
                message = super.messageModel.getLocalMessage("3911800032");
            } else {
                message = super.messageModel.getLocalMessage("3911800034");
            }
            logger.error("MDGController#m391180003post() is error! ", e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MDGController#m391180003post()!");
        }

        return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB,
                message, "/39118001", isError);
    }
    
    /**
     * 删除注册证
     *
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-1
     */
    @RequestMapping(value = "/391180006/{pukid}", method = RequestMethod.POST)
    public ModelAndView m391180006post(@PathVariable String pukid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MDGController#m391180006post()!");
            logger.debug("pukid:{}", pukid);
        }

        boolean isError = false;
        String message = super.messageModel.getLocalMessage("3911800061");
        try {
            MDG_ZCZXXDBO param = new MDG_ZCZXXDBO();
            param.setPuk(pukid);
            this.MDG_ZCZXXService_.toDelete(param);
        } catch (Exception e) {
            isError = true;
            message = super.messageModel.getLocalMessage("3911800062");
            logger.error("MDGController#m391180006post() is error! ", e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Exit MDGController#m391180006post()!");
        }
        return MessagePageHelper.getMessageMAV(MessagePageHelper.MESSAGE_WEB,
                message, "/39118001", isError);
    }
}
