package gui;
import java.io.Serializable;
import java.lang.annotation.Documented;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Core.Clients;
import connections.Client;

/**
Class from which extend SchoolLib's JFrames : Main AppLibrarian AppReader
@author DLP Team
@since 2.4.2017 
*/
public class SL_JFrame extends JFrame implements Serializable  {		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	School Lib JFrame	
	
	AppType					SL_Type;	
	Client					SL_Client;
	SL_JFrame  				SL_JF;
	
	public 	JTextField		text;
	public 	JPanel 			ac 			= new JPanel();
	
	/**
	 * Each SL_JFrame receives a Client rif.x
	 * @param x
	 * update in Client x ActW [ActiveWindows]  
	 */
	public SL_JFrame (){		
	}
	
	
	public void addMsg(String msg){
		text.setText(msg);	
	}

	// SET GET Metod
	public void ClientAggiornaActW(){			
		getSL_Client().setActW((SL_JFrame)SL_JF);
	}
	
	public SL_JFrame getSL_JF() {
	return this;
	}	
	public void setSL_JF(SL_JFrame sLJF) {
		SL_JF = sLJF;
	}
	public Client getSL_Client() {
		return SL_Client;
	}
	public void setSL_Client(Client sLClient) {
		SL_Client = sLClient;
	}


	public AppType getSL_Type() {
		return SL_Type;
	}


	public void setSL_Type(AppType sL_Type) {
		SL_Type = sL_Type;
	}
}
