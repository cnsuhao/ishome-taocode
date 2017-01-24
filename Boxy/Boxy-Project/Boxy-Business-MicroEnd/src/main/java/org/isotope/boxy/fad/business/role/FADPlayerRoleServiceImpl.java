package org.isotope.boxy.fad.business.role;

import java.util.HashMap;

import org.isotope.boxy.fad.bean.FADPlayerRoleBean;
import org.springframework.stereotype.Service;

/**
 * 玩家角色
 * 
 * @author 001745
 *
 */
@Service("PlayerRole")
public class FADPlayerRoleServiceImpl {

	HashMap<String, FADPlayerRoleBean> PlayerRoleCache = new HashMap<String, FADPlayerRoleBean>();

	public FADPlayerRoleBean loadRole(String roleID) {
		FADPlayerRoleBean role = PlayerRoleCache.get(roleID);
		if(role==null)
			role = new FADPlayerRoleBean();
		role.setRoleID(roleID);
		return role;
	}

	public void updateRole(FADPlayerRoleBean role) {
		PlayerRoleCache.put(role.getRoleID(), role);
	}
	//////////////////////////////////////////////////////////
	/**
	 * 频次统计增加<br>
	 * 每隔15分钟运行一次
	 * @see <FrequencyTimeTaskJobServiceImpl>
	 */
	public void statisticalTime() {
		//漂流瓶:5分钟增加一次
		//许愿墙\幸运星\种树:15分钟增加一次
	}

	///////////////////////////////// 活力vigour/////////////////////////////////
	/**
	 * 活力值变更<br>
	 * 公共方法，不能直接使用
	 * 
	 * @param role
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public void setVigour(FADPlayerRoleBean role, int num) throws Exception {
		// 获得当前活力值
		int vigour = role.getVigour() + num;
		int maxVigour = role.getMaxVigour();
		if (vigour > maxVigour) {
			vigour = maxVigour;
		} else if (vigour < 0) {
			vigour = 0;
		}
		// 保存数据计算结果
		role.setVigour(vigour);
	}

	/**
	 * 消耗体力
	 */
	public boolean useVigour(String roleID, int num) throws Exception {
		// 获得当前角色
		FADPlayerRoleBean role = loadRole(roleID);
		// 获得当前活力值
		int vigour = role.getVigour();
		// 增加玩家数值,计算增加活力值
		vigour = vigour - num;
		if (vigour < 0)
			vigour = 0;
		// 保存数据计算结果
		role.setVigour(vigour);
		// 保存最终结果,保存玩家数据
		updateRole(role);
		return true;
	}
	///////////////////////////////// 经验experience/////////////////////////////////
	///////////////////////////////// 金钱money/////////////////////////////////
}
