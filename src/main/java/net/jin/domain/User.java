/*
 * This is a Jin-alpha Project
 * File name : User.java
 * Created by : Jinhyun
 * Created on : Dec 2019
 */
package net.jin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User extends AbstractEntity{
	@Column(nullable=false, length=20, unique=true)
	@JsonProperty
	private String userId;
	
	@JsonIgnore
	private String password;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String email;

	//로그인 한 사용자가 다른 로그인 사용자 정보를 수정하지 못하게 특정하기 위한  getter
	//아래에 id 체크하는 로직이 있으면 getId()로 id 체크 하지 않아도 됨. AbstractEntity.java 에 정의 후 상속 
//	public Long getId() { 
//		return id;
//	}
	
	//id 정보가 같은지 체크하는 로직
	public boolean matchNewId(Long newId) {
		if(newId == null) {
			return false;
		}
		return newId.equals(getId());
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
	
	//mouse right and source toString()
	@Override
	public String toString() {
		return "User [" + super.toString()+ ", userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}

}
