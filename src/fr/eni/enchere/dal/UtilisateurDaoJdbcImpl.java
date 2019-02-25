package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;




public class UtilisateurDaoJdbcImpl {
	
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur VALUES(?,?,?,?,?,?,?,?,?,?,0,0);";

	private static final String GETUSER = "select * from user where pseudo = ? and mot_de_passe = ? ;";
	
	private static final String GETVENTEUSER = "select * from ARTICLES_VENDUS where no_utilisateur = ?"; 

	private static final String GETCATEGORIE = "select * from CATEGORIES where no_categorie = ?"; 
	
	private static final String UPDATEUSER = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?,credit=?, administrateur=? where no_utilisateur = ? ; ";

	private static final String GETACHATUSER= "select distinct ";
	
	public void insert(Utilisateur user) {
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtUser = cnx.prepareStatement(INSERT_USER,Statement.RETURN_GENERATED_KEYS))
			{
			Utilisateur userVerif = getUser(user.getPseudo(),user.getNom());
			
			if(userVerif == null) {
			pstmtUser.setString(1, user.getPseudo());
			pstmtUser.setString(2, user.getNom());
			pstmtUser.setString(3, user.getPrenom());
			pstmtUser.setString(4, user.getEmail());
			pstmtUser.setString(5, user.getTelephone());
			pstmtUser.setString(6, user.getRue());
			pstmtUser.setString(7, user.getCodePostale());
			pstmtUser.setString(8, user.getVille());
			pstmtUser.setString(9, user.getMotDePasse());
			
			
			pstmtUser.executeUpdate();
				ResultSet rs = pstmtUser.getGeneratedKeys();
				if(rs.next())
				{
					user.setNoUtilisateur(rs.getInt(1));
				}
				
			}
				
			}//Fermeture automatique de la connexion
			catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	public Utilisateur getUser(String pseudo, String mdp) {
		Utilisateur user = null;
		
		
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER);)
		{
			pstmtUser.setString(1, pseudo);
			pstmtUser.setString(2, mdp);

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
	
	
	public List<Article> getListVente(Utilisateur user) {
		List<Article> listeArticleVendu = new ArrayList<Article>();
		Categorie categorie = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtUser = cnx.prepareStatement(GETVENTEUSER);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE))
			{
			pstmtUser.setInt(1, user.getNoUtilisateur());

			ResultSet rs = pstmtUser.executeQuery();
			while(rs.next())
			{
				
				pstmtCategorie.setInt(1,rs.getInt("no_categorie"));

				ResultSet rsCat = pstmtCategorie.executeQuery();
				while(rsCat.next()) {
				 categorie = new Categorie(rsCat.getInt("no_categorie"),rsCat.getString("libelle"));

				}
				Article article = new Article(rs.getInt("noArticle"),
										rs.getString("nom_article"), 
										rs.getString("description"), 
										rs.getDate("date_debut_encheres"),
										rs.getDate("date_fin_encheres"),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeArticleVendu.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeArticleVendu;
	}
	
	
	
	public void updateUser(Utilisateur user) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmtUser = cnx.prepareStatement(UPDATEUSER);
			pstmtUser.setString(1, user.getPseudo());
			pstmtUser.setString(2, user.getNom());
			pstmtUser.setString(3, user.getPrenom());
			pstmtUser.setString(4, user.getEmail());
			pstmtUser.setString(5, user.getTelephone());
			pstmtUser.setString(6, user.getRue());
			pstmtUser.setString(7, user.getCodePostale());
			pstmtUser.setString(8, user.getVille());
			pstmtUser.setString(9, user.getMotDePasse());
			pstmtUser.setInt(10, user.getNoUtilisateur());

			pstmtUser.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
