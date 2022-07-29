package com.student.controller;

import java.util.Scanner;

import com.student.di.AppContext;
import com.student.service.StudentService;

public class DeleteStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		StudentService service = (StudentService) AppContext.getInstance()
				.getCtx().getBean("service");
		
		System.out.println("학생정보 삭제를 시작합니다...........");

		System.out.print("삭제할 학번 입력 : ");
		String sno = sc.nextLine();
		
		service.deleteStudent(sno);
	}

}
