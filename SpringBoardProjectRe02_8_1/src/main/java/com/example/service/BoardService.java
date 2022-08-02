package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.BoardDTO;
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

}
