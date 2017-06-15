package com.my.springbatch.fixedlength;

import org.springframework.batch.item.ItemProcessor;

public class FixedLengthProcessor implements ItemProcessor<Student, Student>{

	@Override
	public Student process(Student student) throws Exception {
		/* 合并ID和名字 */
        student.setName(student.getID() + "--" + student.getName());
        /* 年龄加2 */
        student.setAge(student.getAge() + 2);
        /* 分数加10 */
        student.setScore(student.getScore() + 10);
        /* 将处理后的结果传递给writer */
		return student;
	}


}
