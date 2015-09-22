package com.aek56.qt.gys.MGA1;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZDBO;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZService;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZDBO;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZService;
import com.aek56.atm.credentials.MGA3_GSSWDJZ.MGA3_GSSWDJZDBO;
import com.aek56.atm.credentials.MGA3_GSSWDJZ.MGA3_GSSWDJZService;

/**
 * 供应商营业执照管理
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MGA1Controller extends MyControllerSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(MGA1Controller.class);

    /**
     * 供应商营业执照
     */
    @Resource
    protected MGA1_YYZZService MGA1_YYZZService_;
    
    /**
     * 经营许可证
     */
    @Resource
    protected MGA2_JYXKZService MGA2_JYXKZService_;
    
    /**
     * 税务登记证
     */
    @Resource
    protected MGA3_GSSWDJZService MGA3_GSSWDJZService_;
    
    /**
     * 事务类
     */
    @Resource
    protected MGA1Business MGA1Business_;

    /**
     * 获取我的三本基础证件
     * 
     * @return
     * @Author:zhengxw
     * @Date:2014-10-20
     */
    @RequestMapping(value = "/3110100", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m3110100post() {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA1Controller#m3110100post()!");
        }

        // 查询供应商营业执照
        MGA1_YYZZDBO mga1Param = new MGA1_YYZZDBO();
        mga1Param.setK01_gysid(this.getCompanyId());
        List<FrameworkDataBean> mga1List = this.MGA1_YYZZService_.doSelectPage(mga1Param, false);
        MGA1_YYZZDBO mga1Rs = (MGA1_YYZZDBO) (mga1List == null || mga1List.size() < 1 ? new MGA1_YYZZDBO() : mga1List.get(0));

        // 查询供应商经营许可证
        MGA2_JYXKZDBO mga2Param = new MGA2_JYXKZDBO();
        mga2Param.setK01_gysid(this.getCompanyId());
        List<FrameworkDataBean> mga2List = this.MGA2_JYXKZService_.doSelectPage(mga2Param, false);
        MGA2_JYXKZDBO mga2Rs = (MGA2_JYXKZDBO) (mga2List == null || mga2List.size() < 1 ? new MGA2_JYXKZDBO() : mga2List.get(0));
        
        // 查询供应商税务登记证
        MGA3_GSSWDJZDBO mga3Param = new MGA3_GSSWDJZDBO();
        mga3Param.setK01_gysid(this.getCompanyId());
        List<FrameworkDataBean> mga3List = this.MGA3_GSSWDJZService_.doSelectPage(mga3Param, false);
        MGA3_GSSWDJZDBO mga3Rs = (MGA3_GSSWDJZDBO) (mga3List == null || mga3List.size() < 1 ? new MGA3_GSSWDJZDBO() : mga3List.get(0));
        
        if (logger.isDebugEnabled()) {
            logger.debug("Exit MGA1Controller#m3110100post()!");
        }
        JSONObject josnObj = new JSONObject();
        josnObj.put("yyzz", mga1Rs);
        josnObj.put("jyxkz", mga2Rs);
        josnObj.put("swdjz", mga3Rs);
        RESTResultBean rs =  new RESTResultBean();
        rs.setResult(josnObj);
        return rs;
    }
    
    /**
     * 提交基本证件数据
     *
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-12-8
     */
    @RequestMapping(value = "/3110101", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m3110101post(MGA1_YYZZDBO param) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA1Controller#m3110100post()!");
        }
        int rsNum = this.MGA1Business_.optGysZj(param);
        RESTResultBean rs =  new RESTResultBean();
        if (rsNum < 3) {
            rs.setCode("9");
            rs.setMessage("311010102");
        } else {
            rs.setMessage("311010101");
        }
        return rs;
    }
}
