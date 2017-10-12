package connections;

import java.io.EOFException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface IServer extends Remote {

	
	
	public static final int PORT=9996;
	
	// stub - skeleton Connection
	public MessageBack 			testConnectionSS		();
    public void 			closeConnectionSS		() throws InterruptedException, EOFException;
    
    // db Connection
    
    // Metodi TEST classe CLIENT
 /*
    public Message			metodotest1				()throws RemoteException, IOException, ClassNotFoundException;
    public Message			metodotest2				()throws RemoteException, IOException, ClassNotFoundException;
    public Message			metodotest3				()throws RemoteException, IOException, ClassNotFoundException;    
  */  
    public String 			testConnectionDB		()throws RemoteException;
    public void 			closeConnectionDB		()throws RemoteException;
    
    
    
    public MessageBack			visualizza				(Message M)throws RemoteException;
    public MessageBack			modifica				(Message M)throws RemoteException;	
    public MessageBack			connection				(Message M)throws RemoteException, InterruptedException;	
    public MessageBack			test					(Message M)throws RemoteException;	
    
    
    public MessageBack	 		SendRequest				(Message x) throws IOException, ClassNotFoundException;
    public MessageBack 			DBExist					();

    

    
    
    
}
