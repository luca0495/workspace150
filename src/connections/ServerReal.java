package connections;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import Core.Clients;
import Core.Guardian;
import Core.Requests;
import Core.SearchFor;
import gui.SystemClientStub;
import gui.SystemServerSkeleton;

import database.*;

//ver 2017 03 29 v1
public class ServerReal extends ServerSkeleton {
		private 	SystemServerSkeleton 	meS;
		private 	Server					Srv;
		private 	Guardian				GpG;
		private 	Requests				Req;		
		private 	String 					mSg;	
		private  	Map<String,Message>  	listcmdDONE = new TreeMap<>();
		
		
		//***************************************************************************
		public 		Boolean					Go;
		//***************************************************************************
		
		private 	MessageBack				mSgB;
		
	//Costruttore----------------------------------------------------------------------
	public ServerReal(Socket socket,Server SrvRif) throws Exception{
		super(socket);
		setSrv(SrvRif);
		super.set_meServer(this);
		// Ottiene i riferimenti di Request e Guardian dal Server
		setGpG(Srv.getG());
		setReq(Srv.getR());	
		Go=false;
		
		/*TODO ELIMINA FINESTRE DI SYSTEMA PER TESTAGGIO */
		setMeS(new SystemServerSkeleton(this));
		getMeS().getFrame().setVisible(true);	
		
		Srv.setSrvconnINC();		//	INCrease Server Connections counter
		Srv.getMeG().addMsg(mSg="numero connessioni attive :"+ Srv.getSrvconn());	
	}	
	//Costruttore----------------------------------------------------------------------
	
	//Connections
	@Override
	public MessageBack connection(Message msg) throws RemoteException, InterruptedException {	
				System.out.println("REAL SERVER :> GESTISCO RICHIESTA CONNNECTION");
		MessageBack x = new MessageBack();
		switch (msg.getCommand()) {		
			case ConnTEST:
				//System.out.println(mSg = "REALServer:> TEST connessione richiesto ");
				getMeS().addMsg(mSg);				
				x.setText(new String ("SRV - Connessione OK"));					
				break;												
			case ConnSTOP:			
				//System.out.println(mSg = "REALServer:> CHIUSURA connessione richiesta ");
				getMeS().addMsg(mSg);
				x.setText(new String ("SRV - chiudo socket tra 5 secondi..."));			
				//super._socket.close();
				//Server.setSrvconn(Server.getSrvconn() - 1);	
				System.out.println("REALServer:> attuale numero connessioni : "+ Server.getSrvconn() +"\n"); 	
				System.out.println("REALServer:> chiusura socket...");
				break;		
			default:
				break;
		}	
		return x;
	}

	@Override
	public MessageBack 	visualizza(Message Mes) {
	// chiamata diretta al dbManager
		MessageRealServer M = this.MessageEncapsulation(Mes);
		MessageBack AnswerM = null;
		//Query DIRETTA su dbManager
		
		/*TODO PREPARA Answer*/
		
		return AnswerM;
	}

