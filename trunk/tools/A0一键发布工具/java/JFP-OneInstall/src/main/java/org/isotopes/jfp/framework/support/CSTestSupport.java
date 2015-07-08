package org.isotopes.jfp.framework.support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.isotopes.jfp.framework.ISFrameworkConstants;


/**
 * 测试环境超类
 * @since 0.1 2012-7-13
 * @version 0.1
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//// 加载测试驱动
//@DirtiesContext
//@ContextConfiguration(locations = { "classpath:spring.xml" })
//// 加载配置文件{ "classpath:spring.xml","" }
public class CSTestSupport implements ISFrameworkConstants{

	/**
	 * 测试方法
	 */
//	@Test
	public final void testMyTestSupport() {
		assertNotNull(EMPTY);
		assertEquals(EMPTY, EMPTY);
	}
}
