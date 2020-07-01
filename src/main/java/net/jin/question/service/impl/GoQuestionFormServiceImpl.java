package net.jin.question.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import net.jin.question.service.GoQuestionFormSerivce;
import net.jin.util.HttpSessionUtils;

@Service
public class GoQuestionFormServiceImpl implements GoQuestionFormSerivce{
	
	@Override
	public String goQuestionForm(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		return "qna/questionForm";
	}
}
