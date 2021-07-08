package com.oracle.oBootjpa02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootjpa02.doamin.Member;
import com.oracle.oBootjpa02.service.MemberService;

@Controller
public class MemberController {
	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping(value = "/members/new")
	public String createForm() {
		System.out.println("MemberController JPA /members/new start..");
		return "members/createMemberForm";
	}

	@PostMapping(value = "/members/save")
	public String create(Member member) {
		System.out.println("MemberController JPA create start..");
		System.out.println("Member.getName()->" + member.getName());
		memberService.join(member);
		return "redirect:/";
	}
}
