package lj.util;

import java.io.FileOutputStream;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
	private final static String BEANS_XML = "beans.xml";
	private static BeanFactory factory = null;

	/**
	 * 禁止实例对象
	 */
	private SpringUtils() {
	}

	/**
	 * 创建SpringBeanFactory
	 * 
	 * @return
	 */
	public static BeanFactory getBeanFactory() {
		if (factory == null)
			factory = new ClassPathXmlApplicationContext(BEANS_XML);
		return factory;
	}

}
