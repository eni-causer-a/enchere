package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class ConnectionProvider {
	private static DataSource dataSource;
	
	
	/*Au chargement de la classe, la datasource est recherchée
	 * dans l'arbre JNDI*/
	static {
		Context context = null;
		try
		{
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		}
		catch(NamingException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Impossible d'accéder à la base de données");
		}
	}
	
	
	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
}
