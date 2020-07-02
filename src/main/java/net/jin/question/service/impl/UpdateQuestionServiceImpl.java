package net.jin.question.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.question.service.UpdateQuestionService;

@Service
public class UpdateQuestionServiceImpl implements UpdateQuestionService{
	public String updateQuestion(@PathVariable Long id, String title, String contents, Model model, HttpSession session) {
		return "";
	}

}
