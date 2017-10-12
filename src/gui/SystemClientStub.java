package gui;
import connections.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Core.Clients;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.Random;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;


public class SystemClientStub extends SL_JFrame {
	private boolean stubok			=false;
	private boolean repeatconn		=true;
	private int 	repeatconnCount = 1;
	private Client  me ;
	
	
	// GRAFICA	
	private JFrame frame;
	private JTextArea text;
	private JTextField textField;
	
	
	public SystemClientStub(Client mme) throws Exception {
		me=mme;
		initialize();
	}

	
	
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 456, 486);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JButton btnClientDefault = new JButton("Client Default");
		btnClientDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {	
					me.setCliType(Clients.Default);
				} catch (Exception e1) {					
					e1.printStackTrace();

				}
				}
		});
		btnClientDefault.setBounds(230, 334, 179, 23);
		frame.getContentPane().add(btnClientDefault);
		
		JButton btnClientReader = new JButton("Client Reader");
		btnClientReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try{
				me.setCliType(Clients.Reader);
			} catch (Exception e2) {					
				e2.printStackTrace();

			}
			}
		});
		btnClientReader.setBounds(230, 368, 179, 23);
		frame.getContentPane().add(btnClientReader);
		
		JTextArea textArea = new JTextArea();
		text=textArea;
		textArea.setBounds(27, 104, 382, 206);
		textArea.setFocusTraversalPolicyProvider(true);
		textArea.setFocusCycleRoot(true);
		textArea.setDropMode(DropMode.INSERT);
		textArea.setDragEnabled(true);
		textArea.setDoubleBuffered(true);
		textArea.setColumns(2);
		textArea.setBackground(Color.GREEN);
		textArea.setAutoscrolls(true);
		getFrame().getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Finestra Terminale");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(147, 83, 283, 14);
		getFrame().getContentPane().add(lblNewLabel);
		
		JLabel lblSchoollibFinestraControllo = new JLabel("Client Stub");
		lblSchoollibFinestraControllo.setForeground(Color.BLUE);
		lblSchoollibFinestraControllo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchoollibFinestraControllo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblSchoollibFinestraControllo.setBounds(120, 31, 310, 51);
		getFrame().getContentPane().add(lblSchoollibFinestraControllo);	

		
		
/*		
		JButton btnNewButton = new JButton("metodo 1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
										try {
					me.metodo1();
										} 	catch (ClassNotFoundException | IOException e) {
											e.printStackTrace();
											}
			}
		});
		btnNewButton.setBounds(27, 334, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("metodo 2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
										try {
					me.metodo2();
										} 	catch (ClassNotFoundException | IOException e) {
											e.printStackTrace();
											}
			}
		});
		btnNewButton_1.setBounds(27, 368, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("metodo 3");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
									try {
					me.metodo3();
									} 	catch (ClassNotFoundException | IOException e) {
										e.printStackTrace();
										}			
			}
		});

		
		btnNewButton_2.setBounds(27, 402, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
*/		
		JLabel lblNewLabel_1 = new JLabel("richiedi stringhe");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(126, 338, 112, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("connetti al DB e crea Tab UTENTI");
		lblNewLabel_2.setBounds(27, 433, 283, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnClose = new JButton("Close Connection");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				me.closeconn();
				
			}
		});
		btnClose.setBounds(269, 11, 161, 23);
		frame.getContentPane().add(btnClose);
		
		JButton btnClientLibrarian = new JButton("Client Librarian");
		btnClientLibrarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			try {	
				
				
				
			
				me.setCliType(Clients.Librarian);
				
				
			} catch (Exception e) {				
				e.printStackTrace();
			}	
			}
		});
		btnClientLibrarian.setBounds(230, 402, 179, 23);
		frame.getContentPane().add(btnClientLibrarian);
		
		textField = new JTextField();//type
		textField.setBounds(27, 12, 195, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
	//-----------------------------------------------------------------------
	public static void main(String[] args)throws Exception {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
										try {
					SystemClientStub window = new SystemClientStub(new Client ());
					window.getFrame().setVisible(true);
										} 	catch (Exception e) {
											e.printStackTrace();
											System.out.println("GUI error");
											}
			}				//run
		});					//runnable
	}						// main			
	//-----------------------------------------------------------------------

	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				me.closeconn();
				
			}
		});
	}
	public void addMsg(String msg){		
		text.setText(msg);	
	}
	public void addMsgType(String msg){		
		textField.setText(msg);	
	}
}
