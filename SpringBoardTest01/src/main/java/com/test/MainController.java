package com.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import com.test.dto.BoardCommentDTO;
import com.test.dto.BoardDTO;
import com.test.dto.FileDTO;
import com.test.vo.PaggingVO;

@Controller
public class MainController {
	private BoardService boardService;

	public MainController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}
	
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
		Map<String, Object> map = boardService.selectNextBefore(bno);
		System.out.println(map);
		
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
		model.addAttribute("move",map);
		
		return "board_detail_view";
	}
	
	@RequestMapping("/boardWriteView.do")
	public String boardWriteView() {
		
		return "board_write_view";
	}
	
	@RequestMapping("/boardWrite.do")
	public String boardWrite(BoardDTO dto, MultipartHttpServletRequest request) {
		int bno = boardService.insertBoard(dto);
		//dto에서 title, writer, content 추가해서 게시글 작성 완료, bno는 dual로 뽑아서 씀
		
		String root = "c:\\uploadFiles\\";
		File userRoot = new File(root);
		if(!userRoot.exists())
			userRoot.mkdirs();
		//1.
		//root파일 있는지 확인후 없으면 만들어준다.
		//File 클래스의 mkdirs를 이용, 파일 명은 없이 디렉토리명만 적어줌
		
		//2.
		//MultipartFile 리스트로 파일을 가져옴
		List<MultipartFile> list = request.getFiles("file");
		int i = 1;
		for(MultipartFile f : list) {
			String originalFileName = f.getOriginalFilename();
			//파일 f의 크기가 0이라면 다음 파일로 넘어간다.
			if(f.getSize() == 0) continue;
			File uploadFile = new File(root + "\\" + originalFileName);
			boardService.insertFileList(new FileDTO(uploadFile,bno,i));
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
	
}
