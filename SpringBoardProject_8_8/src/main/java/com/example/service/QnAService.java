package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.QnADTO;
import com.example.mapper.QnAMapper;

@Service
public class QnAService {
	private QnAMapper mapper;

	public QnAService(QnAMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public int insertQnA(QnADTO dto) {
		return mapper.insertQnA(dto);
	}

	public List<QnADTO> selectQnaList(String writer, int page) {
		Map<String, Object> map = new HashMap<>();
		map.put("writer", writer);
		map.put("page", page);
		return mapper.selectQnaList(map);
	}

	public int selectCount() {
		return mapper.selectCount();
	}

	public List<QnADTO> selectQnaAdminList(int page) {
		return mapper.selectQnaAdminList(page);
	}

	public QnADTO selectQna(int qno) {
		return mapper.selectQna(qno);
	}

	public int updateAnswer(int qno, String response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("qno", qno);
		map.put("response", response);
		
		return mapper.updateAnswer(map); 
	}

	public int updateStatus(int qno) {
		return mapper.updateStatus(qno);
	}

	public String selectQnaAdminId(int qno) {
		return mapper.selectQnaAdminId(qno);
	}

	public String selectQnaAdminDate(int qno) {
//		return mapper.selectQnaAdminDate(qno);
		return null;
	}
	
	
}
