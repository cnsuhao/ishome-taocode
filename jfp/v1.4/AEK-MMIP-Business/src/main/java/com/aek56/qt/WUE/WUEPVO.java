package com.aek56.qt.WUE;

import javax.inject.Named;

import org.jfpc.beans.common.MSSUU.MSSUUDBO;

@Named
/** 添加子账号*/
public class WUEPVO extends MSSUUDBO {
	private String roles ="";

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}


	

}
