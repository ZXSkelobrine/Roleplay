package com.github.ZXSkelobrine.server.connections;

import java.io.IOException;
import java.net.Socket;

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
}
