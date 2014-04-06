package com.github.ZXSkelobrine.server.connections.messages;

import java.awt.Color;
import java.lang.reflect.Field;

import com.github.ZXSkelobrine.neutral.variables.Client;
import com.github.ZXSkelobrine.server.connections.Connections;
import com.github.ZXSkelobrine.server.connections.Sending;

public class Messages {
	public static void processMessage(String message, Client client) {
		String type = message.substring(1, 4);
		if (type.equalsIgnoreCase("say")) {
			say(message, client);
		}
		if (type.equalsIgnoreCase("nam")) {
			name(message, client);
		}
		if (type.equalsIgnoreCase("act")) {
			act(message, client);
		}
		if (type.equalsIgnoreCase("clo")) {
			close(message, client);
		}
	}

	private static void close(String message, Client client) {
		Connections.terminateSocket(client);
	}

	private static void act(String message, Client client) {
		// 0 - Nothing
		// 1 - ID
		// 2 - Act
		for (int i = 0; i < message.split("/act/").length; i++) {
			System.out.println(i + ": " + message.split("/act/")[i]);
		}
		Sending.broadcast(Sending.prepareActBroadcast(client.getName(), message.split("/act/")[2], client.getColour()));
	}

	private static void name(String message, Client client) {
		// Split /nam/:
		// 0 - Nothing
		// 1 - ID
		// 2 - Name
		// 3 - Description String
		// Split /des/:
		// 0 - Name String
		// 1 - ID
		// 2 - Description
		Connections.setClientName(message.split("/nam/")[2], client);
		Connections.setClientDescription(message.split("/des/")[2], client);
		Connections.setClientColour(getColourFromString(message.split("/col/")[2]), client);
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

	public static void processMessage(byte[] message, Client client) {
		processMessage(new String(message), client);
	}

	private static void say(String message, Client client) {
		// 0 - Nothing
		// 1 - ID
		// 2 - Message
		Sending.broadcast(Sending.prepareSayBroadcast(client.getName(), message.split("/say/")[2], client.getColour()));
	}
}
