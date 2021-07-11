package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
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

public class ServerGUI extends JFrame {
	private JPanel contentPane;
	private JLabel lblInput;
	private JTextField textInput;
	private JLabel lblOutput;
	private JTextArea textOutput;
	private JButton btnSend;

	public ServerGUI() throws Exception {
		Handler listener = new Handler();
		setTitle("CHAT_SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblInput = new JLabel("Input");
		lblInput.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblInput.setBounds(33, 235, 46, 22);
		contentPane.add(lblInput);
		
		textInput = new JTextField();
		textInput.setBounds(33, 268, 227, 32);
		contentPane.add(textInput);
		
		lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblOutput.setBounds(33, 32, 57, 22);
		contentPane.add(lblOutput);
		
		textOutput = new JTextArea();
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
		ServerSocket server = new ServerSocket(9000);
		System.out.println("Door 9000 is open, waiting for client connection...");
		
		Socket client = server.accept();
		System.out.println("Connection established "+client.getInetAddress().getHostAddress());
		
		Scanner s = new Scanner(client.getInputStream());
		while(s.hasNextLine()) {
			textOutput.append("Client: " + s.nextLine() + "\n");
		}
		
		s.close();
		client.close();
		server.close();
	}
	
	public class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource()==btnSend) {

				String textE = textInput.getText();
				
				if(   (textE.equals(""))  ) {
					JOptionPane.showMessageDialog(getContentPane(), "Required to fill all fields!!!", "WARNING!",  1);
				}
				
				else {
					
					textOutput.append("Server: " + textE + "\n");
					
					clear();
				}
			}

		}
	}

}
