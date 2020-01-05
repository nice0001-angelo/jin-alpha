package net.jin.web;

import javax.servlet.http.HttpSession;

import net.jin.domain.User; //User 객체를 가져오기 위한 import

public class HttpSessionUtils {
	//자주 쓰는 sessonedUser를 상수로 선언하여 공통 관리
	public static final String USER_SESSION_KEY = "sessionedUser";
	
	//login 상태값 확인을 위한 메소드 : 사용자가 로그인 되어있는지 아닌지를 판단함
	public static boolean isLoginUser(HttpSession session) {
		Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
		if (sessionedUser == null) {
			return false;
		}
		return true;	
	}
	
	// User객체 정보를 갖고 있기 위함
	public static User getUserFromSession(HttpSession session) {
		if (!isLoginUser(session)) {
			return null;
		}
		
		//User sessionedUser = (User)session.getAttribute(USER_SESSION_KEY);
		//return sessionedUser;
		return (User)session.getAttribute(USER_SESSION_KEY);
	}
}
