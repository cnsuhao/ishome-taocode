package org.isotope.boxy.fad.bean.item;

import org.isotope.boxy.bean.MessageBean;

/**
 * 角色许愿卡信息
 * 
 * @author 001745
 *
 */
public class FADCardBean extends MessageBean {
	String cardID;
	// 点赞次数
	int praiseNum = 0;

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	/**
	 * 设定点赞次数
	 */
	public void doPraise() {
		praiseNum = praiseNum + 1;
	}

}
