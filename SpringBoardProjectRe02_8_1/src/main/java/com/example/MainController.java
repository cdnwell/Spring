package com.example;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.BoardDTO;
import com.example.service.BoardService;
import com.example.service.MemberService;
import com.example.vo.PagingVO;

@Controller
public class MainController {
	private MemberService memberService;
	private BoardService boardService;
	
	public MainController(MemberService memberService, BoardService boardService) {
		super();
		this.memberService = memberService;
		this.boardService = boardService;
	}

	@RequestMapping("/")
	public String main(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, Model model) {
		List<BoardDTO> list = boardService.selectBoardList(pageNo);
		model.addAttribute("list",list);
		
		//페이징 처리
		int count = boardService.selectBoardCount();
		PagingVO vo = new PagingVO(count, pageNo, 15, 4);
		model.addAttribute("paging",vo);
		
		return "main";
	}
	
	@RequestMapping("/main.do")
	public String mainView() {
		
		return "redirect:/";
	}
}
