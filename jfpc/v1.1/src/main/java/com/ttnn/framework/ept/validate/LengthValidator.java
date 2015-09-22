package com.ttnn.framework.ept.validate;

import com.ttnn.framework.ept.util.Message;

/**
 * 长度校验�?
 */
public class LengthValidator implements IValidate {
	/**
	 * 分隔符定�?
	 */
	public final static String VALIDATE_LENGTH_SPLIT = "#";
	public final static String VALIDATE_LENGTH_MESSAGE1 = "validateLength1";
	public final static String VALIDATE_LENGTH_MESSAGE2 = "validateLength2";

	/**
	 * 数据校验处理
	 * 
	 * @param message
	 *            当前操作的提示信息对象
	 * @param rowNumber
	 *            行
	 * @param columNnumber
	 *            列
	 * @param value
	 *            被校验的单元格数据
	 * @param param
	 *            4#20
	 * @return
	 */
	public boolean doValidate(Message message, int rowNumber, int columNnumber,
			String value, String param) throws Exception {
		if (value == null || param == null) {
			return true;
		}
		int[] checkLength = new int[] { 0, 0 };
		// 准备长度参数
		try {
			String[] p = param.split(VALIDATE_LENGTH_SPLIT);
			for (int i = 0; i < p.length; i++) {
				checkLength[i] = Integer.parseInt(p[i]);
			}
		} catch (NumberFormatException e) {
			throw e;
		}
		// �?��校验
		// 参数定义格式为MAX
		if (checkLength[1] == 0) {
			if (value.getBytes().length <= checkLength[0]) {
				return true;
			}
			String[] messageParam = new String[3];
			messageParam[0] = "" + rowNumber;
			messageParam[1] = "" + columNnumber;
			messageParam[2] = "" + checkLength[0];
			// 失败日志
			message
					.updateTicketErrorMsg(VALIDATE_LENGTH_MESSAGE1,
							messageParam);
		} else {
			// 参数定义格式为MIN<>MAX
			if (value.getBytes().length >= checkLength[0]
					&& value.getBytes().length <= checkLength[1]) {
				return true;
			}
			// 失败日志
			String[] messageParam = new String[4];
			messageParam[0] = "" + rowNumber;
			messageParam[1] = "" + columNnumber;
			messageParam[2] = "" + checkLength[0];
			messageParam[3] = "" + checkLength[1];
			message
					.updateTicketErrorMsg(VALIDATE_LENGTH_MESSAGE2,
							messageParam);
		}

		return false;
	}
}