package com.example;

import org.springframework.stereotype.Service;

//@Service로 싱글턴 패턴이 적용된다.
//컨트롤러에서 역주입으로 받아올 수 있다.
@Service
public class MainService {
	
	public void testService() {
		System.out.println("testService method");
	}
	
}
