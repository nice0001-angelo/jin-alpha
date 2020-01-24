package net.jin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jinhyun")
public class JinhyunController {
	
	// profile.html 을 Template 폴더 밑에 두고 호출하기 위한 메소드 : 이래야 navigation, header,
	// footer를 공통으로 쓸 수 있음
	@GetMapping("/profile")
	public String profile() {
		return "jinhyun/profile";
	}
	
	

}
