/*
 * This is a Jin-alpha Project
 * File name : Result.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for result
 */
package net.jin.util;

import javax.servlet.http.HttpSession;

import net.jin.model.Question;
import net.jin.model.User;

public class ResultUtils {
	private boolean valid;
	private String errorMessage;
	
	//생성자
	private ResultUtils(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	//생성자
	private ResultUtils(boolean valid, String errorMessage) {
		this.valid = valid;
		this.errorMessage = errorMessage;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public static ResultUtils ok() {
		return new ResultUtils(true, null);
	}
	
	public static ResultUtils fail(String errorMessage) {
		return new ResultUtils(false, errorMessage);
	}
	
	//validQuestion
	public static ResultUtils valid(HttpSession session, Question question) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return ResultUtils.fail("You have to do this after login");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWriter(loginUser)) {
			return ResultUtils.fail("Your login is not matched");
		}
		return ResultUtils.ok();
	}
}
