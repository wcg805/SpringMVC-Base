package com.go.spring.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebStartUtil implements ServletContextListener {


	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Initialized EmujeesuPayWeb Start...");
		
		DataUtil.FILE_PATH =  event.getServletContext().getRealPath("/");
		
		System.out.println("Initialized EmujeesuPayWeb Over...");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Destroyed...");

	}

}
