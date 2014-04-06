package com.github.ZXSkelobrine.client.windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.github.ZXSkelobrine.client.connections.Connection;
import com.github.ZXSkelobrine.client.connections.Types;
import com.github.ZXSkelobrine.neutral.Chooser;

public class PrivateMessage extends JFrame {

	private static final long serialVersionUID = -3237453120064080184L;
	private JPanel contentPane;
	private JTextField txtMessage;
	private static JTextPane txtpnMessages;
	private static String user;
	public static boolean opened = false;

	/**
	 * Create the frame.
	 */
	public PrivateMessage(String user) {
		PrivateMessage.user = user;
		setResizable(false);
		setTitle("Project Coloured Box - PM");
		setIconImage(Chooser.currentLogo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 289, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtpnMessages = new JTextPane();
		txtpnMessages.setBounds(10, 11, 262, 268);
		contentPane.add(txtpnMessages);

		txtMessage = new JTextField();
		txtMessage.setBounds(10, 290, 176, 20);
		contentPane.add(txtMessage);
		txtMessage.setColumns(10);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtMessage != null && txtMessage.getText() != null && !txtMessage.getText().equals("")) {
					Connection.send(Connection.prepareMessage(Types.Private_Message, PrivateMessage.user + "/prm/" + txtMessage.getText(), Connection.colour));
				}
			}
		});
		btnSend.setBounds(193, 289, 79, 23);
		contentPane.add(btnSend);

		JButton btnClose = new JButton("Close");
		btnClose.setBounds(96, 319, 89, 23);
		contentPane.add(btnClose);
		initCompleted();
	}

	private void initCompleted() {
		setVisible(true);
	}

	public static boolean appendMessage(String name, String message, Color colour) {
		try {
			StyledDocument doc = txtpnMessages.getStyledDocument();
			Style style = doc.addStyle("Coloured", null);
			StyleConstants.setForeground(style, colour);
			try {
				doc.insertString(doc.getLength(), name + ": " + message + "\n", style);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
