package com.my.springbatch.db;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.stereotype.Component;

@Component("itemFailureLoggerListener")
public class ItemFailureLoggerListener extends ItemListenerSupport<Object, Object>{
	private final static Logger LOGGER = Logger.getLogger(ItemFailureLoggerListener.class);

	@Override
	public void onReadError(Exception ex) {
		LOGGER.error("Encountered error on read", ex);
	}

	//test
	@Override
	public void afterRead(Object item) {
		System.out.println(item);
	}

	@Override
	public void onWriteError(Exception ex, List<? extends Object> item) {
		LOGGER.error("Encountered error on writer", ex);
	}
	
}
