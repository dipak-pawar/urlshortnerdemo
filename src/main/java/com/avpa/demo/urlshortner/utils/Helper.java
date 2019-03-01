package com.avpa.demo.urlshortner.utils;

import java.util.Random;

public class Helper {
	
	private Helper() {
		// this class should contain static method only
	}
	
	public static String getRandomString(int length) {
		String fromChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder randomStringBuilder = new StringBuilder();
		Random rnd = new Random();
		while (randomStringBuilder.length() < length) { 
			int index = rnd.nextInt(fromChars.length());
			randomStringBuilder.append(fromChars.charAt(index));
		}
		return randomStringBuilder.toString();
	}
}
