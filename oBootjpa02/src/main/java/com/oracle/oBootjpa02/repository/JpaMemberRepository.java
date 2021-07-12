package com.oracle.oBootjpa02.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.oracle.oBootjpa02.domain.Member;
import com.oracle.oBootjpa02.domain.Team;

public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
		// TODO Auto-generated constructor stub
	}	
	
	@Override
	public Member save(Member member) {
		// 팀 저장
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);
		member.setTeam(team); // 단방향 연관관계 설정, 참조 저장
		//화원 저장
		// TODO Auto-generated method stub
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class) // m은 member 클래스를 지칭 
									.getResultList();
		return memberList;
	}
	// Optional 객체를 사용하면 예상치 못한 NullPointerException 예외를 제공되는 메소드로 간단히 회피.
		// 즉, 복잡한 조건문 없이도 널(null) 값으로 인해 발생하는 예외를 처리
	// select * from member where id=id;를 아래에 find를 이용해서 바로 사용가능
	@Override
	public Optional<Member> findByMember(Long id) {
		// TODO Auto-generated method stub
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;
		Member member3 = em.find(Member.class, member.getId());
		// member3.ifPresent(object);
		if(member3 != null) {
			// 팀 저장
			Team team = em.find(Team.class, member.getTeamid());
			if(team != null) {
				team.setName(member.getTeamname());
				em.persist(team);				
			}
			//회원 저장
			member3.setTeam(team); //단방향 연관관계 설정, 참조 저장
			member3.setName(member.getName());
			member.setTeam(member.getTeam());
			em.persist(member3);
			System.out.println("JpaMemberRepository updateByMember member.getName()->"+member.getName());
			result = 1;
		} else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember No Exist..");
		}
		return result;
	}

	@Override
	public List<Member> findByNames(String name) {
		String pname= name +'%';
		System.out.println("JpaMemberRepository findByNames name->"+name);
		List<Member> memberList = em.createQuery("select m from Member m where name Like :name", Member.class)
				.setParameter("name", pname)
				.getResultList();
		System.out.println("JpaMemberRepository memberList.size()->"+memberList.size());
		return memberList;
	}

	
}
