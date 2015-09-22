package com.aek56.qt.gys.MGA4;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aek56.atm.credentials.MGA4_JXSQS.MGA4_JXSQSDBO;
import com.aek56.atm.credentials.MGA4_JXSQS.MGA4_JXSQSService;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZService;
import com.aek56.atm.credentials.MGAD_SQSGLZCZXX.MGAD_SQSGLZCZXXService;

/**
 * 供应商经销授权书管理
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MGA4Controller extends MyControllerSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(MGA4Controller.class);

    /**
     * 供应商经销授权书
     */
    @Resource
    protected MGA4_JXSQSService MGA4_JXSQSService_;
    
    /**
     * 经销书和注册证关联表
     */
    @Resource
    protected MGAD_SQSGLZCZXXService MGAD_SQSGLZCZXXService_;
    
    /**
     * 供应商注册证
     */
    @Resource
    protected MGAAC_YLQXZCZService MGAAC_YLQXZCZService_;
    
    /**
     * 事务类
     */
    @Resource
    protected MGA4Business MGA4Business_;

    /**
     * 获取我的经销授权书列表
     * 
     * @return
     * @Author:zhengxw
     * @Date:2014-10-20
     */
    @RequestMapping(value = "/3110400", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m3110400post(MGA4_JXSQSDBO param, String pageCurrent,
            String pageLimit) {

        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA4Controller#m3110400post()!");
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
        param.setK01_gysid(this.getCompanyId());
        super.pageModel.setFormParamBean(param);
        
        this.MGA4_JXSQSService_.doSelectPage(super.pageModel, false);
        
        JSONObject jsonRs = new JSONObject();
        jsonRs.put("list", super.pageModel.getPageListData());
        jsonRs.put("pageCount", super.pageModel.getResultCount());

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MGAACController#m3110400post()!");
        }
        RESTResultBean rs =  new RESTResultBean();
        rs.setResult(jsonRs);
        return rs;
    }
    
    /**
     * 跳转到添加或修改页面
     *
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-6
     */
    @RequestMapping(value = "/31104001", method = RequestMethod.GET)
    public ModelAndView m31104001post(String pukid) {
        MGA4_JXSQSDBO rs = new MGA4_JXSQSDBO();
        List<FrameworkDataBean> list = new ArrayList<FrameworkDataBean>();
        StringBuffer sb = new StringBuffer();
        JSONArray array = new JSONArray();
        // 如果ID不为空，则说明是修改，根据ID查询记录
        if (!StringUtils.isEmpty(pukid)) {
            MGA4_JXSQSDBO param = new MGA4_JXSQSDBO();
            param.setPuk(pukid);
            rs = (MGA4_JXSQSDBO) this.MGA4_JXSQSService_.doRead(param);
            // 查询已经勾选的注册证
//            MGAD_SQSGLZCZXXDBO mgadParam = new MGAD_SQSGLZCZXXDBO();
//            mgadParam.setPuk(pukid);
//            mgadParam.setK01_gysid(this.getCompanyId());
//            alreadyAddZcz = this.MGAD_SQSGLZCZXXService_.doSelectPage(mgadParam, false);
//            for (FrameworkDataBean bean : alreadyAddZcz) {
//                sb.append(bean.getPuk() + ",");
//            }
            
            MGAAC_YLQXZCZDBO mgaacParam = new MGAAC_YLQXZCZDBO();
            mgaacParam.setP01_gysid(this.getCompanyId());
            mgaacParam.setEb5(pukid);
            list = this.MGA4Business_.getAlreadyAddZcz(mgaacParam);
            for (FrameworkDataBean bean : list) {
                MGAAC_YLQXZCZDBO zczBean = (MGAAC_YLQXZCZDBO) bean;
                sb.append(zczBean.getPuk() + ",");
                JSONObject tmp = new JSONObject();
                tmp.put("id", bean.getPuk());
                tmp.put("zczh", zczBean.getF01_zczzwmc());
                tmp.put("cpmc", zczBean.getF03_cpzwmc());
                tmp.put("cjmc", zczBean.getF32_scqyzwmc());
                array.add(tmp);
            }
        }
        
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/authorize_add");
        view.addObject(USER_DATA, rs);
        view.addObject(PAGE, array.toString());
        if (sb.length() > 0) {
            view.addObject("zczIds", sb.substring(0, sb.length() - 1).toString());
        }
        return view;
    }
    
    /**
     * 添加或修改授权书入库
     *
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-12-6
     */
    @RequestMapping(value = "/31104002", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m31104002post(MGA4_JXSQSDBO param, String zczIds) {

        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA4Controller#m3110400post()!");
            logger.debug("param:{}, zczIds:{}", param, zczIds);
        }
        
        int rsNum = this.MGA4Business_.saveJxsqs(param, zczIds);

        if (logger.isDebugEnabled()) {
            logger.debug("Exit MGAACController#m3110400post()!");
        }
        RESTResultBean rs =  new RESTResultBean();
        if (StringUtils.isEmpty(param.getPuk()) && rsNum < 1) {
            rs.setMessage("311040022");
        } else if (StringUtils.isEmpty(param.getPuk()) && rsNum > 1){
            rs.setMessage("311040021");
        } else if (!StringUtils.isEmpty(param.getPuk()) && rsNum < 1) {
            rs.setMessage("311040024");
        } else if (!StringUtils.isEmpty(param.getPuk()) && rsNum > 1) {
            rs.setMessage("311040023");
        } 
        JSONObject url = new JSONObject();
        url.put("url", "/3110400");
        rs.setResult(url);
        return rs;
    }
    
    /**
     * 删除
     * @param pukid
     * @return
     */
    @RequestMapping(value = "/31104003/{pukid}", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m31104003post(@PathVariable String pukid) {

        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA4Controller#m3110400post()!");
            logger.debug("pukid:{}", pukid);
        }
        
        MGA4_JXSQSDBO param = new MGA4_JXSQSDBO();
        param.setPuk(pukid);
        int rsNum = this.MGA4_JXSQSService_.doDelete(param);

        if (logger.isDebugEnabled()) {
            logger.debug("Exit MGAACController#m3110400post()!");
        }
        RESTResultBean rs =  new RESTResultBean();
        if (rsNum > 0) {
            rs.setMessage("311040025");
        } else {
            rs.setMessage("311040026");
        }
        return rs;
    }
    
    /**
     * 检索我的注册证top10
     *
     * @param q
     * @return
     * @throws UnsupportedEncodingException 
     * @Author:zhengxw
     * @Date:2014-12-8
     */
    @RequestMapping(value = "/31104004", method = RequestMethod.GET)
    @ResponseBody
    public RESTResultBean m31104004post(String q) throws UnsupportedEncodingException {

        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA4Controller#m3110400post()!");
            logger.debug("q:{}", q);
        }
        q = URLDecoder.decode(q, "utf-8");
        super.pageModel.setPageCurrent(1);
        super.pageModel.setPageLimit(10);
        MGAAC_YLQXZCZDBO param = new MGAAC_YLQXZCZDBO();
        param.setP01_gysid(this.getCompanyId());
        param.setF01_zczzwmc(q);
        super.pageModel.setFormParamBean(param);
        List<FrameworkDataBean> list = this.MGAAC_YLQXZCZService_.doSelectPage(super.pageModel, false).getPageListData();
        
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            MGAAC_YLQXZCZDBO bean = (MGAAC_YLQXZCZDBO) list.get(i);
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", bean.getPuk());
            map.put("text", bean.getF01_zczzwmc() + " " + bean.getF03_cpzwmc());
            map.put("cpmc", bean.getF03_cpzwmc());
            map.put("cjmc", bean.getF32_scqyzwmc());
            map.put("zczh", bean.getF01_zczzwmc());
            array.add(map);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Exit MGAACController#m3110400post()!");
        }
        RESTResultBean rs =  new RESTResultBean();
        rs.setResult(array);
        return rs;
    }
}
