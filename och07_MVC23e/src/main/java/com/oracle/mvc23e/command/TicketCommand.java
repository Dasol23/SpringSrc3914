package com.oracle.mvc23e.command;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.oracle.mvc23e.dao.TicketDao;
import com.oracle.mvc23e.dto.TicketDto;

public class TicketCommand implements ITicketCommand {
	private TicketDao ticketDao;
	private TransactionTemplate transactionTemplate2;

	public void setTrTemplate2(TransactionTemplate trTemplate2) {
		this.transactionTemplate2 = transactionTemplate2;
	}

	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	public void setTransactionTemplate2(TransactionTemplate traansactionTemplate2) {
		this.transactionTemplate2 = traansactionTemplate2;
	}
//Service
	@Override
	public void execute(final TicketDto ticketDto) { // 결과값 없이 리턴해주겠다는 뜻
		transactionTemplate2.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// TODO Auto-generated method stub
				ticketDto.setAmount("1");
				ticketDao.buyTicket(ticketDto);
				
				ticketDto.setAmount("3");
				ticketDao.buyTicket(ticketDto);
			}
		}
		);
	}

}
