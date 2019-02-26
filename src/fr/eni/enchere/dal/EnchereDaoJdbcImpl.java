package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

public class EnchereDaoJdbcImpl implements EnchereDao {
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES(?,?,?,?);";
	
	private static final String LASTENCHERE = "select * from enchere where no_article = ? order by montant_enchere(DESC) limit 1";
	private static final String GETUSER = "select * from user where no_utilisateur = ?  ;";


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

	@Override
	public Enchere getLastEnchere(Article article) {
		Enchere enchere = null;
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtUser = cnx.prepareStatement(LASTENCHERE);)
			{
			Utilisateur user = getUser(article.getProprietaire().getNoUtilisateur());
				pstmtUser.setInt(1, article.getNoArticle());

				ResultSet rs = pstmtUser.executeQuery();
				while(rs.next())
				{
					if(rs.getString("pseudo")!=null)
					{
						enchere = new Enchere(user,
										rs.getDate("date_enchere"),
										rs.getInt("montant_enchere"), 
										article
										);
					}
				}
			}//Fermeture automatique de la connexion
			catch (SQLException e) {
				e.printStackTrace();
			}
					
			return enchere;
	}
	
	public Utilisateur getUser(int id) {
		Utilisateur user = null;
		
		
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER);)
		{
			pstmtUser.setInt(1, id);

			ResultSet rs = pstmtUser.executeQuery();
			while(rs.next())
			{
				if(rs.getString("pseudo")!=null)
				{
					user = new Utilisateur(rs.getInt("noUtilisateur"),
									rs.getString("pseudo"),
									rs.getString("Nom"), 
									rs.getString("Prenom"),
									rs.getString("email"),
									rs.getString("telephone"),
									rs.getString("rue"),
									rs.getString("code_postal"),
									rs.getString("ville"),
									rs.getString("mot_de_passe"),
									rs.getInt("credit"),
									rs.getBoolean("administrateur")
									);
				}
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
				
		return user;
	}
	
	
	
	
}
