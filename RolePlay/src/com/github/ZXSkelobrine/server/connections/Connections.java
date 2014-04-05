package com.github.ZXSkelobrine.server.connections;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.github.ZXSkelobrine.neutral.variables.Client;

public class Connections {
	private static List<Client> clients = new ArrayList<Client>();

	public static synchronized List<Client> getClients() {
		return new CopyOnWriteArrayList<Client>(clients);
	}

	public static synchronized void addClient(Socket socket) {
		clients.add(new Client(socket));
	}

	public static void setClientName(String name, Client client) {
		client.setName(name);
		clients.set(clients.indexOf(client), client);
	}

	public static void setClientDescription(String description, Client client) {
		client.setDescription(description);
		clients.set(clients.indexOf(client), client);
	}

	public static void setClientColour(String colour, Client client) {
		client.setColour(colour);
		clients.set(clients.indexOf(client), client);
	}

	public static synchronized void terminateSocket(Socket socket) {
		Sending.sendTerminationNotice(socket);
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clients.remove(socket);
	}
}
