package net.jin.answer.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;

import net.jin.model.Answer;

public interface CreateAnsewerService {
	public Answer createAnswer(@PathVariable Long questionId, String contents, HttpSession session); 
}
