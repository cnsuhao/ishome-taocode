package com.upg.zx.web.utils;

public class ThreadUtils {
	
	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
		}
	}
}
