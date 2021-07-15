package com.oracle.oBootMybatis03.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "member3")
public class Member {
	@Id
	private Long id;
	private String name;
}
