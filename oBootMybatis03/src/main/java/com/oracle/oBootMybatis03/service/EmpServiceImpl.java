package com.oracle.oBootMybatis03.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis03.dao.DeptDao;
import com.oracle.oBootMybatis03.dao.EmpDao;
import com.oracle.oBootMybatis03.dao.Member1Dao;
import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
import com.oracle.oBootMybatis03.model.Member1;

@Service // bean 생성 안해줘도 된다. 컴포넌트 등록시켜줌!
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	@Autowired
	private DeptDao dd;
	@Autowired
	private Member1Dao md;
	
	@Override
	public int total() {
		System.out.println("EmpServiceImpl Start total...");
		int totCnt = ed.total();
		System.out.println("EmpServiceImpl total totCnt->"+totCnt);
		return totCnt;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList= null;
		System.out.println("EmpServiceImpl listEmp Start...");
		empList = ed.listEmp(emp);
		System.out.println("EmpServiceImpl listEmp empList.size()->" + empList.size());
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpServiceImp1 detail...");
		Emp emp = null;
		emp = ed.detail(empno);
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpServiceIml update...");
		int kkk = 0;
		kkk = ed.update(emp);
		return kkk;
	}

	// 관리자 emp만 Get
	@Override
	public List<Emp> listManager() {
		List<Emp> empList= null;
		System.out.println("EmpServiceImpl listManager Start...");
		empList = ed.listManager(); // 관리자만 Get
		System.out.println("EmpServiceImpl listEmp empList.size()->" + empList.size());
		return empList;
	}

	// 부서 List
	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList= null;
		System.out.println("DeptDaoImpl deptSelect Start...");
		deptList = dd.deptSelect(); // 관리자만 Get
		System.out.println("DeptDaoImpl deptSelect deptList.size()->" + deptList.size());
		return deptList;
	}

	@Override
	public int insert(Emp emp) {
		int result = 0;
		System.out.println("EmpServiceImpl insert Start...");
		result = ed.insert(emp);
		return result;
	}

	@Override
	public int delete(int empno) {
		int result = 0;
		System.out.println("EmpServiceImpl delete Start...");
		result = ed.delete(empno);
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> empDeptList= null;
		System.out.println("DeptDaoImpl listEmpDept Start...");
		empDeptList = ed.listEmpDept(); // 관리자만 Get
		System.out.println("DeptDaoImpl listEmpDept empDeptList.size()->" + empDeptList.size());
		return empDeptList;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl insertDept Start...");
		dd.insertDept(deptVO);
		
	}

	@Override
	public void selListDept(Map<String, Object> map) {
		System.out.println("EmpServiceImpl selListDept Start...");
		dd.selListDept(map);
		
	}

	@Override
	public int memCount(String id) {
		System.out.println("EmpServiceImpl memCount id ->"+id);
		return md.memCount(id);
	}

	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("EmpServiceImpl listMem Start...");
		return md.listMem(member1);
	}

}
