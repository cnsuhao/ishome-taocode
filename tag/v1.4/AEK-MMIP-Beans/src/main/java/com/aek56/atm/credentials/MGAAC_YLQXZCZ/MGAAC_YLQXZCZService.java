package com.aek56.atm.credentials.MGAAC_YLQXZCZ;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.company.MGYS3_GYSTJYYXX.MGYS3_GYSTJYYXXDBO;
import com.aek56.atm.company.MGYS3_GYSTJYYXX.MGYS3_GYSTJYYXXService;
import com.aek56.atm.company.MQC_PZZX.MQC_PZZXDBO;
import com.aek56.atm.company.cmp.CompanyService;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLDBO;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLService;
import com.aek56.atm.credentials.MGTA_CJYLQXZCZ.MGTA_CJYLQXZCZDBO;
import com.aek56.utils.CredentialsHelper;

/** 厂家医疗器械注册证*/
@Service
public class MGAAC_YLQXZCZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGAAC_YLQXZCZService.class);

    /**
     * 推送记录
     */
    @Resource
    private CGTZ_TSJLService CGTZ_TSJLService_;
    
    /**
     *  供应商信息
     */
    @Resource
    private CompanyService CompanyService_;
    
    public MGAAC_YLQXZCZDao getDao(){
        return getMySqlSession().getMapper(MGAAC_YLQXZCZDao.class);
    }
    
    /**
	 * 数据库分表
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data) {
		CredentialsHelper.getCode(data);//根据注册证号返回68码
		MGAAC_YLQXZCZDBO md = (MGAAC_YLQXZCZDBO)data;
		md.setGgg(EMPTY);
		//生产企业拼音码
		if(EmptyHelper.isEmpty(md.getF37_scqypym())){
   			md.setF37_scqypym(StringHelper.getPinYinSample(md.getF32_scqyzwmc()));
   		}
//		String companyType = ((XXXXXDBO)data).getT01();
//		// 分表处理
//		if (ZERO.equals(companyType)) {
//			data.setTablename("0");
//		} else if (ONE.equals(companyType)) {
//			data.setTablename("1");
//		}
	}
	
	
	/**
     *  供应商添加医院信息
     */
    @Resource
    private MGYS3_GYSTJYYXXService MGYS3_GYSTJYYXXService_;
	
	/**
	 * 换证自动审核
	 * @param data
	 */
	public void auditingLog(MyDataBaseObjectSupport data) {
		MGAAC_YLQXZCZDBO md = (MGAAC_YLQXZCZDBO)data;
		//证件标识（换证原因）P02_ZJBS，1新增2变更3替换4修正
		if(ONE.equals(md.getP02_zjbs())){
			return;
		}
		
		///////////////////////////判断推送给哪些医院(已经审核通过)///////////////////////////
		//企业信息
		MGYS0_JBXXDBO cmp = (MGYS0_JBXXDBO) CompanyService_.myInfo(md.getP01_gysid(), ONE);
		MQC_PZZXDBO cfg = CompanyService_.myConfig(md.getP01_gysid());
		//已经接收该证件的
		MGTA_CJYLQXZCZDBO
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//供应商医院信息
		MGYS3_GYSTJYYXXDBO mgys3 = new MGYS3_GYSTJYYXXDBO();//实例化供应商添加医院实例
		mgys3.setP01_gysid(md.getP01_gysid());
		
		//好友关系
		List<FrameworkDataBean> listMgys3 = MGYS3_GYSTJYYXXService_.doSelectPage(mgys3, false);
		if(listMgys3 !=null && listMgys3.size()>0){
			CGTZ_TSJLDBO cgtz = new CGTZ_TSJLDBO();//创建推送表实例
			cgtz.setK01_gysid(md.getP01_gysid());//设置供应商ID
			
			cgtz.setFb1(cmp.getF01_qyqc());//设置企业全称
			cgtz.setFb2(cmp.getF30_lxrxm());//设置联系人姓名
			cgtz.setFb3(cmp.getF32_lxrdh()+"  "+cmp.getF16_lxdh());//设置联系人电话
			cgtz.setF13_shzt("2");//设置审核状态
			cgtz.setFb4("a");
			
			cgtz.setBbb(md.getBbb());//图片路径
			cgtz.setFb5("A");
			cgtz.setFb5(md.getP02_zjbs());//证件标识（换证原因）P02_ZJBS，1新增2变更3替换4修正
			
			//证件信息ID
			cgtz.setF10_cjylqxzcz(md.getPuk());//设置注册证ID,备注:id+,
			//厂商名称
			cgtz.setEb1(md.getF32_scqyzwmc());										
			//证件号																	
			cgtz.setEb2(md.getF01_zczzwmc());										
			//开始日期																	
			cgtz.setEb3(md.getF09_yxksrq());										
			//终了日期																	
			cgtz.setEb4(md.getF10_yxzzrq());										
			//原始证件ID								
			cgtz.setEb5(md.getK01_zczid());
			
			cgtz.setGgg(ONE);//1换证2推送
			for(int i=0;i<listMgys3.size();i++){
	            cgtz.makePuk();
				cgtz.setK02_yyid(((MGYS3_GYSTJYYXXDBO)listMgys3.get(i)).getPuk());
				CGTZ_TSJLService_.doInsert(cgtz);//插入推送表
				//换证自动审核
				if(ZERO.equals(cfg.getF11())){
					CompanyService_.auditingCredentialsWithAuto(cgtz);
				}
			}
		}
	}
	
	public int doInsert(MyDataBaseObjectSupport formParamBean) {
		auditingLog(formParamBean);
		return super.doInsert(formParamBean);
	}
	
	public int doUpdate(MyDataBaseObjectSupport formParamBean) {
		auditingLog(formParamBean);
		return super.doUpdate(formParamBean);		
	}
	
	public FrameworkDataBean loadNewCredentials(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean);
		MGAAC_YLQXZCZDBO mdb = (MGAAC_YLQXZCZDBO) getDao().loadNewCredentials(formParamBean);
		if(mdb==null)
			mdb= new MGAAC_YLQXZCZDBO();
		return mdb;
	}
}
