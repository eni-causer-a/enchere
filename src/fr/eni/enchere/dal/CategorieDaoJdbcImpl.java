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

}
