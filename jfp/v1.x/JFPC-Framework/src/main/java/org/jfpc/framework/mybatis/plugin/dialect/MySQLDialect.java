package org.jfpc.framework.mybatis.plugin.dialect;
/**
 * @author 
 */
public class MySQLDialect extends DefaultDialect{

	public boolean supportsLimitOffset(){
		return true;
	}
	
    public boolean supportsLimit() {   
        return true;   
    }  
    
	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
        if (offset > 0) {   
        	return sql + super.getSupportsOrderby() + " limit "+offsetPlaceholder+","+limitPlaceholder; 
        } else {   
            return sql + super.getSupportsOrderby() + " limit "+limitPlaceholder;
        }  
	}   
  
}
