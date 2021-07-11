package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class ClientGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblEntrada;
	private JTextField textInput;
	private JLabel lblOutput;
	private JTextArea textOutput;
	private JButton btnSend;

	Socket client;
	Scanner s;
	PrintStream out;

	public ClientGUI() throws Exception {
		Handler listener = new Handler();
		setTitle("CHAT_CLIENT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblEntrada = new JLabel("Input");
		lblEntrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblEntrada.setBounds(33, 235, 46, 22);
		contentPane.add(lblEntrada);

		textInput = new JTextField();
		textInput.setFont(new Font("Calibri", Font.PLAIN, 12));
		textInput.setBounds(33, 268, 227, 32);
		contentPane.add(textInput);

		lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblOutput.setBounds(33, 32, 57, 22);
		contentPane.add(lblOutput);

		textOutput = new JTextArea();
		textOutput.setFont(new Font("Calibri", Font.PLAIN, 12));
		textOutput.setBounds(33, 65, 320, 149);
		contentPane.add(textOutput);

		btnSend = new JButton("SEND");
		btnSend.setBounds(274, 277, 79, 23);
		btnSend.addActionListener(listener);
		contentPane.add(btnSend);

		setVisible(true);
		getConnection();
	}

	public void clear() {
		textInput.setText("");
	}

	public void getConnection() throws Exception {
		client = new Socket("127.0.0.1", 9000);
		System.out.println("Connection succeeded!!!");

		try {
			out.close();
			s.close();
			client.close();
		} catch (NullPointerException e) {

		}
	}

	public class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnSend) {
				String textE = textInput.getText();

				if ((textE.equals(""))) {
					JOptionPane.showMessageDialog(getContentPane(), "Required to fill all fields!!!", "WARNING!", 1);

				} else {

					textOutput.append("Client: " + textE + "\n");

					try {
						s = new Scanner(textE);
						out = new PrintStream(client.getOutputStream());

						while (s.hasNextLine()) {
							out.println(s.nextLine());
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					clear();
				}

			}

		}

	}
}