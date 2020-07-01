package net.jin.question.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jin.model.Question;
import net.jin.model.User;
import net.jin.question.service.CreateQuestionService;
import net.jin.repository.QuestionRepository;
import net.jin.util.HttpSessionUtils;

@Service
public class CreateQuestionServiceImpl implements CreateQuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Override
	public String createQuestion(String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginform";
		}

		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		// Question newQuestion = new Question(sessionUser.getUserId(), title,
		// contents);
		// Question.java 안에서 User와 관계를 맺었기 때문에 User객체를 바로 가져올수 있음
		// 객체내에서 get으로 꺼내올 생각 말고 바로 객체를 가져오는 것으로 수정
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
	
}
