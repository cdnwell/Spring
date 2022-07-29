package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

//@Component 써도 된다. component는 변경이 되는 것,
//repository는 외부 입출력 담당, 역주입되는지 체크 정도
//기본값 설정
//기본값 설정해주고 set으로 받아오기
@Repository
public class PersonDTO {
	private String name;
	private int age;
	
	//@Value("값") <- 값을 숫자로 넣어도 알아서 숫자로 바꿔준다.
	//String만 들어갈 수 있다. 
	//기본생성자 없이 기본값을 설정하는 방법
	public PersonDTO(@Value("홍길동") String name,@Value("22") int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "PersonDTO [name=" + name + ", age=" + age + "]";
	}
	
}