	@Override
	public MessageBack modifica(Message Mes) {
	// accodamento richiesta su Requests gestita da Guardian 	
		
		System.out.println("RealServer :> Rx Change ");
		
		MessageRealServer M = this.MessageEncapsulation(Mes);
		MessageBack x = new MessageBack();
		MessageBack Answer = new MessageBack();
		
		
		switch (M.getMsg().getUType()) {			// estrae tipo di utente da Message 				
		//**********************************************************************************
		case Librarian :
			System.out.println("RealServer :> Rx Librarian");			
			switch ( M.getMsg().getCommand().getTarget()){
			//------------------------------------------------------------------------------			
			case Account:		//AL				
						System.out.println("RealServer :> Rx Account");
						try {					
							System.out.println("RealServer :> Accodo M [ AL ]");
							System.out.println("RealServer :> AL in attesa prima... "+Req.getAL().getWr());
							
							Req.getAL().put(M);
							//listcmdDONE.put(Mes.getMesId(),null);
							while (!Go){
								try {
									System.out.println("REAL SERVER :> in attesa GO da Guardian");
									Thread.sleep(10);
								} catch (Exception e) 
								{}
								System.out.println("REAL SERVER :> go "+Go);						
							}	//Attesa del turno...
							//******************************************************************************
							System.out.println("REAL SERVER :> fine attesa \nREAL SERVER :> Gestisco RICHIESTA :> tableExistPerson ");					
							try {
								ChkDBandTab.tableExistPerson();
								getMeS().addMsg(mSg);
								x.setText(new String ("SRV :> CHECK TABLE Person Exist :> OK"));	
							} catch (SQLException e) {
								getMeS().addMsg(mSg);
								x.setText(new String ("SRV :> CHECK TABLE Person Exist :> NG..."));
								System.out.println("problemi con controllo tabella person");
								e.printStackTrace();
							}
							System.out.println("SYS AL :> srv ritorna "+x.getText());
							//******************************************************************************										
							return x;
						} catch (InterruptedException e) {
							System.out.println("RealServer :> Problemi Accodamento CMD AL");
							e.printStackTrace();
							x.setText("RealServer :> Problemi Accodamento CMD AL");	
						}
									
			break;
			
			case Book:			//BL
				
						System.out.println("RealServer :> Rx Book");
						try {							
							System.out.println("RealServer :> Accodo M [ BL ]");
							System.out.println("RealServer :> BL in attesa prima... "+Req.getBL().getWr());
							Req.getBL().put(M);
							System.out.println("RealServer :> BL in attesa dopo... "+Req.getBL().getWr());	
							System.out.println("RealServer :> GO : "+Go);	
							while (!Go){
								try {
									Thread.sleep(10);
								} catch (Exception e) 
								{}
								System.out.println("REAL SERVER :> go "+Go);						
							}	//Attesa del turno...
							//******************************************************************************
							System.out.println("REAL SERVER :> fine attesa \nREAL SERVER :> Gestisco RICHIESTA :> tableExistBook ");					
							try {
								ChkDBandTab.tableExistBook();
								getMeS().addMsg(mSg);
								x.setText(new String ("SRV :> CHECK TABLE book Exist :> OK"));	
							} catch (SQLException e) {
								getMeS().addMsg(mSg);
								x.setText(new String ("SRV :> CHECK TABLE book Exist :> NG..."));
								System.out.println("problemi con controllo tabella Book");
								e.printStackTrace();
							}
							System.out.println("SYS BL :> srv ritorna "+x.getText());
							return x;
							//******************************************************************************
											
						} catch (InterruptedException e) {
							System.out.println("RealServer :> Problemi Accodamento CMD BL");
							e.printStackTrace();
						}	
			break;
			

			case Prenotation:	//PL
			//
			
				System.out.println("RealServer :> Rx Prenotations");
//TODO provvisorio...				
				x.setText(new String ("SRV :> CHECK TABLE Prenotations Exist :> SIMULAZIONE"));
				
				System.out.println("SYS PL :> srv ritorna "+x.getText());
				
				try {
					return x;	
				} catch (Exception e) {
				}	
				
				
				/*
				 try {							
				 
					System.out.println("RealServer :> Accodo M [ PL ]");

//TODO inserire nell algoritmo...
//TODO non esiste ancora il codice comando per GUARDIANO
					
					
					System.out.println("RealServer :> PL in attesa prima... "+Req.getBL().getWr());
			//Req.getPL().put(M);
					
					
					System.out.println("RealServer :> PL in attesa dopo... "+Req.getBL().getWr());	
					System.out.println("RealServer :> GO : "+Go);	
					while (!Go){
						try {
							Thread.sleep(10);
						} catch (Exception e) 
						{}
						System.out.println("REAL SERVER :> go "+Go);						
					}	//Attesa del turno...
					//******************************************************************************
					System.out.println("REAL SERVER :> fine attesa \nREAL SERVER :> Gestisco RICHIESTA :> tableExistPrenotations ");					
					try {
						ChkDBandTab.tableExistPerson();
						getMeS().addMsg(mSg);
						x.setText(new String ("SRV :> CHECK TABLE Prenotations Exist :> OK"));	
					} catch (SQLException e) {
						getMeS().addMsg(mSg);
						x.setText(new String ("SRV :> CHECK TABLE Prenotations Exist :> NG..."));
						System.out.println("problemi con controllo tabella Prenotations");
						e.printStackTrace();
					}
					
					
					System.out.println("SYS PL :> srv ritorna "+x.getText());
					return x;
					//******************************************************************************
									
				} catch (InterruptedException e) {
					System.out.println("RealServer :> Problemi Accodamento CMD PL");
					e.printStackTrace();
				}	
				*/
				
	break;

			default:
			//
				// errore
				System.out.println("comando indefinito");	
			//	
			break;	
			
			}
		break;	
		//**********************************************************************************	

//--------------------------------------------------------------------------------------------------------------			
		case Reader :

			
			if (M.getMsg().getCommand().getTarget()== SearchFor.Account ){	//AR
				try {
					Req.getAR().put(M);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while (!Go){	}	//Attesa del turno...
				
				// Query
				
				
				GpG.setBusy(false);
				return Answer;
				
			}else{
				if (M.getMsg().getCommand().getTarget()== SearchFor.Book ){	//BR
					try {
						Req.getBR().put(M);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					while (!Go){	}	//Attesa del turno...
					
					// Query
					
					
					GpG.setBusy(false);
					return Answer;
					
				}else{
					
					// errore
					System.out.println("comando indefinito");
				}		
			}
			break;		
		default:
			break;
		}
		return null;
	}

	//
	public Server getSrv() {
		return Srv;
	}
	public void setSrv(Server srv) {
		Srv = srv;
	}
	public SystemServerSkeleton getMeS() {
		return meS;
	}
	public void setMeS(SystemServerSkeleton meS) {
		this.meS = meS;
	}

	public Guardian getGpG() {
		return GpG;
	}
	public void setGpG(Guardian gpG) {
		GpG = gpG;
	}
	public Requests getReq() {
		return Req;
	}
	public void setReq(Requests req) {
		Req = req;
	}
	// *************************************************************	
	public MessageRealServer MessageEncapsulation (Message msg){
		MessageRealServer mrs=new MessageRealServer(msg, this);
		return mrs;
	}
	// *************************************************************

	
	
	
	
	
}
