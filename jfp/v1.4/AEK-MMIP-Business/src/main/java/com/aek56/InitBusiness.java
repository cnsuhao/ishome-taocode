package com.aek56;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.beans.common.MS0M1.MS0M1DBO;
import org.jfpc.beans.common.MS0M1.MS0M1Service;
import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.support.MyFrameworkSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aek56.constants.ISCredentialsConstants;

/**
 * 系统初始化
 * 
 * @author Administrator
 * 
 */
@Component
@Scope("singleton")
public class InitBusiness extends MyFrameworkSupport implements ISFrameworkConstants {
	
	@Resource
	MS0M1Service MS0M1Service_;
	
	//////////////////////////////////////////////////////
	/**
	 * 获得合作伙伴信息，存放到缓存中
	 */
	List<FrameworkDataBean> friends = new ArrayList<FrameworkDataBean>();
	public void loadFriends() {
		if(friends.size()!=0) return;
		// 数据字典
		MS0M1DBO md = new MS0M1DBO();
		md.setK01_dflid(ISCredentialsConstants.QYHZHB);
		friends = MS0M1Service_.doSelectPage(md,false);		
		setSessionAttribute("friends", friends);
	}
	
}
