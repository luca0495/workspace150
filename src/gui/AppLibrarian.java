package gui;


import java.awt.CardLayout;
import java.awt.Component;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AppLibrarian extends JFrame  {

	private JFrame frmSchoolib;	
	private AppReader a;
	private JTextField textField;

	
	public AppLibrarian(Component c)
	{
		AppLibrarian(c);
	}


	public void AppLibrarian(Component c) {

		frmSchoolib = new JFrame();
		frmSchoolib.setTitle("Schoolib");
		frmSchoolib.setBounds(100, 100, 893, 545);
		frmSchoolib.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSchoolib.setLocationRelativeTo(c);
		frmSchoolib.setVisible(true);
		frmSchoolib.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelSelection1 = new JPanel();
		frmSchoolib.getContentPane().add(panelSelection1, "name_353237010061838");
		panelSelection1.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 877, 21);
		panelSelection1.add(menuBar);
		
		JButton btnAssistanceRead = new JButton("AssistereReader");
		menuBar.add(btnAssistanceRead);
		
		JButton btnDelete = new JButton("Cancella Profilo");
		menuBar.add(btnDelete);
		
		textField = new JTextField();
		textField.setColumns(10);
		menuBar.add(textField);
		
		JButton btnModify = new JButton("Modifica Profilo");
		menuBar.add(btnModify);
		
		JButton btnSaveBib = new JButton("Registrazione Bibliotecario");
		btnSaveBib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(btnSaveBib);
	
		
		
	}
}
