package com.aek56.qt.gys.MGA4;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.helper.PKHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aek56.atm.credentials.MGA4_JXSQS.MGA4_JXSQSDBO;
import com.aek56.atm.credentials.MGA4_JXSQS.MGA4_JXSQSService;
import com.aek56.atm.credentials.MGAD_SQSGLZCZXX.MGAD_SQSGLZCZXXDBO;
import com.aek56.atm.credentials.MGAD_SQSGLZCZXX.MGAD_SQSGLZCZXXService;

/**
 * 供应商维护注册证事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class MGA4Business extends MyServiceSupport {
    /**
     * 供应商经销授权书
     */
    @Resource
    protected MGA4_JXSQSService MGA4_JXSQSService_;
    
    /**
     * 经销授权书和注册证关联表
     */
    @Resource
    protected MGAD_SQSGLZCZXXService MGAD_SQSGLZCZXXService_;
    
    /**
     * 添加或修改授权书入库
     * 
     * @param param
     * @param zczIds
     * @Author:zhengxw
     * @Date:2014-11-29
     */
    @Transactional
    public int saveJxsqs(MGA4_JXSQSDBO param, String zczIds) {
        // 供应商ID
        String gysid = this.getCompanyId();
        // 授权书入库
        param.setK01_gysid(gysid);
        int rs = 0;
        if (StringUtils.isEmpty(param.getPuk())) {
            param.setPuk(PKHelper.creatPUKey());
            rs = this.MGA4_JXSQSService_.doInsert(param);
        } else {
            rs = this.MGA4_JXSQSService_.doUpdate(param);
            MGAD_SQSGLZCZXXDBO mgadParam = new MGAD_SQSGLZCZXXDBO();
            mgadParam.setPuk(param.getPuk());
            mgadParam.setK01_gysid(gysid);
            this.getDao().doDelete(mgadParam);
        }
        // 授权书ID
        String sqsId = param.getPuk();
        
        // 授权书和注册证关联表入库
        if (!StringUtils.isEmpty(zczIds)) {
            List<MyDataBaseObjectSupport> rsList = new ArrayList<MyDataBaseObjectSupport>();
            String[] zczIdArray = zczIds.split(",");
            for (String zczid : zczIdArray) {
                MGAD_SQSGLZCZXXDBO dbo = new MGAD_SQSGLZCZXXDBO();
                dbo.setPuk(sqsId);
                dbo.setK02_zczid(zczid);
                dbo.setK01_gysid(gysid);
                rsList.add(dbo);
            }
//            MGAD_SQSGLZCZXXDBO deleteParam = new MGAD_SQSGLZCZXXDBO();
//            deleteParam.setPuk(PUK);
//            deleteParam.setK01_gysid(gysid);
//            this.MGAD_SQSGLZCZXXService_.doDelete(deleteParam);
            this.MGAD_SQSGLZCZXXService_.doInsert(rsList);
        }
        return rs;
    }
    
    public List<FrameworkDataBean> getAlreadyAddZcz(MyDataBaseObjectSupport paramBean) {
        return this.getDao().getAlreadyAddZcz(paramBean);
    }
    
    public MGA4Dao getDao(){
        return getMySqlSession().getMapper(MGA4Dao.class);
    }
}