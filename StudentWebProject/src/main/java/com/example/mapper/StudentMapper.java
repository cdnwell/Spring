package com.example.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.StudentDTO;

@Mapper
public interface StudentMapper {

	List<StudentDTO> selectAllStudent();

	List<StudentDTO> searchStudent(HashMap<String, String> map);

}
