package org.isotope.boxy.fad.business.common;

import java.util.ArrayDeque;
import java.util.HashMap;

import org.isotope.boxy.fad.bean.FADPlayerRoleBean;
import org.isotope.boxy.fad.bean.item.FADBottleBean;
import org.springframework.stereotype.Service;

/**
 * 墙上卡片
 * @author fucy
 * @version 0.0.1
 * @since 3.1.2 2017/01/20
 */

@Service("CardWall")
public class FADCardWallServiceImpl {
	/**
	 * 拥有瓶子信息
	 */
	ArrayDeque<FADBottleBean> seaBottles = new ArrayDeque<FADBottleBean>();
	
	/*
	 * 公共组件工程
	 */
	
	public FADBottleBean loadBottle(){
		if(seaBottles.size()==0)
			return null;
		return seaBottles.pop();
	}

	public void addRoleBottles(FADPlayerRoleBean role, FADBottleBean bottle) {
		//保存到当前玩家信息
		role.addRoleBottles(bottle);
		//TODO 保存到公共区域
		seaBottles.add(bottle);
	}
}
