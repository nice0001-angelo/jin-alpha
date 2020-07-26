package net.jin.user.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface GoUpdateUserFormService {
	//abstract method
	public abstract String goUpdateUserForm(@PathVariable Long id, Model model, HttpSession session); 
}
