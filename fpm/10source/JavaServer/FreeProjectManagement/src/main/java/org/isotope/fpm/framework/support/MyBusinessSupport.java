package org.isotope.fpm.framework.support;

//@Repository、@Service、@Controller，它们分别对应存储层Bean，业务层Bean，和展示层Bean
//@Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按byName自动注入罢了。
//@Resource有两个属性是比较重要的，分别是name和type，Spring将 @Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。
//所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。
/**
 * 业务规则超类(系统超类)
 * @since 0.1 2012-7-13
 * @version 0.1
 */
public class MyBusinessSupport implements IMyBusinessSupport {
	//Spring使用了接口名称扫描注册，当一个类实现多个接口的时候，这个类会知道注册到相应的接口下面去，
	//出现多个的时候，就需要详细指定实现类的声明bean名称，参考Qualifier
	//@Qualifier("transactionManager")
	//@Qualifier 只能和 @Autowired 结合使用，是对 @Autowired 有益的补充。
	//默认使用@Resource
	
	//开启事务控制,处理业务
//	@Transactional
//	protected void doProcess(){
//		
//	}
}
