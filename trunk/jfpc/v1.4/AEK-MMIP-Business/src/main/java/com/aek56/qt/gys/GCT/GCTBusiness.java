package com.aek56.qt.gys.GCT;

import java.util.List;

import javax.annotation.Resource;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLDBO;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLService;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZDBO;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZService;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZService;
import com.aek56.atm.credentials.MGA3_GSSWDJZ.MGA3_GSSWDJZService;
import com.aek56.atm.credentials.MGA4_JXSQS.MGA4_JXSQSService;

/**
 * 推送事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class GCTBusiness extends MyServiceSupport {
    /**
     * 营业执照
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
    
    protected MGA4_JXSQSService MGA4_JXSQSService_;
    
    /**
     * 供应商推送记录表
     */
    @Resource
    protected CGTZ_TSJLService CGTZ_TSJLService_;
    
    /**
     * 推送所有证书
     *
     * @Author:zhengxw
     * @Date:2014-12-9
     */
    public int sentAllZs(String yyid, String zczids) {
    	String gysid = this.getCompanyId();
        // 获取所有推送的证书ID
        MGA1_YYZZDBO param = new MGA1_YYZZDBO();
        param.setK01_gysid(gysid);
        param.setEb1(zczids);
        param.setEb2(yyid);
        
        List<FrameworkDataBean> list = this.getDao().getMyAllZs(param);
        
        // 插入结果集
        CGTZ_TSJLDBO insertRs = new CGTZ_TSJLDBO();
        insertRs.setK01_gysid(gysid);
        insertRs.setK02_yyid(yyid);
        
        // 各个证书ID集合，以","隔开
        StringBuffer wtsIds = new StringBuffer(), jxsqsIds = new StringBuffer(), 
                cjyyzzIds = new StringBuffer(), cjscxkzIds = new StringBuffer(), cnsIds = new StringBuffer();
        
        // 各个证书数量统计
        int yyzzNum = 0, swdjzNum = 0, jyxkzNum = 0, jxsqsNum = 0, wtsNum = 0,
                cnsNum = 0, cjyyzzNum = 0, cjscxkzNum = 0;
        
        // 分割符
        String separator = ",";
        
        for (int i = 0; i < list.size(); i++) {
            MGA1_YYZZDBO dbo = (MGA1_YYZZDBO) list.get(i);
            int zsType = Integer.parseInt(dbo.getEb1());
            String puk = dbo.getPuk();
            if (i == list.size() - 1) {
                separator = "";
            }
            
            switch (zsType) {
                case 1 : 
                    yyzzNum = 1;
                	if (StringUtils.isEmpty(insertRs.getF01_yyzz())) {
                		insertRs.setF01_yyzz(puk);
                	}
                	break;
                case 2 : 
                    jyxkzNum = 1;
                	if (StringUtils.isEmpty(insertRs.getF02_jyxkz())) {
                		insertRs.setF02_jyxkz(puk);
                	}
                	break;
                case 3 : 
                    swdjzNum = 1;
                	if (StringUtils.isEmpty(insertRs.getF03_gsswdjz())) {
                		insertRs.setF03_gsswdjz(puk);
                	}
                	break;
                case 4 : 
                    jxsqsNum++;
                	jxsqsIds.append(puk + separator);
                	break;
                case 5 : 
                    wtsNum++;
                	wtsIds.append(puk + separator);
                	break;
                case 6 : 
                    cnsNum++;
                	cnsIds.append(puk + separator);
                	break;
                case 7 : 
                    cjyyzzNum++;
                	cjyyzzIds.append(puk + separator);
                	break;
                case 8 : 
                    cjscxkzNum++;
                	cjscxkzIds.append(puk + separator);
                	break;
            }
        }
        insertRs.setF04_jxsqs(jxsqsIds.toString());
        insertRs.setF05_xsrywts(wtsIds.toString());
        insertRs.setF08_cjyyzz(cjyyzzIds.toString());
        insertRs.setF09_cjscxkz(cjscxkzIds.toString());
        insertRs.setF10_cjylqxzcz(zczids);
        insertRs.setF06_shfwcns(cnsIds.toString());
        insertRs.setGgg(TWO);
        insertRs.setFb1(this.getLoginerBean().getCompanyNameCN());
        insertRs.setFb2(this.getLoginerBean().getUserNameCN());
        insertRs.setFb3(this.getLoginerBean().getCompanyPhone());
        insertRs.setFb5(ONE);
        insertRs.setN01_yyzz(String.valueOf(yyzzNum));
        insertRs.setN02_jyxkz(String.valueOf(jyxkzNum));
        insertRs.setN03_gsswdjz(String.valueOf(swdjzNum));
        insertRs.setN04_jxsqs(String.valueOf(jxsqsNum));
        insertRs.setN05_xsrywts(String.valueOf(wtsNum));
        insertRs.setN06_shfwcns(String.valueOf(cnsNum));
        insertRs.setN07_jksjbg(ZERO);
        insertRs.setN08_cjyyzz(String.valueOf(cjyyzzNum));
        insertRs.setN09_cjscxkz(String.valueOf(cjscxkzNum));
        insertRs.setN10_cjylqxzcz(String.valueOf(zczids.split(",").length));
        insertRs.setN11_cjhcsprz(ZERO);
        insertRs.setN12_cjxdcpwsxkz(ZERO);
        insertRs.setF13_shzt(TWO);
        
        // 判断是否已有数据
//        CGTZ_TSJLDBO cgtzParam = new CGTZ_TSJLDBO();
//        cgtzParam.setK01_gysid(gysid);
//        cgtzParam.setK02_yyid(yyid);
//        List<FrameworkDataBean> cgtzList = this.CGTZ_TSJLService_.doSelectPage(cgtzParam, false);
        
        int updateNum = 1;
//        if (null == cgtzList || cgtzList.size() < 1) {
            this.CGTZ_TSJLService_.doInsert(insertRs);
//        } else {
//            insertRs.setPuk(cgtzList.get(0).getPuk());
//            this.CGTZ_TSJLService_.doUpdate(insertRs);
//        }
        return updateNum;
    }
    
    /**
     * 获取未推送的注册证
     * 
     * @param formParam
     * @Author:zhengxw
     * @Date:2014-11-29
     */
    public void doSelectPageUnsentZcz(PageVOSupport formParam) {
        formParam.setPageListData(getDao().doSelectPageUnsentZcz(formParam));
    }
    
    public List<FrameworkDataBean> doSelectPageUnsentZcz(MyDataBaseObjectSupport paramBean) {
        return getDao().doSelectPageUnsentZcz(paramBean);
    }
    
    /**
     * 业务员列表
     * 
     * @param formParam
     * @Author:zhengxw
     * @Date:2014-11-29
     */
    public List<FrameworkDataBean> getYwyAndState(MyDataBaseObjectSupport paramBean) {
        return this.getDao().getYwyAndState(paramBean);
    }
    
    public GCTDao getDao(){
        return getMySqlSession().getMapper(GCTDao.class);
    }
}