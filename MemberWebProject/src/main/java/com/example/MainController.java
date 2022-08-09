package com.example;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.MemberDTO;
import com.example.service.MemberService;

@Controller
public class MainController {
	private MemberService service;

	public MainController(MemberService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/")
	public String main() {
		
		return "member_manager";
	}
	
	@RequestMapping("/selectAll.do")
	public void selectAll(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		List<MemberDTO> list = service.selectAll();
		
		JSONArray array = new JSONArray(list);
		
		response.getWriter().write(array.toString());
	}
	
	@RequestMapping("/registerMember.do")
	public void registerMember(MemberDTO dto, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		int result = service.insertMember(dto);
		
		response.getWriter().write(String.valueOf(result));
	}
	
	@RequestMapping("/deleteMember.do")
	public void deleteMember(String id, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		int result = service.deleteMember(id);
		
		JSONObject json = new JSONObject();
		if(result == 0) {
			json.put("code", 500);
			json.put("message", "회원정보가 삭제되지 않았습니다.");
		} else {
			json.put("code", 200);
			json.put("message", "회원정보가 정상적으로 삭제되었습니다.");
		}
		
		response.getWriter().write(json.toString());
	}
	
	@RequestMapping("/errorLog.do")
	public void insertErrorLog(String log, String result) {
		service.insertErrorLog(log,result);
		
		
	}
	
}
