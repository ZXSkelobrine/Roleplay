package com.github.ZXSkelobrine.server.connections;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.github.ZXSkelobrine.neutral.variables.Client;
import com.github.ZXSkelobrine.server.revolve.Chief;

public class Connections {
	private static List<Client> clients = new ArrayList<Client>();

	public static synchronized List<Client> getClients() {
		return new CopyOnWriteArrayList<Client>(clients);
	}

	public static synchronized void addClient(Socket socket) {
		clients.add(new Client(socket));
		Chief.logMessage("Connections", "Client added");
	}

	public static void setClientName(String name, Client client) {
		client.setName(name);
		clients.set(clients.indexOf(client), client);
		Chief.logMessage("Connections", "Client name added");
	}

	public static Client getClientFromName(String name) {
		synchronized (getClients()) {
			for (Client client : getClients()) {
				if (name.toLowerCase().equals(client.getName().toLowerCase())) return client;
			}
		}
		return null;
	}

	public static void setClientDescription(String description, Client client) {
		client.setDescription(description);
		clients.set(clients.indexOf(client), client);
		Chief.logMessage("Connections", "Client description added");
	}

	public static void setClientColour(Color colour, Client client) {
		client.setColour(colour);
		clients.set(clients.indexOf(client), client);
		Chief.logMessage("Connections", "Client colour added");
	}

	public static synchronized void terminateSocket(Client client) {
		Sending.sendTerminationNotice(client.getSocket());
		try {
			client.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clients.remove(client);
		client.nullify();
		Chief.logMessage("Connections", "Client terminated");
	}
}
