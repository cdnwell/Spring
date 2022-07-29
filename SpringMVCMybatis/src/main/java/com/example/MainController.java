package com.example;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.MemberDTO;
import com.example.service.MemberService;

@Controller
public class MainController {
	private MemberService service;

	public MainController(MemberService service) {
		super();
		this.service = service;
	}
	
	//url만 입력했을 때 실행되는 부분
	@RequestMapping("/")
	public String main(Model model) {
		//전체 회원정보를 읽어와서 출력하기\
		//회원정보를 request영역에 저장해서 jsp에 출력
		//model도 가능
		List<MemberDTO> list = service.selectAllMember();
		model.addAttribute("list",list);
		return "main";
	}
	
	//jsp는 앞에 파일명만 있을 때 붙는다. redirect:/ 에 jsp가 뒤에 붙고 앞에 파일 경로가 붙지는
	//않는다.
	@RequestMapping("/register.do")
	public String register(MemberDTO dto) {
		service.insertMember(dto);
		System.out.println(dto);
		return "redirect:/";
	}
	
	@RequestMapping("/delete.do")
	public String delete(String id) {
		service.delete(id);
		return "redirect:/";
	}
	
	@RequestMapping("/updateView.do")
	public String updateView(String id,Model model) {
		MemberDTO dto = service.selectMember(id);
		System.out.println(dto);
		model.addAttribute("dto",dto);
		return "update_view";
	}
	
	@RequestMapping("/update.do")
	public String update(MemberDTO dto) {
		service.updateMember(dto);
		
		return "redirect:/";
	}
	
}
