package com.github.ZXSkelobrine.client.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.github.ZXSkelobrine.client.connections.Connection;
import com.github.ZXSkelobrine.client.connections.Types;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Prompt extends JFrame {

	private static final long serialVersionUID = -8217745885908752069L;
	private JPanel contentPane;
	private JTextField txtIp;
	private JTextField txtPort;
	private JTextField txtName;
	private JTextArea txtrDescription;

	/**
	 * Create the frame.
	 */
	public Prompt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConnectToA = new JLabel("Connect To A Server");
		lblConnectToA.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnectToA.setBounds(78, 11, 119, 14);
		contentPane.add(lblConnectToA);

		JLabel lblIp = new JLabel("Ip:");
		lblIp.setBounds(10, 49, 106, 14);
		contentPane.add(lblIp);

		txtIp = new JTextField();
		txtIp.setBounds(126, 46, 139, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);

		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(10, 77, 106, 14);
		contentPane.add(lblPort);

		txtPort = new JTextField();
		txtPort.setBounds(126, 74, 139, 20);
		contentPane.add(txtPort);
		txtPort.setColumns(10);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection.connect(txtIp.getText(), Integer.parseInt(txtPort.getText()));
				Connection.send(Connection.prepareMessage(Types.Name, txtName.getText()));
				Connection.send(Connection.prepareMessage(Types.Description, txtrDescription.getText()));
				dispose();
			}
		});
		btnConnect.setBounds(96, 198, 89, 23);
		contentPane.add(btnConnect);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(126, 102, 139, 20);
		contentPane.add(txtName);

		JLabel lblCharacterName = new JLabel("Character Name:");
		lblCharacterName.setBounds(10, 105, 106, 14);
		contentPane.add(lblCharacterName);

		JLabel lblCharacterDescription = new JLabel("Character Description:");
		lblCharacterDescription.setBounds(10, 136, 119, 14);
		contentPane.add(lblCharacterDescription);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(126, 131, 139, 56);
		contentPane.add(scrollPane);

		txtrDescription = new JTextArea();
		scrollPane.setViewportView(txtrDescription);
		initCompleted();
	}

	private void initCompleted() {
		setVisible(true);
	}
}
