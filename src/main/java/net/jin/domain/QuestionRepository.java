/*
 * This is a Jin-alpha Project
 * File name : QuestionRepository.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for question table Repository object
 */
package net.jin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//<class 명, 타입>
//Question.java 의 Class를 Long 타입으로 모두 가져옮
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
