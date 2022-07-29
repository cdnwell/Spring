package com.eg;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eg.dto.MemberDTO;
import com.eg.service.MemberService;

@Controller
public class MainController {
	private MemberService service;
	
	public MainController(MemberService service) {
		this.service = service;
	}
	
	@RequestMapping("/")
	public String main(Model model) {
		List<MemberDTO> list = service.selectAllMember();
		model.addAttribute("list",list);
		return "main";
	}
	
	@RequestMapping("/register.do")
	public String register(MemberDTO dto) {
		service.insertMember(dto);
		return "redirect:/";
	}
	
	@RequestMapping("/delete.do")
	public String delete(String id) {
		service.delete(id);
		return "redirect:/";
	}
	
	@RequestMapping("updateView.do")
	public String updateView(String id,Model model) {
		MemberDTO dto = service.selectMember(id);
		model.addAttribute("dto",dto);
		return "update_view";
	}
	
	@RequestMapping("update.do")
	public String update(MemberDTO dto) {
		service.updateMember(dto);
		return "redirect:/";
	}
	
}
