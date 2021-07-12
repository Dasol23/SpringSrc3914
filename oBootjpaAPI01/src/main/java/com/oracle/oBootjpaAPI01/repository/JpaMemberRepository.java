package com.oracle.oBootjpaAPI01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootjpaAPI01.domain.Member;

@Repository // 일전에 써준 서비스와 함께사용하면서 빈 설정 없이 사용가능
public class JpaMemberRepository implements MemberRepository {

	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}

	@Override
	public Long save(Member member) {
		// 회원 저장
		em.persist(member);
		return member.getId();
	}

	@Override
	public Member findByMember(Long id) {
	Member member = em.find(Member.class, id);
		return member;
	}

}
