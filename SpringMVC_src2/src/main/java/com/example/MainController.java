package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.RegisterDTO;

@Controller
public class MainController {
	//역주입이 자동으로 된다. 아래 변수 선언과 생성사 선언 두가지만 있으면 된다.
	//이전에는 @Autowired를 붙여 줘야 헀는데 이젠 안쓴다.
	//controller > 클라이언트 요청-응답 처리 부분
	//외부 입출력 처리 >> 컨트롤러서비스리파지토리 
	private MainService service;
	private PersonDTO person;
	
	public MainController(MainService service,PersonDTO person) {
		this.service = service;
		this.person = person;
	}

	@RequestMapping("/")
	public String test() {
		System.out.println("test()");
		service.testService();
		System.out.println(person.toString());
		return "test";
	}
	
	//request 객체를 받아온다.
	//필요한 것만 씀 request, response에서
	//web servlet 대신에 쓴 것
	//매핑 이름은 기능에 따라 바꾼다.
	//안쓴다고 객체가 없는 건 아님
	@RequestMapping("/form.do")
	public String form(HttpServletRequest request) {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println(name + " " + age);
		request.setAttribute("test", "셋팅한 데이터");
		return "result";
	}
	
	//컨트롤러에서 받을 때 get인지 post인지 상관없다
	@RequestMapping("/registerView.do")
	public String registerView() {
		return "register";
	}
	
	//일반적으로 forward 방식으로만 전송된다.
	//return 값에 조작을 조금 하면 sendredirect형태로도 전송 가능하다.
//	@RequestMapping("/register.do")
//	public String register(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String pass = request.getParameter("pass");
//		String name = request.getParameter("name");
//		int age = Integer.parseInt(request.getParameter("age"));
//		
//		System.out.println(id);
//		System.out.println(pass);
//		System.out.println(name);
//		System.out.println(age);
//		return null;
//	}
	
//	@RequestParam(name = "")
//	이전 버전에서는 이렇게 설정을 해주어야 변수 값을 받아왔다.
//	@RequestMapping("/register.do")
//	public String register(String id, String pass, String name, int age) {
//		RegisterDTO dto = new RegisterDTO(id,pass,name,age);
//		System.out.println(dto);
//		return null;
//	}
	
	//변수명과 보내는 데이터의 변수명이 같아야 가능하다.
	//request를 추가해도 데이터를 받아옴
	//model도 역주입해서 받아오는 것이다.
	//return을 해줄 필요가 없음
	//setattribute할 때 저장할 것 여기에 저장하면 됨
	//addallattribute는 map으로 만들어 한번에 넣기도 가능해짐
	//request.setattribute로 하나 모델로하나 같기 때문에 편한 걸 쓰면 된다.
	//@autowired << 역주입 위해 쓴 어노테이션 이젠 안씀
	@RequestMapping("/register.do")
	public String register(RegisterDTO dto, Model model) {
		System.out.println(dto);
		model.addAttribute("dto",dto);
		return "register_result";
	}
	
	@RequestMapping("/loginView.do")
	public String loginView() {
		return "login";
	}
	
	//session 객체 역으로 받아오기
	//파일 업로드 같은 경우는 request를 직접 쓰기도 함
	@RequestMapping("/login.do")
	public String login(String id, String pass, HttpSession session) {
		//직접 만들지 않고 역주입으로 받아온다. 아래와 같이 쓰지 않음
//		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setAttribute("pass", pass);
		
		return "login_result";
	}
	
	
}
