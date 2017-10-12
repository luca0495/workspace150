package connections;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import Core.Guardian;
import Core.Requests;
import gui.SystemServer;
import gui.SystemServerSkeleton;

// 2017 04 01 V9

public class Server {						
	private	static 			Server 			me;
	private static 			SystemServer 	meG		=null;
	
	private static 			String 			msg		=null;
	private static 			int 			srvconn = 0;
	
	private 				Guardian		G;		
	private 				Requests 		R;

	public Server(){
		setR(new Requests(10));
		setG(new Guardian(this,getR()));
		try {
			new Thread(getG()).start();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("problemi con creazione Guardian");
		}
	}
	
	public static void main(String[] args) throws Exception {
		me = new Server();
		setMeG(new SystemServer(me));
		getMeG().getFrame().setVisible(true);
		
		ServerSocket serverSocket = new ServerSocket(IServer.PORT);
		System.out.println(msg = "xxx schoolLib Server Started: \n" + serverSocket);
		getMeG().addMsg(msg);
		
	    while(true){
	    	try {
	    			System.out.println(msg="Connessioni attive : " + getSrvconn() + "\n Waiting a connection... ");
	    			getMeG().addMsg(msg);
	    			Socket socket = serverSocket.accept();
	    			new Thread(new ServerReal(socket,me)).start();			
			} catch (Exception e) {
			} 
	    	 //System.out.println("schoolLib Server // connessioni attive : "+srvconnlist.size());
	    	 //srvconnlist.add(socket);	 
	    }
	}
	public static int getSrvconn() {
		return srvconn;
	}
	public static void setSrvconn(int srvconn) {
		Server.srvconn = srvconn;
	}
	public static void setSrvconnINC() {
		Server.srvconn++;
	}
	public static void setSrvconnDEC() {
		Server.srvconn--;
	}
	public static SystemServer getMeG() {
		return meG;
	}
	public static void setMeG(SystemServer meG) {
		Server.meG = meG;
	}
	public Guardian getG() {
		return G;
	}
	public void setG(Guardian g) {
		G = g;
	}
	public Requests getR() {
		return R;
	}
	public void setR(Requests r) {
		R = r;
	}

}
