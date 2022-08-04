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

	public int addBoardCount(int bno) {
		return mapper.addBoardCount(bno);
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

	public int deleteBoard(int bno) {
		return mapper.deleteBoard(bno);
	}

	public int insertComment(BoardCommentDTO dto) {
		return mapper.insertComment(dto);
	}

	public int insertBoardComment(BoardCommentDTO dto) {
		return mapper.addBoardComment(dto);
	}

	public int insertBoardLike(int bno, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("id", id);
		int result = 0;
		try {
			result = mapper.insertBoardLike(map);
		} catch (Exception e) {
			mapper.deleteBoardLike(map);
		}
		return result;
	}

	public int insertBoardHate(int bno, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("id", id);
		int result = 0;
		try {
			result = mapper.insertBoardHate(map);
		} catch (Exception e) {
			mapper.deleteBoardHate(map);
		}
		return result;
	}

	public int selectBoardNo() {
		return mapper.selectBoardNo();
	}

	public int insertBoard(BoardDTO dto) {
		int bno = mapper.selectBoardNo();	//게시글 번호 받아옴
		dto.setBno(bno);	//dto에 게시글 번호를 셋팅
		mapper.insertBoard(dto);	//게시글 등록
		return bno;
	}

	public int insertFileList(FileDTO fileDTO) {
		return mapper.insertFileList(fileDTO);
	}

	public FileDTO selectFile(String bno, String fno) {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("bno", bno);
		map.put("fno", fno);
		return mapper.selectFile(map);
	}

	public void insertCommentLike(String cno, String id) {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("cno", cno);
		map.put("id", id);
		try {
			mapper.insertCommentLike(map);
		} catch (Exception e) {
			mapper.deleteCommentLike(map);
		}
	}
	
	public void insertCommentHate(String cno, String id) {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("cno", cno);
		map.put("id", id);
		try {
			mapper.insertCommentHate(map);
		} catch (Exception e) {
			mapper.deleteCommentHate(map);
		}
	}

	public int deleteBoardComment(int cno) {
		return mapper.deleteBoardComment(cno);
	}

	public int uploadImage(String path) {
		int fno = mapper.selectBoardImageNo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", path);
		map.put("fno", fno);
		mapper.insertBoardImage(map);
		return fno;
	}

	public String selectImageFile(int fno) {
		return mapper.selectImageFile(fno);
	}
	
}
