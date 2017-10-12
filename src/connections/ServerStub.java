package connections;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.io.EOFException;
import java.io.IOException;
import gui.SystemClientStub;

public class ServerStub implements Serializable, IServer {
	private ObjectInputStream in;
	private ObjectOutputStream out; 
	private Socket _socket;
	private String mSg;
	private int conntentativi;
	
	private MessageBack msg;
	private Client Rifclient;
	
	public ServerStub(Client client) throws Exception{
		Rifclient = client;
		conntentativi=0;		
	//	InetAddress addr= InetAddress.getByName(null);
	//	InetAddress addr= InetAddress.getByName("192.168.1.3");		//test con Dexo note 
		InetAddress addr= InetAddress.getByName("127.0.0.1");		//test con Local host		
	
	System.out.println("addr = " + addr );
	try {
		_socket = new Socket(addr, IServer.PORT );
			
			System.out.print(mSg = "STUB:> Socket = " + _socket +"\n")		;// leggi testo nel messaggio);
			Rifclient.getActW().addMsg(mSg);	
		out = new ObjectOutputStream(_socket.getOutputStream());
		in 	= new ObjectInputStream(_socket.getInputStream());						
			
			System.out.print(mSg = "STUB:> Check in/out:> OK "+"\n")		;// leggi testo nel messaggio);
			Rifclient.getActW().addMsg(mSg);		
			Rifclient.setStubok(true);
		
	} catch (Exception e) {
			
			System.out.print(mSg = "STUB:> Server Assente \n")				;// leggi testo nel messaggio);
			Rifclient.getActW().addMsg(mSg);	
			Rifclient.setStubok(false);
			Rifclient.setRepeatconn(true);	
	} 
/*	
	try {
		_socket = new Socket(addr, IServer.PORT );
			
			System.out.print(mSg = "STUB:> Socket = " + _socket +"\n")		;// leggi testo nel messaggio);
			Rifclient.getMeG().addMsg(mSg);	
		out = new ObjectOutputStream(_socket.getOutputStream());
		in 	= new ObjectInputStream(_socket.getInputStream());						
			System.out.print(mSg = "STUB:> Check in/out:> OK "+"\n")		;// leggi testo nel messaggio);
			Rifclient.getMeG().addMsg(mSg);		
			Rifclient.setStubok(true);
		
	} catch (Exception e) {
			System.out.print(mSg = "STUB:> Server Assente \n")				;// leggi testo nel messaggio);
			Rifclient.getMeG().addMsg(mSg);	
			Rifclient.setStubok(false);
			Rifclient.setRepeatconn(true);	
	} 
*/
}
		
	@Override
	public MessageBack SendRequest(Message x) throws IOException, ClassNotFoundException {
			MessageBack mb = new MessageBack();
			
			System.out.println("STUB :> Spedita richiesta comando a SKELETON");
		
		out.writeObject(x);
		
			try{				
		mb = (MessageBack)in.readObject();
			
		System.out.println("STUB :> Tornata risposta da SKELETON ");				
		System.out.println("STUB :> Tornata risposta da SKELETON code    : "+mb);
		System.out.println("STUB :> Tornata risposta da SKELETON gettest : "+mb.getText());
				
			}catch (IOException e){
			System.out.println("STUB:> ECCEZIONE");	
			e.printStackTrace();
			mb.setText(new String ("STUB :> Eccezione *** nessuna risposta da SKELETON"));
			}finally{
		return mb;
			}
	}
	
	
		// Connection SS
	@Override
		public MessageBack testConnectionSS() {	
		MessageBack mb = new MessageBack("STUB :> nessuna risposta...");
		
		//	out.println("<testConn>");
			try {		
				out.writeObject(new Message(Core.Commands.ConnTEST));
				mb = (MessageBack)in.readObject();	
				// test
				
				if ( mb == null){
					System.out.println("nessuna risposta");
				}
					
					
				
				System.out.print(mSg = "STUB:> RITORNO DA TEST CONN \n"+ mb.getText())		;// leggi testo nel messaggio);
				
				//Rifclient.getMeG().addMsg(mSg);		
				Rifclient.getActW().addMsg(mSg);		
		
				// test ok
				return mb;
				
			} catch (IOException | ClassNotFoundException e1) {				
				e1.printStackTrace();
				Rifclient.setStubok(false);
				Rifclient.setRepeatconn(true);
				mb.setText(new String ("STUB ERRORE LETTURA"));
				return mb;
			}		
	}		
		public void closeConnectionSS() throws EOFException {
			MessageBack msg=null;
			
			System.out.println("STUB : closing... ");
			//out.println("<end>");
			try {
				out.writeObject(new Message(Core.Commands.ConnSTOP));
				msg = (MessageBack)in.readObject();	
				
			} catch (IOException | ClassNotFoundException e1) {
				e1.printStackTrace();
				msg.setText("STUB:> problemi con la chiusura socket");
			}
			
			
			
			try{
				_socket.close();
			}catch(IOException e ){
				e.printStackTrace();
			}
			
			
			
		}
/*		
		// TEST Metodi 1 2 3
		@Override
		public Message metodotest1() throws IOException, ClassNotFoundException {
			Message x=null;
			//out.println("<test1>");
			out.writeObject(new Message(Core.Commands.Test1));
			
			String strResult;
			try{
				//strResult=in.readLine();
				return (Message)in.readObject();
				//strResult=in.readObject()	
				//return strResult;
			
			}catch (IOException e){
				e.printStackTrace();
				//String x = "";
				x.setText(new String ("problemi con metodo test 1"));
				return x;
			}	
		}
		@Override
		public Message metodotest2() throws IOException, ClassNotFoundException {
			Message x=null;
			//out.println("<test1>");
			out.writeObject(new Message(Core.Commands.Test2));
			
			String strResult;
			try{
				//strResult=in.readLine();
				return (Message)in.readObject();
				//strResult=in.readObject()	
				//return strResult;
			
			}catch (IOException e){
				e.printStackTrace();
				//String x = "";
				x.setText(new String ("problemi con metodo test 2"));
				return x;
			}	
		}
		@Override
		public Message metodotest3() throws RemoteException, IOException, ClassNotFoundException {
			Message x=null;
			//out.println("<test1>");
			out.writeObject(new Message(Core.Commands.Test3));
			
			String strResult;
			try{
				//strResult=in.readLine();
				return (Message)in.readObject();
				//strResult=in.readObject()	
				//return strResult;
			
			}catch (IOException e){
				e.printStackTrace();
				//String x = "";
				x.setText(new String ("problemi con metodo test 3"));
				return x;
			}
		}
*/		
		// Connection DB
		@Override
		public MessageBack 	visualizza(Message M) {
			return null;
		}
		@Override
		public MessageBack modifica(Message M) {
			return null;
		}
		@Override
		public String 	testConnectionDB() {
			return null;
		}
		@Override
		public void 	closeConnectionDB() {
		}

		@Override
		public MessageBack  DBExist() {
			return null;
		}

		@Override
		public MessageBack connection(Message msg) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MessageBack test(Message M) throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

	
}