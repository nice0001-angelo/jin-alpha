/*
 * This is a Jin-alpha Project
 * File name : UserRepository.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for user table Repository object
 */
package net.jin.model;

import org.springframework.data.jpa.repository.JpaRepository;

//<class 명, 타입>
//User.java 의 Class를 Long 타입으로 모두 가져옮
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserId(String userId);
}
