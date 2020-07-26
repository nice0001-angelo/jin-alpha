package net.jin.question.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface CreateQuestionService {
	//abstract method
	public abstract String createQuestion(HttpServletRequest httpServletRequest);
}
