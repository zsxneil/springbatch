package com.my.springbatch.multi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class MultiItemWriter<T> implements ItemWriter<T> {
	
	private List<ItemWriter<T>> delegates;
	

	public void setDelegates(List<ItemWriter<T>> delegates) {
		this.delegates = delegates;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void write(List<? extends T> items) throws Exception {
		//学生信息的Writer
		ItemWriter studentWriter = (ItemWriter) delegates.get(0);
		//商品信息的Writer
		ItemWriter goodsWriter = (ItemWriter) delegates.get(1);
		
		//学生信息
		List<Student> studentList = new ArrayList<>();
		//商品信息
		List<Goods> goodsList = new ArrayList<>();
		// 将传过来的信息按照不同的类型添加到不同的List中
		for(int i=0; i<items.size(); i++) {
			if ("Student".equals(items.get(i).getClass().getSimpleName())) {
				studentList.add((Student) items.get(i));
			} else {
				goodsList.add((Goods) items.get(i));
			}
		}
		
		if (studentList.size() >0) {
			studentWriter.write(studentList);
		}
		
		if (goodsList.size() > 0) {
			goodsWriter.write(goodsList);
		}
	}
	
}
