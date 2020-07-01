package net.jin.question.service;

import javax.servlet.http.HttpSession;

public interface CreateQuestionService {
	public String createQuestion(String title, String contents, HttpSession session);
}
