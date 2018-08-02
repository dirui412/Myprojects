package lj.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lj.global.AppFun;
import lj.global.AppVar;
import lj.util.LogUtils;

/**
 * 系统的第一个listener，执行应用程序加载
 * 
 * @author samlv
 *
 */
public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 1-保存运行路径
		AppVar.appPath = sce.getServletContext().getRealPath("/");  
		// 2-执行预加载方法
		AppFun.preLoad();
		// 3-日志系统开始运行
		String str = AppVar.sysConfig.getSystemName() + " is beginning to run";
		LogUtils.logInfo(str);
		// 4-初始化
		try {

		} catch (Exception e) {
			LogUtils.logError("InitialServlet.init", e);
			e.printStackTrace();
		}
		System.out.println(str);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
