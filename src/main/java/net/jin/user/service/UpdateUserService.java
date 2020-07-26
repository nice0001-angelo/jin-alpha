package net.jin.user.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;

import net.jin.model.User;

public interface UpdateUserService {
	//abstract method
	public abstract String updateUser(@PathVariable Long id, User updatedUser, HttpSession session);
}
