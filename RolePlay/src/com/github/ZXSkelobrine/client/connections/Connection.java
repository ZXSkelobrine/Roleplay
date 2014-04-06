package com.github.ZXSkelobrine.client.connections;

import java.io.IOException;
import java.net.Socket;

public class Connection {
	public static Socket socket;
	public static String id = Identification.generateNewID();
	public static String colour;

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
	 * Sets the sockets colour.
	 * 
	 * @param colour
	 *            - The colour to set to
	 */
	public static void setColour(String colour) {
		Connection.colour = colour;
	}

	/**
	 * This attempts to disconnect the socket.
	 * 
	 * @return whether the connection was closed successfully.
	 */
	public static boolean disconnect() {
		try {
			socket.getOutputStream().write(("/clo/" + id + "/clo/close/clo/").getBytes());
			socket.close();
			return true;
		} catch (IOException e) {
			System.out.println("Error Locations:");
			System.out.println("Class: Connections");
			System.out.println("Method: disconnect()");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method just calls {@link #send(byte[])} after converting to a byte
	 * array and returns the result of the {@link #send(byte[])} method
	 * 
	 * @param message
	 *            - The message to send as a string.
	 * @return Whether the message was sent.
	 */
	public static boolean send(String message) {
		return send(message.getBytes());
	}

	/**
	 * This send the given message through the client after checking if it is
	 * open with the {@link #clientConnected()} method.
	 * 
	 * @param message
	 *            - The message to send the server in a byte array.
	 * @return whether the message was sent
	 */
	public static boolean send(byte[] message) {
		try {
			if (clientConnected()) {
				socket.getOutputStream().write(message);
				socket.getOutputStream().flush();
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			System.out.println("Error Locations:");
			System.out.println("Class: Connections");
			System.out.println("Method: send(byte[] message)");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This check if the socket is open by testing these things:
	 * <ul>
	 * <li>Is the socket null?</li>
	 * <li>Is the output stream null?</li>
	 * <li>Is the input stream null?</li>
	 * </ul>
	 * 
	 * @return Whether the socket is open
	 * @throws IOException
	 */
	private static boolean clientConnected() throws IOException {
		return socket != null && socket.getOutputStream() != null && socket.getInputStream() != null;
	}

	public static String prepareMessage(Types type, String message, String colour) {
		switch (type) {
		case Details:
			return "/det/" + id + "/det/" + message + "/det/" + colour + "/det/";
		case Login:
			return "/log/" + id + "/log/" + message + "/log/" + colour + "/log/";
		case Message:
			return "/mes/" + id + "/mes/" + message + "/mes/" + colour + "/mes/";
		case User:
			return "/usr/" + id + "/usr/" + message + "/usr/" + colour + "/usr/";
		case Message_Do:
			return "/act/" + id + "/act/" + message + "/act/" + colour + "/act/";
		case Message_Say:
			return "/say/" + id + "/say/" + message + "/say/" + colour + "/say/";
		case Description:
			return "/des/" + id + "/des/" + message + "/des/" + colour + "/des/";
		case Name:
			return "/nam/" + id + "/nam/" + message + "/nam/" + colour + "/nam/";
		case Colour:
			return "/col/" + id + "/col/" + message + "/col/" + colour + "/col/";
		default:
			return "";
		}
	}

}
