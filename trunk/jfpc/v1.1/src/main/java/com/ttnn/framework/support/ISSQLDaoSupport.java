package com.ttnn.framework.support;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;

/**
 * 数据库操作接口定义超类<br>
 * 定义通用8个操作方法<br>
 * 缓存参考SSM获得MyBatis配置
 * @author Spook
 * @since 0.1.0 2013-8-21
 * @version 0.1.0
 */
public interface ISSQLDaoSupport extends ISBusinessSupport{
//	/**
//	 * 批量删除
//	 * <p>exp : delete from Users where   DBKEY in('admin','admin2')
//	 * @param dbParamBean 长度大于0
//	 * @return
//	 */
//	public int[] doBatchDelete(List<? extends FrameworkDataBean> dbParamBean);
//
//	/**
//	 * 批量插入
//	 * <p>exp : insert into Users(PUKEY)values('admin'),('admin2');
//	 * @param dbParamBean 长度大于0
//	 * @return
//	 */
//	public int[] doBatchInsert(List<? extends FrameworkDataBean> dbParamBean);

//	/**
//	 * 一览
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public List<FrameworkDataBean> doFindList(FrameworkDataBean dbParamBean);
	
//	/**
//	 * 根据条件更新所有记录<br>
//	 * 添加与结果不能使用同一个字段<br>
//	 * 如果需要，请自行修改对应方法<br>
//	 * 参考XML#doUpdateAll
//	 * 
//	 * @param dbParamBean
//	 * @return
//	 */
//	public int doUpdateAll(FrameworkDataBean dbParamBean);

	/**
	 * 一览（分页）
	 * @param start 		每页起始数目
	 * @param size  		每页显示数目
	 * @param dbParamBean		查询条件
	 * @return
	 * int start,int size,
	 */
	public List<FrameworkDataBean> doSelectPage(PageVO paramPageVO);

	
	///////////////////////////////////////////////////////////////////////
	// 增删改查（CRUD）

	/**
	 * 插入一条记录
	 * 
	 * @param dbParamBean
	 */
	public int doInsert(FrameworkDataBean dbParamBean);
	
	/**
	 * 更新一条记录
	 * @UpdateSingleCache(namespace = "_Name_Space_", expiration = 3600)
	 * @param dbParamBean
	 */
	/*
	 * 当执行updateUser方法时，系统会更新缓存中PUKEY对应的实体 将实体内容更新成@*DataUpdateContent标签所描述的实体
	 */
//	@UpdateSingleCache
//	@ParameterValueKeyProvider @ParameterDataUpdateContent
	public int doUpdate( FrameworkDataBean dbParamBean);
	
	/**
	 * 查询记录
	 * @UseJson
	 * @ReadThroughSingleCache(namespace = "_Name_Space_", expiration = 3600)
	 * @param dbParamBean
	 */
	/*
	 * 当执行getById查询方法时，系统首先会从缓存中获取PUKEY对应的实体 如果实体还没有被缓存，则执行查询方法并将查询结果放入缓存中
	 */

//	@UseJson
//	@ReadThroughSingleCache
//	@ParameterValueKeyProvider
	public FrameworkDataBean doRead( FrameworkDataBean dbParamBean);

	/**
	 * 物理删除一条记录
	 * @InvalidateSingleCache(namespace = "_Name_Space_")
	 * @param dbParamBean
	 */
	/*
	 * 当执行deleteUser方法时，系统会删除缓存中PUKEY对应的实体  
	 */
//	@InvalidateSingleCache
//	@ParameterValueKeyProvider
	public int doDelete( FrameworkDataBean dbParamBean);
	
	/**
	 * 逻辑删除一条记录
	 * @InvalidateSingleCache(namespace = "_Name_Space_")
	 * @param dbParamBean
	 */
	/*
	 * 当执行deleteUser方法时，系统会删除缓存中PUKEY对应的实体  
	 */
//	@InvalidateSingleCache
//	@ParameterValueKeyProvider
	public int toDelete( FrameworkDataBean dbParamBean);

	
}
