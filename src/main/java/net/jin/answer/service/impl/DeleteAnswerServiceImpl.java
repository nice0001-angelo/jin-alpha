package net.jin.answer.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.answer.service.DeleteAnswerService;
import net.jin.model.Answer;
import net.jin.model.Question;
import net.jin.model.User;
import net.jin.repository.AnswerRepository;
import net.jin.repository.QuestionRepository;
import net.jin.util.HttpSessionUtils;
import net.jin.util.ResultUtils;

@Service
public class DeleteAnswerServiceImpl implements DeleteAnswerService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	AnswerRepository answerRepository;
	
	@Override
	public ResultUtils deleteAnswer(@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
		System.out.println("**************Stsrt API deleteAnswer************");
		if (!HttpSessionUtils.isLoginUser(session)) {
			return ResultUtils.fail("You have to login first");
		}

		Question question = questionRepository.findById(questionId).get();
		Answer answer = answerRepository.findById(id).get();
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!answer.isSameWriter(loginUser)) {
			return ResultUtils.fail("You can delete only your answer");
		}
		answerRepository.deleteById(id);
		question.deleteAnswer();
		questionRepository.save(question);
		return ResultUtils.ok();
	}
}