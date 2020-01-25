/*
 * This is a Jin-alpha Project
 * File name : QuestionRepository.java
 * Created by : Jinhyun
 * Created on : Dec 2019
 */
package net.jin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//<class 명, 타입>
//Question.java 의 Class를 Long 타입으로 모두 가져옮
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
