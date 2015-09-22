package com.aek56.qt.WUE;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.beans.common.MS1C4.MS1C4DBO;
import org.jfpc.beans.common.MS1C4.MS1C4Service;
import org.jfpc.beans.common.MSSUU.MSSUUDBO;
import org.jfpc.beans.common.MSSUU.MSSUUService;
import org.jfpc.constants.ISCommonConstants;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 子账号管理
 */
@Service
public class WUEBusiness extends MyServiceSupport implements ISCommonConstants {

	protected static final Logger logger = LoggerFactory.getLogger(WUEBusiness.class);

	/**
	 * 用户基本信息
	 */
	@Resource
	protected MSSUUService mssuuService;

	/**
	 * 企业用户所属角色
	 */
	@Resource
	protected MS1C4Service ms1c4Service;
	
	/**
	 * 一览
	 * @return
	 */
	@Transactional
	public List<WUEPVO> listPOST(){
		MSSUUDBO param = new MSSUUDBO();
		List<WUEPVO> result = new ArrayList<WUEPVO>();
		//设置企业ID
		param.setK01_ssqyid(super.getCompanyId());
		List<FrameworkDataBean> relist = mssuuService.doSelectPage(param, false);
		if(relist!=null && relist.size()>0){
			for (FrameworkDataBean fb : relist) {
				MSSUUDBO mb = (MSSUUDBO)fb;
				WUEPVO wp = new WUEPVO();
				BeanUtils.copyProperties(mb, wp);
				//获取权限
				MS1C4DBO ms1 = new MS1C4DBO();
				ms1.setPuk(wp.getPuk());
				List<FrameworkDataBean> rlist = ms1c4Service.doSelectPage(ms1, false);
				String roles = "";
				for(int i=0;i<rlist.size();i++){
					MS1C4DBO t= (MS1C4DBO)rlist.get(i);
					
					if(i==(rlist.size()-1))
						roles+=t.getK02_jsid();
					else
						roles+=t.getK02_jsid()+",";
				}
				wp.setRoles(roles);
				result.add(wp);
			}
		}
		return result;
	}
	
	
	/**
	 * 添加帐号
	 * 
	 * @param user
	 * @return
	 */
	@Transactional
	public void addUser(WUEPVO user) {
		// 所属企业ID
		user.setK01_ssqyid(super.getCompanyId());
		// 用户类别（0运维人员1供应商2医院3厂商6研究人员7公众9客服）
		user.setF02_yhlb(super.getLoginerBean().getUserType());
		// 激活状态
		user.setF03_jhzt("0");// 0已经激活1未激活
		// 用户等级
		user.setF04_yhdj("1");// 0超级管理员1普通用户
		// 用户ID
		user.makePuk();
		//密码加密
		String passWord = new Md5PasswordEncoder().encodePassword(user.getK04_dlmm(), null);
		user.setK04_dlmm(passWord);
		if(user.getRoles()!=null &&!user.getRoles().isEmpty()){
		String[] roles = user.getRoles().split(",");
			if (roles != null) {
				MS1C4DBO appli;
				// 添加用户较色关联
				for (String role : roles) {
					appli = new MS1C4DBO();
					appli.setPuk(user.getPuk());
					appli.setK01_qyid(user.getK01_ssqyid());
					appli.setK02_jsid(role);
					ms1c4Service.doInsert(appli);
				}
			}
		}
		// 添加基本数据
		mssuuService.doInsert(user);
	}
	/**
	 * 删除账号
	 * @param user
	 */
	@Transactional
	public void delUser(WUEPVO user){
		//删除企业用户基本信息
		mssuuService.toDelete(user);
		//删除关联角色
		MS1C4DBO p = new MS1C4DBO();
		p.setPuk(user.getPuk());
		ms1c4Service.doDelete(p);
	}
	
	/**
	 * 获取成员明细
	 * @param user
	 * @return
	 */
	public WUEPVO detailUser(WUEPVO user){
		//获取基本信息
		WUEPVO base = (WUEPVO)mssuuService.doRead(user);
		//获取关联角色
		MS1C4DBO p= new MS1C4DBO();
		p.setPuk(user.getPuk());
		List<FrameworkDataBean> rlist = ms1c4Service.doSelectPage(p, false);
		if(rlist!=null && rlist.size()>0){
			String roles = "";
			for(int i=0;i<rlist.size();i++){
				MS1C4DBO t= (MS1C4DBO)rlist.get(i);
				
				if(i==(rlist.size()-1))
					roles+=t.getK02_jsid();
				else
					roles+=t.getK02_jsid()+",";
			}
			base.setRoles(roles);
		}
		return base;
	}
	/**
	 * 修改权限
	 * @param user
	 */
	public void updateRole(WUEPVO user){
		//删除所有角色
		MS1C4DBO param = new MS1C4DBO();
		param.setPuk(user.getPuk());
		ms1c4Service.doDelete(param);
		//添加新的角色
		if (user.getRoles() != null && !user.getRoles().isEmpty()) {
			MS1C4DBO appli;
			// 添加用户较色关联
			String[] roles = user.getRoles().split(",");
			for (String role : roles) {
				appli = new MS1C4DBO();
				appli.setPuk(user.getPuk());
				appli.setK01_qyid(super.getCompanyId());
				appli.setK02_jsid(role);
				ms1c4Service.doInsert(appli);
			}
		}	
	}
}
