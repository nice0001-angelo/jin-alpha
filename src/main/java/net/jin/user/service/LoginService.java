package net.jin.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public interface LoginService {
	//abstract method
	public abstract String loginUser(HttpServletRequest httpServletRequest);
}
