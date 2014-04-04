package com.github.ZXSkelobrine.client.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;
import com.github.ZXSkelobrine.client.connections.Connection;
import com.github.ZXSkelobrine.client.connections.Types;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pswPassword;

	/**
	 * Create the frame.
	 */
	public Login() {
		WebLookAndFeel.install();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 252, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPleaseEnterYour = new JLabel("Please enter your details below");
		lblPleaseEnterYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterYour.setBounds(10, 11, 216, 14);
		contentPane.add(lblPleaseEnterYour);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(0, 36, 64, 14);
		contentPane.add(lblUsername);

		JLabel lblPasswor = new JLabel("Password");
		lblPasswor.setBounds(0, 83, 64, 14);
		contentPane.add(lblPasswor);

		txtUsername = new JTextField();
		txtUsername.setBounds(68, 33, 158, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		pswPassword = new JPasswordField();
		pswPassword.setBounds(68, 80, 158, 20);
		contentPane.add(pswPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = txtUsername.getText();
				String passWord = new String(pswPassword.getPassword());
				Connection.send(Connection.prepareMessage(Types.Login, userName + "/l/" + passWord));
				dismiss();
			}
		});
		btnLogin.setBounds(73, 111, 89, 23);
		contentPane.add(btnLogin);
		initCompleted();
	}

	private void initCompleted() {
		setVisible(true);
	}

	private void dismiss() {
		dispose();
	}
}
