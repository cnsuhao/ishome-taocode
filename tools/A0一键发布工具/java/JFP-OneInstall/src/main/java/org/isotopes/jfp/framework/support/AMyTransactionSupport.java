package org.isotopes.jfp.framework.support;

import javax.annotation.Resource;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;




/**
 * 数据库手动操作超类
 * @since 0.1 2012-7-13
 * @version 0.1
 */
public abstract class AMyTransactionSupport extends CSDBConnectionSupport{

	@Resource
	protected PlatformTransactionManager transactionManager;
	
	/**
	 * 开始手动控制
	 * @throws Exception
	 */
	public void toTransaction() throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();// 事务定义类
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);// 初始化事务,参数定义事务的传播类型
		TransactionStatus status = transactionManager.getTransaction(def);// 返回事务对象
		try {
			doDBProcess();
			transactionManager.commit(status);
		} catch (Exception ex) {
			transactionManager.rollback(status);
		}
	}

	/**
	 * 数据访问操作
	 */
	protected abstract void doDBProcess();

}
