package com.student.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContext {
	private AnnotationConfigApplicationContext ctx;
	private static AppContext instance = new AppContext();
	public AppContext() {
		ctx = new AnnotationConfigApplicationContext(DIContainer.class);
	}
	public AnnotationConfigApplicationContext getCtx() {
		return ctx;
	}
	public static AppContext getInstance() {
		if(instance == null)
			instance = new AppContext();
		return instance;
	}
	
	
}
