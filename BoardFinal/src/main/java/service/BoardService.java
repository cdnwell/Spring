package service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import config.DBManager;
import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.FileDTO;
import mapper.BoardMapper;

public class BoardService {
	private static BoardService instance = new BoardService();
	private Connection conn;
	
	private BoardService() {
		conn = DBManager.getInstance().getConn();
	}
	
	public static BoardService getInstance() {
		if(instance == null)
			instance = new BoardService();
		
		return instance;
	}

	public List<BoardDTO> selectBoardList(int pageNo) {
		return BoardMapper.getInstance().selectBoardList(pageNo);
	}

	public int selectAllCount() {
		return BoardMapper.getInstance().selectAllCount();
	}

	public int insertBoard(BoardDTO dto) {
		return BoardMapper.getInstance().insertBoard(dto);
	}

	public BoardDTO selectBoard(int bno) {
		return BoardMapper.getInstance().selectBoard(bno);
	}

	public void deleteBoard(int bno) {
		BoardMapper.getInstance().deleteBoard(bno);
	}

	public int updateBoard(BoardDTO dto) {
		int result = BoardMapper.getInstance().updateBoard(dto);
		return result;
	}

	public void addBoardCount(int bno) {
		BoardMapper.getInstance().addCountBoard(bno);
	}

	public int insertBoardLike(int bno,String id) {
		//등록하면 1, 취소하면 0이 나옴
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("id", id);
		int result = BoardMapper.getInstance().insertBoardLike(map);
		if(result == 0)
			BoardMapper.getInstance().deleteBoardLike(map);
		return result;
	}

	public int insertBoardHate(int bno, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("id", id);
		int result = BoardMapper.getInstance().insertBoardHate(map);
		if(result == 0)
			BoardMapper.getInstance().deleteBoardHate(map);
		return result;
	}

	public void insertBoardComment(BoardCommentDTO dto) {
		BoardMapper.getInstance().insertBoardComment(dto);
	}

	public List<BoardCommentDTO> selectCommentList(int bno) {
		return BoardMapper.getInstance().selectBoardCommentList(bno);
	}

	public void deleteBoardComment(int cno) {
		BoardMapper.getInstance().deleteBoardComment(cno);
	}

	public int insertBoardCommentLike(int cno, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("id", id);
		int result = BoardMapper.getInstance().insertBoardCommentLike(map);
		if(result == 0)
			BoardMapper.getInstance().deleteBoardCommentLike(map);
		return result;
	}
	
	public int insertBoardCommentHate(int cno, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("id", id);
		int result = BoardMapper.getInstance().insertBoardCommentHate(map);
		if(result == 0)
			BoardMapper.getInstance().deleteBoardCommentHate(map);
		return result;
	}

	public int selectBoardNo() {
		return BoardMapper.getInstance().selectBoardNo();
	}

	public int insertFile(FileDTO file) {
		
		return BoardMapper.getInstance().insertFile(file);
	}

	public List<FileDTO> selectFileList(int bno) {
		return BoardMapper.getInstance().selectFileList(bno);
	}

	public String selectFileName(int bno, int fno) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("bno", bno);
		map.put("fno", fno);
//		return BoardMapper.getInstance().selectFileName(map);
		return null;
	}

	public FileDTO selectFile(int bno, int fno) {
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("bno", bno);
		map.put("fno", fno);
		return BoardMapper.getInstance().selectFile(map);
	}
	
}
