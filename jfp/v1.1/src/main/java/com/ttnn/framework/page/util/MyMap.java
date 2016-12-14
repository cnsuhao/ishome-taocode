package com.ttnn.framework.page.util;

import java.util.HashMap;

public class MyMap extends HashMap {

	@Override
	public String toString() {
	  return  super.toString().replaceAll("=",":");
        
	}

	

}
