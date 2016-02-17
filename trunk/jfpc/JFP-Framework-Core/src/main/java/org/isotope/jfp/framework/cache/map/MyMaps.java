package org.isotope.jfp.framework.cache.map;

import java.util.ArrayList;
import java.util.HashMap;

import org.isotope.jfp.framework.cache.ICacheService;

/**
 * 本地缓存
 * 
 *
 */
@SuppressWarnings("unchecked")
public class MyMaps implements ICacheService {

	HashMap<String, Object> cache = new HashMap<String, Object>();
	
	public boolean clear(){
		cache.clear();
		return true;
	}
	
	@Override
	public boolean putObject(String key, Object value) {
		cache.put(key, value);
		return true;
	}

	@Override
	public boolean putObject(String key, Object value, int expireTimeWithSecond, boolean translation) {
		cache.put(key, value);
		return true;
	}

	@Override
	public Object getObject(String key) {
		return cache.get(key);
	}

	@Override
	public Object getObject(String key, boolean translation) {
		return cache.get(key);
	}

	@Override
	public Object deleteObject(String key) {
		return cache.remove(key);
	}

	@Override
	public Object deleteObject(String key, boolean translation) {
		return cache.remove(key);
	}

	@Override
	public HashMap<String, Object> getAllObjectInMap(String key) {
		HashMap<String, Object> ms = (HashMap<String, Object>) getObject(key);
		if (ms == null)
			ms = new HashMap<String, Object>();
		return ms;
	}

	/**
	 * 追加数据到已有的缓存数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public boolean addObjectInMap(String rkey, String mkey, Object value) {
		HashMap<String, Object> ms = (HashMap<String, Object>) getObject(rkey);
		if (ms == null)
			ms = new HashMap<String, Object>();
		ms.put(mkey, value);
		cache.put(rkey, ms);
		return true;
	}

	@Override
	public boolean addObjectInMap(String rkey, String mkey, Object value, boolean translation) {
		return addObjectInMap(rkey, mkey, value);
	}

	/**
	 * 从已有的缓存数据里面删除一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object removeObjectInMap(String rkey, String mkey) {
		HashMap<String, Object> ms = (HashMap<String, Object>) getObject(rkey);
		if (ms == null)
			ms = new HashMap<String, Object>();
		return ms.remove(mkey);
	}

	@Override
	public Object removeObjectInMap(String rkey, String mkey, boolean translation) {
		HashMap<String, Object> ms = (HashMap<String, Object>) getObject(rkey);
		if (ms == null)
			ms = new HashMap<String, Object>();
		return ms.remove(mkey);
	}

	/**
	 * 从已有的缓存数据里面获取一个数据 <br>
	 * （基于保存Key,Map数据队列）
	 * 
	 * @return
	 */
	public Object findObjectInMap(String rkey, String mkey) {
		HashMap<String, Object> ms = (HashMap<String, Object>) getObject(rkey);
		if (ms == null)
			ms = new HashMap<String, Object>();
		return ms.get(mkey);
	}

	@Override
	public Object findObjectInMap(String rkey, String mkey, boolean translation) {
		HashMap<String, Object> ms = (HashMap<String, Object>) getObject(rkey);
		if (ms == null)
			ms = new HashMap<String, Object>();
		return ms.get(mkey);
	}

	@Override
	public long sizeOfMap(String key) {
		HashMap<String, Object> ms = (HashMap<String, Object>) getObject(key);
		if (ms == null)
			return 0;
		return (long) ms.size();
	}

	// ///////////////////////////////////////////////////////////////////////////
	@Override
	public long sizeOfList(String key) {
		ArrayList<Object> ms = (ArrayList<Object>) getObject(key);
		if (ms == null)
			return 0;
		return (long) ms.size();
	}

	public boolean offerObjectInList(String key, Object value) {
		ArrayList<Object> ms = (ArrayList<Object>) getObject(key);
		if (ms == null)
			ms = new ArrayList<Object>();
		ms.add(value);
		return true;
	}

	@Override
	public boolean offerObjectInList(String key, Object value, boolean translation) {
		return offerObjectInList(key, key);
	}

	@Override
	public Object peekFirstObjectInList(String key) {
		ArrayList<Object> ms = (ArrayList<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.get(0);
	}

	@Override
	public Object peekFirstObjectInList(String key, boolean translation) {
		ArrayList<Object> ms = (ArrayList<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.get(0);
	}

	@Override
	public Object pollFirstObjectInList(String key) {
		ArrayList<Object> ms = (ArrayList<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.remove(0);
	}

	@Override
	public Object pollFirstObjectInList(String key, boolean translation) {
		ArrayList<Object> ms = (ArrayList<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.remove(0);
	}

	@Override
	public Object peekLastObjectInList(String key) {
		ArrayList<Object> ms = (ArrayList<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.get(ms.size());
	}

	@Override
	public Object peekLastObjectInList(String key, boolean translation) {
		ArrayList<Object> ms = (ArrayList<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.get(ms.size());
	}

	@Override
	public Object pollLastObjectInList(String key) {
		ArrayList<Object> ms = (ArrayList<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.remove(ms.size());
	}

	@Override
	public Object pollLastObjectInList(String key, boolean translation) {
		ArrayList<Object> ms = (ArrayList<Object>) getObject(key);
		if (ms == null)
			return null;
		else
			return ms.remove(ms.size());
	}

	@Override
	public void selectDB(int index) {
		// TODO Auto-generated method stub
		
	}
}
