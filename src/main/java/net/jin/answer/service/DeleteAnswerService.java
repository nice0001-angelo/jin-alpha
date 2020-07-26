/*
 * This is a Jin-alpha Project
 * File name : DeleteAnswerService.java
 * Created by : Jinhyun
 * Created on : Jul 2020
 * Contents : interface for DeleteAnswer
 */
package net.jin.answer.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;

import net.jin.util.ResultUtils;

public interface DeleteAnswerService {
	//abstract method
	public abstract ResultUtils deleteAnswer(@PathVariable Long questionId, @PathVariable Long id, HttpSession session);
}
