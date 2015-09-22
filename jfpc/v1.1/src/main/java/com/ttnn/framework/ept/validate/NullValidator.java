package com.ttnn.framework.ept.validate;

import com.ttnn.framework.ept.util.Message;

/**
 * 校验数据不能为空
 */
public class NullValidator  implements IValidate {

	@Override
	public boolean doValidate(Message message, int rowNumber,int columNnumber,
			String value, String param) throws Exception{
		// TODO Auto-generated method stub
		return false;
	}

}