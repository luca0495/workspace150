package Core;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import connections.Message;
import connections.MessageRealServer;

	public class CommandsList {
		
		
		private Queue<Commands>CmdList = new LinkedList<Commands>();
		private int maxRequests;
		private int wr=0;						// wr = Waiting Requests		
	
		public CommandsList(int maxR){
			this.maxRequests=maxR;			
		}
		
		public CommandsList(){
			maxRequests=100;
		}
	
		public synchronized Commands take() throws InterruptedException{			
	
			while(getCmdList().size()<1){
				wait();
			}	// forse inutile 
			
			
			System.out.println("CMDList :>  TAKE wr PRIMA "+wr);
			Commands obj=getCmdList().remove();
			System.out.println("CMDList :>  TAKE cmd "+obj.toString());
			
			decWr();
			System.out.println("CMDList :>  TAKE wr DOPO "+wr);
			
			notifyAll();
			return obj;
		}
		
		public synchronized void put(Commands objName) throws InterruptedException{
			incWr();
				
			while(getCmdList().size()>=maxRequests){
				System.out.println("CMDList :> :>  PUT limit reached , wait... ");			
				wait();//FINO A 100 RICHIESTE NON ASPETTA
				System.out.println("CMDList :>  PUT ricevuto notify sblocco ");
			}
			
			getCmdList().add(objName);
				System.out.println("CMDList :>  PUT Added request : "+ objName );
				//incWr();		// incrementa contatore attese
								
				notifyAll();	// forse inutile - Guardian non resta in wait su nessuna RL
				System.out.println("CMDList :>  PUT spedito notify");
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

		public Queue<Commands> getCmdList() {
			return CmdList;
		}

		public void setCmdList(Queue<Commands> cmdList) {
			CmdList = cmdList;
		}

	}
