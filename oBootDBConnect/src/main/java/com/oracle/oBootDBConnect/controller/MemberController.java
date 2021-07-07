package com.oracle.oBootDBConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootDBConnect.domain.Member;
import com.oracle.oBootDBConnect.service.MemberService;

@Controller
public class MemberController {
	// final을 쓰는걸 권장
	private final MemberService memberService; 
	 // 다른 곳에 있는 객체를 받아와서 , 이 페이지에서 사용할 수 있도록 설정(이때 bean으로 선언 되어 있어야함) 
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value = "/members/new")
	public String createForm() {
		System.out.println("MemberController /members/new Get start..");
		return "members/createMemberForm";
	}
	@PostMapping(value = "/members/new")
	public String create(Member member) {
		System.out.println("MemberController /members/new Post start..");
		memberService.join(member);
		return "redirect:/";
	}
	
	@GetMapping(value= "/members")
	public String list(Model model) {
		List<Member> memberList = memberService.findMembers();
		model.addAttribute("members", memberList);
		return "members/memberList";
	}
}
