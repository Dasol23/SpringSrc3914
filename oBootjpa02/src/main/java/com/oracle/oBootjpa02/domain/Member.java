package com.oracle.oBootjpa02.domain;

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

@Entity //테이블을 만들어주기 위한 세팅 작업을 하는 클래스
@SequenceGenerator(name = "member_seq_gen", // spring 객체에서 사용하는 시퀀스 이름
				   sequenceName = "member_seq_generator",  // 매핑함 DB 시퀀스 이용(DB 시퀀스 이름)
				   initialValue = 1,
				   allocationSize = 1
				   ) // 선언
@Table(name = "member1")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
	private Long   id;
	@Column(name = "username")
	private String name;
	
	@ManyToOne // 다대일
	@JoinColumn(name = "team_id") // 폴인키 연결
	private Team team;
	
	// Update시->Team에 저장할 teamname 임시저장, TBL에는 존재 안 함 (객체의 버퍼로만 사용)
	@Transient
	private String teamname;
	@Transient
	@Column(name = "team_id")
	private Long teamid;
	
	public Long getTeamid() {
		return teamid;
	}
	public void setTeamid(Long teamid) {
		this.teamid = teamid;
	}
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
