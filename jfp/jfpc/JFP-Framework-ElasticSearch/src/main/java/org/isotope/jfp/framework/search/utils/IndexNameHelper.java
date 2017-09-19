package org.isotope.jfp.framework.search.utils;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;

public class IndexNameHelper implements ISFrameworkConstants {
	public static final String CREAT = "creat";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";

	public static String getCreatId(String indexName) {
		if (indexName.indexOf(CREAT) > 0)
			return indexName;
		String[] s = indexName.split(DOWN_LINE);
		StringBuffer id = new StringBuffer();
		id.append(s[0]);
		id.append(DOWN_LINE);
		id.append(s[1]);
		id.append(DOWN_LINE);
		id.append(CREAT);
		return id.toString();
	}

	public static String getUpdateId(String indexName) {
		if (indexName.indexOf(CREAT) < 0)
			return indexName;
		String[] s = indexName.split(DOWN_LINE);
		StringBuffer id = new StringBuffer();
		if (s.length > 0)
			id.append(s[0]);
		if (s.length > 1 && CREAT.equals(s[1]) == false) {
			id.append(DOWN_LINE);
			id.append(s[1]);
		}
		if (s.length > 2 && CREAT.equals(s[2]) == false) {
			id.append(DOWN_LINE);
			id.append(s[2]);
		}
		if (s.length > 3 && CREAT.equals(s[3]) == false) {
			id.append(DOWN_LINE);
			id.append(s[3]);
		}
		id.append(DOWN_LINE);
		id.append(UPDATE);
		return id.toString();
	}

	public static String getDeleateId(String indexName) {
		if (indexName.indexOf(CREAT) > 0)
			return indexName;
		String[] s = indexName.split(DOWN_LINE);
		StringBuffer id = new StringBuffer();
		id.append(s[0]);
		id.append(DOWN_LINE);
		id.append(s[1]);
		id.append(DOWN_LINE);
		id.append(DELETE);
		return id.toString();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getUpdateId("corp_copyright_work_creat"));
		System.out.println(getUpdateId("corp_copyright_work_update"));
	}
}
