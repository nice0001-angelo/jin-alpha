/*
 * This is a Jin-alpha Project
 * File name : AnswerRepository.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for Answer table Repository object
 */
package net.jin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
