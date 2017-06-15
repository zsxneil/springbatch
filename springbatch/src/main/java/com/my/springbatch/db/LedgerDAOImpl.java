package com.my.springbatch.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;


@Repository
public class LedgerDAOImpl implements LedgerDAO {
	
	private static final String SAVE_SQL = "INSERT INTO T_LEDGER_TEMP (RECEIPT_DATE, MEMBER_NAME, CHECK_NUMBER, CHECK_DATE, PAYMENT_TYPE, DEPOSIT_AMOUNT, PAYMENT_AMOUNT, COMMENTS) VALUES(?,?,?,?,?,?,?,?)";
	private static final String INIT_SQL = "INSERT INTO T_LEDGER (RECEIPT_DATE, MEMBER_NAME, CHECK_NUMBER, CHECK_DATE, PAYMENT_TYPE, DEPOSIT_AMOUNT, PAYMENT_AMOUNT, COMMENTS) VALUES(?,?,?,?,?,?,?,?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Ledger item) {
		jdbcTemplate.update(SAVE_SQL, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setDate(1, new Date(item.getReceiptDate().getTime()));
				stmt.setString(2, item.getMemberName());
				stmt.setString(3, item.getCheckNumber());
				stmt.setDate(4, new Date(item.getCheckDate().getTime()));
				stmt.setString(5, item.getPaymentType());
				stmt.setDouble(6, item.getDepositAmount());
				stmt.setDouble(7, item.getPaymentAmount());
				stmt.setString(8, item.getComments());
			}
		});
	}

	@Override
	public void save(int i) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, i);
		String memberName = "Name";
		String checkNumber = "Num";
		String paymentType = "Type";
		double depositAmount = 12.2d;
		double paymentAmount = 11.4d;
		String comments = "Comments";
		
		jdbcTemplate.update(INIT_SQL, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setDate(1, new Date(calendar.getTimeInMillis()));
				stmt.setString(2, memberName + i);
				stmt.setString(3, checkNumber + i);
				stmt.setDate(4, new Date(calendar.getTimeInMillis()));
				stmt.setString(5, paymentType + i);
				stmt.setDouble(6, depositAmount + i);
				stmt.setDouble(7, paymentAmount + i);
				stmt.setString(8, comments + i);
			}
		});
	}

}
