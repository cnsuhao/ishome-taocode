package com.aek56.qt.gys.MGA5;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jfpc.beans.common.MSSUU.MSSUUDBO;
import org.jfpc.beans.common.MSSUU.MSSUUService;
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

import com.aek56.atm.company.MGYS3_GYSTJYYXX.MGYS3_GYSTJYYXXDBO;
import com.aek56.atm.company.MGYS3_GYSTJYYXX.MGYS3_GYSTJYYXXService;
import com.aek56.atm.credentials.MGA5_XSRYWTS.MGA5_XSRYWTSDBO;
import com.aek56.atm.credentials.MGA5_XSRYWTS.MGA5_XSRYWTSService;

/**
 * 供应商委托书管理
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MGA5Controller extends MyControllerSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(MGA5Controller.class);

    /**
     * 供应商法人委托书
     */
    @Resource
    protected MGA5_XSRYWTSService MGA5_XSRYWTSService_;
    
    /**
     * 业务员表
     */
    @Resource
    protected MSSUUService MSSUUService_;
    
    /**
     * 供应商添加医院
     */
    @Resource
    protected MGYS3_GYSTJYYXXService MGYS3_GYSTJYYXXService_;
    
    /**
     * 事务类
     */
    @Resource
    protected MGA5Business MDGBusiness_;

    /**
     * 获取我的法人委托书列表
     * 
     * @return
     * @Author:zhengxw
     * @Date:2014-10-20
     */
    @RequestMapping(value = "/3110500", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m3110500post(MGA5_XSRYWTSDBO param, String pageCurrent,
            String pageLimit) {

        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA5Controller#m3110500post()!");
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
        param.setK01_gysid("2542512542");
        super.pageModel.setFormParamBean(param);
        
        this.MGA5_XSRYWTSService_.doSelectPage(super.pageModel, false);

        JSONObject jsonRs = new JSONObject();
        jsonRs.put("list", super.pageModel.getPageListData());
        jsonRs.put("pageCount", super.pageModel.getResultCount());
        
        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MGA5Controller#m3110500post()!");
        }
        RESTResultBean rs =  new RESTResultBean();
        rs.setResult(jsonRs);
        return rs;
    }
    
    /**
     * 跳转到添加或者修改页面
     *
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    @RequestMapping(value = "/31105001", method = RequestMethod.GET)
    public ModelAndView m31105001post(String pukid) {
        MGA5_XSRYWTSDBO rs = new MGA5_XSRYWTSDBO();
        // 如果ID不为空，则说明是修改，根据ID查询记录
        if (!StringUtils.isEmpty(pukid)) {
            MGA5_XSRYWTSDBO param = new MGA5_XSRYWTSDBO();
            param.setPuk(pukid);
            rs = (MGA5_XSRYWTSDBO) this.MGA5_XSRYWTSService_.doRead(param);
        }
        
        // 查询人员列表
        MSSUUDBO ywyParam = new MSSUUDBO();
        ywyParam.setK01_ssqyid(this.getCompanyId());
        List<FrameworkDataBean> ywyList = this.MSSUUService_.doSelectPage(ywyParam, false);
        
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/weituoshu_add");
        view.addObject(USER_DATA, rs);
        view.addObject(PAGE, ywyList);
        return view;
    }
    
    /**
     * 保存添加或者修改信息
     *
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    @RequestMapping(value = "/31105002", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m31105002post(MGA5_XSRYWTSDBO param) {
    	String[] nameAndId = param.getF10().split("@");
    	param.setF09(nameAndId[0]);
    	param.setF10(nameAndId[1]);
    	int rsNum = 0;
        if (!StringUtils.isEmpty(param.getF10())) {
            param.setF08(param.getF10().substring(0, 1));
        }
        String messageCode = "";
        if (StringUtils.isEmpty(param.getPuk())) {
            param.setK01_gysid(this.getCompanyId());
            rsNum = this.MGA5_XSRYWTSService_.doInsert(param);
            messageCode = rsNum > 0 ? "311050021" : "311050022";  
        } else {
            rsNum = this.MGA5_XSRYWTSService_.doUpdate(param);
            messageCode = rsNum > 0 ? "311050023" : "311050024";  
        }
        
        RESTResultBean rs =  new RESTResultBean();
        rs.setMessage(messageCode);
        JSONObject url = new JSONObject();
        url.put("url", "/3110500");
        rs.setResult(url);
        return rs;
    }
    
    /**
     * 删除
     * @param pukid
     * @return
     */
    @RequestMapping(value = "/31105003/{pukid}", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m31105003post(@PathVariable String pukid) {
        
    	MGA5_XSRYWTSDBO param = new MGA5_XSRYWTSDBO();
    	param.setPuk(pukid);
        int rsNum = this.MGA5_XSRYWTSService_.doDelete(param);
        RESTResultBean rs =  new RESTResultBean();
        rs.setMessage(rsNum > 0 ? "311050031" : "311050032");
        rs.setResult(super.pageModel.getPageListData());
        return rs;
    }
    
    /**
     * 获取我的医院列表top10
     *
     * @param q
     * @return
     * @throws UnsupportedEncodingException 
     * @Author:zhengxw
     * @Date:2014-12-10
     */
    @RequestMapping(value = "/31105004", method = RequestMethod.GET)
    @ResponseBody
    public RESTResultBean m31105004post(String q) throws UnsupportedEncodingException {
        super.pageModel.setPageCurrent(1);
        super.pageModel.setPageLimit(10);
        q = URLDecoder.decode(q, "utf-8");
        MGYS3_GYSTJYYXXDBO param = new MGYS3_GYSTJYYXXDBO();
        param.setP01_gysid(this.getCompanyId());
        param.setF01_qyqc(q);
        super.pageModel.setFormParamBean(param);
        List<FrameworkDataBean> list = this.MGYS3_GYSTJYYXXService_.doSelectPage(super.pageModel, false).getPageListData();
        
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            MGYS3_GYSTJYYXXDBO bean = (MGYS3_GYSTJYYXXDBO) list.get(i);
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", bean.getPuk());
            map.put("text", bean.getF01_qyqc());
            array.add(map);
        }
        
        RESTResultBean rs =  new RESTResultBean();
        rs.setResult(array);
        return rs;
    }
}
