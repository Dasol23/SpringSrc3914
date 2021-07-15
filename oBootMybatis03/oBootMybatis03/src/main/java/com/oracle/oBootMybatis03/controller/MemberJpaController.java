package com.oracle.oBootMybatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootMybatis03.domain.Member;
import com.oracle.oBootMybatis03.service.MemberJpaService;

@Controller
public class MemberJpaController {
	private final MemberJpaService memberJpaService;
	
	@Autowired
	public MemberJpaController(MemberJpaService memberJpaService) {
		this.memberJpaService = memberJpaService;
	}
	@GetMapping(value = "/memberJpa/new")
	public String createForm() {
		System.out.println("MemberController / members/new start..");
		return "memberJpa/createMemberForm";
	}
	@PostMapping(value = "/memberJpa/save")
	public String create(Member member) {
		System.out.println("MemberController create start..");
		System.out.println("Member.getId()->"+member.getId());
		System.out.println("Member.getName()->"+member.getName());
		memberJpaService.join(member);
		return "memberJpa/createMemberForm";
	}
	@GetMapping(value = "/members")
	public String listMember(Model model){
		System.out.println("MemberController listMember start..");
		List<Member> memberList = memberJpaService.getListAllMember();
		model.addAttribute("members", memberList);
		return "memberJpa/memberList";
	}
	
}
