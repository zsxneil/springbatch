package com.my.springboot.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	private static final Logger log = Logger.getLogger(PersonItemProcessor.class);
	
	@Override
	public Person process(Person person) throws Exception {
		final String firstName = person.getFirstName().toUpperCase();
		final String lastName = person.getLastName().toUpperCase();
		
		final Person transformedPerson = new Person(lastName, firstName);
		log.info("Converting (" + person + ") into (" + transformedPerson + ")");
		return transformedPerson;
	}

}
