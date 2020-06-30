package net.jin.user.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import net.jin.model.User;
import net.jin.repository.UserRepository;
import net.jin.user.service.GoUpdateUserFormService;
import net.jin.util.HttpSessionUtils;

@Service
public class GoUpdateUserFormServiceImpl implements GoUpdateUserFormService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String goUpdateUserForm(@PathVariable Long id, Model model, HttpSession session) {
	if (!HttpSessionUtils.isLoginUser(session)) {
		return "redirect:/users/loginForm";
	}

	// 자기 ID로 로그인한 정보만 수정할 수 있도록 로직 추가(로그인 상태에서 다른 아이디도 수정할 수 있는 문제점 제거)
	User sessionedUser = HttpSessionUtils.getUserFromSession(session);
	if (!sessionedUser.matchNewId(id)) {
		System.out.println("You can update with only yours~!");
		return "redirect:/users/userList";
	}

	User user = userRepository.findById(id).get();
	model.addAttribute("user", user);
	return "user/updateUserForm";
    }
}