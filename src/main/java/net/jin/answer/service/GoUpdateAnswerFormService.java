package net.jin.answer.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface GoUpdateAnswerFormService {
	public String goUpdateAnswerForm(@PathVariable Long questionId, @PathVariable Long id, HttpSession session, Model model);
}
