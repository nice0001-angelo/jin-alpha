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
