package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.enchere.bo.Enchere;

public class EnchereDaoJdbcImpl {
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES(?,?,?,?);";
	
	private static final String LASTENCHERE = "select * from enchere where no_article = ? order by date_enchere(DESC) limit 1";

	public void insert(Enchere enchere) {
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtEnchere = cnx.prepareStatement(INSERT_ENCHERE))
			{
			
			if(pstmtEnchere == null) {
				pstmtEnchere.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
				pstmtEnchere.setInt(2, enchere.getArticle_vendu().getNoArticle());
				pstmtEnchere.setDate(3, (Date) enchere.getDateEnchere());
				pstmtEnchere.setInt(4, enchere.getMontant_enchere());
			
			
			
				pstmtEnchere.executeUpdate();
				
				
			}
				
			}//Fermeture automatique de la connexion
			catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	
	
}
