package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.EtatVente;
import fr.eni.enchere.bo.Utilisateur;

public class ArticleDaoJdbcImpl {

	private static final String GETETAT = "select date_debut_encheres, date_fin_encheres from ARTICLES_VENDUS where no_article = ? "; 


	public void getEtat(Article article) throws ParseException {
		Utilisateur user = null;
		
		
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtUser = cnx.prepareStatement(GETETAT);)
		{
			pstmtUser.setInt(1, article.getNoArticle());

			ResultSet rs = pstmtUser.executeQuery();
			while(rs.next())
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String uneDate = dateFormat.format(date);
				
				if(dateFormat.parse(rs.getString("date_debut_encheres")).compareTo(date) >0)
				{
					article.setEtatVente(EtatVente.CREE);
					
				}
				else if (date.compareTo(dateFormat.parse(rs.getString("date_debut_encheres"))) >0 && dateFormat.parse(rs.getString("date_fin_encheres")).compareTo(date) >0 ) {
					article.setEtatVente(EtatVente.EN_COURS);

				}
				else if(date.compareTo(dateFormat.parse(rs.getString("fin"))) >0)
				{
					article.setEtatVente(EtatVente.ENCHERE_TERMINE);
					
				}
				
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
				
	}

}
