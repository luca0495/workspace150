package connections;
public class Sem {

	
		private int value ;
		
		public Sem ( int initValue ) {
		 value = initValue ;
		}
		
		public synchronized void acquire ( ) throws InterruptedException{
		 while ( value == 0)
		 wait ( ) ;
		 value--;
		}
		
		public synchronized void release ( ) {
		 value++;
		 notify ( ) ;
		}
		 	
	
	
}