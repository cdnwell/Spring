package com.example;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	public String main(@RequestParam(name ="pageNo", defaultValue="1") int pageNo, Model model) {
		List<BoardDTO> list = boardService.selectBoardList(pageNo);
		model.addAttribute("board",list);
		
		int count = boardService.selectBoardCount();
		PagingVO vo = new PagingVO(count, pageNo, 15, 4);
		model.addAttribute("paging",vo);
		
		return "main";
	}
	
	@RequestMapping("/main.do")
	public String mainView() {
		
		return "redirect:/";
	}
	
	@RequestMapping("/loginView.do")
	public String loginView() {
		
		return "login";
	}
	
	@RequestMapping("/login.do")
	public String login(String id, String pass, HttpSession session, HttpServletResponse response) {
		MemberDTO dto = memberService.login(id,pass);
		
		if(dto == null) {
			
			return "redirect:/loginView.do";
		} else {
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			session.setAttribute("gradeNo", dto.getGradeNo());
			session.setAttribute("login", true);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(Calendar.getInstance().getTime());
			session.setAttribute("date", date);
			
			return "redirect:/main.do";
		}
		
		
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session, @RequestParam(name = "loginMsg", defaultValue="none")String login) {
		session.invalidate();
		
		switch(login) {
		case "none":
			return "redirect:/main.do";
		case "fieldset":
			return "redirect:/loginView.do";
		}
		
		return null;
		
	}
	
	@RequestMapping("/boardView.do")
	public String boardView(int bno,HttpServletResponse response,Model model,HttpSession session) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		BoardDTO dto = boardService.selectBoard(bno);
		List<FileDTO> flist= boardService.selectFileList(bno);
		List<BoardCommentDTO> comment = boardService.selectComment(bno);
		
		HashSet<Integer> set = (HashSet<Integer>)session.getAttribute("bno_history");
		
		if(set == null)
			set = new HashSet<>();
		
		if(set.add(bno))
			boardService.addBoardCount(bno);
		
		if(dto != null) {
			session.setAttribute("bno_history",set);
			model.addAttribute("board",dto);
			model.addAttribute("flist",flist);
			model.addAttribute("comment",comment);
			
			return "board_view";
		} else {
			response.getWriter().write("<script>alert('게시물이 존재하지 않습니다.');history.back();</script>");
			return null;
		}
	}
	
	
	
}
