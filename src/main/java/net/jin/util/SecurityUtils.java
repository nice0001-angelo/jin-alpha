package net.jin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {
	public String encryptSHA256(String str) {
		String sha = "";
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer stringBuffer = new StringBuffer();
			for(int i=0; i<byteData.length; i++) {
				stringBuffer.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			sha = stringBuffer.toString();
		}catch(NoSuchAlgorithmException e) {
			//e.printStackTrace()
			System.out.println("Encrypt Error - NoSuchAlgorithmException");
			sha = null;
		}
		return sha;
	}
}
