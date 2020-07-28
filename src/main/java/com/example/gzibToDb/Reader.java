package com.example.gzibToDb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.GZIPInputStream;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<Sales> {
	
	public Reader() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException,FileNotFoundException{
		super();
		// TODO Auto-generated constructor stub
	}


	private boolean batchJobState = false;
	int bufferSize = 8 * 1024;
	String inputFile = "C:/Users/ELCOT/Desktop/gzibToDb/src/main/resources/read/sales.gz";
	GZIPInputStream input = new GZIPInputStream(new FileInputStream(inputFile));
	InputStreamReader decoder = new InputStreamReader(input);
	BufferedReader br = new BufferedReader(decoder, bufferSize);

	
	public Sales read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		//System.err.println("READER");
	
		//ArrayList<Sales> salesList = new ArrayList<Sales>();

	
		String line;

		if (!batchJobState) {
			while ((line = br.readLine()) != null) {
				Sales sales = new Sales();
				String[] item = line.split(",");

				sales.setOrderId(Integer.parseInt(item[6]));
				sales.setRegion(item[0]);
				sales.setCountry(item[1]);
				sales.setItemType(item[2]);
				sales.setSalesChannel(item[3]);
				sales.setOrderPriority(item[4]);
				Date orderDate = new SimpleDateFormat("MM/dd/yyyy").parse(item[5]);
				java.sql.Date sDate = new java.sql.Date(orderDate.getTime());
				sales.setOrderDate(sDate);

				Date shipDate = new SimpleDateFormat("MM/dd/yyyy").parse(item[7]);
				java.sql.Date sDate1 = new java.sql.Date(shipDate.getTime());
				
				System.out.println(sales.getShipDate());
				sales.setUnitsSold(Integer.parseInt(item[8]));
				sales.setUnitPrice(Float.parseFloat(item[9]));
				sales.setUnitCost(Float.parseFloat(item[10]));
				sales.setTotalRevenue(Float.parseFloat(item[11]));
				sales.setTotalCost(Float.parseFloat(item[12]));
				sales.setTotalProfit(Float.parseFloat(item[13]));
	//			salesList.add(sales);
				sales.setShipDate(sDate1);
                  return sales;
			}
  if(br.readLine()==null) {
			batchJobState = true;
			input.close();
			decoder.close();
			br.close();
			//Thread.sleep(3000);
  }
//			return sales;

		}

		return null;

	}
}
