package com.oracle.oBootMybatis03.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptVO {	//Dto, VO 같은데, VO는 대부분 read only 조회할 때 많이 쓰임

	private int		deptno;
	private String	dname;
	private String	loc;
	private int		odeptno;
	private String	odname;
	private String	oloc;
	
	
}
