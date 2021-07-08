package com.oracle.oBootjpa02.service;

import javax.transaction.Transactional;
//JPA  --> 서비스 계층에 트랜잭션 추가
//스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고,
//메서드가 정상 종료되면 트랜잭션을 커밋. 만약 런타임 예외가 발생하면 롤백.
//JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행

import com.oracle.oBootjpa02.doamin.Member;
import com.oracle.oBootjpa02.repository.MemberRepository;

@Transactional
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 회원가입
	public Member join(Member member) {
		System.out.println("MemberService join member.getName()->" + member.getName());
		memberRepository.save(member);
		return member;
	}
}
