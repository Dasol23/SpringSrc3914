package com.oracle.board.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.oracle.board.dao.BDao;
import com.oracle.board.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {

		BDao dao = new BDao();
		ArrayList<BDto> bdtos = dao.list();
		model.addAttribute("list",bdtos);
	}

}
