package com.my.springbatch.db;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ledgerWriter")
public class LedgerWriter implements ItemWriter<Ledger>{

	@Autowired
	private LedgerDAO ledgerDAO;
	
	@Override
	public void write(List<? extends Ledger> items) throws Exception {
		long current = System.currentTimeMillis();
		for (Ledger ledger : items) {
			ledgerDAO.save(ledger);
		}
		System.out.println(System.currentTimeMillis() - current);
		//init data 数据库必须有初始数据才会走到这里，即如果reader读取的数据为空，则不会继续执行writer
		/*for(int i=0; i<1000; i++) {
			System.out.println(i);
			ledgerDAO.save(i);
		}*/
	}

}
