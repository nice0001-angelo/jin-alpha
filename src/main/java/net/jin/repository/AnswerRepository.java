/*
 * This is a Jin-alpha Project
 * File name : AnswerRepository.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for Answer table Repository object
 */
package net.jin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.jin.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
