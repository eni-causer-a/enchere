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
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS set prix_vente=? WHERE no_article=?";
	
	private static final String LASTENCHERE = "select top 1 * from encheres where no_article = ? order by montant_enchere DESC ;";

	@Override
	public void insert(Enchere enchere) {
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtEnchere = cnx.prepareStatement(INSERT_ENCHERE))
		{
		
			pstmtEnchere.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			pstmtEnchere.setInt(2, enchere.getArticle_vendu().getNoArticle());
			pstmtEnchere.setDate(3, new Date(enchere.getDateEnchere().getTime()));
			pstmtEnchere.setInt(4, enchere.getMontant_enchere());
		
			pstmtEnchere.executeUpdate();
			
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement ps = cnx.prepareStatement(UPDATE_ARTICLE))
			{
			
				ps.setInt(1, enchere.getMontant_enchere());
				ps.setInt(2, enchere.getArticle_vendu().getNoArticle());
			
				ps.executeUpdate();
			
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
			pstmtUser.setInt(1, article.getNoArticle());
				ResultSet rs = pstmtUser.executeQuery();
				if(rs.next())
				{
					Utilisateur user = DAOFactory.getUtilisateurDao().findUserById(Integer.parseInt(rs.getString("no_utilisateur")));
					
					enchere = new Enchere(user,
									rs.getDate("date_enchere"),
									rs.getInt("montant_enchere"), 
									article
									);
				}
			}//Fermeture automatique de la connexion
			catch (SQLException e) {
				e.printStackTrace();
			}
					
			return enchere;
	}	
	
}
