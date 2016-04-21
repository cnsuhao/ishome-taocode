package org.isotope.jfp.framework.search;

import org.isotope.jfp.framework.utils.HttpServiceHelper;

public class TTT {

	public static void main(String[] args) throws Exception {
		System.out.println(
		HttpServiceHelper.doHttpGET("http://127.0.0.1:8080/Zheng/corp/queryCompanyNew?paramValue=合成西&encrypt_key=AAMKdxYTFFNcRFRSBhBSQQ==&life=&UserID=1&capital=&userLog=355653050424941&paramType=0000010&version=3.3.1")
		);
	}

}
