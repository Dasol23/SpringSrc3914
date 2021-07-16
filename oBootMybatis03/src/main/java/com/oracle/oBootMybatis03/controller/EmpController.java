package com.oracle.oBootMybatis03.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
import com.oracle.oBootMybatis03.model.Member1;
import com.oracle.oBootMybatis03.service.EmpService;
import com.oracle.oBootMybatis03.service.Paging;

@Controller
public class EmpController {
	@Autowired //생성자 주입, 필드주입, setget주입방식 ?? (repository로 인해 컴포넌트로 등록되어있어야 사용가능?)
	private EmpService es;	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value="list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController Start list...");
		int total = es.total();		// Emp Count -> 17
		System.out.println("EmpController total=>"+total);
		System.out.println("currentPage=>" + currentPage);
		//						14		NULL(0,1....)
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());
		emp.setEnd(pg.getEnd());
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController  list listEmp.size()=>" + listEmp.size());
		model.addAttribute("total", total);
		model.addAttribute("listEmp",listEmp);
		model.addAttribute("pg",pg);
		
		return "list";
	}
	
	@GetMapping(value="detail")
	public String detail(HttpServletRequest request, int empno, Model model) {
		System.out.println("EmpController Start detail...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp",emp);
	
		return "detail";
	}
	
	@GetMapping(value="updateForm")
	public String updateForm(int empno, Model model) {
		System.out.println("EmpController Start updateForm...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp",emp);
		
		return "updateForm";
	}
	
