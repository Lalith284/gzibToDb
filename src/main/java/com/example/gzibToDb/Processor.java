package com.example.gzibToDb;

import java.io.BufferedReader;
import java.util.ArrayList;

import org.springframework.batch.item.ItemProcessor;



public class Processor implements ItemProcessor<Sales,Sales> {

	public Sales process(Sales sales) throws Exception {
		// TODO Auto-generated method stub
		System.err.println(sales.getShipDate());
		return sales;
	}

	
	}



