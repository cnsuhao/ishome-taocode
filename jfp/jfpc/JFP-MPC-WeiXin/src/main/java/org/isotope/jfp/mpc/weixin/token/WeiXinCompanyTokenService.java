package org.isotope.jfp.mpc.weixin.token;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeiXinCompanyDBO;
import org.isotope.jfp.common.weixin.WeiXinCompanyGroupDBO;
import org.isotope.jfp.common.weixin.WeiXinCompanyGroupUserDBO;
import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinJsapiSignature;
import org.isotope.jfp.mpc.weixin.txapi.TxWeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.messagebuilder.NewsBuilder;

/**
 * 微信企业Token对接
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Service("WeiXinCompanyTokenService")
public class WeiXinCompanyTokenService implements ISFrameworkConstants, ISWeixinConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 等待时间（小时）
	 */
	private int waitTime = 3600 * 6;

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime * 3600;
	}

	// 缓存定义
	@Resource
	ICacheService myCatch;

	/**
	 * 基于缓存加载企业Token，由企业id+管理组凭证密钥为唯一标识
	 */
	public WeiXinCompanyTokenBean loadCompanyToken(WeiXinCompanySenderBean company) {
		WeiXinCompanyDBO sender = new WeiXinCompanyDBO();
		BeanUtils.copyProperties(company, sender);
		return loadWeixinCompanyToken(sender);
	}

	public WeiXinCompanyTokenBean loadCompanyToken(WeiXinCompanyDBO company) {
		if (company == null)
			return null;
		myCatch.selectDB(9);
		String key = TOKEN_Company + company.getCompanyId();// + ":" +
															// TOKEN_CorpSecret
															// +
															// company.getCorpSecret()
		WeiXinCompanyTokenBean comanyToken = JSONObject.parseObject((String) myCatch.getObject(key, false), WeiXinCompanyTokenBean.class);
		if (comanyToken == null) {
			comanyToken = loadWeixinCompanyToken(company);
			if (comanyToken == null) {
				return null;
			}
			myCatch.putObject(key, JSONObject.toJSONString(comanyToken), waitTime, false);
		}
		myCatch.init();
		return comanyToken;
	}

	/**
	 * 基于APi获得用户Token
	 * 
	 * @param company
	 * @return
	 */
	private WeiXinCompanyTokenBean loadWeixinCompanyToken(WeiXinCompanyDBO company) {
		// getAccessToken,登陆后，仅仅得到使用token内容
		WeiXinCompanyTokenBean config = new WeiXinCompanyTokenBean();
		config.setCorpId(company.getAppId());
		config.setCorpSecret(company.getAppSecret());
		TxWeixinService wxCpService = new TxWeixinService(config);
		try {
			wxCpService.getAccessToken(true);
			config.setAgentId(company.getWxId());//企业应用的id，整型。可在应用的设置页面查看
			return config;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	///////////////////////////////////////////////////////////////////
	/**
	 * 添加用户组
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String addCompanyGroup(WeiXinCompanyDBO company, WeiXinCompanyGroupDBO group) {
		TxWeixinService wxCpService = new TxWeixinService(loadCompanyToken(company));

		WxCpDepart dept = new WxCpDepart();

		dept.setId(Integer.parseInt(group.getGroupId()));//部门ID
		
		dept.setOrder(Integer.parseInt(group.getGroupId()));
		dept.setName(group.getGroupName());
		if (EmptyHelper.isEmpty(group.getParentId())) {
			dept.setParentId(1);
		} else {
			dept.setParentId(Integer.parseInt(group.getParentId()));
		}

		// 创建部门
		Integer id = 0;
		try {
			id = wxCpService.departCreate(dept);
		} catch (WxErrorException e) {
//			e.printStackTrace();
			try {
				wxCpService.departUpdate(dept);
				id = dept.getId();
			} catch (WxErrorException e1) {
				e1.printStackTrace();
			}
		}
		return ""+id;
	}

	/**
	 * 删除用户组
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String deleteCompanyGroup(WeiXinCompanyDBO company, WeiXinCompanyGroupDBO group) {
		TxWeixinService wxCpService = new TxWeixinService(loadCompanyToken(company));
		// 删除部门
		try {
			wxCpService.departDelete(Integer.parseInt(group.getWxId()));
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return ZERO;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 添加用户组用户
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String addCompanyGroupUser(WeiXinCompanyDBO company, WeiXinCompanyGroupUserDBO companyGroupUser) {
		TxWeixinService wxCpService = new TxWeixinService(loadCompanyToken(company));

		WxCpUser user = new WxCpUser();
		user.setUserId(companyGroupUser.getUserId());
		user.setName(companyGroupUser.getUserName());
		user.setEmail(companyGroupUser.getEmail());// 邮箱。长度为0~64个字节。企业内必须唯一
		user.setMobile(companyGroupUser.getMobile());// 手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
														// weixinid
														// 微信号。企业内必须唯一。（注意：是微信号，不是微信的名字）
		if (EmptyHelper.isNotEmpty(companyGroupUser.getGroupid())) {
			Integer[] departIds = { Integer.parseInt(companyGroupUser.getGroupid()) };
			user.setDepartIds(departIds);
		} else {
			Integer[] departIds = { 1 };
			user.setDepartIds(departIds);
		}

		// 创建用户
		try {
			wxCpService.userCreate(user);
		} catch (WxErrorException e) {
//			e.printStackTrace();
			try {
	/*			WxCpUser oldUser = wxCpService.userGet(companyGroupUser.getUserId());
				Integer[] departIds = oldUser.getDepartIds();

				Integer[] newDepartIds = new Integer[departIds.length+1];//定义新数组 
				for(int i=0;i<departIds.length;i++){ 
					newDepartIds[i] = departIds[i];//把旧数组中的元素拷贝到新数组中 
				}
				newDepartIds[departIds.length] = Integer.parseInt(companyGroupUser.getGroupid());
						
				user.setDepartIds(newDepartIds);*/
				wxCpService.userUpdate(user);
				
			} catch (WxErrorException e1) {
				e1.printStackTrace();
			}
		}

		String wxId = user.getUserId();
		return wxId;

	}

	/**
	 * 删除用户组用户
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String deleteCompanyGroupUser(WeiXinCompanyDBO company, WeiXinCompanyGroupUserDBO companyGroupUser) {
		TxWeixinService wxCpService = new TxWeixinService(loadCompanyToken(company));
		// 删除用户组用户
		try {
			wxCpService.userDelete(companyGroupUser.getWxId());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return ZERO;
	}

	public void loadUsers(WeiXinCompanyDBO company) {
		TxWeixinService wxCpService = new TxWeixinService(loadCompanyToken(company));
		try {
			// 获得所有用户
			wxCpService.userList(1, true, 0);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 基于APi获得用户jspai signature
	 * 
	 * @param comany,url
	 * @return
	 */
	public WxJsapiSignature loadWeixinJsapiSignature(WeiXinCompanySenderBean company, String url) {
		// getAccessToken,登陆后，仅仅得到使用token内容
		TxWeixinService wxCpService = new TxWeixinService(loadCompanyToken(company));
//		WeiXinJsapiSignature jsapi = new WeiXinJsapiSignature();

		try {
			wxCpService.getAccessToken(true);
			WxJsapiSignature jsapi =  wxCpService.createJsapiSignature(url);
			return jsapi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	///////////////////////////////

	public static void main(String[] args) throws WxErrorException {
		WeiXinCompanyTokenBean config = loadCompanyToken();
		TxWeixinService wxCpService = new TxWeixinService(config);
		wxCpService.getAccessToken(true);
		System.out.println(config.getAccessToken());
		////////////////////////////////////////////////
		//部门
		{
			WxCpDepart dept = new WxCpDepart();
			Integer id = 2222;
			dept.setId(id);
			dept.setOrder(2222);
			dept.setName("世界上");
			dept.setParentId(1);

			// 创建部门
			try {
				System.out.println("departCreate");
				id = wxCpService.departCreate(dept);
				System.out.println(id);
			} catch (WxErrorException e) {
				try {
					System.out.println("departUpdate");
					wxCpService.departUpdate(dept);
					id = dept.getId();
					System.out.println(id);
				} catch (WxErrorException e1) {
					e1.printStackTrace();
				}
			}

			// 获取部门
			List<WxCpDepart> departList = wxCpService.departGet();
			System.out.println(departList);
			// 删除部门
			// wxCpService.departDelete(id);

			departList = wxCpService.departGet();
			System.out.println(departList);
		}
		/////////////////////////////////////////////////////////////
		//用户
		{
			WxCpUser user = new WxCpUser();
			user.setUserId("11111");
			user.setName("小王吧");
			user.setEmail("aa11@qq.com");
			user.setMobile("1505711234591");
			Integer[] departIds = { 1, 2 };
			user.setDepartIds(departIds);

			// 创建用户
			try {
				System.out.println("userCreate");
				wxCpService.userCreate(user);
			} catch (WxErrorException e) {
				e.printStackTrace();
				try {
					System.out.println("userUpdate");
					wxCpService.userUpdate(user);
				} catch (WxErrorException e1) {
					e1.printStackTrace();
				}
			}
			String wxId = user.getUserId();
			System.out.println(wxId);
			List<WxCpUser> users = wxCpService.userList(1, true, 0);
			for (WxCpUser u : users) {
				System.out.println(u.getUserId());
				for (int d : u.getDepartIds()) {
					System.out.println(d);
				}
				// wxCpService.userDelete(u.getUserId());
			}
		}
		//////////////////////////////////////////////////////////////
		//打印所有
		{
			List<WxCpDepart> departList = wxCpService.departGet();
			for (WxCpDepart d : departList) {
				System.out.println("WxCpDepart===>>>"+d.getName() + "," + d.getId());
				List<WxCpUser> users = wxCpService.userList(d.getId(), true, 0);
				for (WxCpUser u : users) {
					System.out.println("WxCpUser===>>>"+u.getName() + "," + u.getUserId());
				}
			}
		}
		//////////////////////////////////////////////////////////////
		//消息发送
		{
			WxCpMessage cpMmessage =WxCpMessage.TEXT().agentId("4").toUser("@all").toParty("")//.toTag(tags)
					.content("微信消息测试，收到请通知").build();
			wxCpService.messageSend(cpMmessage);
		}

	}

	public static WeiXinCompanyTokenBean loadCompanyToken() {
		WeiXinCompanyTokenBean config = new WeiXinCompanyTokenBean();
		config.setCorpId("wx52610bfc027c3df8");
		config.setCorpSecret("fCVLAo4AoqHb-oyeD2RJTNZ7EM3cMCbRX_jQhvsO10t41fiJ1DPvuyMBhZtGKDj7");
		return config;
	}

}
