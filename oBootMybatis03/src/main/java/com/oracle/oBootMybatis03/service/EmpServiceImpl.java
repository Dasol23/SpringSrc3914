package com.oracle.oBootMybatis03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis03.dao.DeptDao;
import com.oracle.oBootMybatis03.dao.EmpDao;
import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private   EmpDao    ed;
	@Autowired
	private   DeptDao   dd;
	
	@Override
	public int total() {
		System.out.println("EmpServiceImpl Start total..." );
		int totCnt = ed.total();
		System.out.println("EmpServiceImpl total totCnt->"+totCnt );
		return totCnt;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listEmp Start..." );
		empList = ed.listEmp(emp);
		System.out.println("EmpServiceImpl listEmp empList.size()->" +empList.size());
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpServiceImpl detail ...");
		Emp emp = null;
		emp = ed.detail(empno);
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpServiceImpl update ...");
		int kkk = 0;
		kkk = ed.update(emp);
		return kkk;
	}

	// 관리자 emp만 Get
	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listManager Start..." );
		empList =  ed.listManager();  
		System.out.println("EmpServiceImpl listEmp empList.size()->" +empList.size());
		return empList;
	}

	// 부서 List
	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		System.out.println("EmpServiceImpl deptSelect Start..." );
		deptList =  dd.deptSelect();
		System.out.println("EmpServiceImpl deptSelect deptList.size()->" +deptList.size());
		return deptList;
	}

	@Override
	public int insert(Emp emp) {
		int result = 0;
		System.out.println("EmpServiceImpl insert Start..." );
		result = ed.insert(emp);
		return result;
	}

	@Override
	public int delete(int empno) {
		int result = 0;
		System.out.println("EmpServiceImpl delete Start..." );
		result =  ed.delete(empno);
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> empDeptList = null;
		System.out.println("EmpServiceImpl listEmpDept Start..." );
		empDeptList =  ed.listEmpDept();
		System.out.println("EmpServiceImpl listEmpDept empDeptList.size()->" +empDeptList.size());
		return empDeptList;
	}

}
