package com.example.gzibToDb;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class NoOpWritter implements ItemWriter<List<Sales>> {

	public void write(List<? extends List<Sales>> items) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("WRITTER>>>>>>>");
		
		for(final List<Sales> list:items) {
			System.err.println(list);
		}
		
	}

}
