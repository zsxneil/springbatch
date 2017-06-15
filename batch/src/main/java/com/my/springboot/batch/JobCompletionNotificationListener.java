package com.my.springboot.batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	private final static Logger log = Logger.getLogger(JobCompletionNotificationListener.class);
	
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");
			List<Person> results = jdbcTemplate.query("select first_name, last_name from person", new RowMapper<Person>(){

				@Override
				public Person mapRow(ResultSet rs, int row) throws SQLException {
					return new Person(rs.getString(1), rs.getString(2));
				}
				
			});
			
			for (Person person : results) {
				log.info("Found <" + person + "> in the database.");
			}
		}
	}
	
	
	
}
