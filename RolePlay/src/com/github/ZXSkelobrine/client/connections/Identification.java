package com.github.ZXSkelobrine.client.connections;

import java.util.Random;

public class Identification {

	public static String generateNewID() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		String greaterAlpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlpha = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "1234567890";
		for (int i = 0; i < 5; i++) {
			switch (random.nextInt(3)) {
			case 0:
				sb.append(greaterAlpha.charAt(random.nextInt(greaterAlpha.length())));
				break;
			case 1:
				sb.append(lowerAlpha.charAt(random.nextInt(lowerAlpha.length())));
				break;
			case 2:
				sb.append(numbers.charAt(random.nextInt(numbers.length())));
				break;
			default:
				sb.append(greaterAlpha.charAt(random.nextInt(greaterAlpha.length())));
				break;
			}
		}
		return sb.toString();
	}
}
