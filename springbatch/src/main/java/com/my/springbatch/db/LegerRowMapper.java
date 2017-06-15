package com.my.springbatch.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component("ledgerRowMapper")
public class LegerRowMapper implements RowMapper<Ledger>{

	@Override
	public Ledger mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ledger ledger = new Ledger();
		ledger.setId(rs.getInt("ID"));
		ledger.setReceiptDate(rs.getDate("RECEIPT_DATE"));
		ledger.setMemberName(rs.getString("MEMBER_NAME"));
		ledger.setCheckNumber(rs.getString("MEMBER_NAME"));
		ledger.setCheckDate(rs.getDate("CHECK_DATE"));
		ledger.setPaymentType(rs.getString("PAYMENT_TYPE"));
		ledger.setDepositAmount(rs.getDouble("DEPOSIT_AMOUNT"));
		ledger.setPaymentAmount(rs.getDouble("PAYMENT_AMOUNT"));
		ledger.setComments(rs.getString("COMMENTS"));
		return ledger;
	}

}
