package org.isotope.boxy.common.data;

import javax.annotation.Resource;

import org.isotope.boxy.common.AGameBussinessService;
import org.isotope.boxy.entity.CommonEntity.CommonEntityDBO;
import org.isotope.boxy.entity.CommonEntity.CommonEntityService;
import org.springframework.stereotype.Service;

/**
 * 数据持久化
 * 
 * @author 001745
 *
 */
@Service("99887766")
public class BoxyDataModelPersistenceBiz extends AGameBussinessService {
	@Resource
	CommonEntityService ces;

	@Override
	public boolean doGameAction() throws Exception {
		CommonEntityDBO ced = new CommonEntityDBO();
		ced.setGgg(paramData.getString("type"));
		setResult(ces.doSelectData(ced));
		return true;
	}
}
