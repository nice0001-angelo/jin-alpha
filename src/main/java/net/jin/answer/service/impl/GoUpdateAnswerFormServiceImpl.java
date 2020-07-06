package net.jin.answer.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.answer.service.GoUpdateAnswerFormService;
import net.jin.model.Answer;
import net.jin.model.Question;
import net.jin.repository.AnswerRepository;
import net.jin.repository.QuestionRepository;
import net.jin.util.ResultUtils;

@Service
public class GoUpdateAnswerFormServiceImpl implements GoUpdateAnswerFormService{
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Override
	public String goUpdateAnswerForm(@PathVariable Long questionId, @PathVariable Long id, HttpSession session, Model model) {
		Answer answer = answerRepository.findById(id).get();
		Question question = questionRepository.findById(questionId).get(); // refactoring 의 local variable를 통해서 추출하고 자동 변경된것임
		ResultUtils result = ResultUtils.valid(session, question);
	
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage()); // Excception into errorMessage and return to
																			// /user/login.html
			return "user/loginForm";
		}

		model.addAttribute("answer", answer); // 해당 id에 해당하는 data를 question 테이블에서 가져다가 return 한다
		return "qna/updateAnswerForm";
	}

}
