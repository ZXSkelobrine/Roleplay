package com.github.ZXSkelobrine.server.connections.messages;

import com.github.ZXSkelobrine.neutral.variables.Client;

public class Messages {
	public static void processMessage(String message, Client client) {
		String type = message.substring(1, 4);
		if (type.equalsIgnoreCase("say")) {
			say(message, client);
		}
	}

	public static void processMessage(byte[] message, Client client) {
		processMessage(new String(message), client);
	}

	private static void say(String message, Client client) {
		for (int i = 0; i < message.split("/say/").length; i++) {
			System.out.println(i + ": " + message.split("/say/")[i]);
		}
		// Sending.broadcast(Sending.prepareBroadcast(client.getName(), ));
	}
}
