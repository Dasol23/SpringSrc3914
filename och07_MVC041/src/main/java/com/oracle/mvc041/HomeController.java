package com.oracle.mvc041;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/index")
	public String goIndex() {
		System.out.println("HomeController index Start..");
		return "index";
	}
	//겟방식
	@RequestMapping(method = RequestMethod.GET,value = "/student")
	public String goStudnet(HttpServletRequest request, Model model) {
		System.out.println("RequestMethod.GET");
		String id = request.getParameter("id");
		System.out.println();
		//여기만 달라지고 나머지는 jsp랑 같음
		model.addAttribute("studentId", id);
		
		return "Student/studentId";
	}
	//포스트방식
	@RequestMapping(method = RequestMethod.POST, value = "/student")
	public ModelAndView goStudent(HttpServletRequest request) {
		System.out.println("RequestMethod.POST");
		String id = request.getParameter("id");
		System.out.println("Post id: " + id);
		logger.info("HomeController POST id : {}", id);
		ModelAndView mv = new ModelAndView();
		//setViewName을 이용해 이름을 지정
		mv.setViewName("studentId");
		mv.addObject("studentId", id);
		return mv;
	}
}
