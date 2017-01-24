package org.isotope.boxy.fad.business.ww;

import javax.annotation.Resource;

import org.isotope.boxy.common.AGameBussinessService;
import org.isotope.boxy.fad.bean.FADPlayerRoleBean;
import org.isotope.boxy.fad.bean.item.FADCardBean;
import org.isotope.boxy.fad.business.role.FADPlayerRoleServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 基于许愿卡点赞
 * 
 * @author 001745
 *
 */
@Service("CardPraise")
public class FADCardPraiseServiceImpl extends AGameBussinessService {

	@Resource
	FADPlayerRoleServiceImpl PlayerRoleServiceImpl_;

	public void Praise(String roleID, String cardID) throws Exception {
		// 获得当前角色
		FADPlayerRoleBean senderRole = PlayerRoleServiceImpl_.loadRole(roleID);
		FADCardBean senderCard = senderRole.getRoleCards().get(cardID);

		// 设定点赞次数
		senderCard.doPraise();
	}

	@Override
	public boolean doGameAction() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
