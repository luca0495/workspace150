package connections;

import Core.Clients;
import Core.Commands;

public class lancia50clients {
Client pip;
	

	public static void main(String[] args) {
		
			for (int x=0;x <54;x++){
			
			try {
				Client p = new Client();				
				
				System.out.println("creato client no "+x);
				p.setCliType(Clients.Librarian);
				p.main(null);
				
				
				
				try {
					Thread.sleep(100);
					
					// Person				
					try {
						// ChkDBandTab.tableExistPerson();
						p.getCmdLIST().put(Commands.tableExistPerson);	
					} catch (Exception e) {
						System.out.println("appMain :> problemi con accodamento comando check table exist PERSON");					
					}
					
					
				// Book				
					try {
						// ChkDBandTab.tableExistPerson();
						p.getCmdLIST().put(Commands.tableExistBook);							
					} catch (Exception e) {
						System.out.println("appMain :> problemi con accodamento comando check table exist BOOK");					
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			
				
				
				
				
				p.setCliType(Clients.Guest);
				
				
	
				
				
			/*	// book					
				//ChkDBandTab.tableExistBook();
				MessageBack back = p.Request(Commands.tableExistBook); // me == Client associato alla GUI
				System.out.println("GUI :> risposta dal DB : "+back.getText());	

			// Person	
				// ChkDBandTab.tableExistPerson();
				MessageBack back2 = p.Request(Commands.tableExistPerson);
				System.out.println("GUI :> risposta dal DB : "+back2.getText());	
			
			/*	
			//  Loans	
				// ChkDBandTab.tableExistLoans();
				MessageBack back1 = me.Request(Commands.tableExistLoans);    
				System.out.println("GUI :> risposta dal DB : "+back1.getText());
			*/
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}		
		
		// TODO Auto-generated constructor stub
		
	} 
	

	
	
}
