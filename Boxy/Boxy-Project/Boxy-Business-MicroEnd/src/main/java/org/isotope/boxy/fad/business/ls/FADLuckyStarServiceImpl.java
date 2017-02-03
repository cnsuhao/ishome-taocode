package org.isotope.boxy.fad.business.ls;

import javax.annotation.Resource;

import org.isotope.boxy.common.AGameBussinessService;
import org.isotope.boxy.fad.bean.FADPlayerRoleBean;
import org.isotope.boxy.fad.bean.item.FADLotteryBean;
import org.isotope.boxy.fad.business.common.FADLotteryTicketServiceImpl;
import org.isotope.boxy.fad.business.role.FADPlayerRoleService;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.PKHelper;
import org.springframework.stereotype.Service;

/**
 * 幸运树
 * 
 * @author 001745
 *
 */
@Service("10401234")
public class FADLuckyStarServiceImpl extends AGameBussinessService {
	@Resource
	FADPlayerRoleService PlayerRoleServiceImpl_;
	@Resource
	FADLotteryTicketServiceImpl FADLotteryTicketServiceImpl_;

	/**
	 * 购买彩票(玩家开奖)
	 */
	public boolean lotteryDraw(String roleID) throws Exception {
		// 获得当前角色
		FADPlayerRoleBean role = PlayerRoleServiceImpl_.loadRole(roleID);
		// 获得扔瓶子次数
		int lotteryNum = role.getLotteryNum() - 1;
		if (lotteryNum < 0) {
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
			// 获得一个彩票
			FADLotteryBean bottle = new FADLotteryBean();
			bottle.setLotteryID(PKHelper.creatBarCodeKey());
			// 设定发送者
			bottle.setPurchaser(role.getRoleID());
			// 设定开始时间
			bottle.setBuyingTime(DateHelper.currentTimeMillis2());
			// 设定中奖号码
			bottle.setLotteryNumber(FADLotteryTicketServiceImpl_.loadLotteryNumber());

			// 兑奖
			boolean lottery = FADLotteryTicketServiceImpl_.checkLottery(role, bottle);
			if(lottery){
				result.setMessage("恭喜中奖，获得奖励！！");				
			}else{
				result.setMessage("很遗憾，未中奖！");				
			}
			// 保存数据计算结果
			role.setLotteryNum(lotteryNum);
			role.setVigour(vigour);
			// 保存最终结果,保存玩家数据
			PlayerRoleServiceImpl_.updateRole(role);
		}
		return true;
	}

	@Override
	public boolean doGameAction() throws Exception {
		result = new RESTResultBean();
		if ("10401000".equals(tokenBean.getBizId())) {
			return lotteryDraw(tokenBean.getUserId());
		}

		return false;
	}

}
