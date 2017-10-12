package connections;
import Core.Commands;
public class ClientConnectionController implements Runnable {
	private 			Client 				me;
	private 			MessageBack			mSgBack;
	private 			String				mSg;	
	private 			int 				FCC=500;	//FrequenzaControlloConnessione [millisecondi]
//------------------------------------------------------------------------	
	public ClientConnectionController(Client Me){
		me=Me;
		System.out.println("CCC :> Creato");
	}
	public ClientConnectionController(Client Me,int msec){
		me=Me;
		FCC=msec;
		System.out.println("CCC :> Creato");
	}
//------------------------------------------------------------------------	
	public void run() {
		try {
			System.out.println("CCC :> Operativo");
			Logica();
		} catch (Exception e) {
			System.out.println("CCC :> Problemi con Lancio CCC ");
		}	
	}
//------------------------------------------------------------------------	
	void Logica() throws Exception {	
 		int ctc=0;						//---- Connection test counter				
 		System.out.println("CCC :> Inizio Logica");
 		
 		while (me.isRepeatconn()){
			try {
				Thread.sleep(FCC);
			} catch (Exception e) {							
			}
 			
 			System.out.println("CCC :> lancio richiesta controllo connessione no. "+ctc);	
			int TurniBusy=100;
			ctc++;
 				try {
					while (me.isBusy()&&TurniBusy>0){	//se busy attende fino a 10 tentativi poi lascia in coda comando					
						System.out.println("CCC :> non lancio richiesta controllo connessione , rimandono turniBUSY da saltare: "+TurniBusy);
						try {
							Thread.sleep(FCC);
						} catch (Exception e) {							
						}
					TurniBusy--;							
					}	
					me.getCmdLIST().put(Commands.ConnTEST);
					
				}catch (Exception e) {e.printStackTrace();
				} 								
		}	//while					
	}		//Logica
//------------------------------------------------------------------------
}
