package Core;

import connections.Message;
import connections.MessageRealServer;
import connections.ServerReal;

// 2017 03 31 v1
public class GuardianTimeOut extends Thread {
ServerReal	SrvR;
Guardian 	GpG;
int 		TOMillisenconds ;
Message 	Mess;	
	
	
public GuardianTimeOut(Guardian Gpg,MessageRealServer Mes,int TOM) {
	GpG				=Gpg;
	TOMillisenconds	=TOM;
	Mess			=Mes.getMsg();
}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(TOMillisenconds);		
			
			if (GpG.getMesServing()!=null){// se sta servendo qualcosa...
				
				if (Mess.equalTo(GpG.getMesServing())){		//Medesimo Message (Richiesta) in lavorazione
					// Time Out
					GpG.setBusy(false);
				}				
			}else{
				
				// non sta servendo nulla...
				
			}
			

		
		} 	catch (InterruptedException e) {
			e.printStackTrace();
			}
	}


}
