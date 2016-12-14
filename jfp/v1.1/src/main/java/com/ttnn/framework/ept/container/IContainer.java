package com.ttnn.framework.ept.container;

import java.util.HashMap;


import com.ttnn.framework.ept.bean.EPTDataBean;
import com.ttnn.framework.ept.bean.template.PojoBean;


/**
 * 数据容器接口
 * @see <ept-config.xml>
 * @since 1.1
 */
public interface IContainer {

	public boolean doContainer(PojoBean pojoBean ,HashMap<String, Object> mapObj);
}
