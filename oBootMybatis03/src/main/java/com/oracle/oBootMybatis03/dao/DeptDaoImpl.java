package com.oracle.oBootMybatis03.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;

@Repository
public class DeptDaoImpl implements DeptDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList= null;
		System.out.println("DeptDaoImpl deptSelect Start...");
		deptList = session.selectList("TKselectDept"); // 관리자만 Get
		System.out.println("DeptDaoImpl deptSelect deptList.size()->" + deptList.size());
		return deptList;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("DeptDaoImpl insertDept Start... ");
		session.selectOne("ProcDept", deptVO);		
	}

	@Override
	public void selListDept(Map<String, Object> map) {
		System.out.println("DeptDaoImpl selListDept Start...");
		session.selectOne("ProcDeptList", map);		
	}

}
