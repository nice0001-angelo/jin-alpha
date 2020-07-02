/*
 * This is a Jin-alpha Project
 * File name : Result.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for result
 */
package net.jin.util;

public class ResultUtils {
	private boolean valid;
	private String errorMessage;
	

	private ResultUtils(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	private ResultUtils(boolean valid, String errorMessage) {
		this.valid = valid;
		this.errorMessage = errorMessage;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public static ResultUtils ok() {
		return new ResultUtils(true, null);
	}
	
	public static ResultUtils fail(String errorMessage) {
		return new ResultUtils(false, errorMessage);
	}
	
}
