package com.oracle.oBootjpa02.doamin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity// -> 테이블을 만들기 위한 셋팅을 하기위해 필요한 것
//제네레이터 선언  -> 아래에서 쓰겠다
@SequenceGenerator( name = "member_seq_gen",//스프링 안에서(객체에서) 사용하는 시퀀스 이름
					sequenceName = "member_seq_gen", //매핑할 DB시퀀스 이름 
					initialValue = 1,
					allocationSize = 1)
@Table(name = "member1")
public class Member {
	@Id
	//위에서 선언한 제네레이터 사용 , 프라이머리 키이자 제네레이터를 사용할 이름
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
	private Long id;
	@Column(name = "username")
	private String name;
	
	@ManyToOne //다대일
	//조인 -> 폴인키 연결
	@JoinColumn(name = "team_id")
	private Team team;
	
	//Update시 -> Team에 저장할 teamname 임시저장, TBL에는 존재 안 함(객체의 버퍼로만 사용)
	@Transient
	private String teamname;

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
