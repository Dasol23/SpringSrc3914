package com.oracle.oBootjpaAPI01.domain;

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
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;



@Entity
@SequenceGenerator(name = "member_seq_gen",
					sequenceName = "member_seq_generator",
					initialValue = 1,
					allocationSize = 1
					)
@Getter
@Setter
@Table(name = "member1")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "member_seq_gen")
	private Long id;
	
	@Column (name = "username")
	@NotNull
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@Transient
	@Column(name = "team_id")
	private Long teamid;
	//UPdate시 ->teamname에 임시저장, TBL에는 존재안함
	@Transient
	private String teamname;
	
}
