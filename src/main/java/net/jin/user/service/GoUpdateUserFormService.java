package net.jin.user.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface GoUpdateUserFormService {
	public String goUpdateUserForm(@PathVariable Long id, Model model, HttpSession session); 
}
