package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardCommentDTO;
import dto.MemberDTO;
import service.BoardService;
import view.ModelAndView;

public class CommentWriteConroller implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String comment = request.getParameter("comment");  
		String writer = ((MemberDTO)request.getSession().getAttribute("dto")).getId();
		
		BoardService.getInstance().insertBoardComment(new BoardCommentDTO(bno, comment, writer));
		
//		if(result == 0) {
//			response.getWriter().write("<script>alert('댓글 등록에 실패하였습니다.');location.href='boardView.do?bno="+bno+"';</script>");
//		} else {
//			response.getWriter().write("<script>alert('댓글 등록에 성공하였습니다.');location.href='boardView.do?bno="+bno+"';</script>");
//		}
		
		return new ModelAndView("boardView.do?bno="+bno,true);
	}

}
