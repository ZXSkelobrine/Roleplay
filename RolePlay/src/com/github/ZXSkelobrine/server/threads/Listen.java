package com.github.ZXSkelobrine.server.threads;

import java.io.IOException;

import com.github.ZXSkelobrine.neutral.variables.Client;
import com.github.ZXSkelobrine.server.connections.Connections;
import com.github.ZXSkelobrine.server.revolve.Chief;

public class Listen {

	private static Thread listenThread;
	private static boolean running = false;
	private final static String THREAD = "Listener";

	public static void startListening() {
		Chief.logMessage(THREAD, "Prepping thread");
		prepThread();
		running = true;
		listenThread.start();
		Chief.logMessage(THREAD, "Thread started");
	}

	private static void prepThread() {
		listenThread = new Thread() {
			@Override
			public void run() {
				while (running) {
					synchronized (Connections.getClients()) {
						for (Client client : Connections.getClients()) {
							if (client.getSocket() != null) {
								try {
									if (client.getSocket().getInputStream() != null) {
										if (client.getSocket().getInputStream().available() > 0) {
											byte[] b = new byte[client.getSocket().getInputStream().available()];
											client.getSocket().getInputStream().read(b);
											Chief.logMessage(THREAD, "Message Recieved: " + new String(b) + "\tType: " + new String(b).substring(1, 4).substring(0, 1).toUpperCase() + new String(b).substring(1, 4).substring(1));
										}
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		};
	}

	public static void stopListening() {
		running = false;
		try {
			listenThread.join();
			Chief.logMessage(THREAD, "Thread stopped");
		} catch (InterruptedException e) {
			listenThread.interrupt();
			e.printStackTrace();
		}
	}
}
