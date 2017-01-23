package org.isotope.boxy.fad.bean.item;

/**
 * 角色彩票信息
 * 
 * @author 001745
 *
 */
public class FADLotteryBean {

	String lotteryID;// 彩票ID
	String purchaser;// 购买者
	String buyingTime;// 购买时间
	String lotteryNumber = "0";// 彩票号码

	public String getLotteryID() {
		return lotteryID;
	}

	public void setLotteryID(String lotteryID) {
		this.lotteryID = lotteryID;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getBuyingTime() {
		return buyingTime;
	}

	public void setBuyingTime(String buyingTime) {
		this.buyingTime = buyingTime;
	}

	public String getLotteryNumber() {
		return lotteryNumber;
	}

	public void setLotteryNumber(String lotteryNumber) {
		this.lotteryNumber = lotteryNumber;
	}

}
