package com.student.controller;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.student.di.AppContext;
import com.student.di.DIContainer;
import com.student.exception.StudentException;
import com.student.service.StudentService;
import com.student.vo.StudentVO;

public class PrintAllStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		StudentService service = (StudentService) AppContext.getInstance()
				.getCtx().getBean("service");
		
		try {
			ArrayList<StudentVO> list = service.selectAllStudent();
			
			for(int i=0;i<list.size();i++)
				System.out.println(list.get(i));
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}

	}

}






