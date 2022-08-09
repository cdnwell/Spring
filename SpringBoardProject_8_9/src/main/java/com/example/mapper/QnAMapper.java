package com.example.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.QnADTO;

@Mapper
public interface QnAMapper {

	int insertQnA(QnADTO dto);
	List<QnADTO> selectQnaList(Map<String, Object> map);
	List<QnADTO> selectQnaAdminList(int page);
	int selectCount();
	QnADTO selectQna(int qno);
	int updateAnswer(HashMap<String, Object> map);
	int updateStatus(int qno);
	String selectQnaAdminId(int qno);

}
