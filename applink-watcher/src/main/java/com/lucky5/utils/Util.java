package com.lucky5.utils;

/**
 * Simple utility class for common library functions
 * 
 * @author Yashpal_Rawat
 *
 */
public class Util {
	public static String encrypt(String password){
		if(password != null && password.trim().length() > 0) {
			char[] chars = password.toCharArray();
			char[] newchars = new char[chars.length];
			for(int i=0;i<chars.length;i++) {
				newchars[i] = (char)(chars[i] - 5);
			}
			return String.valueOf(newchars);
		}
		return password;
	}
	public static String decrypt(String password){
		if(password != null && password.trim().length() > 0) {
			char[] chars = password.toCharArray();
			char[] newchars = new char[chars.length];
			for(int i=0;i<chars.length;i++) {
				newchars[i] = (char)(chars[i] + 5);
			}
			return String.valueOf(newchars);
		}
		return password;
	}
	
	public static void main(String[] args){
		String pwd = encrypt("consoleuser1");
		System.out.println(pwd + "consoleuser1" + " == " + decrypt(pwd));
	}
	
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}
}
