package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.QnADTO;

@Mapper
public interface QnAMapper {

	int insertQnA(QnADTO dto);
	List<QnADTO> selectQnaList(Map<String, Object> map);

}
