package com.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.test.dto.BoardCommentDTO;
import com.test.dto.BoardDTO;
import com.test.dto.FileDTO;

@Service
public class BoardService {
	private BoardMapper mapper;

	public BoardService(BoardMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public BoardDTO selectBoardDTO(int bno) {
		return mapper.selectBoardDTO(bno);
	}

	public List<FileDTO> selectFileList(int bno) {
		return mapper.selectFileList(bno);
	}

	public List<BoardCommentDTO> selectCommentDTO(int bno) {
		return mapper.selectCommentDTO(bno);
	}

	public Map<String, Object> selectNextBefore(int bno) {
		return mapper.selectNextBefore(bno);
	}

	public int addBoardCount(int bno) {
		return mapper.addBoardCount(bno);
	}

	public List<BoardDTO> selectBoardList(int pageNo) {
		return mapper.selectBoardList(pageNo);
	}

	public int selectBoardCount() {
		return mapper.selectBoardCount();
	}

	public int insertBoard(BoardDTO dto) {
		int bno = mapper.selectBoardNo();
		dto.setBno(bno);
		dto.setWriter("A0002");
		mapper.insertBoard(dto);
		return bno;
	}

	public int insertFileList(FileDTO dto) {
		return mapper.insertFileList(dto);
	}

	public FileDTO selectFile(String bno, String fno) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("fno", fno);
		return mapper.selectFile(map);
	}

	
	
	
	
}
