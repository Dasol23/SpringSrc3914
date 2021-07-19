package com.oracle.oBootMybatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.SampleVO;
import com.oracle.oBootMybatis03.service.EmpService;
//Controller + Response Body
@RestController
public class EmpRestController {
	@Autowired
	private EmpService es;
	
	@RequestMapping("/hello")
	public SampleVO hello() {
		System.out.println("EmpRestController start...");
		SampleVO vo = new SampleVO();
		vo.setFirstName("hello");
		vo.setLastName("연");
		vo.setMno(123);
		return vo;
		
	}
	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("EmpRestController helloText Start");
		String hello ="안녕";
		return hello;
	}
	@RequestMapping("/sendVO3")
	
	public List<Dept> sendVO3(){
		System.out.println("RestController sendVO3 Start");
		List<Dept> deptliList = es.deptSelect();
		return deptliList;
	}
	//Ajax Test
	
	@RequestMapping(value = "getDeptName", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String getDeptName(int deptno, Model model) {
		System.out.println("deptno->"+deptno);
		return es.deptName(deptno);
	}
}
