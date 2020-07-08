package net.jin.answer.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;

import net.jin.util.ResultUtils;

public interface DeleteAnswerService {
	public ResultUtils deleteAnswer(@PathVariable Long questionId, @PathVariable Long id, HttpSession session);
}
