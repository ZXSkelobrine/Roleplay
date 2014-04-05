package com.github.ZXSkelobrine.server.threads;

import java.io.IOException;
import java.net.ServerSocket;

import com.github.ZXSkelobrine.server.connections.Connections;

public class Accept {

	static boolean running = false;
	static Thread accept;
	static ServerSocket serverSocket;

	public static void startAccepting(ServerSocket serverSocket) {
		prepThread();
		Accept.serverSocket = serverSocket;
		running = true;
		accept.start();
	}

	private static void prepThread() {
		accept = new Thread() {
			@Override
			public void run() {
				while (running) {
					try {
						Connections.addClient(serverSocket.accept());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

	public static void stopAccepting() {
		running = false;
		try {
			accept.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
