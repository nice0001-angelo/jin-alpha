package net.jin.question.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeleteQuestionService {
	//abstract method
	public abstract String deleteQuestion(@PathVariable Long id, Model model, HttpSession session);
}
