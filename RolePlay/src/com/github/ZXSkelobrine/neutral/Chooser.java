/*
 * This project contains the WebLAF (Web Look And Feel) which is distributed under a GPLv3 licence. See GitHub for code.
 * 
 */
package com.github.ZXSkelobrine.neutral;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;
import com.github.ZXSkelobrine.client.windows.Main;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Chooser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chooser frame = new Chooser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Chooser() {
		WebLookAndFeel.install();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 215, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnServer = new JButton("Start A Server");
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnServer.setBounds(10, 11, 179, 23);
		contentPane.add(btnServer);

		JButton btnClient = new JButton("Launch The Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Main();
				dismiss();
			}
		});
		btnClient.setBounds(10, 129, 179, 23);
		contentPane.add(btnClient);

		JLabel lblPickAndOption = new JLabel("Pick and option to start.");
		lblPickAndOption.setHorizontalAlignment(SwingConstants.CENTER);
		lblPickAndOption.setBounds(0, 74, 199, 14);
		contentPane.add(lblPickAndOption);
	}

	public void dismiss() {
		dispose();
	}
}
