package com.github.ZXSkelobrine.client.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;
import com.github.ZXSkelobrine.client.connections.Connection;
import com.github.ZXSkelobrine.client.connections.Types;
import com.github.ZXSkelobrine.client.connections.threads.Listen;
import com.github.ZXSkelobrine.neutral.Chooser;

public class Prompt extends JFrame {

	private static final long serialVersionUID = -8217745885908752069L;
	private JPanel contentPane;
	private JTextField txtIp;
	private JTextField txtPort;
	private JTextField txtName;
	private JTextArea txtrDescription;
	private JComboBox<String> cbxColour;

	/**
	 * Create the frame.
	 */
	public Prompt() {
		WebLookAndFeel.install();
		setTitle("Project Coloured Chest - Connect");
		setIconImage(Chooser.currentLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 312);
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
				Connection.setColour((String) cbxColour.getSelectedItem());
				Connection.connect(txtIp.getText(), Integer.parseInt(txtPort.getText()));
				Connection.send(Connection.prepareMessage(Types.Name, txtName.getText(), Connection.colour));
				Connection.send(Connection.prepareMessage(Types.Description, txtrDescription.getText(), Connection.colour));
				Connection.send(Connection.prepareMessage(Types.Colour, (String) cbxColour.getSelectedItem(), Connection.colour));
				Listen.startListening();
				dispose();
			}
		});
		btnConnect.setBounds(93, 240, 89, 23);
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

		JLabel lblCharacterColour = new JLabel("Character Colour:");
		lblCharacterColour.setBounds(10, 199, 119, 14);
		contentPane.add(lblCharacterColour);

		String[] colours = new String[] { "Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Light Gray", "Magenta", "Orange", "Pink", "Red", "White", "Yellow" };
		cbxColour = new JComboBox<String>();
		DefaultComboBoxModel<String> dcbxm = new DefaultComboBoxModel<>(colours);
		cbxColour.setModel(dcbxm);
		cbxColour.setBounds(126, 198, 139, 20);
		contentPane.add(cbxColour);
		initCompleted();
	}

	private void initCompleted() {
		setVisible(true);
	}
}
