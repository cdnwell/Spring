package com.example.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링에 필요한 파일을 xml이 아니라 class로 정의하겠다.
@Configuration
public class DIContainer {
	
	//bean = 객체, DI 세팅을 하겠다.
	//클래스하나 만들어놓고 리턴할 메서드 만들어 놓으면 된다.
	@Bean
	public Greeting greeter() {
		Greeting g = new Greeting(1, "test");
		return g;
	}
	
	@Bean
	public MemberService service() {
		return new MemberService();
	}
}
