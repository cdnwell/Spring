package com.example;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;

@Controller
public class MainController {
	private EmployeeService service;

	public MainController(EmployeeService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/")
	public String main() {
		//페이지 연결해주기
		return "employee_search";
	}
	
	//ajax는 보내줄 것이 없으니 반환 값은 void로 한다.
	@RequestMapping("/search.do")
	public void search(String kind, String search, HttpServletResponse response) throws JSONException, IOException {
		List<EmployeeDTO> list = service.searchEmployee(kind,search);
		JSONObject obj = new JSONObject();
		if(list.size()==0) {
			//객체는 만들어 주지만, 사이즈는 0이다.
			obj.put("code", 500);
			obj.put("message", "조회된 데이터가 없습니다.");
			obj.put("result", "None");
		} else {
			obj.put("code", 200);
			obj.put("message", "정상적으로 조회되었습니다.");
			obj.put("result", new JSONArray(list));
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(obj.toString());
	}
	
	@RequestMapping("/all.do")
	public void searchall(String kind, String search, HttpServletResponse response) throws JSONException, IOException {
		List<EmployeeDTO> list = service.selectAllEmployee(kind,search);
		JSONObject obj = new JSONObject();
		if(list.size()==0) {
			//객체는 만들어 주지만, 사이즈는 0이다.
			obj.put("code", 500);
			obj.put("message", "조회된 데이터가 없습니다.");
			obj.put("result", "None");
		} else {
			obj.put("code", 200);
			obj.put("message", "정상적으로 조회되었습니다.");
			obj.put("result", new JSONArray(list));
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(obj.toString());
	}
	
}
