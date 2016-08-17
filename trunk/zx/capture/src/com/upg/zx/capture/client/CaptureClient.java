package com.upg.zx.capture.client;
/**
 * 客户端数据抓取
 * @author lujf
 *
 */
public class CaptureClient {
	public static void main(String[] arg){
		int[] ff = new int[2];
		int[] a = {1,2,3,4};
		int[] b = {6,7,8,9,0};
		int[] cc = new int[a.length+b.length];
		System.arraycopy(a, 0, cc, 0, a.length);
		System.arraycopy(b, 0, cc, a.length, b.length);
		ff = cc;
		for(int d : ff){
			System.out.println(d);
		}
	}
}
