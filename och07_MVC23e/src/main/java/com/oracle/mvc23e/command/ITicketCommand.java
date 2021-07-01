package com.oracle.mvc23e.command;

import com.oracle.mvc23e.dto.TicketDto;

public interface ITicketCommand {
	public void execute(TicketDto ticketDto);
}
