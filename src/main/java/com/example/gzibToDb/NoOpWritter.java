package com.example.gzibToDb;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class NoOpWritter implements ItemWriter<Sales> {

	public void write(List<? extends Sales> sales) throws Exception {
		// TODO Auto-generated method stub
System.err.println("WRITTER>>>>>>>");
		
	
			System.err.println(sales);
		
		
	

	}	
}
