package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FileDTO;
import service.BoardService;
import view.ModelAndView;

public class FileDownController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		int fno = Integer.parseInt(request.getParameter("fno"));
		
		FileDTO dto = BoardService.getInstance().selectFile(bno,fno);
		
//		String pathName = BoardService.getInstance().selectFileName(bno,fno);
//		
//		int idx = pathName.lastIndexOf("\\");
//		if(idx == -1)
//			idx = pathName.lastIndexOf("/");
//		
//		String fileName = pathName.substring(idx + 1);
//		String fileName = request.getParameter("file");
		
//		String path = "c:\\fileUpload\\" + fileName;
		File file = new File(dto.getPath());
		//이전에는 따로 세팅안해줘도 됬지만 첨부파일을 보내야 하기 떄문에 헤더를 넣어야함
		response.setHeader("Content-Disposition", "attachment;fileName="+file.getName());
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength((int)file.length());
		
		//클라이언트에게 전달해 주어야 한다.
		FileInputStream fis = new FileInputStream(file);
		
		//파일다운로드 시킬 준비 끝--------------------------
		
		//클라이언트로 출력 > getwriter > 텍스트 출력
		//파일을(이진파일) 보내려면 바이트 단위로 보내야 함 , getwriter로 보내면 안된다.
		//--stream으로 끝나는 것으로 사용해야함
		//outputstream으로 클라이언트와 연결
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		//파일 읽어 온 것을 버퍼에 저장
		//1mb짜리 버퍼
		byte[] buffer = new byte[1024*1024];
		
		//출력만 하고 끝
		while(true) {
			//이진파일로 쓰는 건 안해봐서 생소함
			//파일에서 읽어온 것을 버퍼에 저장함
			//내가 읽을 수 있을 만큼만 읽어옴 1mb씩 읽어옴
			//몇 바이트 읽어왔는지 리턴
			//0번부터 size까지 출력하겠다. 라는 의미에서 size 설정
			//이진파일일경우 size 받는 부분이 꼭 생김
			int size = fis.read(buffer);
			//받는 파일이 없다면 -1
			if(size == -1) break;
			bos.write(buffer,0,size);
			bos.flush();
		}
		fis.close();
		bos.close();
		
		return null;
	}

}
