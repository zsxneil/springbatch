package com.my.springbatch.multi;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchLaunch {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:com/my/springbatch/multi/spring-batch.xml");
		JobLauncher launcher = (JobLauncher) applicationContext.getBean("jobLauncher");
		Job job = (Job) applicationContext.getBean("multiJob");
		try {
			//运行job
			JobExecution result = launcher.run(job, new JobParametersBuilder()
													.addString("inputFilePath", "com/my/springbatch/multi/input.txt")
													.addString("outputFilePathStudent", "E:\\student.txt")
													.addString("outputFilePathGoods", "E:\\goods.csv").toJobParameters());
			/* 处理结束，控制台打印处理结果 */
			System.out.println(result);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