//	@RequestMapping(value="update", method=RequestMethod.POST) : 아래의 것과 같음
	@PostMapping(value="update")
	public String update(Emp emp, Model model) {
		int k = es.update(emp);
		System.out.println("es.update(emp) k-->"+k);
		model.addAttribute("kkk",k); // Test Controller간 Data 전달
		model.addAttribute("kk3","Message Test"); // Test Controller간 Data 전달
//		return "redirect:list";
		return "forward:list";
	}
	
	@RequestMapping(value="writeForm")
	public String writeForm(Model model) {
		// Emp emp = null;
		List<Emp> list = es.listManager();
		System.out.println("EmpController writeForm list.size->"+list.size());
		model.addAttribute("empMngList",list);	// emp Manager List
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList); //dept
		return "writeForm";
	}
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String write(Emp emp, Model model) {
		System.out.println("EmpController Start write...");
//		System.out.println("emp.getHiredate->"+emp.getHiredate());
//		Service, Dao, Mapper명 까지 -> insert
		int result = es.insert(emp);
		if (result > 0) return "redirect:list";
		else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
			return "forward:writeForm";
		}
	}
	
	@GetMapping(value="confirm")
	public String confirm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("msg","중복된 사번입니다");
		if(emp != null) {
		return "forward:writeForm";
	} else {
		model.addAttribute("msg","사용 가능한 사번입니다");
		return "forward:writeForm";
		}
	}
	//default : get 방식
	@RequestMapping(value="delete")
	public String delete(int empno, Model model) {
		System.out.println("EmpController Start delete...");
		int k = es.delete(empno);
		return "redirect:list";
	}
	
	@GetMapping(value = "listEmpDept")
	public String listEmpDept(Model model) {
		EmpDept empDept = null;
		System.out.println("EmpController listEmpDept Start...");
		List<EmpDept> listEmpDept = es.listEmpDept();
		model.addAttribute("listEmpDept",listEmpDept);
		return "listEmpDept";
	}
	
	@RequestMapping(value="mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		System.out.println("mailSending...");
		String tomail = "wjdalsgml710@naver.com";			// 받는 사람 이메일
		System.out.println(tomail);
		String setfrom = "alsgml710@gmail.com";
		String title = "mailTransport 입니다";				// 제목
		try {
			//Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom);		// 보내는 사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail);		// 받는 사람 이메일
			messageHelper.setSubject(title); 	// 메일제목은 생략이 가능하다
			String tempPassword = (int) (Math.random() * 999999) + 1 + "";
			messageHelper.setText("임시 비밀번호입니다: " + tempPassword);
			DataSource dataSource = new FileDataSource("c:\\log\\jung1.jpg");
			messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1);		// 정상 전달
//			s.tempPw(u_id, tempPassword) ; // db에 비밀번호를 임시비밀번호로 업데이트
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("check", 2);		// 메일 전달 실패
		}
		return "mailResult";
		
	}
	
	// Procedure Test 입력화면
	@RequestMapping(value = "writeDeptIn", method = RequestMethod.GET)
	public String writeDeptIn(Model model) {
		System.out.println("writeDeptIn Start...");
	return "writeDept3";
	}
	
	@PostMapping(value = "writeDept")
	// Procedure Test 입력후 VO 전달
	public String writeDept(DeptVO deptVO, Model model) {
	// DeptVO rDeptVO = es.insertDept(deptVO);	// 일반 Service
		es.insertDept(deptVO);		// Procedure Call
		if (deptVO == null) {
			System.out.println("deptVO NULL");
		} else {
			System.out.println("RdeptVO.getOdeptno()->"+deptVO.getOdeptno());
			System.out.println("RdeptVO.getOdname()->"+deptVO.getOdname());
			System.out.println("RdeptVO.getOloc()->"+deptVO.getOloc());
			model.addAttribute("msg", "정상 입력 되었습니다 ^^");
			model.addAttribute("dept", deptVO);
		}
		return "writeDept3";
	}
	

	@GetMapping(value = "writeDeptCursor")
	public String writeDeptCursor(Model model) {
		System.out.println("EmpController writeDeptCursor Start...");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 10);
		map.put("eDeptno", 80);
		es.selListDept(map);
		java.util.List<Dept> deptLists = (List<Dept>) map.get("dept");
		for(Dept dept : deptLists) {
			System.out.println("deptList.dname[0].getDname->"+dept.getDname());
			System.out.println("deptList.dname[0].getLoc->"+dept.getLoc());			
		}
		System.out.println("deptList Size->"+deptLists.size());
		model.addAttribute("deptList", deptLists);
		
	return "writeDeptCursor";
	}
	
	// interCepter 시작 화면
	@RequestMapping(value = "interCepterForm", method = RequestMethod.GET)
	public String interCepterForm(Model model) {
		System.out.println("interCepterForm Start");
		return "interCepterForm";
	}
	
	// interCepter 진행 Test  2
	@RequestMapping(value = "interCepter")
	public String interCepter(String id, Model model) {
		System.out.println("interCepter Test Start");
		System.out.println("interCepter id->"+id);
		int memCnt = es.memCount(id);
		
		System.out.println("memCnt ->"+ memCnt);
		// List<EmpDept> listEmp = es.listEmp(empDept); User 가져오늘 Service
		// member1의 Count가져오는 Service 수행
		// member.serId("kkk");
		
		model.addAttribute("id",id);
		model.addAttribute("memCnt",memCnt);
		System.out.println("interCepter Test End");
		return "interCepter"; // User 존재하면 User 이용 조회 Page
	}
	
	// interCepter 진행 Test
	@RequestMapping(value = "doMemberList")
	public String doMemberList(Model model, HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("doMemberList Test Start ID ->"+ID);
		Member1 member1 = null;
		// Member1 List Get Service
		List<Member1> listMem = es.listMem(member1);
		model.addAttribute("ID",ID);
		model.addAttribute("listMem",listMem);
		return "doMemberList";	// User 존재하면 User 이용조회 Page		
	}
	
	// SampleInterceptor 내용을 받아 처리
	@RequestMapping(value = "doMemberWrite", method = RequestMethod.GET)
	public String doMemberWrite(Model model, HttpServletRequest request) {
		String ID = (String) request.getSession().getAttribute("ID");
		System.out.println("doMemberWrite................");
		model.addAttribute("id",ID);
		return "doMemberWrite";
	}
}
