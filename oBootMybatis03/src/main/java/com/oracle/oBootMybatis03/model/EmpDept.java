package com.oracle.oBootMybatis03.model;

import lombok.Getter;
import lombok.Setter;

//Join 목적 (lombok은 코드가 심플해진다)
@Getter
@Setter
public class EmpDept {
	// Emp용
	private int empno;			private String ename;
	private String job;			private int mgr;
	private String hiredate;	private int sal;
	private int comm;			private int deptno;
	
	// Dept용
	private	String	dname;
	private String  loc;

}
