package com.github.ZXSkelobrine.client.connections;

import java.io.IOException;
import java.net.Socket;

public class Connection {
	public static Socket socket;
	public static String id = Identification.generateNewID();

	/**
	 * This attempts to connect to the given server.
	 * 
	 * @return whether the connection was made successfully.
	 */
	public static boolean connect(String host, int port) {
		try {
			socket = new Socket(host, port);
			return true;
		} catch (IOException e) {
			System.out.println("Error Locations:");
			System.out.println("Class: Connections");
			System.out.println("Method: connect(String host, int port)");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This attempts to disconnect the socket.
	 * 
	 * @return whether the connection was closed successfully.
	 */
	public static boolean disconnect() {
		try {
			socket.getOutputStream().write(("/cl/" + id + "/cl/close/cl/").getBytes());
			socket.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
