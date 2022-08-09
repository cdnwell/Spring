package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.BoardCommentDTO;
import com.example.dto.BoardDTO;
import com.example.dto.FileDTO;
import com.example.mapper.BoardMapper;

@Service
public class BoardService {
	private BoardMapper mapper;

	public BoardService(BoardMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<BoardDTO> selectBoardList(int pageNo) {
		return mapper.selectBoardList(pageNo);
	}

	public int selectBoardCount() {
		return mapper.selectBoardCount();
	}

	public BoardDTO selectBoard(int bno) {
		return mapper.selectBoard(bno);
	}

	public List<FileDTO> selectFileList(int bno) {
		return mapper.selectFileList(bno);
	}

	public List<BoardCommentDTO> selectComment(int bno) {
		return mapper.selectComment(bno);
	}

	public int addBoardCount(int bno) {
		return mapper.addBoardCount(bno);
	}

	public int commentDelete(int cno) {
		return mapper.commentDelete(cno);
	}

	public int insertComment(int bno, String content,String id) {
		HashMap<String,Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("content", content);
		map.put("writer", id);
		return mapper.insertComment(map);
	}

	public int insertBoard(BoardDTO dto) {
		int bno = mapper.selectBoardNo();
		dto.setBno(bno);
		mapper.insertBoard(dto);
		return bno;
	}

	public int insertFileList(FileDTO fileDTO) {
		return mapper.insertFileList(fileDTO);
	}

	public FileDTO selectFile(String bno, String fno) {
		HashMap<String, String> map = new HashMap<>();
		map.put("bno", bno);
		map.put("fno", fno);
		return mapper.selectFile(map);
	}

	public int insertBoardLike(int bno, String id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("id", id);
		int result = 0 ;
		try {
			result = mapper.insertBoardLike(map);
		} catch (Exception e) {
			mapper.deleteBoardLike(map);
		}
		
		return result;
	}

	public int insertBoardHate(int bno, String id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("id", id);
		int result = 0 ;
		try {
			result = mapper.insertBoardHate(map);
		} catch (Exception e) {
			mapper.deleteBoardHate(map);
		}
		
		return result;
	}

	public int insertCommentLike(int cno, String id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("cno", cno);
		map.put("id", id);
		int result = 0;
		try {
		result = mapper.insertCommentLike(map);
		} catch (Exception e) {
			mapper.deleteCommentLike(map);
		}
		return 0;
	}
	
	public int insertCommentHate(int cno, String id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("cno", cno);
		map.put("id", id);
		int result = 0;
		try {
			result = mapper.insertCommentHate(map);
		} catch (Exception e) {
			mapper.deleteCommentHate(map);
		}
		return 0;
	}

	public Map<String, Integer> selectLagLead(int bno) {
		return mapper.selectLagLead(bno);
	}

	public int searchRownum(int bno) {
		return mapper.searchRownum(bno);
	}

	
	
}
