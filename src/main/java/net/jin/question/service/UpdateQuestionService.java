package net.jin.question.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface UpdateQuestionService {
	//abstract method
	public abstract String updateQuestion(@PathVariable Long id, String title, String contents, Model model, HttpSession session);
}
