package com.example.gzibToDb;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileDeleteStrategy;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GzibToDbBatchScheduler {
	
	
	@Autowired
	JobLauncher jobLauncher;
	
@Autowired 
Job job;
	

@Scheduled(cron="*/10 * * * * *")
public void jobScheduled() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, IOException, InterruptedException{
	File file = new File("C:/Users/ELCOT/Desktop/gzibToDb/src/main/resources/read/sales5lakhs.gz");
	
	System.err.println(file.exists());
	if(file.exists()) {
	Map<String,JobParameter>maps = new HashMap<String, JobParameter>();
	maps.put("time", new JobParameter(System.currentTimeMillis()));
	JobParameters parameters=new JobParameters(maps);
	
	JobExecution jobExecution =  jobLauncher.run(job, parameters);
	System.out.println("JOB EXECUTION :" + jobExecution.getStatus());
	if(!jobExecution.getStatus().isUnsuccessful()) {
		
//		Thread.sleep(5000);
		//FileDeleteStrategy.FORCE.delete(file);
		
		file.delete();
		System.err.println("DELETED");
	
	}
	}

	
}


}
