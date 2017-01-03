package com.mollychin.utils;

import java.util.Timer;//定时器类

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SysContextListener implements ServletContextListener {

	public static String PROJECT_PATH = "";
	private Timer timer = null;

	// 重写contextInitialized
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// D:\Tomcat8.0.38\webapps\4Fun
		PROJECT_PATH = event.getServletContext().getRealPath("/");
		// 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
		timer = new Timer(true);
		// 添加日志，可在tomcat日志中查看到
		event.getServletContext().log("定时器已启动");
		// 调用定时任务，0表示任务无延迟，120*1000表示每隔120秒执行任务，触发间隔以毫秒计算。
		timer.schedule(new TimerAction(), 0, ConstantsUtil.CYCLES_TIME);
		event.getServletContext().log("已经添加任务");
	}

	// 重写contextDestroyed
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// 在这里关闭监听器，所以在这里销毁定时器。
		timer.cancel();
		event.getServletContext().log("定时器销毁");
	}

}
