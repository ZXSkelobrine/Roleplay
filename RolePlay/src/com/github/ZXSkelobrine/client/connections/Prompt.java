package com.github.ZXSkelobrine.client.connections;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Prompt extends JFrame {

	private static final long serialVersionUID = -8217745885908752069L;
	private JPanel contentPane;
	private JTextField txtIp;
	private JTextField txtPort;

	/**
	 * Create the frame.
	 */
	public Prompt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 184);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConnectToA = new JLabel("Connect To A Server");
		lblConnectToA.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnectToA.setBounds(78, 11, 119, 14);
		contentPane.add(lblConnectToA);

		JLabel lblIp = new JLabel("Ip:");
		lblIp.setBounds(10, 49, 46, 14);
		contentPane.add(lblIp);

		txtIp = new JTextField();
		txtIp.setBounds(55, 46, 210, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);

		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(10, 90, 46, 14);
		contentPane.add(lblPort);

		txtPort = new JTextField();
		txtPort.setBounds(55, 87, 210, 20);
		contentPane.add(txtPort);
		txtPort.setColumns(10);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection.connect(txtIp.getText(), Integer.parseInt(txtPort.getText()));
				dispose();
			}
		});
		btnConnect.setBounds(93, 118, 89, 23);
		contentPane.add(btnConnect);
		initCompleted();
	}

	private void initCompleted() {
		setVisible(true);
	}
}
