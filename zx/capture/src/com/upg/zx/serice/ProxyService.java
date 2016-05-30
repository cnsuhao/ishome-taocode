package com.upg.zx.serice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.upg.zx.domain.entity.Proxy;
public class ProxyService {
	/** 锁对象 */
	private ReentrantLock fachlock = new ReentrantLock();
	
	private List<Proxy> inperfectInfoProxyBuffer = new ArrayList<Proxy>();
	
	private JdbcTemplate jdbcTemplate;
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}  
	
	
	public List<Proxy> queryAll(){
		return (List<Proxy>)this.jdbcTemplate.query("select * from proxy",new ResultSetExtractor<List>(){
			@Override
			public List extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<Proxy> result = new ArrayList<Proxy>();  
				while(rs.next()) {
					Proxy proxy = new Proxy();
					proxy.setId(rs.getInt("id"));
					proxy.setPort(rs.getInt("port"));
					proxy.setIp(rs.getString("ip"));
					proxy.setFlag(rs.getInt("flag"));
					result.add(proxy);
				} 
				return result;
			}
		
			
		});

	}
	 
	 
	public  List<Proxy> getProxyList(){
		fachlock.lock();
		try {
			if (inperfectInfoProxyBuffer == null || inperfectInfoProxyBuffer.size() == 0) {
				inperfectInfoProxyBuffer = this.queryAll();
			} 		 
		} finally {
			fachlock.unlock();
		}
		return inperfectInfoProxyBuffer;
	}
	
}
