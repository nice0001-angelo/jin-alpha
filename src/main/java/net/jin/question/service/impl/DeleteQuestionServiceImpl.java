package net.jin.question.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.model.Question;
import net.jin.question.service.DeleteQuestionService;
import net.jin.repository.QuestionRepository;
import net.jin.util.ResultUtils;

@Service
public class DeleteQuestionServiceImpl implements DeleteQuestionService{
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Override
	public String deleteQuestion(@PathVariable Long id, Model model, HttpSession session) {
		Question question = questionRepository.findById(id).get();

		//ResultUtils result = resultUtils.valid(session, question); 이런식으로 하려면 @Autowired로 접근해야함 하지만 Static은 그런식으로 접근 안됨
		ResultUtils result = ResultUtils.valid(session, question); // static 직접접근방법(class명.method명)
		
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "user/login";
		}
		questionRepository.deleteById(id);
		return "redirect:/";
	}
}
