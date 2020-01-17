package net.jin.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jin.domain.Answer;
import net.jin.domain.AnswerRepository;
import net.jin.domain.Question;
import net.jin.domain.QuestionRepository;
import net.jin.domain.Result;
import net.jin.domain.User;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired //Autowired annotation alot value to anwserRepository from AnwerRepository under Spring Framework
	private AnswerRepository answerRepository;
	
	
	@PostMapping("")
	public Answer create(@PathVariable Long questionId, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}

		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		
		Question question = questionRepository.findById(questionId).get();
		Answer answer = new Answer(loginUser, question, contents);
		return answerRepository.save(answer);
		
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("You have to login first");
		}
		
		Answer answer = answerRepository.findById(id).get();
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!answer.isSameWriter(loginUser)) {
			return Result.fail("You can delete only your answer");
		}
		answerRepository.deleteById(id);
		return Result.ok();
	}
}
