package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import connections.MessageBack;
import connections.MessageRealServer;

public class ChkDBandTab {
	
	// CHECK DB
	
	//	public static boolean DBExist()throws SQLException{
		
	
	/*public static MessageBack DBExist()throws SQLException{
		MessageBack mb = null;
			
			
	String DB_URL = "jdbc:postgresql://localhost/";
    String USER = "postgres";
    String PASS = "postgres";
	Connection connection = null;
    Statement statement= null;
		
		
		try {
		     Class.forName("org.postgresql.Driver");
			 System.out.println("Creating a connection...");
		     connection = DriverManager.getConnection(DB_URL,USER,PASS);
		     ResultSet resultSet = connection.getMetaData().getCatalogs();
		    
						    if(!resultSet.next()){
						    	 
						    statement = connection.createStatement();
						    
						    String sql =  " CREATE DATABASE IF NOT EXISTS schoolib ";
						    
						    statement.executeUpdate(sql);
						    	System.out.println("Database created!");
						    	//mb.setText("SRV :> DATABASE CREATO");
						    	
						     }
						    
		
		
		} catch (SQLException sqlException) {
					   if (sqlException.getErrorCode() == 1007) {
					        //Database already exists error
					   // mb.setText("SRV :> DATABASE ALREADY EXIST");
					    	System.out.println("SYS :> DATABASE ALREADY EXIST");
					    	
					    	
					    } else {
					        //Some other problems, e.g. Server down, no permission, etc
					    //	mb.setText("SRV :> Other Problems");
					   System.out.println("SYS :> OTHER PROBLEMS");
					       System.out.println("SYS :> "+sqlException.getErrorCode());					        
					    sqlException.printStackTrace();
 
					  }
		  }   catch (ClassNotFoundException e) {
		    //No driver class found!
			//mb.setText("SRV :> ECCEZIONE Class not found");
			System.out.println("SYS :> class not found");
		}
		return mb;
		 * 
		}
	*/
	   
	 

	// CHECK TAB
	public static void tableExistBook()throws SQLException{
	Connection connection = DBmanager.getConnection("jdbc:postgresql://localhost:5432/schoolib", "postgres", "postgres");
	 DatabaseMetaData metadata = connection.getMetaData();
	 ResultSet resultSet;
	 Statement statement = connection.createStatement();
	 resultSet = metadata.getTables(null, null, "libro", null);
	 
	 if(!resultSet.next()){
	 
		 statement.executeUpdate
                  ("CREATE TABLE  libro ( "
                  +"codice varchar(10) not null, "
                  +"nome_autore varchar(15) not null, "
                  +"cognome_autore varchar(15) not null, "
                  +"categoria varchar(15) not null,"
                  +"titolo varchar(35) not null,"
                  +"primary key(codice,nome_autore,cognome_autore,categoria,titolo))")  ;
		 
		 	System.out.println("ChkDBandTable :> table Book CREATED !");		 
	 }
	 else
	 {
		    System.out.println("ChkDBandTable :> exists table Book !");
		}
	    resultSet.close();
		DBmanager.closeConnection();

	}
	
	public static void tableExistPerson()throws SQLException{
	Connection connection = DBmanager.getConnection("jdbc:postgresql://localhost:5432/schoolib", "postgres", "postgres");
	 DatabaseMetaData metadata = connection.getMetaData();
	 ResultSet resultSet;
	 Statement statement = connection.createStatement();
	 resultSet = metadata.getTables(null, null, "utente", null);
	 if(!resultSet.next()){
	 
		 statement.executeUpdate
                  ("CREATE TABLE  utente ( "
                  +"id serial primary key,"
                  +"nome varchar(20) not null,"
                  +"cognome varchar(20) not null,"
                  +"email varchar(20) not null,"
                  +"codice_fiscale varchar(16) not null,"
                  +"inquadramento varchar(20) not null,"
                  +"password varchar(20) not null,"
                  +"password_temp varchar(20) not null,"
                  +"ntel varchar(10) not null,"
                  +"unique (email))")  ;
		 
		 System.out.println("ChkDBandTable :> table Person CREATED !");
	 }
	 else
	 {
		 System.out.println("ChkDBandTable :> exists table Person !");
		    
		}
	    resultSet.close();
		DBmanager.closeConnection();

	}
	public static void tableExistLoans()throws SQLException{
		Connection connection = DBmanager.getConnection("jdbc:postgresql://localhost:5432/schoolib", "postgres", "postgres");
		 DatabaseMetaData metadata = connection.getMetaData();
		 ResultSet resultSet;
		 Statement statement = connection.createStatement();
		 resultSet = metadata.getTables(null, null, "prestiti", null);
		 if(!resultSet.next()){
		 
			 statement.executeUpdate
	                  ("CREATE TABLE  prestiti ( "
	                  +"codice varchar(10) not null,"
	                  +"id serial not null,"
	                  +"titolo varchar(35) not null,"
	                  +"nome_autore varchar(15) not null,"
	                  +"cognome_autore varchar(15) not null,"
	                  +"categoria varchar (15) not null,"
	                  +"data_inizio date not null,"
	                  +"data_fine date not null,"
	                  +"lista_prenotazioni integer[] not null,"
	                  +"foreign key (id) references UTENTE(id) ON UPDATE CASCADE ON DELETE CASCADE,"
	                  +"foreign key ( codice, nome_autore, cognome_autore, categoria, titolo)references LIBRO ON UPDATE CASCADE ON DELETE CASCADE)")  ;
			 
			 System.out.println("ChkDBandTable :> table Prestiti CREATED !");
		 
		 }
		 else
		 {
			 System.out.println("ChkDBandTable :> exists table Prestiti !");
			    
			}
		    resultSet.close();
			DBmanager.closeConnection();

		}
	/*
	public static boolean Verifica() throws SQLException{
		Connection connection = DBmanager.getConnection("jdbc:postgresql://localhost:5432/schoolib", "postgres", "postgres");
		String query = "SELECT id FROM utente WHERE email = '" + mail + "';";
		return false;
		
	}
	*/
}

