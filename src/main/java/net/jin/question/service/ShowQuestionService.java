package net.jin.question.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface ShowQuestionService {
	//abstract method
	public abstract String showQuestion(@PathVariable Long id, Model model);
}
