package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBmanager {
	//CAMPI
	private static Connection connection;
	//METODI
	public static void 		openConnection() 			throws SQLException
	{
		//String url ="jdbc:postgresql://localhost:5432/GestioneSpiaggia";
		//String url ="jdbc:postgresql://localhost:5432/Dexodb";
		
		String url ="jdbc:postgresql://localhost:5432/schoolib";
		String username="postgres";
		String password="postgres";
		connection = DriverManager.getConnection(url,username,password);
	}
	
	
	public static void 		closeConnection() 			throws SQLException
	{	
		connection.close();
	}
	public static void 		execute(String query) 		throws SQLException
	{
		//Returns true if the first object that the query returns is a ResultSet object. 
		//Use this method if the query could return one or more ResultSet objects.
		//Retrieve the ResultSet objects returned from the query by repeatedly calling Statement.getResultSet.
		
		Statement stmt = connection.createStatement();
		stmt.execute(query);
		// rs ?
	}
	public static ResultSet executeQuery(String query) 	throws SQLException
	{
		//returns one ResultSet object.
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
	public static void 		executeUpdate(String query) throws SQLException
	{
		//Returns an integer representing the number of rows affected by the SQL statement.
		//Use this method if you are using INSERT, DELETE, or UPDATE SQL statements.
		
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.executeUpdate();
	}
	public static Connection getConnection(String string, String string2, String string3) throws SQLException {
		
		String url = 		"jdbc:postgresql://localhost:5432/schoolib";
		String username=	"postgres";
		String password=	"postgres";
		
		return connection = DriverManager.getConnection(url,username,password);
		
	}
	
}
