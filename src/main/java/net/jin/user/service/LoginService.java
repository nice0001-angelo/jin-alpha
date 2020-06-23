package net.jin.user.service;

import javax.servlet.http.HttpSession;

public interface LoginService {
	public String loginUser(String userId, String password, HttpSession session);
}
