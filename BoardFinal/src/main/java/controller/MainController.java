package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.BoardService;
import view.ModelAndView;
import vo.PaggingVO;

public class MainController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNo = 1;
		//페이지 번호가 있을때만 읽어옴
		if(request.getParameter("pageNo") != null)
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		//페이지에 해당하는 글 목록을 조회
		List<BoardDTO> list = BoardService.getInstance().selectBoardList(pageNo);
		//전체 페이지 개수
		int count = BoardService.getInstance().selectAllCount();
		PaggingVO vo = new PaggingVO(count, pageNo, 15, 5);
		
		request.setAttribute("board_list", list);
		request.setAttribute("pagging", vo);
		
		ModelAndView view = new ModelAndView("main.jsp", false);
		
		return view;
	}

}
