package gui;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Core.Clients;
import Core.Commands;
import connections.Client;
import connections.Message;
import connections.MessageBack;
import database.ChkDBandTab;
import java.awt.Label;
import java.awt.FlowLayout;

public class AppMain extends SL_JFrame  {
	
	private Client  		me ;
	
	private AppReader 		c;
	private AppLibrarian 	a;
	
	private JFrame 			frame;
	public 	JPanel 			ac = new JPanel();
	private JTextField 		text;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	/**
	 * Create the application.
	 */
	
	public AppMain() {
		initialize();		
		super.SL_Type = AppType.AppMain;	
		super.SL_Client		=null;
		addMsg("inizializzazione completata");	
	}

	public AppMain(Client x) {
		//me = x;
		//me.setActW(this);
		//me.setCliType(Clients.Guest);
		
		super.SL_Client 	= x;
		super.SL_Client.setActW(this);
		super.SL_Client.setCliType(Clients.Guest);
		super.SL_Type	=	AppType.AppMain;
		me=super.SL_Client;
		
		initialize();
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());

		getFrame().setTitle("Schoolib");
		getFrame().setBounds(100, 100, 890, 540);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelLog = new JPanel();
		getFrame().getContentPane().add(panelLog);
		panelLog.setLayout(null);
		
		JPanel Research = new JPanel();
		frame.getContentPane().add(Research, "name_1471890526861409");
		Research.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cognome Autore");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(21, 129, 151, 24);
		Research.add(lblNewLabel);
		
