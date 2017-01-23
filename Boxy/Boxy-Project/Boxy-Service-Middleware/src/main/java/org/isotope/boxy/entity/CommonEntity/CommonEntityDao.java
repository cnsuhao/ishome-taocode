package org.isotope.boxy.entity.CommonEntity;

import java.util.HashMap;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 数据信息 */
public interface CommonEntityDao extends IDatabaseSupport<CommonEntityDBO> {

	void doCall(HashMap<String, String> map);

}
