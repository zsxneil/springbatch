package com.my.springbatch.db;

import org.springframework.transaction.annotation.Transactional;

public interface LedgerDAO {
	public void save(final Ledger item);
	@Transactional(readOnly = false)
	public void save(int i);
}
