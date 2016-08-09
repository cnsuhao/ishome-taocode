package org.isotope.jfp.common.login;

import java.util.HashMap;
import java.util.List;

import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 软件包信息 */
public interface LoginDao {
	/**
	 * 用户登录
	 * 
	 * @param loginer
	 * @return
	 */
	public List<UserBean> readTeacherLoginer(HashMap<String, String> loginer);
	public List<UserBean> readParentLoginer(HashMap<String, String> loginer);
	public List<UserBean> readStudentLoginer(HashMap<String, String> loginer);
	
	public int creatLoginerByOpenId(MyDataBaseObjectSupport loginer);

}
