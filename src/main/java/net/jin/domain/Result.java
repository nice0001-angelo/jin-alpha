/*
 * This is a Jin-alpha Project
 * File name : Result.java
 * Created by : Jinhyun
 * Created on : Dec 2019
 */
package net.jin.domain;

import com.sun.xml.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

public class Result {
	private boolean valid;
	private String errorMessage;
	

	private Result(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	private Result(boolean valid, String errorMessage) {
		this.valid = valid;
		this.errorMessage = errorMessage;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public static Result ok() {
		return new Result(true, null);
	}
	
	public static Result fail(String errorMessage) {
		return new Result(false, errorMessage);
	}
	
}
