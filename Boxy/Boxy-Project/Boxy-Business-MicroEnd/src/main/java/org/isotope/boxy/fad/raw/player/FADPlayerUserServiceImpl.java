package org.isotope.boxy.fad.raw.player;

import javax.annotation.Resource;

import org.isotope.boxy.common.AGameBussinessService;
import org.isotope.boxy.fad.business.role.FADPlayerRoleServiceImpl;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.springframework.stereotype.Service;

/**
 * 玩家账户管理
 * 
 * @author 001745
 *
 */
@Service("10001234")
public class FADPlayerUserServiceImpl extends AGameBussinessService {

	@Resource
	FADPlayerRoleServiceImpl PlayerRoleServiceImpl_;

	/**
	 * 创建角色
	 * 
	 * @param roleID
	 * @return
	 * @throws Exception
	 */
	public boolean addRole(String roleID) throws Exception {
		PlayerRoleServiceImpl_.addRole(roleID);
		result.setMessage("创建成功");
		return true;
	}

	@Override
	public boolean doGameAction() throws Exception {
		result = new RESTResultBean();
		if ("10001000".equals(tokenBean.getBizId())) {
			return addRole(tokenBean.getUserId());
		}
		return false;
	}

}
