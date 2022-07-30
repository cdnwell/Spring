package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		int bno = 0;
		String title = null;
		String content = null;
		String writer = null;
		
		String encoding = "utf-8";
		File userRoot = new File("c:\\fileUpload\\");
		if(!userRoot.exists())
			userRoot.mkdirs();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(userRoot);//업로드될 폴더 설정
		factory.setSizeThreshold(1024*1024);//버퍼 메모리
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		
		try {
			List<FileItem> list = upload.parseRequest(request);
			ArrayList<FileDTO> fList = new ArrayList<FileDTO>();
			
			for(FileItem item : list) {
				if(item.isFormField()) {
					switch(item.getFieldName()) {
					case "title":
						title = item.getString(encoding);
						break;
					case "content":
						content = item.getString(encoding);
						break;
					case "writer":
						writer = item.getString(encoding);
						break;
					}
					
				} else {
					//파일을 쓰는 부분
					if(item.getSize() > 0) {
						int idx = item.getName().lastIndexOf("\\");
						if(idx == -1)
							idx = item.getName().lastIndexOf("/");
						
						String fileName = item.getName().substring(idx + 1);
						//*핵심 uploadFile*
						File uploadFile = new File(userRoot + "\\" + fileName);
						if(!uploadFile.getParentFile().exists())
							uploadFile.getParentFile().mkdirs();
						fList.add(new FileDTO(uploadFile, 0, fList.size()));
						//fList.size() 아무것도 없으면 0, 하나 있으면 1, 두 개 들어가면 2...
						//생성자 만들면 해결
						item.write(uploadFile);
					}
				}
			}
			bno = boardService.selectBoardNo();
			//게시글 추가
			BoardDTO dto = new BoardDTO(title, writer, content);
			dto.setBno(bno);
			boardService.insertBoard(dto);
			//파일 테이블에 업로드한 파일 정보를 저장
			//미리 세팅해서 보내거나 map을 이용해서 보내도 됨(bno)
			for(FileDTO file : fList) {
				file.setBno(bno);
				boardService.insertFile(file);
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/boardView.do";
	}
	
}
