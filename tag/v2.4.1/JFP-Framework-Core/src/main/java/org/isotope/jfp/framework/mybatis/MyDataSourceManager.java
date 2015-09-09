package org.isotope.jfp.framework.mybatis;

import java.sql.SQLException;
import java.util.Map;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * SQL数据源管理器
 * 
 * @author Spook
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 * @version 2.1.2.20150422
 */
@SuppressWarnings("unchecked")
public class MyDataSourceManager extends AbstractRoutingDataSource implements ISFrameworkConstants {
	
	public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
	}
	
	@Override  
    public Object unwrap(@SuppressWarnings("rawtypes") Class iface) throws SQLException {  
        return null;  
    }  
  
    @Override  
    public boolean isWrapperFor(@SuppressWarnings("rawtypes") Class iface) throws SQLException {  
        return false;  
    }  

	public void setTargetDataSources(@SuppressWarnings("rawtypes") Map targetDataSources) {
		super.setTargetDataSources(targetDataSources);
		afterPropertiesSet();
	}

	protected Object determineCurrentLookupKey() {
		return MyDataSourceHolder.getDataSourceName();
	}

}
