package com.ttnn.framework.ept.convert;

import java.util.HashMap;


import com.ttnn.framework.ept.bean.EPTDataBean;
import com.ttnn.framework.ept.bean.template.PojoBean;


/**
 * 数据z转换器接口
 * @see <ept-config.xml>
 * @since 1.1
 */
public interface IConvert {
	public boolean doConvert(PojoBean pojoBean ,HashMap<String, Object> mapObj );
}
