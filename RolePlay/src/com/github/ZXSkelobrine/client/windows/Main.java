package com.github.ZXSkelobrine.client.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.github.ZXSkelobrine.client.connections.Connection;
import com.github.ZXSkelobrine.client.connections.Types;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMessage;

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 310, 114, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 100, 29, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JTextPane txtpnMessages = new JTextPane();
		GridBagConstraints gbc_txtpnMessages = new GridBagConstraints();
		gbc_txtpnMessages.gridheight = 2;
		gbc_txtpnMessages.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnMessages.fill = GridBagConstraints.BOTH;
		gbc_txtpnMessages.gridx = 0;
		gbc_txtpnMessages.gridy = 0;
		contentPane.add(txtpnMessages, gbc_txtpnMessages);

		JList<String> lstUsers = new JList<String>();
		GridBagConstraints gbc_lstUsers = new GridBagConstraints();
		gbc_lstUsers.gridheight = 2;
		gbc_lstUsers.insets = new Insets(0, 0, 5, 5);
		gbc_lstUsers.fill = GridBagConstraints.BOTH;
		gbc_lstUsers.gridx = 1;
		gbc_lstUsers.gridy = 0;
		contentPane.add(lstUsers, gbc_lstUsers);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Prompt();
			}
		});
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConnect.insets = new Insets(0, 0, 5, 0);
		gbc_btnConnect.gridx = 2;
		gbc_btnConnect.gridy = 0;
		contentPane.add(btnConnect, gbc_btnConnect);

		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection.disconnect();
			}
		});
		GridBagConstraints gbc_btnDisconnect = new GridBagConstraints();
		gbc_btnDisconnect.insets = new Insets(0, 0, 5, 0);
		gbc_btnDisconnect.gridx = 2;
		gbc_btnDisconnect.gridy = 1;

		contentPane.add(btnDisconnect, gbc_btnDisconnect);

		txtMessage = new JTextField();
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 5, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);

		JButton btnSend = new JButton("Say");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(Connection.send(Connection.prepareMessage(Types.Message_Say, txtMessage.getText())));
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSend.insets = new Insets(0, 0, 5, 5);
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 2;
		contentPane.add(btnSend, gbc_btnSend);

		JButton btnDetails = new JButton("Details");
		GridBagConstraints gbc_btnDetails = new GridBagConstraints();
		gbc_btnDetails.insets = new Insets(0, 0, 5, 0);
		gbc_btnDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDetails.gridx = 2;
		gbc_btnDetails.gridy = 2;
		contentPane.add(btnDetails, gbc_btnDetails);

		JButton btnDo = new JButton("Do");
		GridBagConstraints gbc_btnDo = new GridBagConstraints();
		gbc_btnDo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDo.insets = new Insets(0, 0, 0, 5);
		gbc_btnDo.gridx = 1;
		gbc_btnDo.gridy = 3;
		contentPane.add(btnDo, gbc_btnDo);
		initCompleted();
	}

	private void initCompleted() {
		setVisible(true);
	}

}
