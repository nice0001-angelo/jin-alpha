package net.jin.question.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.question.service.ShowQuestionService;
import net.jin.repository.QuestionRepository;

@Service
public class ShowQuestionServiceImpl implements ShowQuestionService{
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public String showQuestion(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionRepository.findById(id).get());
		return "/qna/showQuestion";
	}
	

}
