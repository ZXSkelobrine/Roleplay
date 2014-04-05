package com.github.ZXSkelobrine.client.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowActionListener implements ActionListener {
	private static WindowActionListener instance;

	public static WindowActionListener getInstance() {
		if (instance == null) instance = new WindowActionListener();
		return instance;
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("connect")) {

		}
	}

}
