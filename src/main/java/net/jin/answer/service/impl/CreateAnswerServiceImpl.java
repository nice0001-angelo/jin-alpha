package net.jin.answer.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.answer.service.CreateAnsewerService;
import net.jin.model.Answer;
import net.jin.model.Question;
import net.jin.model.User;
import net.jin.repository.AnswerRepository;
import net.jin.repository.QuestionRepository;
import net.jin.util.HttpSessionUtils;

@Service 
//IoC컨테이너에 등록되어 관리되는 객체를 빈(Bean)이라고 합니다. 
//New 생성자를 이용해 생성한 객체는 IoC컨테이너에 등록된 객체가 아니므로 빈이 아니다
//빈(Bean) 등록방법 3가지
//1. Application context.xml(root-context.xml)에 등록 (Bean id와 클래스 명, 변수 이름, 의존관계 등을 xml로 작성하는 방법) 
public class CreateAnswerServiceImpl implements CreateAnsewerService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Override
	public Answer createAnswer(@PathVariable Long questionId, String contents, HttpSession session) {
		System.out.println("**************Start API createAnswer************");
		if (!HttpSessionUtils.isLoginUser(session)) {
			System.out.println("**************You are Not login User************");
			return null;
		}

		User loginUser = HttpSessionUtils.getUserFromSession(session);

		Question question = questionRepository.findById(questionId).get();
		Answer answer = new Answer(loginUser, question, contents);
		question.addAnswer();
		return answerRepository.save(answer);
	}

}
