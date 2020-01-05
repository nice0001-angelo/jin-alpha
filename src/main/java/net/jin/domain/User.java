package net.jin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, length=20, unique=true)
	private String userId;
	
	private String password;
	private String name;
	private String email;

	//로그인 한 사용자가 다른 로그인 사용자 정보를 수정하지 못하게 특정하기 위한  getter
	//아래에 id 체크하는 로직이 있으면 getId()로 id 체크 하지 않아도 됨 
	public Long getId() { 
		return id;
	}
	
	//id 정보가 같은지 체크하는 로직
	public boolean matchNewId(Long newId) {
		if(newId == null) {
			return false;
		}
		return newId.equals(id);
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	//아래에 password 같은지 체크하는 로직으로 인해 필요가 없어짐.
	public String getPassword() {
		return password;
	}

	//password 정보가 같은지 체크하는 로직
	public boolean matchNewPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}
		return newPassword.equals(password);
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void update(User newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;		
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}



}
