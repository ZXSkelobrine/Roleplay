package com.github.ZXSkelobrine.client.connections.threads;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Field;

import com.github.ZXSkelobrine.client.connections.Connection;
import com.github.ZXSkelobrine.client.windows.Details;
import com.github.ZXSkelobrine.client.windows.Main;

public class Listen {

	private static Thread listenThread;
	private static boolean running = false;

	public static void startListening() {
		prepThread();
		running = true;
		listenThread.start();
	}

	private static void prepThread() {
		listenThread = new Thread() {
			@Override
			public void run() {
				while (running) {
					if (Connection.socket != null && !Connection.socket.isClosed()) {
						try {
							if (Connection.socket.getInputStream().available() > 0) {
								byte[] arg0 = new byte[Connection.socket.getInputStream().available()];
								Connection.socket.getInputStream().read(arg0);
								if (new String(arg0).substring(1, 4).equalsIgnoreCase("usr")) {
									String[] users = new String(arg0).split("/usr/");
									Main.updateUsers(users);
								} else if (new String(arg0).substring(1, 4).equalsIgnoreCase("det")) {
									// 1 - Name
									// 2 - Description
									// 3 - Colour
									String[] info = new String(arg0).split("/det/");
									new Details(info[1], info[2], info[3]);
								} else {
									Main.logMessage(new String(arg0).split("/mes/")[1], getColourFromString(new String(arg0).split("/mes/")[2]));
								}
							}
						} catch (IOException e) {

							e.printStackTrace();
						}
					}
				}
			}
		};
	}

	public static void stopListening() {
		running = false;
		try {
			listenThread.join();
		} catch (InterruptedException e) {
			listenThread.interrupt();
			e.printStackTrace();
		}
	}

	private static Color getColourFromString(String string) {
		System.out.println("Init: " + string);
		String temp = string.replaceAll("\\s", "");
		temp = temp.toLowerCase();
		System.out.println("Aft: " + temp);
		Color toRet = null;
		try {
			Field field = Class.forName("java.awt.Color").getField(temp);
			toRet = (Color) field.get(null);
		} catch (Exception e) {

		}
		return toRet;
	}

}
