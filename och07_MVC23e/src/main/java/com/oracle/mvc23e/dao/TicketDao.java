package com.oracle.mvc23e.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.oracle.mvc23e.dto.TicketDto;

public class TicketDao {
	JdbcTemplate template;
	TransactionTemplate transactionTemplate1;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public TransactionTemplate getTransactionTemplate1() {
		return transactionTemplate1;
	}

	public void setTransactionTemplate1(TransactionTemplate transactionTemplate1) {
		this.transactionTemplate1 = transactionTemplate1;
	}

	public TicketDao() {
	}

	public void buyTicket(final TicketDto dto) {
		System.out.println("TicketDao buyTicket() start....");
		System.out.println("dto.getConssumerId():" + dto.getConsumerId());
		System.out.println("dto.getAmount():" + dto.getAmount());

		transactionTemplate1.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				template.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						String query = "insert into card (consummerId, amunt) values (?, ?)";
						PreparedStatement pstmt = con.prepareStatement(query);
						pstmt.setString(1, dto.getConsumerId());
						pstmt.setString(2, dto.getAmount());
						
						return pstmt;
					}
				}
				);//template.update end
				
				template.update(new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

						String query = "insert into ticket(consumerId, countnum) values(?,?)";
						PreparedStatement pstmt = con.prepareStatement(query);
						pstmt.setString(1, dto.getConsumerId());
						pstmt.setString(2, dto.getAmount());

						return pstmt;
					}
				});
			}
		}
		);
	}
}
