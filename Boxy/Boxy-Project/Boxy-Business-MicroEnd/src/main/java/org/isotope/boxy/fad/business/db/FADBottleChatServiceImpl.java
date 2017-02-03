package org.isotope.boxy.fad.business.db;

import javax.annotation.Resource;

import org.isotope.boxy.bean.MessageBean;
import org.isotope.boxy.common.AGameBussinessService;
import org.isotope.boxy.fad.bean.FADPlayerRoleBean;
import org.isotope.boxy.fad.bean.item.FADBottleBean;
import org.isotope.boxy.fad.business.role.FADPlayerRoleService;
import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Service;

/**
 * 基于漂流瓶聊天
 * 
 * @author 001745
 *
 */
@Service("BottleChat")
public class FADBottleChatServiceImpl extends AGameBussinessService {

	@Resource
	FADPlayerRoleService PlayerRoleServiceImpl_;

	public void chat(String roleID, String bottleID) throws Exception{
		// 获得当前角色
		FADPlayerRoleBean senderRole = PlayerRoleServiceImpl_.loadRole(roleID);
		FADBottleBean senderBottle = senderRole.getRoleBottles().get(bottleID);
		
		
		
		// 设定消息信息
		MessageBean message = new MessageBean();
		message.setMessage("" + paramData.get("message"));
		message.setSendTime(DateHelper.currentTimeMillis2());
		senderBottle.addMessages(message);
	}


	@Override
	public boolean doGameAction() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
