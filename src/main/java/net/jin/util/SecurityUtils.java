package net.jin.util;

import java.security.MessageDigest;

public class SecurityUtils {
	public String encryptSHA256(String plainText) {
		String plainText = "";
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			sha256.update(plainText.getBytes());
			byte byteData[] = plainText.digest();
			
		}
	}
}
