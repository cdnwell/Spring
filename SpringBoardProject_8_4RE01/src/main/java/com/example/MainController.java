package com.example;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	public void login(String id, String pass, HttpSession session, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		MemberDTO dto = memberService.login(id,pass);
		
		if(dto == null) {
			
			response.getWriter().write("<script>alert('아이디 혹은 비밀번호가 틀렸습니다.');location.href='loginView.do';</script>");
//			return "redirect:/loginView.do";
		} else {
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			session.setAttribute("gradeNo", dto.getGradeNo());
			session.setAttribute("login", true);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(Calendar.getInstance().getTime());
			session.setAttribute("date", date);
			
			response.getWriter().write("<script>location.href='main.do';</script>");
//			return "redirect:/main.do";
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
	public String boardView(int bno, HttpServletResponse response,Model model,HttpSession session) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		BoardDTO dto = boardService.selectBoard(bno);
		List<FileDTO> flist= boardService.selectFileList(bno);
		List<BoardCommentDTO> comment = boardService.selectComment(bno);
		Map<String, Integer> move = boardService.selectLagLead(bno);
		//move -> NEXT, BEFORE
		int rownum = boardService.searchRownum(bno);
		int pageNo = rownum / 15 + (rownum % 15 == 0 ? 0 : 1);
		
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
			model.addAttribute("pageNo",pageNo);
			model.addAttribute("move",move);
			
			return "board_view";
		} else {
			response.getWriter().write("<script>alert('게시물이 존재하지 않습니다.');history.back();</script>");
			return null;
		}
	}
	
	@RequestMapping("/commentDelete.do")
	public String commentDelete(int cno,int bno) {
		boardService.commentDelete(cno);
		
		return "redirect:/boardView.do?bno="+bno;
	}
	
	@RequestMapping("/commentWrite.do")
	public void commentWrite(int bno, String content, HttpSession session,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String id = (String)session.getAttribute("id");
		content = content.replaceAll("\n", "<br>");
		int result = 0;
		try{
			result = boardService.insertComment(bno,content,id);
			if(result == 0) throw new Exception();
			response.getWriter().write("<script>alert('댓글 등록 성공');location.href='boardView.do?bno="+bno+"';</script>");
		} catch (Exception e) {
			response.getWriter().write("<script>alert('댓글 등록에 실패하였습니다.');location.href='boardView.do?bno="+bno+"';</script>");
		}
	}
	
	@RequestMapping("/boardWriteView.do")
	public String boardWriteView() {
		
		return "board_write_view";
	}
	
	@RequestMapping("/boardWrite.do")
	public String boardWrite(BoardDTO dto, MultipartHttpServletRequest request) {
		int bno = boardService.insertBoard(dto);
		
		String root = "c:\\uploadFiles\\";
		File userRoot = new File(root);
		if(!userRoot.exists())
			userRoot.mkdirs();
		
		List<MultipartFile> fileList = request.getFiles("file");
		int i = 1;
		for(MultipartFile f : fileList) {
			String originalFileName = f.getOriginalFilename();
			if(f.getSize() == 0) continue;
			File uploadFile = new File(root + "\\" + originalFileName);
			boardService.insertFileList(new FileDTO(uploadFile,bno,i));
			i++;
			try {
				f.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/boardView.do?bno="+bno;
	}
	
	@RequestMapping("/fileDown.do")
	public void fileDown(String fno, String bno, HttpServletResponse response) {
		FileDTO dto = boardService.selectFile(bno,fno);
		
		File file = new File(dto.getPath());
		
		response.setHeader("Content-Disposition", "attachment;fileName="+file.getName());
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength((int)file.length());
		
		try {
			FileInputStream fis = new FileInputStream(file);
			
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[1024*1024];
			
			while(true) {
				int size = fis.read(buffer);
				if(size == -1) break;
				bos.write(buffer,0,size);
				bos.flush();
			}
			
			fis.close();
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/boardLike.do")
	public void boardLike(int bno,HttpSession session, HttpServletResponse response) throws IOException {
		String id = (String)session.getAttribute("id");
		int result = boardService.insertBoardLike(bno,id);
		response.getWriter().write(String.valueOf(result));
	}
	
	@RequestMapping("/boardHate.do")
	public void boardHate(int bno,HttpSession session,HttpServletResponse response) throws IOException {
		String id = (String)session.getAttribute("id");
		int result = boardService.insertBoardHate(bno,id);
		response.getWriter().write(String.valueOf(result)); 
	}
	
	@RequestMapping("/commentLike.do")
	public String commentLike(int bno, int cno, HttpSession session) {
		String id = (String)session.getAttribute("id");
		int result = boardService.insertCommentLike(cno,id);
		return "redirect:/boardView.do?bno="+bno;
	}
	
	@RequestMapping("/commentHate.do")
	public String commentHate(int bno, int cno, HttpSession session) {
		String id = (String)session.getAttribute("id");
		int result = boardService.insertCommentHate(cno,id);
		return "redirect:/boardView.do?bno="+bno;
	}
	
	@RequestMapping("/updateView.do")
	public String updateView(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		MemberDTO dto = memberService.selectMember(id);
		
		dto.setPasswd("");
		
		model.addAttribute("member",dto);
		
		return "update_view";
	}
	
	@RequestMapping("/update.do")
	public void update(MemberDTO dto, HttpServletResponse response, HttpSession session) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			memberService.updateMember(dto);
			session.setAttribute("name", dto.getName());
			
			response.getWriter().write("<script>location.href='/';</script>");
		} catch (Exception e) {
			response.getWriter().write("<script>alert('회원정보 수정에 실패하였습니다.');history.back();</script>");
		}
		
	}
	
	@RequestMapping("/registerView.do")
	public String registerView() {
		
		return "register_view";
	}
	
	@RequestMapping("/idCheck.do")
	public void idCheck(String id, HttpServletResponse response) throws IOException {
		String id_cmp = memberService.selectId(id);
		if(id.equals(id_cmp)) {
			response.getWriter().write(String.valueOf(0));
		} else {
			response.getWriter().write(String.valueOf(1));
		}
	}
	
	@RequestMapping("/register.do")
	public void registerMember(MemberDTO dto, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		int result = 0;
		result = memberService.insertMember(dto);
		
		if(result != 0) {
			response.getWriter().write("<script>alert('회원등록이 성공하였습니다.');location.href='loginView.do';</script>");
		} else {
			response.getWriter().write("<script>alert('회원등록에 실패하였습니다.');history.back();</script>");
		}
	}
	
}
