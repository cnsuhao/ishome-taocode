package org.isotope.jfp.framework.search.utils;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;

public class IndexNameHelper implements ISFrameworkConstants{
	public static final String CREAT = "_creat";
	public static final String UPDATE = "_update";
	public static final String DELETE = "_delete";

	public static String getCreatId(String indexName){
		if(indexName.indexOf(CREAT)>0)
			return indexName;
		String[] s = indexName.split(DOWN_LINE);
		StringBuffer id = new StringBuffer();
		id.append(s[0]);
		id.append(DOWN_LINE);
		id.append(s[1]);
		id.append(CREAT);
		return id.toString();
	}
	
	public static String getUpdateId(String indexName){
		if(indexName.indexOf(CREAT)>0)
			return indexName;
		String[] s = indexName.split(DOWN_LINE);
		StringBuffer id = new StringBuffer();
		id.append(s[0]);
		id.append(DOWN_LINE);
		id.append(s[1]);
		id.append(UPDATE);
		return id.toString();
	}
	
	public static String getDeleateId(String indexName){
		if(indexName.indexOf(CREAT)>0)
			return indexName;
		String[] s = indexName.split(DOWN_LINE);
		StringBuffer id = new StringBuffer();
		id.append(s[0]);
		id.append(DOWN_LINE);
		id.append(s[1]);
		id.append(DELETE);
		return id.toString();
	}
}
