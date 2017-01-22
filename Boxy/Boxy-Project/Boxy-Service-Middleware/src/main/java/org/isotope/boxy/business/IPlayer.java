package org.isotope.boxy.business;

/**
 * 玩家账户
 * @author 001745
 *
 */
public interface IPlayer {

	void creatPlayer();
	
	void deletePlayer();
	
	void loadPlayer();
	
	void updatePlayer();
	
	/**
	 * 忘记密码
	 */
	void loadPassword();
}
