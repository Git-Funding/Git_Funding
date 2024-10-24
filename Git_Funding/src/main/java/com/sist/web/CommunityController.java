package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("community/")
public class CommunityController {
	@GetMapping("qna_list.do")
	public String qna_list(Model model,HttpSession session) {
		String id=(String)session.getAttribute("userId");
		
		model.addAttribute("sessionId",id);
		return "community/qna_list";
	}
	@GetMapping("qna_insert.do")
	public String qna_insert() {
		return "community/qna_insert";
	}
	@GetMapping("faq_list.do")
	public String faq_list(Model model,HttpSession session) {
		String id=(String)session.getAttribute("userId");
		
		model.addAttribute("sessionId",id);
		return "community/faq_list";
	}
	@GetMapping("qna_detail.do")
	public String qna_detail(int qno,Model model,HttpSession session) {
		String id=(String)session.getAttribute("userId");
		model.addAttribute("qno",qno);
		model.addAttribute("sessionId",id);
		return "community/qna_detail";
	}
	@GetMapping("qna_update.do")
	public String qna_update(int qno,Model model) {
		model.addAttribute("qno",qno);
		return "community/qna_update";
	}
}
