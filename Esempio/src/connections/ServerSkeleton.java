package connections;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.StringTokenizer;
import javax.swing.SingleSelectionModel;

import com.sun.mail.handlers.message_rfc822;

import Core.Commands;
import database.ChkDBandTab;

public class ServerSkeleton implements IServer, Runnable {
			Socket 		_socket;
			ServerReal 	_meServer;	
			boolean STOP=false;
			String mSg;
	private ObjectInputStream istream ;
	private ObjectOutputStream ostream ;
	
	
	public ServerSkeleton(Socket socket){	
			_socket = socket;
			try {
				istream = new ObjectInputStream	(socket.getInputStream	());
				ostream = new ObjectOutputStream(socket.getOutputStream	());
			}catch(IOException e ){
		}
	}
	public void run() {	
		try{
				while(!_socket.isClosed()||STOP){			
				
				Message myOper = ( Message ) istream.readObject() ;		//cast a Message della lettura dell'oggetto								
				System.out.println("SKELETON :> rx cmd :> "+myOper.getCommand().getCommandType().name());
				System.out.println("SKELETON :> rx cmd :> "+myOper.getCommand().Den);
				switch (myOper.getCommand().getCommandType()){	
				
				case CHANGE:
					System.out.println("Skeleton SWITCH Type:> rx cmd :> MODIFICA");
					MessageBack mb = new MessageBack();
					
					//mb.setText("SRVSKT:> Cmd accodato");
					//ostream.writeObject(mb);
					
					
					ostream.writeObject(modifica(myOper));
 					ostream.flush();					
					//------------------------------------------------------------------------------------------
 					
 					switch (myOper.getCommand()) {					
					case tableExistBook:						
						System.out.println("Skeleton SWITCH:>  test Table Book exist ");					
						//ostream.writeObject(ChkDBandTab.tableExistBook());	
	 					//ostream.flush();										
						break;						
						// TODO per Mauro verifica
                    case tableExistLoans:     					
                    	System.out.println("Skeleton SWITCH:>  test Table Loans exist ");	
						//ostream.writeObject(ChkDBandTab.tableExistBook());	
	 					//ostream.flush();									
						break;						
						// TODO per Mauro verifica
                    case tableExistPerson:     							
                    	System.out.println("Skeleton SWITCH:>  test Table Person exist ");	
						//ostream.writeObject(ChkDBandTab.tableExistBook());	
		 				//ostream.flush();										
						break;		
 					//------------------------------------------------------------------------------------------
					default: 					
						break;
 					}	
 					
 					break;
 					
				case READ:
					System.out.println("Skeleton SWITCH Type:> rx cmd :>  LETTURA");
					ostream.writeObject(visualizza(myOper));
 					ostream.flush();
 					
					break;		
					
				case TEST:
					// richiamato da crea dbtest della main gui
					
					System.out.println("Skeleton SWITCH Type:> rx cmd :>  TEST");
					ostream.writeObject(test(myOper));
 					ostream.flush();
 					//------------------------------------------------------------------------------------------
 					
 					switch (myOper.getCommand()) {					
 					/*
 					case tableExistBook:						
						System.out.println("Skeleton :>  test Table Book exist ");					
						//ostream.writeObject(ChkDBandTab.tableExistBook());	
	 					//ostream.flush();										
						break;						
						// TODO per Mauro verifica
                    case tableExistLoans:     					
                    	System.out.println("Skeleton :>  test Table Loans exist ");	
						//ostream.writeObject(ChkDBandTab.tableExistBook());	
	 					//ostream.flush();									
						break;						
						// TODO per Mauro verifica
                    case tableExistPerson:     							
                    	System.out.println("Skeleton :>  test Table Person exist ");	
						//ostream.writeObject(ChkDBandTab.tableExistBook());	
		 				//ostream.flush();										
						break;		
 					
					*/	
					//------------------------------------------------------------------------------------------
					default:
						break;	
 					}
				break;		
						
						
				case CONNECTION:
					System.out.println("Skeleton SWITCH Type:> rx cmd :>  richiesta CONNECTIONs");
					ostream.writeObject(connection(myOper));
 					ostream.flush();
		 					if (myOper.getCommand()==Commands.ConnSTOP){
		 						Thread.sleep(5000);					
		 						_socket.close();
		 						System.out.println("attuale numero connessioni : "+ Server.getSrvconn() +"\n");
		 					}			
					break;		
					
					
				
				default:// CATEGORIE SPECIALI
					
						switch (myOper.getCommand()) {
								//case "TestConnection":
								
								/*
								case ConnTEST:
									System.out.println(" Test connessione richiesto "); 
									ostream.writeObject(testConnectionSS());
				 					ostream.flush();		
				 					break;										
								
								case ConnSTOP:
									closeConnectionSS();
									System.out.println("chiusura socket..."); 
									break;
								*/
								
								
									case tableExistBook:
									
									System.out.println("Skeleton :>  test DB exist ");
									System.out.println("Skeleton :>  testaggio esistenza DB");
									
								//	ostream.writeObject(ChkDBandTab.tableExistBook());	
				 					ostream.flush();									
									
									
									break;
									
									// TODO per Mauro verifica
                                   case tableExistLoans:     
									
									System.out.println("Skeleton :>  test DB exist ");
									System.out.println("Skeleton :>  testaggio esistenza DB");
									System.out.println("Loans");
								//ostream.writeObject(ChkDBandTab.tableExistBook());	
				 					ostream.flush();									
									
									
									break;
									// TODO per Mauro verifica
                                   case tableExistPerson:     
   									
   									System.out.println("Skeleton :>  test DB exist ");
   									System.out.println("Skeleton :>  testaggio esistenza DB");
   									System.out.println("Loans");
   								//ostream.writeObject(ChkDBandTab.tableExistBook());	
   				 					ostream.flush();									
   									
   									
   									break;
								/*	
								// metodi test
								case Test1:
									System.out.println("Skeleton :> M1");
									ostream.writeObject(metodotest1());
				 					ostream.flush();	
									break;										
								case Test2:
									System.out.println("Skeleton :> M2");
									ostream.writeObject(metodotest2());
				 					ostream.flush();	
									break;									
								case Test3:
									System.out.println("Skeleton :> M3");
									ostream.writeObject(metodotest3());
				 					ostream.flush();	
									break;			
							// metodi test
							 *  			
							 */
								default:
									System.out.println("Skeleton :> richiesta indefinita "); 
									break;
						
						}	//fine switch command
						break;
						
				}			//fine switch command type	
						
						
						
						
				}//socket close
				
				_meServer.getMeS().getFrame().setVisible(false);			
				_meServer.getSrv().setSrvconnDEC();		//DECREASE Server Connections counter
				_meServer.getSrv().getMeG().addMsg(mSg="numero connessioni attive"+ _meServer.getSrv().getSrvconn()+"");
				
				
				
			}catch(Exception e){
				e.printStackTrace();
				
				
				// Socket chiuso...
			}	
	}
/*
	@Override
	public Message metodotest1() {
		return null;
	}
	@Override
	public Message metodotest2() {
		return null;	
	}	
	@Override
	public Message metodotest3() {
		return null;
	}
*/	
	// Connessione stub - skeleton
	@Override
	public MessageBack testConnectionSS() {
		return null;
	}
	public void closeConnectionSS() throws InterruptedException {
		try{
			Thread.sleep(5000);					
			_socket.close();
			//Server.setSrvconn(Server.getSrvconn() - 1);
			System.out.println("attuale numero connessioni : "+ Server.getSrvconn() +"\n");	
		}catch(IOException e){	
		}
	}
	
	//**************************************************************************
	@Override
	public MessageBack visualizza(Message M) {
		return null;
	}
	//**************************************************************************
	@Override
	public MessageBack modifica(Message M) {
		return null;
	}
	//**************************************************************************
	
	// Connessione db	
	@Override
	public String testConnectionDB() {
		return null;
	}
	@Override
	public void closeConnectionDB() {	
	}		
	public ServerReal get_meServer() {
		return _meServer;
	}
	public void set_meServer(ServerReal _meServer) {
		this._meServer = _meServer;
	}	
	@Override
	public MessageBack SendRequest(Message x) throws IOException, ClassNotFoundException {
		return null;
	}
	@Override
	public MessageBack DBExist() {
		return null;
	}
	
	
	// implementati in server real
	@Override
	public MessageBack connection(Message M) throws RemoteException, InterruptedException {
		System.out.println("procede SKELETON");
		return null;
	}
	@Override
	public MessageBack test(Message M) throws RemoteException {		
		System.out.println("procede SKELETON");
		return null;
	}



	}
