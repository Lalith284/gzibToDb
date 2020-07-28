package com.example.gzibToDb;

import java.io.BufferedReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;






@Configuration
public class GzibToDbBatchConfig {
	

	  @Autowired
	    private DataSource dataSource;
	  
//	  
//	  @Autowired
//	  private CustomWritter<Sales> customWritter ;
	  
	  
	  @Autowired
	  private NoOpWritter noOpWritter ;
	  	  
	  
	  
		@Bean
		public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) 
		
		{
			Step step =stepBuilderFactory.get("CSV-FILE-LOAD")
					.<ArrayList<Sales>, ArrayList<Sales>>chunk(0)
					.reader(customReader())
					.processor(processor())
					.writer(noOpWritter)
					//.taskExecutor(taskExecutor())
					.build();	
			
			return jobBuilderFactory.get("CSV-LOAD")
			.incrementer(new RunIdIncrementer())
			.start(step)
			.build();
			
		}

		
	
		
		@Bean
		public ItemReader<ArrayList<Sales>> customReader(){
			return new Reader();
		}
		
		
		@Bean
		public ItemProcessor<ArrayList<Sales>,ArrayList<Sales>> processor(){
			return new Processor();
		}
		
		
		@Bean
		public NoOpWritter itemWriter(){
			return new NoOpWritter();
		}
			
	
		
//		
//		@Bean
//		public CustomWritter<Sales> itemWriter(){
//			JdbcBatchItemWriter<Sales> jdbcBatchItemWriter=new JdbcBatchItemWriter<Sales>(); 
//			jdbcBatchItemWriter.setItemPreparedStatementSetter(setter());
//			jdbcBatchItemWriter.setSql("INSERT INTO salesrecord (orderid,region,country,itemtype,saleschannel,orderpriority,orderdate,shipdate,unitssold,unitprice,unitcost,totalrevenue,totalcost,totalprofit) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//			jdbcBatchItemWriter.setDataSource(dataSource);
//			jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Sales>());
//			CustomWritter<Sales> customWritter= new CustomWritter<Sales>();
//			customWritter.setDelegate(jdbcBatchItemWriter);
//			return customWritter;
//		}
//		
//		
//		
//		
//		
//
//	    @Bean
//	    public ItemPreparedStatementSetter<Sales> setter() {
//	        return new ItemPreparedStatementSetter<Sales>() {
//				public void setValues(Sales rs, PreparedStatement Sales) throws SQLException {
//
//					Sales.setInt(1,rs.getOrderId());
//					Sales.setString(2,rs.getRegion());
//					Sales.setString(3,rs.getCountry());
//					Sales.setString(4,rs.getItemType());
//					Sales.setString(5,rs.getSalesChannel());
//					Sales.setString(6,rs.getOrderPriority());	
//					Sales.setDate(7, rs.getOrderDate());
//					Sales.setDate(8, rs.getShipDate());
//					Sales.setInt(9, rs.getUnitsSold());
//					Sales.setFloat(10, rs.getUnitPrice());
//					Sales.setFloat(11, rs.getUnitCost());
//					Sales.setFloat(12, rs.getTotalRevenue());
//					Sales.setFloat(13, rs.getTotalCost());
//					Sales.setFloat(14, rs.getTotalProfit());
//					
//				}
//			};
//
//	    }        
//	        
	        
		
		@Bean
		public TaskExecutor taskExecutor() {
			ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
			taskExecutor.setMaxPoolSize(22);
			taskExecutor.afterPropertiesSet();
			return taskExecutor;
		}

}
