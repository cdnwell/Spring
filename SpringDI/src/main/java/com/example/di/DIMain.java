package com.example.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DIMain {

	public static void main(String[] args) {
		//annotation으로 설정한 것을 읽어오겠다.
		AnnotationConfigApplicationContext ctx 
			= new AnnotationConfigApplicationContext(DIContainer.class);
		Greeting g1 = ctx.getBean("greeter",Greeting.class);
		System.err.println(g1);
		Greeting g2 = ctx.getBean("greeter",Greeting.class);
		System.err.println(g2);
		// == 등호는 메모리 주소 비교
		System.out.println(g1 == g2);
		
		MemberService service = ctx.getBean("service",MemberService.class);
		service.selectMember();
		
	}

}
