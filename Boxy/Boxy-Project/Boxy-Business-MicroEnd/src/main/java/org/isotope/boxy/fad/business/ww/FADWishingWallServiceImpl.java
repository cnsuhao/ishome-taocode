package org.isotope.boxy.fad.business.ww;

import javax.annotation.Resource;

import org.isotope.boxy.common.AGameBussinessService;
import org.isotope.boxy.fad.bean.FADPlayerRoleBean;
import org.isotope.boxy.fad.bean.item.FADCardBean;
import org.isotope.boxy.fad.business.common.FADCardWallServiceImpl;
import org.isotope.boxy.fad.business.role.FADPlayerRoleServiceImpl;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.PKHelper;
import org.springframework.stereotype.Service;

/**
 * 许愿墙<br>
 * Wishing Wall
 * 
 * @author 001745
 * @version 0.0.1
 * @since 3.1.2 2017/01/20
 *
 */
@Service("10301234")
public class FADWishingWallServiceImpl extends AGameBussinessService {
	@Resource
	FADPlayerRoleServiceImpl PlayerRoleServiceImpl_;
	@Resource
	FADCardWallServiceImpl FADCardWallServiceImpl_;

	/**
	 * 许愿(玩家写卡)
	 */
	public boolean castCard(String roleID) throws Exception {// 获得当前角色
		FADPlayerRoleBean role = PlayerRoleServiceImpl_.loadRole(roleID);
		// 获得扔瓶子次数
		int wishNum = role.getWishNum() - 1;
		if (wishNum < 0) {
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
			// 创建一个许愿卡
			FADCardBean card = new FADCardBean();
			card.setCardID(PKHelper.creatBarCodeKey());
			// 设定发送者
			card.setSender(role.getRoleID());
			// 设定开始时间
			card.setSendTime(DateHelper.currentTimeMillis2());

			// 设定消息信息
			card.setMessage("" + paramData.get("message"));

			// 保存许愿卡
			FADCardWallServiceImpl_.addRoleCard(role, card);
			// 保存数据计算结果
			role.setWishNum(wishNum);
			role.setVigour(vigour);
			// 保存最终结果,保存玩家数据
			PlayerRoleServiceImpl_.updateRole(role);

			result.setMessage("OK");
		}
		return true;
	}

	@Override
	public boolean doGameAction() throws Exception {
		result = new RESTResultBean();
		if ("10301000".equals(tokenBean.getBizId())) {
			return castCard(tokenBean.getUserId());
		}
		return false;
	}

}
