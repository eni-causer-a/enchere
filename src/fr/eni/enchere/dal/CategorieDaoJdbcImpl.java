package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Categorie;

public class CategorieDaoJdbcImpl implements CategorieDao {
	
	public static final String GETALLCAT = "select * from CATEGORIES;";
	public static final String GETALLWITHOUTTOUTE = "select * from CATEGORIES where no_categorie != 1";
	public static final String GETCATEGORIE = "select * from CATEGORIES where libelle like ?";

	@Override
	public List<Categorie> getListCategorie() {
		List<Categorie> listeCategorie = new ArrayList<Categorie>();

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETALLCAT))
			{

			ResultSet rs = pstmtArticle.executeQuery();
			while(rs.next())
			{
				Categorie categorie = new Categorie(rs.getInt("no_categorie"),
										rs.getString("libelle"));

				listeCategorie.add(categorie);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCategorie;
	}
	@Override
	public List<Categorie> getListCategorieWithoutToutes() {
		List<Categorie> listeCategorie = new ArrayList<Categorie>();

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETALLWITHOUTTOUTE))
			{

			ResultSet rs = pstmtArticle.executeQuery();
			while(rs.next())
			{
				Categorie categorie = new Categorie(rs.getInt("no_categorie"),
										rs.getString("libelle"));

				listeCategorie.add(categorie);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCategorie;
	}
	@Override
	public Categorie getCategorie(String libelle) {
		Categorie categorie = null;
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtCat = cnx.prepareStatement(GETCATEGORIE))
			{

			pstmtCat.setString(1, "%"+libelle+"%");
			
			ResultSet rs = pstmtCat.executeQuery();
			while(rs.next())
			{
				 categorie = new Categorie(rs.getInt("no_categorie"),
										rs.getString("libelle"));

				
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
		
	}

}
