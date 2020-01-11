package net.jin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//<class 명, 타입>
//User.java 의 Class를 Long 타입으로 모두 가져옮
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserId(String userId);
}
