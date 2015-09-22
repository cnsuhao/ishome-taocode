package com.ttnn.framework.ept.validate;

import com.ttnn.framework.ept.util.Message;

/**
 * 校验器接口
 * @since 1.1
 * @see <ept-config.xml>
 * @version 1.3
 */
public interface IValidate {

	/**
	 * 数据校验处理
	 * @param message 当前操作的提示信息对象
	 * @param rowNumber 行
	 * @param columNnumber 列
	 * @param value 被校验的单元格数据
	 * @param param 校验器参数
	 * @return
	 */
	public boolean doValidate(Message message, int rowNumber,int columNnumber,
			String value, String param) throws Exception;
}
