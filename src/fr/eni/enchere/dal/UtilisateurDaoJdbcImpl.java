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


public class UtilisateurDaoJdbcImpl implements UtilisateurDao{
	
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,0,0);";

	private static final String LAUNCH_REC = "UPDATE UTILISATEURS SET nb_recup= ? WHERE email = ? ; ";
	
	private static final String REC_REC = "SELECT * FROM UTILISATEURS WHERE nb_recup= ? ; ";
	
	private static final String END_REC = "UPDATE UTILISATEURS SET mot_de_passe= ? , nb_recup = null WHERE nb_recup = ? ; ";
	
	
	private static final String GETUSER = "select * from UTILISATEURS where pseudo = ? and mot_de_passe = ? ;";
	
	private static final String GETVENTEUSER = "select * from ARTICLES_VENDUS where no_utilisateur = ?"; 

	private static final String GETCATEGORIE = "select * from CATEGORIES where no_categorie = ?"; 
	
	private static final String UPDATEUSER = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=? where no_utilisateur = ? ; ";

	private static final String GETACHATUSER = "select distinct ";
	
	
	private static final String DELETEUSER = "update UTILISATEURS set mot_de_passe='' where no_utilisateur = ? ;";
	private static final String DELETE_ARTICLE="delete from ARTICLES_VENDUS \r\n" + 
			"where no_utilisateur=?\r\n" + 
			"AND date_fin_encheres > GETDATE();";
	private static final String DELETE_ENCHERE="delete from ENCHERES\r\n" + 
			"where no_article=(\r\n" + 
			"	select no_article from ARTICLES_VENDUS\r\n" + 
			"	where no_utilisateur=?\r\n" + 
			"	AND date_fin_encheres > GETDATE());";
	private static final String DELETE_RETRAIT="delete from RETRAITS\r\n" + 
			"where no_article=(\r\n" + 
			"	select no_article from ARTICLES_VENDUS\r\n" + 
			"	where no_utilisateur=?\r\n" + 
			"	AND date_fin_encheres > GETDATE());";
	
	private static final String FINDUSERBYID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ? ;";
	
	private static final String GET_USER_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo LIKE ?;";
	
	private static final String GET_ALL_USERS = "SELECt * FROM UTILISATEURS;";
	
	public void insert(Utilisateur user) {
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtUser = cnx.prepareStatement(INSERT_USER,Statement.RETURN_GENERATED_KEYS))
			{
						
			if(!pseudoTaken(user.getPseudo())) {
				pstmtUser.setString(1, user.getPseudo());
				pstmtUser.setString(2, user.getNom());
				pstmtUser.setString(3, user.getPrenom());
				pstmtUser.setString(4, user.getEmail());
				pstmtUser.setString(5, user.getTelephone());
				pstmtUser.setString(6, user.getRue());
				pstmtUser.setString(7, user.getCodePostale());
				pstmtUser.setString(8, user.getVille());
				pstmtUser.setString(9, user.getMotDePasse());
				
				
				pstmtUser.execute();
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
	
	@Override
	public boolean pseudoTaken(String pseudo) {
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtUser = cnx.prepareStatement(GET_USER_BY_PSEUDO);)
		{
			pstmtUser.setString(1, pseudo);

			ResultSet rs = pstmtUser.executeQuery();
			return rs.next();
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
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
					user = new Utilisateur(rs.getInt("no_utilisateur"),
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
			pstmtUser.setInt(10, user.getCredit());
			pstmtUser.setInt(11, user.getNoUtilisateur());

			pstmtUser.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
	}


	


	@Override
	public List<Article> getListAchat(Utilisateur user) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteUser(Utilisateur user) {
		try(Connection cnx = ConnectionProvider.getConnection();)
		{
			PreparedStatement ps = cnx.prepareStatement(DELETE_ENCHERE);
			ps.setInt(1, user.getNoUtilisateur());
			ps.executeUpdate();
			
			ps = cnx.prepareStatement(DELETE_RETRAIT);
			ps.setInt(1, user.getNoUtilisateur());
			ps.executeUpdate();
			
			ps = cnx.prepareStatement(DELETE_ARTICLE);
			ps.setInt(1, user.getNoUtilisateur());
			ps.executeUpdate();

			ps = cnx.prepareStatement(DELETEUSER);
			ps.setInt(1, user.getNoUtilisateur());
			ps.executeUpdate();
			
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public Utilisateur findUserById(int id) {
		Utilisateur user = null;
		
		
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtUser = cnx.prepareStatement(FINDUSERBYID);)
		{
			pstmtUser.setInt(1, id);

			ResultSet rs = pstmtUser.executeQuery();
			while(rs.next())
			{
				if(rs.getString("pseudo")!=null)
				{
					user = new Utilisateur(rs.getInt("no_utilisateur"),
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

	@Override
	public void launchRecup(int nb, String mail) {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtUser = cnx.prepareStatement(LAUNCH_REC)){
			
					pstmtUser.setInt(1, nb);
					pstmtUser.setString(2, mail);
					System.out.println("send");
					pstmtUser.executeUpdate();
					
				}//Fermeture automatique de la connexion
				catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	@Override
	public boolean setRecup(int nb) {
		Utilisateur user = null;
		
		
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtUser = cnx.prepareStatement(REC_REC);)
		{
			pstmtUser.setInt(1, nb);


			ResultSet rs = pstmtUser.executeQuery();
			while(rs.next())
			{
				if(rs.getString("pseudo")!=null)
				{
					return true;
				}
			}
			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
				
	}

	@Override
	public void endRecup(int nb, String mdp) {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtUser = cnx.prepareStatement(END_REC)){
			
				pstmtUser.setString(1, mdp);
				pstmtUser.setInt(2, nb);
				pstmtUser.executeUpdate();
				
			}//Fermeture automatique de la connexion
			catch (SQLException e) {
				e.printStackTrace();
			}	
	}
	
	@Override
	public List<Utilisateur> getAllUtilisateurs(){
		List<Utilisateur> res = new ArrayList<Utilisateur>();
		
		try(Connection cnx = ConnectionProvider.getConnection();){
			PreparedStatement ps = cnx.prepareStatement(GET_ALL_USERS);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Utilisateur user = new Utilisateur(rs.getInt("no_utilisateur"),
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
				res.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
