package com.student.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.student.di.AppContext;
import com.student.exception.StudentException;
import com.student.service.StudentService;
import com.student.vo.StudentVO;

public class SelectTopStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		StudentService service = (StudentService) AppContext.getInstance()
				.getCtx().getBean("service");
		
		try {
			ArrayList<StudentVO> list = service.selectRankOne();
			
			for(int i=0;i<list.size();i++)
				System.out.println(list.get(i));
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
	}

}
