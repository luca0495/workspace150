package Core;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import connections.Message;
import connections.MessageRealServer;

	public class RequestsList {
		
		
		private Queue<MessageRealServer>ReqList = new LinkedList<MessageRealServer>();
		private int maxRequests;
		private int wr=0;						// wr = Waiting Requests
		
	
		public RequestsList(int maxR){
			this.maxRequests=maxR;			
		}
		public RequestsList(){
		}
	
		public synchronized MessageRealServer take() throws InterruptedException{			
			
			
			while(ReqList.size()<1){
				wait();
			}	// forse inutile - Guardian non resta in wait su nessuna RL
			
			
			System.out.println("RequestList :>  TAKE wr PRIMA "+wr);
			MessageRealServer obj=ReqList.remove();
			System.out.println("RequestList :>  TAKE cmd REMOVE"+obj.toString());
			
			decWr();
			System.out.println("RequestList :>  TAKE wr DOPO "+wr);
			
			notifyAll();
			return obj;
		}
		
		public synchronized void put(MessageRealServer objName) throws InterruptedException{
			incWr();
				
			while(ReqList.size()>=maxRequests){
				System.out.println("RequestList :>  PUT limit reached , wait... ");			
				wait();//FINO A 100 RICHIESTE NON ASPETTA
				System.out.println("RequestList :>  PUT ricevuto notify sblocco ");
			}
			
			ReqList.add(objName);
				System.out.println("RequestList :>  PUT Added request : "+ objName );
				//incWr();		// incrementa contatore attese
								
				notifyAll();	// forse inutile - Guardian non resta in wait su nessuna RL
				System.out.println("RequestList :>  PUT spedito notify");
		}
		
		//operazioni su wr
		public void incWr() {
			wr++;
		}		
		public void decWr() {
			wr--;
		}
		public int getWr() {
			return wr;
		}
		public void setWr(int wr) {
			this.wr = wr;
		}

	}
