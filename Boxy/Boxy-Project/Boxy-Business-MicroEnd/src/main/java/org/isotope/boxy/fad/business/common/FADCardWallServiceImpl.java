package org.isotope.boxy.fad.business.common;

import java.util.ArrayDeque;

import org.isotope.boxy.fad.bean.FADPlayerRoleBean;
import org.isotope.boxy.fad.bean.item.FADCardBean;
import org.springframework.stereotype.Service;

/**
 * 墙上卡片
 * 
 * @author fucy
 * @version 0.0.1
 * @since 3.1.2 2017/01/20
 */

@Service("CardWall")
public class FADCardWallServiceImpl {
	/**
	 * 拥有瓶子信息
	 */
	ArrayDeque<FADCardBean> cardWalls = new ArrayDeque<FADCardBean>();

	/*
	 * 公共组件工程
	 */

	public FADCardBean loadBottle() {
		if (cardWalls.size() == 0)
			return null;
		return cardWalls.pop();
	}

	public void addRoleCard(FADPlayerRoleBean role, FADCardBean card) {
		// 保存到当前玩家信息
		role.addRoleCard(card);
		// TODO 保存到公共区域
		cardWalls.add(card);
	}
}
