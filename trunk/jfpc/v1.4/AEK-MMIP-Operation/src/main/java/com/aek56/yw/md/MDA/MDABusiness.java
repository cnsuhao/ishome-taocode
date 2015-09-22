package com.aek56.yw.md.MDA;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.helper.PKHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek56.atm.master.MDA_CPXXX.MDA_CPXXXDBO;
import com.aek56.atm.master.MDA_CPXXX.MDA_CPXXXService;
import com.aek56.atm.master.MDB_CPXFLXX.MDB_CPXFLXXDBO;
import com.aek56.atm.master.MDB_CPXFLXX.MDB_CPXFLXXService;

/**
 * 产品线维护事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class MDABusiness extends MyServiceSupport {
    /**
     * 产品线主数据表
     */
    @Resource
    protected MDA_CPXXXService MDA_CPXXXService_;
    
    /**
     * 产品线分类
     */
    @Resource
    protected MDB_CPXFLXXService MDB_CPXFLXXService_;

	/**
	 * 添加产品线和产品分类
	 * 
	 * @param param
	 */
	@Transactional
	public void doInsertCpx(MDAPvo param) {
		// 拆分产品线对象
	    MDA_CPXXXDBO mdaParam = new MDA_CPXXXDBO();
	    BeanUtils.copyProperties(param, mdaParam);
	    mdaParam.setPuk(PKHelper.creatPUKey());
	    
	    // 拆分产品线分类对象
	    List<MDB_CPXFLXXDBO> cpxlbList = param.getCpxlbList();
	    List<MyDataBaseObjectSupport> mdbList = new ArrayList<MyDataBaseObjectSupport>();
	    
	    // 给每个bean添加产品线和厂家ID
	    for (MDB_CPXFLXXDBO bean : cpxlbList) {
	        bean.setK01_scxid(mdaParam.getPuk());
	        bean.setK02_csid(mdaParam.getK01_csid());
	        mdbList.add(bean);
	    }
	    
	    // 分别入库
	    this.MDA_CPXXXService_.doInsert(mdaParam);
	    this.MDB_CPXFLXXService_.doInsert(mdbList);
	}
	
	/**
	 * 修改产品线以及产品分类
	 *
	 * @param param
	 * @Author:zhengxw
	 * @Date:2014-12-2
	 */
	@Transactional
    public void doUpdateCpx(MDAPvo param) {
        // 拆分产品线对象
        MDA_CPXXXDBO mdaParam = new MDA_CPXXXDBO();
        BeanUtils.copyProperties(param, mdaParam);
        
        // 拆分产品线分类对象
        List<MDB_CPXFLXXDBO> cpxlbList = param.getCpxlbList();
        List<MyDataBaseObjectSupport> mdbList = new ArrayList<MyDataBaseObjectSupport>();
        
        // 给每个bean添加产品线和厂家ID
        for (MDB_CPXFLXXDBO bean : cpxlbList) {
            bean.setK01_scxid(mdaParam.getPuk());
            bean.setK02_csid(mdaParam.getK01_csid());
            mdbList.add(bean);
        }
        
        // 分别入库
        this.MDA_CPXXXService_.doUpdate(mdaParam);
        
        // 先删除，再入库
        MDB_CPXFLXXDBO mdbParam = new MDB_CPXFLXXDBO();
        mdbParam.setK02_csid(mdaParam.getK01_csid());
        mdbParam.setK01_scxid(mdaParam.getPuk());
        this.getDao().toDeleteCpxfl(mdbParam);
        this.MDB_CPXFLXXService_.doInsert(mdbList);
    }
	
	/**
	 * 根据产品线ID获取产品线信息和产品线分类信息
	 *
	 * @param pukid
	 * @return
	 * @Author:zhengxw
	 * @Date:2014-12-2
	 */
	public MDAPvo getCpxAndCpflByID(String pukid) {
	    MDAPvo rs = new MDAPvo();
	    // 获取产品线信息
        MDA_CPXXXDBO param = new MDA_CPXXXDBO();
        param.setPuk(pukid);
        MDA_CPXXXDBO mdaRs = (MDA_CPXXXDBO) this.MDA_CPXXXService_.doRead(param);
        BeanUtils.copyProperties(mdaRs, rs);
        
        // 获取产品线分类信息
        MDB_CPXFLXXDBO mdbParam = new MDB_CPXFLXXDBO();
        mdbParam.setK01_scxid(pukid);
        List<FrameworkDataBean> mdbList = this.MDB_CPXFLXXService_.doSelectPage(mdbParam, false);
        List<MDB_CPXFLXXDBO> cpxlbList = new ArrayList<MDB_CPXFLXXDBO>();
        for (FrameworkDataBean bean : mdbList) {
            cpxlbList.add((MDB_CPXFLXXDBO) bean);
        }
        rs.setCpxlbList(cpxlbList);
        return rs;
	}
	
	public MDA01Dao getDao(){
        return getMySqlSession().getMapper(MDA01Dao.class);
    }

}