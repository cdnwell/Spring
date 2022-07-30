package mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import config.DBManager;
import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.FileDTO;

public class BoardMapper {
	private static BoardMapper instance = new BoardMapper();
	
	private BoardMapper() {
		
	}
	
	public static BoardMapper getInstance() {
		if(instance == null)
			instance = new BoardMapper();
		
		return instance;
	}

	public List<BoardDTO> selectBoardList(int pageNo) {
		SqlSession session = DBManager.getInstance().getSession();
		List<BoardDTO> list = session.selectList("selectBoardList",pageNo);
		session.close();
		return list;
	}

	public void addCountBoard(int bno) {
		SqlSession session = DBManager.getInstance().getSession();
		session.update("addCountBoard", bno);
		session.close();
	}

	public BoardDTO selectBoard(int bno) {
		SqlSession session = DBManager.getInstance().getSession();
		BoardDTO dto = session.selectOne("selectBoard",bno);
		session.close();
		return dto;
	}

	public List<BoardCommentDTO> selectBoardCommentList(int bno) {
		SqlSession session = DBManager.getInstance().getSession();
		List<BoardCommentDTO> list = session.selectList("selectBoardCommentList",bno);
		session.close();
		return list;
	}

	public int selectAllCount() {
		SqlSession session = DBManager.getInstance().getSession();
		int count = session.selectOne("selectAllCount");
		session.close();
		return count;
	}

	public int insertBoard(BoardDTO dto) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = session.insert("insertBoard",dto);
		session.commit();
		return result;
	}

	public int updateBoard(BoardDTO dto) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = session.update("updateBoard",dto);
		session.close();
		return result;
	}

	public int insertBoardHate(HashMap<String, Object> map) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		try {
			result = session.insert("insertBoardHate",map);
			session.commit();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int deleteBoardHate(HashMap<String, Object> map) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		try {
			result = session.insert("deleteBoardHate",map);
			session.commit();
	 	} catch (Exception e) {
	 		System.out.println(e.getMessage());
	 	}
		return result;
	}
	
	public int insertBoardLike(HashMap<String, Object> map) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0; 
		try {		
			result = session.insert("insertBoardLike",map);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int deleteBoardLike(HashMap<String, Object> map) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		try {
			result = session.insert("deleteBoardLike",map);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public int insertBoardComment(BoardCommentDTO dto) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		try {
			result = session.insert("insertBoardComment",dto);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	public int insertBoardCommentLike(HashMap<String, Object> map) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		try {
			result = session.insert("insertBoardCommentLike",map);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	public int deleteBoardCommentLike(HashMap<String, Object> map) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		try {
			result = session.delete("deleteBoardCommentLike",map);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	public int insertBoardCommentHate(HashMap<String, Object> map) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		try {
			result = session.insert("insertBoardCommentHate",map);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	public int deleteBoardCommentHate(HashMap<String, Object> map) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		try {
			result = session.delete("deleteBoardCommentHate",map);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	public int deleteBoardComment(int cno) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		try {
			result = session.delete("deleteBoardComment", cno);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public int deleteBoard(int bno) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		try {
			result = session.delete("deleteBoard", bno);
			session.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public int selectBoardNo() {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		result = session.selectOne("selectBoardNo");
		session.close();
		return result;
	}

	public int insertFile(FileDTO file) {
		SqlSession session = DBManager.getInstance().getSession();
		int result = 0;
		result = session.insert("insertFile",file);
		session.commit();
		session.close();
		return result;
	}

	public List<FileDTO> selectFileList(int bno) {
		SqlSession session = DBManager.getInstance().getSession();
		List<FileDTO> list = session.selectList("selectFileList", bno);
		session.close();
		return list;
	}

	public String selectFileName(HashMap<String, String> map) {
		SqlSession session = DBManager.getInstance().getSession();
		String fileName = session.selectOne("selectFileName", map);
		session.close();
		return fileName;
	}

	public FileDTO selectFile(HashMap<String, Integer> map) {
		SqlSession session = DBManager.getInstance().getSession();
		FileDTO dto = session.selectOne("selectFile", map);
		session.close();
		return dto;
	}
	
}
