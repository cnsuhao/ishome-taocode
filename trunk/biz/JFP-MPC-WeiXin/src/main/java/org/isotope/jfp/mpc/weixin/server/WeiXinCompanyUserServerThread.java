package org.isotope.jfp.mpc.weixin.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.isotope.jfp.framework.common.CommonChannelConfig;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;

import com.alibaba.fastjson.JSONObject;
import com.hp.hpl.sparta.Document.Index;

/**
 * 微信企业用户管理<br>
 * 通讯录管理
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * @deprecated
 */
public class WeiXinCompanyUserServerThread extends CommonChannelConfig implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	/**
	 * 加载企业微信号配置信息
	 */
	public void loadCompanyConfig() {

	}

}
