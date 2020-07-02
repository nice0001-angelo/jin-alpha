package net.jin.question.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.controller.QuestionController;
import net.jin.model.Question;
import net.jin.question.service.DeleteQuestionService;
import net.jin.repository.QuestionRepository;
import net.jin.util.ResultUtils;

@Service
public class DeleteQuestionServiceImpl implements DeleteQuestionService{
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionController questionController;
	
	@Override
	public String deleteQuestion(@PathVariable Long id, Model model, HttpSession session) {
		Question question = questionRepository.findById(id).get();
		ResultUtils result = questionController.valid(session, question);
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "user/login";
		}
		questionRepository.deleteById(id);
		return "redirect:/";
	}
}
