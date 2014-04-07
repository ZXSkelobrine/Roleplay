package com.github.ZXSkelobrine.client.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.github.ZXSkelobrine.neutral.Chooser;

public class Details extends JFrame {

	private static final long serialVersionUID = -3789059441206463095L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtColour;

	/**
	 * Create the frame.
	 */
	public Details(String name, String description, String colour) {
		setTitle("Project Coloured Chest - Details");
		setIconImage(Chooser.currentLogo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 277, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDetails = new JLabel("Details");
		lblDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetails.setBounds(97, 22, 66, 14);
		contentPane.add(lblDetails);

		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(10, 66, 60, 14);
		contentPane.add(lblName);

		txtName = new JTextField(name);
		txtName.setEditable(false);
		txtName.setBounds(97, 63, 154, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 111, 81, 14);
		contentPane.add(lblDescription);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 106, 154, 96);
		contentPane.add(scrollPane);

		JTextArea txtrDescription = new JTextArea(description);
		txtrDescription.setLineWrap(true);
		txtrDescription.setEditable(false);
		scrollPane.setViewportView(txtrDescription);

		JLabel lblColour = new JLabel("Colour:");
		lblColour.setBounds(10, 244, 46, 14);
		contentPane.add(lblColour);

		txtColour = new JTextField(colour);
		txtColour.setEditable(false);
		txtColour.setBounds(97, 241, 154, 20);
		contentPane.add(txtColour);
		txtColour.setColumns(10);

		JButton btnNewButton = new JButton("Okay");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(86, 278, 89, 23);
		contentPane.add(btnNewButton);
		initCompleted();
	}

	private void initCompleted() {
		setVisible(true);
	}

}
