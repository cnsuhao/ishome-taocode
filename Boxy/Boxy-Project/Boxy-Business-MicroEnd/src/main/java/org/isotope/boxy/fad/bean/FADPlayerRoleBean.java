package org.isotope.boxy.fad.bean;

import java.util.HashMap;

import org.isotope.boxy.fad.bean.item.FADBottleBean;
import org.isotope.boxy.fad.bean.item.FADCardBean;

/**
 * 玩家角色信息
 * 
 * @author 001745
 *
 */
public class FADPlayerRoleBean {
	/**
	 * 角色ID
	 */
	String roleID;

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	//////////////////////////////////////////////////////////////
	/**
	 * 活力
	 */
	int maxVigour = 15;
	int vigour = 15;
	/**
	 * 最后活力最后计算时间
	 */
	long vigourTime = System.currentTimeMillis();

	public int getMaxVigour() {
		return maxVigour;
	}

	public void setMaxVigour(int maxVigour) {
		this.maxVigour = maxVigour;
	}

	public int getVigour() {
		return vigour;
	}

	public void setVigour(int vigour) {
		this.vigour = vigour;
	}

	public long getVigourTime() {
		return vigourTime;
	}

	public void setVigourTime(long vigourTime) {
		this.vigourTime = vigourTime;
	}

	///////////////////////////////////////////////////////////////////////
	/**
	 * 扔瓶子次数
	 */
	int castNum = 15;
	/**
	 * 拾取瓶子次数
	 */
	int pickUp = 15;

	/**
	 * 拥有瓶子信息
	 */
	HashMap<String, FADBottleBean> roleBottles = new HashMap<String, FADBottleBean>();

	public HashMap<String, FADBottleBean> getRoleBottles() {
		return roleBottles;
	}

	public void setRoleBottles(HashMap<String, FADBottleBean> roleBottles) {
		this.roleBottles = roleBottles;
	}

	public void addRoleBottle(FADBottleBean bottle) {
		this.roleBottles.put(bottle.getBottleID(), bottle);
	}

	public int getCastNum() {
		return castNum;
	}

	public void setCastNum(int castNum) {
		this.castNum = castNum;
	}

	public int getPickUp() {
		return pickUp;
	}

	public void setPickUp(int pickUp) {
		this.pickUp = pickUp;
	}

	///////////////////////////////////////////////////////////////////////
	/**
	 * 许愿次数
	 */
	int wishNum = 5;

	public int getWishNum() {
		return wishNum;
	}

	public void setWishNum(int wishNum) {
		this.wishNum = wishNum;
	}

	HashMap<String, FADCardBean> roleCards = new HashMap<String, FADCardBean>();

	public HashMap<String, FADCardBean> getRoleCards() {
		return roleCards;
	}

	public void setRoleCards(HashMap<String, FADCardBean> roleCards) {
		this.roleCards = roleCards;
	}

	public void addRoleCard(FADCardBean card) {
		this.roleCards.put(card.getCardID(), card);
	}

	///////////////////////////////////////////////////////////////////////
	/**
	 * 抽奖次数
	 */
	int lotteryNum = 3;

	public int getLotteryNum() {
		return lotteryNum;
	}

	public void setLotteryNum(int lotteryNum) {
		this.lotteryNum = lotteryNum;
	}
}
