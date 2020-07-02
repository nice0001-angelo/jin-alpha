package net.jin.question.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.controller.QuestionController;
import net.jin.model.Question;
import net.jin.question.service.UpdateQuestionService;
import net.jin.repository.QuestionRepository;
import net.jin.util.ResultUtils;

@Service
public class UpdateQuestionServiceImpl implements UpdateQuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuestionController questionController;
	
	@Autowired
	ResultUtils resultUtils;

	@Override
	public String updateQuestion(@PathVariable Long id, String title, String contents, Model model,
			HttpSession session) {
		Question question = questionRepository.findById(id).get();
		ResultUtils result = resultUtils.valid(session, question);
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "user/login";
		}
		question.update(title, contents);
		questionRepository.save(question);
		return String.format("redirect:/questions/%d/showQuestion", id);
	}
}
