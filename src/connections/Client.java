package connections;
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
import Core.Commands;
import Core.CommandsList;
import Core.CommandsType;
import Core.Requests;
import Core.RequestsList;
import Core.SearchFor;

import java.awt.Font;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

import gui.AppLibrarian;
import gui.AppReader;
import gui.AppMain;
import gui.SL_JFrame;
import gui.SystemClientStub;

public class Client implements Serializable, Runnable  {
	public 				int 				ctc=0;
	
	private 			SL_JFrame			ActW			=null;	//Active Window
	private 			Clients				ClientType;
	private 			ServerStub			srv;		
	private 			boolean 			stubok			=false;
	private 			boolean 			repeatconn		=true;
	private 			int 				repeatconnCount = 1;	
	static private 		int 				conn;	
	private 			String 				tc  ; 
	private 			Message				mess;
	private 			MessageBack			mSgBack;
	private 			String				mSg;
	
	private				CommandsList		CmdLIST			=new CommandsList();
	private 			boolean				ClientON=true;
	
	private 			Message				mSgTOsend;
	
	private				boolean				Busy=false;	//SE ARRIVA RICHIESTA INVIO COMANDO BUSY == TRUE
	private				boolean				BusyControl=false;	//SE ARRIVA RICHIESTA INVIO COMANDO BUSY == TRUE
	
	
	public Client() throws Exception {		
		this.setCliType(Clients.Default);
		System.out.println("Creato 		 Client");
		System.out.println("CType:		"+getCliType());	
	}
//------------------------------------------------------------------------------------------
	public static void main(String[] args) throws Exception {			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client x  = new Client();
					ClientConnectionController y1 = new ClientConnectionController(x,100);//1 CTLL OGNI 10 SEC					

					AppMain StartWindow = new AppMain(x);
					StartWindow.getFrame().setVisible(true);							
					System.out.println("creato start windows");
					StartWindow.addMsg("test");	
					new Thread(x).start();	//parte > Logica
					new Thread(y1).start();  //connection controller

						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}	
//------------------------------------------------------------------------------------------
	@Override
	public void run() {	
		try {
			while (isClientON()){
					//Attesa comandi...
					
					while ( getCmdLIST().getCmdList().size() < 1){			
					
							System.out.println("CLI :> cmdLIST.size : "+getCmdLIST().getCmdList().size());							
							System.out.println("CLI :> IN ATTESA di COMANDI... ");
						try {
							System.out.println("CLI :> IN ATTESA di COMANDI... dormo100");
							Thread.sleep(100);
						} catch (Exception e) { e.printStackTrace();
						}
					}
							System.out.println("CLI :> cmdLIST.size : "+getCmdLIST().getCmdList().size());							
							System.out.println("CLI :> Comandi in LISTA ");
					
					//Comando Arrivato					
					//Comando Prelevo dalla lista CmdLIST
					Commands com = getCmdLIST().take();
							System.out.println("CLI :> Trovata richiesta in coda "+ com.Den );						
					
							
					/*		
					while (Busy){
							System.out.println("CLI :> Trovata richiesta in coda "+ com.Den + " [CLI  BUSY], attendo... ");
						try {
							Thread.sleep(10);
						} catch (Exception e) { e.printStackTrace();
						}						
					}
					*/
// !!!! --------------------------
					setBusy(true);
// !!!! --------------------------					
					System.out.println("CLI :> Trovata richiesta in coda "+ com.Den + " [CLI !BUSY] < ORA ESEGUO > ");						
					switch (com.getCommandType()) {				//   		Core\CommandsType	***   									
				//	****************************************************	Connections	   		***		
					case CONNECTION:										
								switch (com) {							
								case ConnSTOP: 					//   		Core\Commands		***
								
									break;
								case ConnTEST: 
												ClientConnectionTest();
												setBusy(false);
									break;
								}
					break;
				//	****************************************************	Test		   		***	
					case TEST:
								switch (com) {							
								case DBExist:							
									
									break;
								default:
									
									break;
								}
					break;
				//	****************************************************	Read		   		***			
					case READ:
								switch (com) {							
						//	book
								case BookREAD:							
								
									break;
						//	Person
								case UserREAD: 
											
									break;
						//	Loans
								case PrenotationREAD: 
									
									break;
								default:
									
									break;
								}				
					break;
				//	****************************************************	Change		   		***			
					case CHANGE:
								switch (com) {							
	
								
							case tableExistBook: 
													ClientCheckExistTableBook();
								break;
							
							
							case tableExistLoans: 
								
								break;
							
							
							case tableExistPerson: 
													ClientCheckExistTablePerson();
								break;
					
								
						//	book			
								case BookUPDATE:							
								
									break;
								case BookDELETE: 
									
									break;
						//	Person
								case UserUPDATE: 
											
									break;
								case UserDELETE: 
											
									break;	
								
						//	Loans
								case Prenotation: 
									
									break;
								case BookGet: 
									
									break;
								case BookGiveback: 
									
									break;
								default:
									
									break;
								}
					break;
				//	****************************************************	Default		   		***			
					default:
						
					break;
				}									

					
				
				
				
				
				
				
			}//Client settato off		
			//Logica();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	//------------------------------------------------------------------------------------------


	void Logica() throws Exception {	
		 		int ctc=0;						//---- Connection test counter				
		 		while (isRepeatconn()) {		//---- Controllo continuo della connessione con il Server		
									
		 							
		 							while (Busy){
		 								System.out.println("CLI :> Logica > attesa ritorno CMD , skippo controllo connessione");
		 								try {
											System.out.println("CLI :> Logica > dormo 1000");	
		 									Thread.sleep(1000);
										} catch (Exception e) { e.printStackTrace();
										}	
		 							}
		 							setBusyControl(true);		 							
		 								try{									
										System.out.println(mSg = "CLI :> CType:"	+ getCliType());
										//System.err.println(mSg = "ActW:"	+ getActW());						
										//getActW().addMsg(mSg);											
														if (isStubok())  {	
															try {
															//	if ((mSg = srv.testConnectionSS().getText()).equals(new String ("SRV - Connessione OK"))){																	
															//  se impegnato in query non esegue conn test																
																
																this.ctc++;																					
																System.out.println("CLIENT :> ciclo di controllo "+ctc);																																							
																				try {											
																					this.mSgBack = 	Request(Commands.ConnTEST);																																							
																					this.mSg 	= 	mSgBack.getText();														
																				} catch (Exception e) {				
																					e.printStackTrace();
																					System.out.println("CLI:> Logica > messaggio non letto");
																				}
																				
																			
																			//if (mSg.equals(new String ("SRV - Connessione OK"))){	
																			if (mSgBack.getText().equals(new String ("SRV - Connessione OK"))){									
																					getActW().addMsg(new String ("CLI:> Connection Test "+ctc+" result"+mSg));	
																		}else{
																			setStubok(false);
																					getActW().addMsg(new String ("CLI:> Connection Test result : NG"));	
																		}			
															} catch (Exception e) {							
																e.printStackTrace();
																System.out.println(mSg = "CLI :> srv stub ERROR ");	
																getActW().addMsg(mSg);		
																setStubok(false);
															}				
															Thread.sleep(1000);// test conn every 0.1 sec
														}
														else {															
															for (int i = 5;i>0;i--){
																try {Thread.sleep(1000);
																System.out.println(mSg = "CLI :> Verifica Stub NG \nTentativo "+getRepeatconnCount()+ " \nnuovo tentativo tra "+i+" secondi ");
																getActW().addMsg(mSg);
																} catch (Exception e) {
																}	
															}
															incRepeatconnCount();	
															srv  = new ServerStub(this);	
															}
										}catch (Exception e) {e.printStackTrace();} finally {}//server.closeConnection();}	
		 								setBusyControl(false);
		 								
					}	//while					
			}		//Logica
//------------------------------------------------------------------------
		public MessageBack Request(Commands cmd)throws IOException{						

			MessageBack Mb = new MessageBack();	
			System.out.println("CLI :> Request ricevuto da GUI :> "+cmd.toString());
			if (!stubok){
				Mb.setText(mSg = "CLI :>  nessuna connessione attiva , riprovare ");
				getActW().addMsg(new String ("Connection Test result"+mSg));
			}else{	
				System.out.println("CLI :> Stub OK");
	// **** Client crea Message	
			Message MsgSend = new Message(	
			cmd,						// Comando richiesto
			this.getCliType() ,			// tipo di Client , Admin,Librarian,Reader
			this.toString()				// id Client 
										);		
	// ****	
			try {
					System.out.println("CLI :> spedisco a stub comando: "+MsgSend.getCmd());	
					Mb = this.getSrv().SendRequest(MsgSend);	// SPEDISCE AL SRV [STUB] MESSAGE contenente COMMAND	
			} 
			catch (IOException io){	
				io.printStackTrace();
				Mb.setText("CLI:>  collegamento mancante");						
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				Mb.setText("CLI:>  class not found");	
			}
		}
		
			//************************************************************	
			// RITORNA UN MESSAGE-BACK
			return Mb;			
		//************************************************************
		}	
		
//------------------------------------------------------------------------		 
	// sys

		public Clients getCliType() {
			return ClientType;
		}
		public void setCliType(Clients cliType) {
			ClientType = cliType;
		}		
		boolean isStubok() {
		return stubok;
	}
		public void setStubok(boolean stubok) {
		this.stubok = stubok;
	}
		public boolean isRepeatconn() {
		return repeatconn;
	}
		public void setRepeatconn(boolean repeatconn) {
		this.repeatconn = repeatconn;
	}
		public int getRepeatconnCount() {
		return repeatconnCount;
	}
		public void setRepeatconnCount(int repeatconnCount) {
		this.repeatconnCount = repeatconnCount;
	}
		public void incRepeatconnCount(){
		this.repeatconnCount++;
	}
		public ServerStub getSrv() {
		return srv;
	}
		public void setSrv(ServerStub srv) {
		this.srv = srv;
	}	
		
	// comandi
		
	/*
	public String testconn (){
		String testcon1=null;
		testcon1 = srv.testConnectionSS().getText();
		return testcon1;
	}
	*/
		
	public MessageBack testconn(){
		//testcon1 = srv.testConnectionSS().getText();
		try {
			return this.mSgBack = 	Request(Commands.ConnTEST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.mSgBack;
		
	}
	
	public MessageBack closeconn(){	
		//srv.closeConnectionSS();
		try {
			return this.mSgBack = 	Request(Commands.ConnSTOP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.mSgBack;
	}	
	
	
	
	
	/*
	
	// test
	public void metodo1 () throws IOException, ClassNotFoundException{		
			for (int i = 0 ; i< 10; i++){	
				System.out.print("CLIENT : cmd no " + i + " Richiedo test 1");				
			
			String M1 = this.getSrv().metodotest1().getText();
				getActW().addMsg(M1);
				System.out.println("CLIENT : Elemento ritornato: " + M1);	
			}
	}
	public void metodo2 () throws IOException, ClassNotFoundException{		
		
			System.out.print("CLIENT : cmd no  Richiedo test 2");				
			
			String M1 = this.getSrv().metodotest2().getText();
				getActW().addMsg(M1);
				System.out.println("CLIENT : Elemento ritornato: " + M1);	
		
	}	
	public void metodo3 () throws IOException, ClassNotFoundException{		
		
			System.out.print("CLIENT : cmd no  Richiedo test 3");				
			
			String M1 = this.getSrv().metodotest3().getText();
				getActW().addMsg(M1);
				System.out.println("CLIENT : Elemento ritornato: " + M1);	
	
	}
*/

	
/*	
	public SystemClientStub getMeG_Sys() {
		return meG_Sys;
	}
	public void setMeG_Sys(SystemClientStub meG_Sys) {
		this.meG_Sys = meG_Sys;
	}
	public Main getMeG_Main() {
		return meG_Main;
	}
	public void setMeG_Main(Main meG_Main) {
		this.meG_Main = meG_Main;
	}
	public AppLibrarian getMeG_Lib() {
		return meG_Lib;
	}
	public void setMeG_Lib(AppLibrarian meG_Lib) {
		this.meG_Lib = meG_Lib;
	}
	public AppReader getMeG_Rd() {
		return meG_Rd;
	}
	public void setMeG_Rd(AppReader meG_Rd) {
		this.meG_Rd = meG_Rd;
	}
	
*/	
	public SL_JFrame getActW() {
		return ActW;
	}
	public void setActW(SL_JFrame actW) {
		ActW = actW;
	}
	public boolean isBusy() {
		return Busy;
	}
	public void setBusy(boolean busy) {
		this.Busy = busy;
	}
	public boolean isBusyControl() {
		return BusyControl;
	}
	public void setBusyControl(boolean busyControl) {
		BusyControl = busyControl;
	}
	public CommandsList getCmdLIST() {
		return CmdLIST;
	}
	public void setCmdLIST(CommandsList cmdLIST) {
		CmdLIST = cmdLIST;
	}
	public boolean isClientON() {
		return ClientON;
	}
	public void setClientON(boolean clientON) {
		ClientON = clientON;
	}


	void ClientConnectionTest() throws Exception {	
 								try{									
								System.out.println(mSg = "CLI :> CType:"	+ getCliType());										
												if (isStubok())  {	
													try {	
														ctc++;																					
														System.out.println("CLIENT :> ciclo di controllo "+ctc);																																							
																		try {											
																			this.mSgBack = 	Request(Commands.ConnTEST);																																							
																			this.mSg 	= 	mSgBack.getText();														
																			
																			
																		} catch (Exception e) {				
																			e.printStackTrace();
																			System.out.println("CLI:> Logica > messaggio non letto");
																		}
																		
																		if (mSgBack.getText().equals(new String ("SRV - Connessione OK"))){									
																			getActW().addMsg(new String ("CLI:> Connection Test "+ctc+" result"+mSg));	
																		}else{
																			setStubok(false);
																			getActW().addMsg(new String ("CLI:> Connection Test result : NG"));	
																		}
																		
													} catch (Exception e) {							
														e.printStackTrace();
														System.out.println(mSg = "CLI :> srv stub ERROR ");	
														getActW().addMsg(mSg);		
														setStubok(false);
													}				
													//Thread.sleep(1000);// test conn every 0.1 sec
												}
												else {// !isStubok															
													//conteggio...
														for (int i = 5;i>0;i--){
															try {Thread.sleep(1000);
															System.out.println(mSg = "CLI :> Verifica Stub NG \nTentativo "+getRepeatconnCount()+ " \nnuovo tentativo tra "+i+" secondi ");
															getActW().addMsg(mSg);
															} catch (Exception e) {
															}	
														}
													//conteggio...
													incRepeatconnCount();	
													srv  = new ServerStub(this);
													setBusy(false);
												}
								}catch (Exception e) {e.printStackTrace();} finally {}//server.closeConnection();}	 								
// !!!! --------------------------
 		setBusy(false);
// !!!! --------------------------										
	}//ClientConnectionTest
	
	
	
	private void sendM(Message MsgSend,MessageBack Mb){
			try {
						System.out.println("CLI :> spedisco a STUB comando: "+MsgSend.getCmd());	
						Mb = this.getSrv().SendRequest(MsgSend);	// SPEDISCE AL SRV [STUB] MESSAGE contenente COMMAND								
						System.out.println("CLI :> ritornato da STUB messaggio : "+Mb.getText());
						setBusy(false);
						System.out.println("CLI -> Ritornato mb :"+Mb.getText());		
			} 
			catch (IOException io){	
						io.printStackTrace();
						Mb.setText("CLI:>  collegamento mancante");	
						setBusy(false);
			} 
			catch (ClassNotFoundException e) {
						e.printStackTrace();
						Mb.setText("CLI:>  class not found");
						setBusy(false);
			}
	}
	
	
	private void ClientCheckExistTableBook(){
		Commands cmd = Commands.tableExistBook;
		MessageBack Mb = new MessageBack();
		
		System.out.println("CLI :> Request ricevuto da GUI :> "+cmd.toString());
		if (!stubok){
			Mb.setText(mSg = "CLI :>  nessuna connessione attiva , riprovare ");
			
			System.out.println(mSg);
			
			getActW().addMsg(new String ("Connection Test result"+mSg));
		}else{	
			System.out.println("CLI :> Stub OK");
			// **** Client crea Message	
			Message MsgSend = new Message(	
					cmd,						// Comando richiesto
					this.getCliType() ,			// tipo di Client , Admin,Librarian,Reader
					this.toString()				// id Client 
									);
			MsgSend.setUType(Clients.Librarian);
			// **** Client invia Message
			sendM(MsgSend, Mb);
		}	
	}			
	
	private void ClientCheckExistTablePerson(){
		Commands cmd = Commands.tableExistPerson;
		MessageBack Mb = new MessageBack();
		
		System.out.println("CLI :> Request ricevuto da GUI :> "+cmd.toString());
		if (!stubok){
			Mb.setText(mSg = "CLI :>  nessuna connessione attiva , riprovare ");
			
			System.out.println(mSg);
			
			getActW().addMsg(new String ("Connection Test result"+mSg));
		}else{	
			System.out.println("CLI :> Stub OK");
			// **** Client crea Message	
			Message MsgSend = new Message(	
					cmd,						// Comando richiesto
					this.getCliType() ,			// tipo di Client , Admin,Librarian,Reader
					this.toString()				// id Client 
									);
			MsgSend.setUType(Clients.Librarian);
			// **** Client invia Message
			sendM(MsgSend, Mb);
		}	
	}	

		

	
	
	
	
	
}
