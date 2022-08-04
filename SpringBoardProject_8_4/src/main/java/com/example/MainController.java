package com.example;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
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
		List<BoardDTO> list = boardService.selectBoardList(pageNo);
		model.addAttribute("list",list);
		
		//페이징 처리
		int count = boardService.selectBoardCount();
		PaggingVO vo = new PaggingVO(count,pageNo,10,5);
		model.addAttribute("pagging",vo);
		
		return "main";
	}
	
	@RequestMapping("/main.do")
	public String main() {
		return "redirect:/";
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
	
	@RequestMapping("/deleteBoard.do")
	public String delete(int bno) {
		//파일 삭제
		//게시글에 올린 첨부 파일 목록을 전부 읽어옴
		List<FileDTO> list = boardService.selectFileList(bno);
		for(int i = 0; i < list.size(); i++) {
			File file = new File(list.get(i).getPath());
			//파일 삭제는 다이렉트로 됨, 휴지통으로 가지 않는다.
			try {
				if(file.delete())
					System.out.println("파일 삭제 성공");
			} catch (Exception e) {
				//e.getMessage()와 e.printStackTrace(); 구분
				//e.printStackTrace() : 정확히 어디서 오류가 났는지 알아야 할 
				//예상 못할 때 > 몇 번 째 줄에서 오류남 확인 시
				//e.getMessage() : exception이 발생한 메세지만 볼 때, 에러 원인만 알면 될 때
				//파일 삭제 때 > 파일이 있는지, 락이 걸렸는지 알면 됨
				System.out.println(e.getMessage());
			}
		}
		//게시글을 삭제
		boardService.deleteBoard(bno);
		
		return "redirect:/";
	}
	
//	@RequestMapping("/insertComment.do")
//	public void insertComent(BoardCommentDTO dto, HttpServletResponse response) throws IOException {
//		int result = boardService.insertComment(dto);
//		System.out.println("result : "+result);
//		
//		response.getWriter().write(result);
//	}
	
	@RequestMapping("/insertComment.do")
	public void insertComment(int bno, String content, String writer, 
			HttpServletResponse response) {
		BoardCommentDTO dto = new BoardCommentDTO(bno,content,writer);
		
		int result = boardService.insertBoardComment(dto);
		
		//String.valueOf를 해야 정확히 문자열로 넘어간다.
		//숫자를 보내면 숫자에 해당하는 아스키 코드값이 넘어간다.
		//반드시 보낼 때는 문자열로 바꾸기, ajax로 보낼 때
		//단순히 result를 보내면 원하는 글자가 안나온다.
		try {
			response.getWriter().write(String.valueOf(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/plusLikeHate.do")
	public void plusLikeHate(int bno, int mode, HttpSession session,
			HttpServletResponse response) {
		int result = 0;
		String id = (String) session.getAttribute("id");
		if(mode == 0) {
			//좋아요
			result = boardService.insertBoardLike(bno, id);
		} else {
			//싫어요
			result = boardService.insertBoardHate(bno, id);
		}
		
		try {
			response.getWriter().write(String.valueOf(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/boardWriteView.do")
	public String boardWriteView() {
		//title,writer,content,file
		
		return "board_write_view";
	}
	
	@RequestMapping("/boardWrite.do")
	public String boardWrite(BoardDTO dto, MultipartHttpServletRequest request) {
//		System.out.println(dto.toString());
		int bno = boardService.insertBoard(dto);
		
		//파일 업로드
		
		//저장할 경로
		String root = "c:\\fileUpload\\";
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
			//실제 전송하는 부분
			try {
				f.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		//forward 방식으로 하면
		//boardWrite.do가 남아있어 새로고침을 하면 글쓰기가 반복된다.
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
	
	@RequestMapping("/commentLike.do")
	public String commentLike(String cno,String bno,HttpSession session) {
		String id = (String)session.getAttribute("id");
		boardService.insertCommentLike(cno,id);
		
		return "redirect:/boardView.do?bno="+bno;
	}
	
	@RequestMapping("/commentHate.do")
	public String commentHate(String cno, String bno, HttpSession session) {
		String id = (String)session.getAttribute("id");
		boardService.insertCommentHate(cno, id);
		
		return "redirect:/boardView.do?bno="+bno;
	}
	
	@RequestMapping("/registerView.do")
	public String registerView() {
		
		return "register";
	}
	
	@RequestMapping("/register.do")
	public String register(MemberDTO dto,HttpServletResponse response) throws IOException {
		memberService.insertMember(dto);
		
		return "redirect:/";
	}
	
	@RequestMapping("/memberManageView.do")
	public String memberManageView(Model model) {
		ArrayList<MemberDTO> list = memberService.selectAllMember();
		
		model.addAttribute("list",list);
		
		return "member_manager";
	}
	
	@RequestMapping("/memberDelete.do")
	public void deleteMember(String id,HttpServletResponse response) throws IOException {
		System.out.println("id : "+id);
		int result = 0;
		result=memberService.deleteMember(id);
		System.out.println("result : " + result);
		response.getWriter().write(String.valueOf(result));
	}
	
	@RequestMapping("/memberUpdate.do")
	public void updateMember(MemberDTO dto,HttpServletResponse response) throws IOException {
		System.out.println(dto.toString());
		int result = 0;
		result = memberService.updateMember(dto);
		
		response.getWriter().write(String.valueOf(result));
	}
	
	@RequestMapping("/memberSearch.do")
	public ResponseEntity<List<MemberDTO> > memberSearch(String kind, String search) {
		List<MemberDTO> list = memberService.selectMember1(kind, search);
		//list를 json으로 보내줌 
		//아까 search로 쓰기는 힘듦 해쉬맵으로 다 만들어야 함
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping("/deleteComment.do")
	public String deleteComment(int cno, int bno, HttpSession session) {
		boardService.deleteBoardComment(cno);
		
		return "redirect:/boardView.do?bno="+bno;
	}
	
	@RequestMapping("/fileUpload.do")
	public void fileUpload(@RequestParam(value="upload")MultipartFile fileload,
			HttpServletResponse response, HttpSession session) {
		//서버에 파일을 저장할 때에는 파일명을 시간값으로 변경
		//DB에 저장할 때에는 원본 파일명과 시간값을 모두 저장
		//filename 취득
		String originFileName = fileload.getOriginalFilename();
		//upload 경로 설정
		String root = "c:\\fileUpload\\";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String date = sdf.format(Calendar.getInstance().getTime());
		System.out.println("원본파일 : " + originFileName);
		System.out.println(originFileName.indexOf("."));
		System.out.println(originFileName.substring(originFileName.indexOf(".") + 1));
		
		String fileName = date +"_" +session.getAttribute("id")+originFileName.substring(originFileName.indexOf("."));
		File file = new File(root + "\\" + fileName);
				
		int fno = boardService.uploadImage(file.getAbsolutePath());
		try {
			PrintWriter pw = response.getWriter();
			fileload.transferTo(file);
			JSONObject obj = new JSONObject();
			obj.put("uploaded", true);
			obj.put("url", "imageDown.do?fno="+fno);
			pw.write(obj.toString());
		} catch (IOException e) {
			JSONObject obj = new JSONObject();
			obj.put("uploaded", false);
			JSONObject msg = new JSONObject();
			msg.put("message", "파일 업로드 중 에러 발생");
			obj.put("error", msg);
		}
	}
	
	@RequestMapping("/imageDown.do")
	public void imageLoad(int fno, HttpServletResponse response) {
		
		String path = boardService.selectImageFile(fno);
		
		File file = new File(path);
		
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
	
}
