package database;

import java.sql.SQLException;

public class MQ_Insert {
	
	public static void insertUtente(String name, String surname, String inq, String mail,  String cf, String tel, String pass) throws SQLException
	{
		String query = "INSERT INTO utente(nome,cognome,email,codice_fiscale,inquadramento,password,ntel) VALUES('" + name + "' , '" + surname + "' , '" + mail + "' , '" + cf + "' , '" + inq + "' , '" + pass + "' , '" + tel + "')";
		DBmanager.openConnection();
		DBmanager.executeUpdate(query);
		DBmanager.closeConnection();
	}

}
