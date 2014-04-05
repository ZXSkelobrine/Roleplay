package com.github.ZXSkelobrine.server.revolve;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;
import com.github.ZXSkelobrine.neutral.components.Switch;

public class Chief extends JFrame {

	private static final long serialVersionUID = -3780196426709350855L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Chief() {
		WebLookAndFeel.install();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Switch btnNewButton = new Switch();
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

		JList<String> list = new JList<String>();
		list.setBounds(10, 100, 414, 151);
		contentPane.add(list);
	}
}
