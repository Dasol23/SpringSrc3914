package com.oracle.oBootjpa02.repository;

import javax.persistence.EntityManager;

import com.oracle.oBootjpa02.doamin.Member;
import com.oracle.oBootjpa02.doamin.Team;

public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em){
		this.em = em;
	} 
	@Override
	public Member save(Member member) {
		//팀저장
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);
		member.setTeam(team);
		//회원저장
		em.persist(member);
		return member;
	}

}
