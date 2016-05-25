package org.isotope.boxy.common.actions;

import org.isotope.boxy.AGameBussinessService;
import org.springframework.stereotype.Service;

@Service("001331")
public class LoginBussinessService extends AGameBussinessService {

	
	@Override
	public boolean doGameAction() throws Exception {
		paramBean.updateUuu();
		return true;
	}

}
