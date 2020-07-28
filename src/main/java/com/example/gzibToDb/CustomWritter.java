package com.example.gzibToDb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class CustomWritter <Sales> implements ItemWriter<List<Sales>> , ItemStream , InitializingBean{
	
	private ItemWriter<Sales> delegate;
	
	

	public CustomWritter(ItemWriter<Sales> delegate) {
		super();
		this.delegate = delegate;
	}

	public CustomWritter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public ItemWriter<Sales> getDelegate() {
		return delegate;
	}

	public void setDelegate(ItemWriter<Sales> delegate) {
		this.delegate = delegate;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(delegate, "You must set a delegate!");
		
	}

	public void open(ExecutionContext executionContext) throws ItemStreamException {
		  if (delegate instanceof ItemStream) {
	            ((ItemStream) delegate).open(executionContext);
	        }
		
	}

	public void update(ExecutionContext executionContext) throws ItemStreamException {
		 if (delegate instanceof ItemStream) {
	            ((ItemStream) delegate).update(executionContext);
	        }
		
	}

	public void close() throws ItemStreamException {
		if (delegate instanceof ItemStream) {
            ((ItemStream) delegate).close();
        }
		
		
	}
	
	
	
	

	public void write(List<? extends List<Sales>> items) throws Exception {
		// TODO Auto-generated method stub
		
		final List<Sales> salesList = new ArrayList<Sales>();
		for(final List<Sales> list:items) {
			salesList.addAll(list);
		}
	//	System.err.println(delegate);
		delegate.write(salesList);

		
		
	}
	

}