		JLabel lblNomeAutore = new JLabel("Nome Autore");
		lblNomeAutore.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNomeAutore.setBounds(21, 78, 113, 24);
		Research.add(lblNomeAutore);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(21, 184, 113, 24);
		Research.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Titolo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(21, 248, 113, 24);
		Research.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(163, 83, 270, 20);
		Research.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(163, 134, 270, 20);
		Research.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(163, 189, 270, 20);
		Research.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(163, 253, 270, 20);
		Research.add(textField_3);
		
	
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
			Research.setVisible(false);
			panelLog.setVisible(true);
			
			}
		});
		btnIndietro.setBounds(617, 445, 89, 23);
		Research.add(btnIndietro);
		
		JButton btnRicerca_1 = new JButton("Ricerca");
		btnRicerca_1.setBounds(259, 310, 89, 23);
		Research.add(btnRicerca_1);
		 
		
		ImageIcon backgroundImage0 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Background0.jpg")));
		ImageIcon backgroundImage1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Background1.jpg")));
		
		JButton btnNewButton = new JButton("check DB exist");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
		
				try {
					
					System.out.println("GUI :> comando inviato dalla gui main");//test tabelle iniziale
					me.setCliType(Clients.Librarian);				
					System.out.println("GUI :> sondo in CLI Busy prima : "+me.isBusy());	
					me.setBusy(true);
				
					
					
					// Person				
						try {
							// ChkDBandTab.tableExistPerson();
							me.getCmdLIST().put(Commands.tableExistPerson);	
						} catch (Exception e) {
							System.out.println("appMain :> problemi con accodamento comando check table exist PERSON");					
						}
						
						
					// Book				
						try {
							// ChkDBandTab.tableExistPerson();
							me.getCmdLIST().put(Commands.tableExistBook);							
						} catch (Exception e) {
							System.out.println("appMain :> problemi con accodamento comando check table exist BOOK");					
						}
					
					
					
					
					
					
					
					
					
					
				/*
				// Person				
					try {
						// ChkDBandTab.tableExistPerson();
						me.getCmdLIST().put(Commands.tableExistPerson);	
						
						
						//MessageBack back = me.Request(Commands.tableExistPerson); // me == Client associato alla GUI
						
						
						System.out.println("GUI :> risposta dal DB : "+back.getText());						
						if (back.getText()!=""){
							System.out.println("GUI :> PERSON : non ritorna nullo");
						}
						
						
					} catch (Exception e) {
						System.out.println("appMain :> problemi con accodamento comando check table exist PERSON");					
					}
					*/
					
				
				/*
				// book					
					try {						
						//ChkDBandTab.tableExistBook();
						MessageBack back = me.Request(Commands.tableExistBook); // me == Client associato alla GUI
						System.out.println("GUI :> risposta dal DB : "+back.getText());						
						if (back.getText()!=""){
							System.out.println("GUI :> BOOK : non ritorna nullo");	
						}
					} catch (Exception e) {
						System.out.println("appMain :> problemi con table exist book");
					}
				*/	
					
					
					
				/*	
				//  Loans	
					// ChkDBandTab.tableExistLoans();
					try {
						
						//ChkDBandTab.tableExistBook();
						MessageBack back = me.Request(Commands.tableExistLoans);    

						System.out.println("GUI :> risposta dal DB : "+back.getText());						
						if (back.getText()!=""){
							System.out.println("GUI :> LOANS : non ritorna nullo");

							System.out.println("GUI :> sondo in CLI Busy prima: "+me.isBusy());	
							me.setBusy(false);
							System.out.println("GUI :> sondo in CLI Busy dopo: "+me.isBusy());	
						}
					} catch (Exception e) {
						System.out.println("appMain :> problemi con table exist LOANS");
						me.setBusy(false);
					}
					*/
					
					
					
					
					
					
					
					
				
				me.setCliType(Clients.Default);	
					
					
					
				} catch (Exception e) {
					
					e.printStackTrace();	
				}	
			}
		});
		
		JButton btnNewButton_2 = new JButton("connection CLOSE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				me.closeconn();
				
			}
		});
		btnNewButton_2.setBounds(581, 380, 195, 23);
		panelLog.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("connection TEST");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				me.testconn();
				
			}
		});
		btnNewButton_1.setBounds(581, 346, 195, 23);
		panelLog.add(btnNewButton_1);
		btnNewButton.setBounds(31, 63, 167, 23);
		panelLog.add(btnNewButton);
		
		setText(new JTextField());
		getText().setBounds(208, 54, 628, 41);
		panelLog.add(getText());
		getText().setColumns(10);
		
		
		
		JButton btnAdmin = new JButton("Admin");
		
		btnAdmin.addMouseListener(new MouseAdapter() {
			 @Override
				public void mousePressed(MouseEvent arg0) {
					
				 
				 		EventQueue.invokeLater(new Runnable() {
							public void run() 
							{
								
							 try 
							{
							 AppLibrarian ak = new AppLibrarian(getFrame());
						    } 
							catch (Exception e) 
							{
							e.printStackTrace();
							}
								
						}	
			
					 });    
				}
		
		 });
		btnAdmin.setBounds(581, 227, 107, 23);
		panelLog.add(btnAdmin);
		
		
		JButton btnRegistrazione = new JButton("Registrazione");
		btnRegistrazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRegistrazione.addMouseListener(new MouseAdapter() {
		 @Override
			public void mousePressed(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
						public void run() 
						{
						 try 
						{
						 AppReader al = new AppReader(getFrame(),me);
					    } 
						catch (Exception e) 
						{
						e.printStackTrace();
						}
					
					}	
		
				 });    
			}
	 });
		
		btnRegistrazione.setBounds(124, 113, 154, 23);
		panelLog.add(btnRegistrazione);
								
		JButton btnEsci = new JButton("Esci");
		btnEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnEsci.setBounds(212, 400, 129, 23);
		panelLog.add(btnEsci);
		
		JButton btnRicerca = new JButton("Ricerca");
		btnRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelLog.setVisible(false);
				Research.setVisible(true);
				
			}
			
			
		});
		
		btnRicerca.setBounds(279, 11, 89, 23);
		panelLog.add(btnRicerca);
		
		
		
		
		JLabel lblBackgound1 = new JLabel();
		lblBackgound1.setBounds(0, 0, 884, 511);
		lblBackgound1.setIcon(backgroundImage1);
		lblBackgound1.setBorder(null);
		panelLog.add(lblBackgound1);
		
		
		
		
		
	}
	public JTextField getText() {
		return text;
	}
	public void setText(JTextField text) {
		this.text = text;
	}
	
	/**
	 * Launch the application.
	 */
	
	@Override
	public void addMsg(String msg){
		text.setText(msg);	
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				if (me!=null){
				me.closeconn();
				}
			}
		});
	}

	
	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Client x = new Client();
					
					AppMain window = new AppMain(x);					
					window.frame.setVisible(true);					
					
					new Thread(x).start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
