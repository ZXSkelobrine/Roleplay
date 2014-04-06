package com.github.ZXSkelobrine.server.revolve;

import java.io.IOException;
import java.net.ServerSocket;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;
import com.github.ZXSkelobrine.neutral.components.Switch;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Chief extends JFrame {

	private static final long serialVersionUID = -3780196426709350855L;
	private JPanel contentPane;
	public static ServerSocket serverSocket;
	private static JTextPane txtpnConsole;

	/**
	 * Create the frame.
	 */
	public Chief() {
		WebLookAndFeel.install();
		try {
			String path = "/images/logo.png";
			setTitle("Project Red Box - Server");
			setIconImage(ImageIO.read(Chief.class.getResource(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Switch btnNewButton = new Switch(true);
		btnNewButton.setBounds(188, 25, 57, 28);
		contentPane.add(btnNewButton);

		JLabel lblEnableServer = new JLabel("Server Status");
		lblEnableServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnableServer.setBounds(177, 6, 79, 14);
		contentPane.add(lblEnableServer);

		JLabel lblUsers = new JLabel("Users");
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setBounds(196, 75, 42, 14);
		contentPane.add(lblUsers);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 414, 77);
		contentPane.add(scrollPane);

		JList<String> list = new JList<String>();
		scrollPane.setViewportView(list);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 181, 414, 70);
		contentPane.add(scrollPane_1);

		txtpnConsole = new JTextPane();
		scrollPane_1.setViewportView(txtpnConsole);
		initFinished();
	}

	private void initFinished() {
		setVisible(true);
	}

	public static void logMessage(String thread, String message) {
		if (txtpnConsole != null) {
			if (txtpnConsole.getText() != null) {
				txtpnConsole.setText(txtpnConsole.getText() + thread + ": " + message + "\n");
			} else {
				txtpnConsole.setText(thread + ": " + message + "\n");
			}
		}
	}
}
