package net.jin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//<class 명, 타입>
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
