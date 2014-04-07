package com.github.ZXSkelobrine.server.connections;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;

import com.github.ZXSkelobrine.neutral.variables.Client;

public class Sending {
	public static boolean send(String message, Socket socket) {
		return send(message.getBytes(), socket);
	}

	public static boolean send(byte[] message, Socket socket) {
		try {
			socket.getOutputStream().write(message);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean sendTerminationNotice(Socket socket) {
		return send("/ism/terminate/ism/", socket);
	}

	public static void broadcast(String message) {
		synchronized (Connections.getClients()) {
			for (Client client : Connections.getClients()) {
				send(message, client.getSocket());
			}
		}
	}

	public static String prepareSayBroadcast(String name, String message, Color colour) {
		return "/mes/" + name + " says " + message + "/mes/" + getColorName(colour) + "/mes/";
	}

	public static String prepareActBroadcast(String name, String message, Color colour) {
		return "/mes/" + name + " " + message + "/mes/" + getColorName(colour) + "/mes/";
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
