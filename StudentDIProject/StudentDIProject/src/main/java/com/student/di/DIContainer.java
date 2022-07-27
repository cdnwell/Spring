package com.student.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.student.config.DBManager;
import com.student.controller.HandlerMapping;
import com.student.dao.StudentDAO;
import com.student.service.StudentService;

//스프링에 필요한 파일을 xml이 아니라 class로 정의하겠다.
@Configuration
public class DIContainer {
	
	//bean = 객체, DI 세팅을 하겠다.
	//클래스하나 만들어놓고 리턴할 메서드 만들어 놓으면 된다.
	@Bean
	public HandlerMapping handler() {
		return new HandlerMapping();
	}
	
	@Bean
	public DBManager dbmanager() {
		return new DBManager();
	}
	
	//dao에서 필요한 것 > dbmanager
	@Bean
	public StudentDAO dao() {
		return new StudentDAO(dbmanager());
	}
	
	//service에서 필요한 것 > dao
	@Bean
	public StudentService service() {
		return new StudentService(dao());
	}
	
}