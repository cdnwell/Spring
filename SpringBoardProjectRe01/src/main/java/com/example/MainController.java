package com.example;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
		this.memberService = memberService;
		this.boardService = boardService;
	}
	
	@RequestMapping("/")
	public String main(@RequestParam(name = "pageNo", defaultValue="1") int pageNo, Model model ) {
		List<BoardDTO> list = boardService.selectBoardList(pageNo);
		
		int count = boardService.selectAllCount();
		PaggingVO vo = new PaggingVO(count, pageNo, 10, 5);
		
		model.addAttribute("list",list);
		model.addAttribute("pagging", vo);
		
		return "main";
	}
	
	@RequestMapping("/main.do")
	public String mainView(String pageNo) {
		
		return "redirect:/?pageNo="+pageNo;
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
	
	@RequestMapping("/boardView.do")
	public String boardView(int bno, HttpSession session, Model model) {
		HashSet<Integer> set = (HashSet<Integer>)session.getAttribute("bno_history");
		
		if(set == null)
			set = new HashSet<>();
		
		if(set.add(bno))
			boardService.addBoardCount(bno);
		
		session.setAttribute("bno_history", set);
		
		BoardDTO dto = boardService.selectBoard(bno);
		dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
		model.addAttribute("board",dto);
		
		List<BoardCommentDTO> list = boardService.selectCommentList(bno);
		model.addAttribute("comment",list);
		
		List<FileDTO> fList = boardService.selectFileList(bno);
		model.addAttribute("flist",fList);
		
		return "board_detail_view";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String delete(int bno) {
		boardService.deleteBoard(bno);
		return "redirect:/";
	}
	
	@RequestMapping("/boardWriteView.do")
	public String writeView() {
		
		return "board_write_view";
	}
	
	@RequestMapping("/boardWrite.do")
	public String write(MultipartHttpServletRequest request) {
		
		return "redirect:/boardView.do";
	}
	
	@RequestMapping(value = "/insertComment.do", method = { RequestMethod.GET })
	@ResponseBody
	public int insertComment(int bno, String content, String writer,HttpServletResponse response) throws IOException {
		//bno,writer,content
		int result = boardService.insertBoardComment(new BoardCommentDTO(bno,content,writer)) ;
		System.out.println(result);
		return result;
	}
	
}
