package com.github.ZXSkelobrine.server.connections.messages;

import java.awt.Color;
import java.lang.reflect.Field;

import com.github.ZXSkelobrine.neutral.variables.Client;
import com.github.ZXSkelobrine.server.connections.Connections;
import com.github.ZXSkelobrine.server.connections.Sending;

public class Messages {
	public static void processMessage(String message, Client client) {
		String type = message.substring(1, 4);
		if (type.equalsIgnoreCase("say")) {// Say
			say(message, client);
		}
		if (type.equalsIgnoreCase("nam")) {// Set Name, Description and Colour
			name(message, client);
		}
		if (type.equalsIgnoreCase("des")) {// Set Name, Description and Colour
			description(message, client);
		}
		if (type.equalsIgnoreCase("col")) {// Set Name, Description and Colour
			colour(message, client);
		}
		if (type.equalsIgnoreCase("act")) {// Perform Act
			act(message, client);
		}
		if (type.equalsIgnoreCase("clo")) {// Close Connection
			close(message, client);
		}
		if (type.equalsIgnoreCase("usr")) {// User List
			users(message, client);
		}
		if (type.equalsIgnoreCase("det")) {// User Details
			details(message, client);
		}
		if (type.equalsIgnoreCase("prm")) {// Private Message
			priv(message, client);
		}
	}

	private static void colour(String message, Client client) {
		Connections.setClientColour(getColourFromString(message.split("/col/")[2]), client);
	}

	private static void description(String message, Client client) {
		Connections.setClientDescription(message.split("/des/")[2], client);
		try {
			Connections.setClientColour(getColourFromString(message.split("/col/")[2]), client);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	private static void priv(String message, Client client) {
		// 0 - Nothing
		// 1 - ID
		// 2 - To
		// 3 - Message
		// 4 - Colour
		Client toSend = Connections.getClientFromName(message.split("/prm/")[2]);
		Sending.send("/prm/" + client.getName() + "/prm/" + message.split("/prm/")[3] + "/prm/" + getColorName(client.getColour()) + "/prm/", toSend.getSocket());
		Sending.send("/prm/" + client.getName() + "/prm/" + message.split("/prm/")[3] + "/prm/" + getColorName(client.getColour()) + "/prm/", client.getSocket());
	}

	private static void details(String message, Client client) {
		Client about = Connections.getClientFromName(message.split("/det/")[2]);
		String toMessage = "/det/" + about.getName() + "/det/" + about.getDescription() + "/det/" + getColorName(about.getColour()) + "/det/";
		Sending.send(toMessage, client.getSocket());

	}

	private static void users(String message, Client client) {
		synchronized (Connections.getClients()) {
			StringBuilder builder = new StringBuilder();
			builder.append("/usr/");
			for (Client clients : Connections.getClients()) {
				builder.append(clients.getName() + "/usr/");
			}
			Sending.send(builder.toString(), client.getSocket());
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
		try {
			Connections.setClientDescription(message.split("/des/")[2], client);
			Connections.setClientColour(getColourFromString(message.split("/col/")[2]), client);
		} catch (ArrayIndexOutOfBoundsException e) {
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

	public static void processMessage(byte[] message, Client client) {
		processMessage(new String(message), client);
	}

	private static void say(String message, Client client) {
		// 0 - Nothing
		// 1 - ID
		// 2 - Message
		Sending.broadcast(Sending.prepareSayBroadcast(client.getName(), message.split("/say/")[2], client.getColour()));
	}

	public static String getColorName(Color c) {
		for (Field f : Color.class.getFields()) {
			try {
				if (f.getType() == Color.class && f.get(null).equals(c)) {
					return f.getName();
				}
			} catch (java.lang.IllegalAccessException e) {
				// it should never get to here
			}
		}
		return "unknown";
	}
}
