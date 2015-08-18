package org.ishome.jfp.framework.support;

import java.util.List;

import org.ishome.jfp.framework.beands.ObjectBean;


/**
 * 数据压缩解压缩接口
 * 
 * @author fucy
 * @since 2.0.5
 * @version 2.0.5 2014/2/15
 */
public interface ISServiceCommunicationSupport {

	/**
	 * List存储对象压缩
	 * 
	 * @param data
	 * @return
	 */
	public String compress(List<ObjectBean> data);

	/**
	 * 压缩对象转换为List存储
	 * 
	 * @param data
	 * @return
	 */
	public List<ObjectBean> unCmpress(String data);
}
