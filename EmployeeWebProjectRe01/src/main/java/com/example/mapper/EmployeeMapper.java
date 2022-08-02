package com.example.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.EmployeeDTO;

@Mapper
public interface EmployeeMapper {

	List<EmployeeDTO> selectAllEmployee();

	List<EmployeeDTO> searchEmployee(HashMap<String, String> map);

}
