package org.isotope.boxy.fad.business.common;

import java.util.ArrayDeque;

import org.isotope.boxy.fad.bean.FADPlayerRoleBean;
import org.isotope.boxy.fad.bean.item.FADCardBean;
import org.isotope.boxy.fad.bean.item.FADLotteryBean;
import org.springframework.stereotype.Service;

/**
 * 墙上卡片
 * 
 * @author fucy
 * @version 0.0.1
 * @since 3.1.2 2017/01/20
 */

@Service("LotteryTicket")
public class FADLotteryTicketServiceImpl {
	/**
	 * 拥有瓶子信息
	 */

	/*
	 * 公共组件工程
	 */
	public String loadLotteryNumber() {
		// TODO Auto-generated method stub
		return "1";
	}

	/**
	 * 兑奖
	 * @param bottle
	 */
	public void checkLottery(FADPlayerRoleBean role , FADLotteryBean bottle) {
		// TODO Auto-generated method stub
		
	}
}
