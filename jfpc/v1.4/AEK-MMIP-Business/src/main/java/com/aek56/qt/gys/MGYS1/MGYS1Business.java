package com.aek56.qt.gys.MGYS1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aek56.atm.company.MGYS1_JYFW.MGYS1_JYFWDBO;
import com.aek56.atm.company.MGYS1_JYFW.MGYS1_JYFWService;
import com.aek56.atm.master.MD1_QYJYFW.MD1_QYJYFWDBO;

/**
 * 供应商维护经营范围事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class MGYS1Business extends MyServiceSupport {
		
    /**
     * 供应商经营范围表
     */
    @Resource
    protected MGYS1_JYFWService MGYS1_JYFWService_;
    
    
    public List<Map<String,String>> loadAllJYFW(){
    	return getDao().loadAllJYFW(new MD1_QYJYFWDBO());
    }
    public List<Map<String,String>> loadAllJYFW123(){
    	return getDao().loadAllJYFW123(new MD1_QYJYFWDBO());
    }
    /**
     * 保存经营范围
     * 
     * @param saveNodeInfos
     * @Author:zhengxw
     * @Date:2014-11-29
     */
    @Transactional
    public void saveJyfw(String saveNodeInfos) {
        // 入库结果集
        List<MyDataBaseObjectSupport> insertList = new ArrayList<MyDataBaseObjectSupport>();
        
        // 先删除所有的记录
        MGYS1_JYFWDBO param = new MGYS1_JYFWDBO();
        param.setP01_gys_qyid(this.getCompanyId());
        this.getDao().doDelete(param);
        
        // 如果字符串不为空，则添加勾选的记录
        if (!StringUtils.isEmpty(saveNodeInfos)) {
            String[] nodes = saveNodeInfos.split(",");
            for (int i = 0; i < nodes.length; i++) {
                String[] nodeTmp = nodes[i].split("\\|");
                MGYS1_JYFWDBO dbo = new MGYS1_JYFWDBO();
                String pukid = nodeTmp[0];
                dbo.setP01_gys_qyid(this.getCompanyId());
                dbo.setPuk(pukid);
                
                dbo.setF01_zdmc(nodeTmp[1]);
                dbo.setF02_pxwz(nodeTmp[6]);
                
                dbo.setK03_xflid(nodeTmp[2]);
                dbo.setN03_xflmc(nodeTmp[3]);
                
                dbo.setN02_zflmc(nodeTmp[4]);
                dbo.setK02_zflid(pukid.substring(0, 6));
                
                dbo.setN01_dflmc(nodeTmp[5]);
                dbo.setK01_dflid(pukid.substring(0, 4));
                
                insertList.add(dbo);
            }
            this.MGYS1_JYFWService_.doInsert(insertList);
        }
    }
    
    /**
     * 经营范围68码树
     *
     * @param paramBean
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    public List<HashMap<String, String>> doSearch68Tree(MyDataBaseObjectSupport paramBean) {
        return this.getDao().doSearch68Tree(paramBean);
    }
    
    /**
     * 经营范围分类树
     *
     * @param paramBean
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    public List<HashMap<String, String>> doSearchFlTree(MyDataBaseObjectSupport paramBean) {
        return this.getDao().doSearchFlTree(paramBean);
    }
    
    public MGYS1Dao getDao(){
        return getMySqlSession().getMapper(MGYS1Dao.class);
    }
}