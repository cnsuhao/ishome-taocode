package org.isotope.boxy.fad.business.db;

import java.util.HashMap;

import javax.annotation.Resource;

import org.isotope.boxy.bean.MessageBean;
import org.isotope.boxy.common.AGameBussinessService;
import org.isotope.boxy.fad.bean.FADPlayerRoleBean;
import org.isotope.boxy.fad.bean.item.FADBottleBean;
import org.isotope.boxy.fad.business.common.FADBottleSeaServiceImpl;
import org.isotope.boxy.fad.business.role.FADPlayerRoleService;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.PKHelper;
import org.springframework.stereotype.Service;

/**
 * 漂流瓶
 * 
 * @author 001745
 *
 */
@Service("10201234")
public class FADDriftBottleServiceImpl extends AGameBussinessService {
	@Resource
	FADPlayerRoleService PlayerRoleServiceImpl_;
	@Resource
	FADBottleSeaServiceImpl BottleSeaServiceImpl_;

	/**
	 * 扔瓶子(玩家扔掉)
	 */
	public boolean castBottle(String roleID) throws Exception {// 获得当前角色
		FADPlayerRoleBean role = PlayerRoleServiceImpl_.loadRole(roleID);
		// 获得扔瓶子次数
		int castNum = role.getCastNum() - 1;
		if (castNum < 0) {
			result.setCode(ONE);
			result.setMessage("已经达到最大次数");
			return false;
		}
		// 获得当前活力值，修正剩余次数，消耗两点活力
		int vigour = role.getVigour() - 3;
		if (vigour < 0) {
			result.setCode(TWO);
			result.setMessage("活力值不足");
			return false;
		}
		{
			// 获得一个瓶子
			FADBottleBean bottle = new FADBottleBean();
			bottle.setBottleID(PKHelper.creatBarCodeKey());
			// 设定发送者
			bottle.setSender(role.getRoleID());
			// 设定最大漂流次数
			bottle.setDriftingTimes(10);
			// 设定开始时间
			bottle.setSendTime(DateHelper.currentTimeMillis2());

			// 设定消息信息
			MessageBean message = new MessageBean();
			message.setMessage("" + paramData.get("message"));
			message.setSendTime(DateHelper.currentTimeMillis2());
			message.setSender(role.getRoleID());
			bottle.addMessages(message);
			
			// 保存瓶子
			BottleSeaServiceImpl_.addRoleBottle(role, bottle);
			// 保存数据计算结果
			role.setCastNum(castNum);
			role.setVigour(vigour);
			// 保存最终结果,保存玩家数据
			PlayerRoleServiceImpl_.updateRole(role);

			result.setMessage("成功丢出一个瓶子");
		}
		return true;
	}

	/**
	 * 捡取瓶子(玩家捡取)
	 */
	public boolean pickUpBottle(String roleID) throws Exception {
		// 获得当前角色
		FADPlayerRoleBean role = PlayerRoleServiceImpl_.loadRole(roleID);
		// 获得拾取瓶子次数
		int pickUp = role.getPickUp() - 1;
		if (pickUp < 0) {
			result.setCode(ONE);
			result.setMessage("已经达到最大次数");
			return false;
		}
		// 获得当前活力值，修正剩余次数，消耗两点活力
		int vigour = role.getVigour() - 2;
		if (vigour < 0) {
			result.setCode(TWO);
			result.setMessage("活力值不足");
			return false;
		}
		{
			// 获得一个瓶子
			FADBottleBean bottle = BottleSeaServiceImpl_.loadBottle();
			if(bottle==null){
				result.setCode(THREE);
				result.setMessage("海面上空空如也");
				return false;
			}
			// 设定接收者
			bottle.setReceiver(role.getRoleID());
			// 保存瓶子
			role.addRoleBottle(bottle);
			// 保存数据计算结果
			role.setPickUp(pickUp);
			role.setVigour(vigour);
			// 保存最终结果,保存玩家数据
			PlayerRoleServiceImpl_.updateRole(role);
//			paramData = new JSONObject();
//			paramData.put("bottleID", bottle.getBottleID());
			result.setMessage("获得一个瓶子");
			result.setResult(bottle);
		}
		return true;
	}

	/**
	 * 捡取瓶子(玩家捡取)
	 */
	public HashMap<String, FADBottleBean> loadBottle(String roleID) throws Exception {
		// 获得当前角色
		FADPlayerRoleBean role = PlayerRoleServiceImpl_.loadRole(roleID);
		return role.getRoleBottles();
	}

	public FADBottleBean loadBottleInfo(String roleID, String bottleID) throws Exception {
		// 获得当前角色
		FADPlayerRoleBean role = PlayerRoleServiceImpl_.loadRole(roleID);
		return role.getRoleBottles().get(bottleID);
	}

	@Override
	public boolean doGameAction() throws Exception {
		result = new RESTResultBean();
		if ("10201000".equals(tokenBean.getBizId())) {
			return castBottle(tokenBean.getUserId());
		} else if ("10202000".equals(tokenBean.getBizId())) {
			return pickUpBottle(tokenBean.getUserId());
		} else if ("10203000".equals(tokenBean.getBizId())) {
			result = new RESTResultBean();
			result.setMessage("加载成功");
			result.setResult(loadBottle(tokenBean.getUserId()));
			return true;
		} else if ("10203010".equals(tokenBean.getBizId())) {
			result = new RESTResultBean();
			result.setMessage("加载成功");
			result.setResult(loadBottleInfo(tokenBean.getUserId(), "" + paramData.get("bottleID")));
			return true;
		}else if ("10204000".equals(tokenBean.getBizId())) {
		}else if ("10205000".equals(tokenBean.getBizId())) {
		}else if ("10206000".equals(tokenBean.getBizId())) {
		}

		return false;
	}

}
