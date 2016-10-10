package com.mcookies.qxy.utils;

import java.text.NumberFormat;

import org.apache.poi.ss.usermodel.Cell;

public final class ExcelUtil {
	private ExcelUtil() {
		throw new AssertionError("不能实例化工具类");
	}

	public static String convertToString(Cell cell) {
		String value = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				NumberFormat nf = NumberFormat.getInstance();
				nf.setGroupingUsed(false);
				nf.setParseIntegerOnly(true);
				value = nf.format(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			}
		}
		return value;
	}

	public static Long convertToLong(Cell cell) {
		Long value = -1L;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				value = Double.valueOf(cell.getNumericCellValue()).longValue();
				break;
			case Cell.CELL_TYPE_STRING:
				value = Long.valueOf(cell.getStringCellValue());
				break;
			}
		}
		return value;
	}

	public static Integer convertToInteger(Cell cell) {
		Integer value = -1;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				value = Double.valueOf(cell.getNumericCellValue()).intValue();
				break;
			case Cell.CELL_TYPE_STRING:
				value = Integer.valueOf(cell.getStringCellValue());
				break;
			}
		}
		return value;
	}
}
