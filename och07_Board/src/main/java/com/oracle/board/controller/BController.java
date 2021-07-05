package com.oracle.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.board.command.BCommand;
import com.oracle.board.command.BContentCommand;
import com.oracle.board.command.BListCommand;
import com.oracle.board.command.BReplyViewCommand;
import com.oracle.board.command.BWriteCommand;
import com.oracle.board.command.BmodifyCommand;

@Controller
public class BController {
	
	BCommand command = null;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("BController list start...");
		command = new BListCommand();
		command.execute(model);
		return "list";
	}
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("BController write_view start");
		
		return "write_view";
	}
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");
		
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	@RequestMapping(value = "/modify", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("BController modify start...");
		model.addAttribute("request", request);
		command = new BmodifyCommand();
		command.execute(model);
		
		return"redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view()");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
}

