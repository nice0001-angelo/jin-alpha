package net.jin.question.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.controller.QuestionController;
import net.jin.model.Question;
import net.jin.question.service.GoUpdateQuestionFormService;
import net.jin.repository.QuestionRepository;
import net.jin.util.ResultUtils;

@Service
public class GoUpdateQuestionFormServiceImpl implements GoUpdateQuestionFormService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionController questionController;
	
	@Override
	public String goUpdateQuestionForm(@PathVariable Long id, Model model, HttpSession session) {
		Question question = questionRepository.findById(id).get(); // refactoring 의 local variable를 통해서 추출하고 자동 변경된것임
		ResultUtils result = questionController.valid(session, question);
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage()); // Excception into errorMessage and return to
																			// /user/login.html
			return "user/loginForm";
		}
		model.addAttribute("question", question); // 해당 id에 해당하는 data를 question 테이블에서 가져다가 return 한다
		return "qna/updateQuestionForm";
	}
}
