package com.example;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.BoardCommentDTO;
import com.example.dto.BoardDTO;
import com.example.dto.FileDTO;
import com.example.dto.MemberDTO;
import com.example.service.BoardService;
import com.example.service.MemberService;
import com.example.vo.PaggingVO;

@Controller
public class MainController {
	private MemberService memberService;
	private BoardService boardService;
	
	public MainController(MemberService memberService, BoardService boardService) {
		super();
		this.memberService = memberService;
		this.boardService = boardService;
	}

	//어노테이션으로 기본값 설정도 가능, 아무값이 없을 경우
	@RequestMapping("/")
	public String main(@RequestParam(name = "pageNo",defaultValue = "1") int pageNo, Model model) {
		System.out.println(pageNo);
		List<BoardDTO> list = boardService.selectBoardList(pageNo);
		model.addAttribute("list",list);
		
		//페이징 처리
		int count = boardService.selectBoardCount();
		PaggingVO vo = new PaggingVO(count,pageNo,10,5);
		model.addAttribute("pagging",vo);
		
		return "main";
	}
	
	@RequestMapping("/boardView.do")
	public String boardView(int bno,Model model, HttpSession session) {
		BoardDTO dto = boardService.selectBoardDTO(bno);
		List<FileDTO> flist = boardService.selectFileList(bno);
		List<BoardCommentDTO> comment = boardService.selectCommentDTO(bno);
		
		//게시글 조회수 증가
		HashSet<Integer> set = (HashSet<Integer>)session.getAttribute("bno_history");
		
		if(set == null)
			set = new HashSet<Integer>();
		
		if(set.add(bno))
			boardService.addBoardCount(bno);
		
		
		session.setAttribute("bno_history", set);
		model.addAttribute("board",dto);
		model.addAttribute("flist",flist);
		model.addAttribute("comment",comment);
		
		return "board_detail_view";
	}
	
	@RequestMapping("loginView.do")
	public String loginView() {
		return "login";
	}
	
	@RequestMapping("login.do")
	public String login(String id, String pass, HttpSession session) throws IOException {
		MemberDTO dto = memberService.login(id, pass);
		
		if(dto != null) {
			session.setAttribute("login", true);
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			session.setAttribute("grade", dto.getGradeNo());
		} else {
			session.setAttribute("login", false);
			return "login";
		}
		
		return "redirect:/";
	}
	
}
