package com.upg.zx.domain.entity;

import java.util.Map;

public class Task {
	
	private String type;
	
	private Map <String,String>map;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
}
