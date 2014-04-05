package com.github.ZXSkelobrine.neutral.variables;

import java.awt.Color;
import java.net.Socket;

public class Client {
	private Socket socket;
	private String name;
	private String description;
	private Color colour;

	public Client(Socket socket) {
		this.socket = socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setColour(String colour) {
		try {
			this.colour = (Color) Class.forName("java.awt.Color").getField(colour.toLowerCase()).get(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public Socket getSocket() {
		return socket;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Color getColour() {
		return colour;
	}
}
