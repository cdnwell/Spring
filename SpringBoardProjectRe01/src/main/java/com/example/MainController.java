package com.example;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.BoardDTO;
import com.example.dto.MemberDTO;
import com.example.service.BoardService;
import com.example.service.MemberService;
import com.example.vo.PaggingVO;

@Controller
public class MainController {
	private MemberService memberService;
	private BoardService boardService;
	
	public MainController(MemberService memberService, BoardService boardService) {
		this.memberService = memberService;
		this.boardService = boardService;
	}
	
	@RequestMapping("/")
	public String main(@RequestParam(name = "pageNo", defaultValue="1") int pageNo, Model model ) {
		System.out.println(pageNo);
		List<BoardDTO> list = boardService.selectBoardList(pageNo);
		
		int count = boardService.selectAllCount();
		PaggingVO vo = new PaggingVO(count, pageNo, 10, 5);
		
		model.addAttribute("list",list);
		model.addAttribute("pagging", vo);
		
		return "main";
	}
	
	@RequestMapping("/loginView.do")
	public String loginView() {
		return "login";
	}
	
	@RequestMapping("/login.do")
	public String login(String id, String pass, HttpSession session) {
		MemberDTO dto = memberService.login(id, pass);
		
		if(dto != null) {
			session.setAttribute("login", true);
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
		} else {
			session.setAttribute("login", false);
			return "login";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "login";
	}
	
}
