package com.example.gzibToDb;

import java.io.BufferedReader;
import java.util.ArrayList;

import org.springframework.batch.item.ItemProcessor;



public class Processor implements ItemProcessor<ArrayList<Sales>,ArrayList<Sales>> {

	public ArrayList<Sales> process(ArrayList<Sales> sales) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("Process");
		return sales;
	}




}
