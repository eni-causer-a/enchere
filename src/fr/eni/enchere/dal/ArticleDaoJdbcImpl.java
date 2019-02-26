package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.EtatVente;
import fr.eni.enchere.bo.Utilisateur;

public class ArticleDaoJdbcImpl implements ArticleDao{

	private static final String GETETAT = "select date_debut_encheres, date_fin_encheres from ARTICLES_VENDUS where no_article = ? "; 
	private static final String GETUSER = "select * from UTILISATEURS where no_utilisateur = ? ;";
	private static final String INSERTARTICLE = " insert into ARTICLES_VENDUS values(?,?,?,?,?,?,?,?)";
	private static final String GETCATEGORIE = "select * from CATEGORIES where no_categorie = ?";
	
	private static final String GETARTCILEENCOURS = "select * from ARTICLES_VENDUS where cast(date_fin_encheres AS DATETIME) - GETDATE() > 0;";
	private static final String GETBYCAT = "select * from ARTICLES_VENDUS av join CATEGORIES c on (av.no_categorie == c.no_categorie) where c.libelle = ?";
	private static final String GETBYCATSEARCH = "select * from ARTICLES_VENDUS av join CATEGORIES c on (av.no_categorie == c.no_categorie) where c.libelle = ? and av.nom_article like %?%";
	private static final String GETBYSEARCH = "select * from ARTICLES_VENDUS where nom_article like %?%";
	
	private static final String DELETEARTICLE = "delete * from ARTICLES_VENDUS where no_article = ?";

	
	public void insert(Article article) {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(INSERTARTICLE,Statement.RETURN_GENERATED_KEYS))
				{				
				if(article != null) {
					
					Categorie categorie =  article.getCategorie();
					Utilisateur user = article.getProprietaire();
					pstmtArticle.setString(1, article.getNomArticle());
					pstmtArticle.setString(2, article.getDescription());
					pstmtArticle.setDate(3,new java.sql.Date( article.getDateDebutEncheres().getTime()));
					pstmtArticle.setDate(4, new java.sql.Date(article.getDateFinEncheres().getTime()));
					pstmtArticle.setInt(5, article.getMiseAPrix());
					pstmtArticle.setInt(6, article.getPrixVente());
					pstmtArticle.setInt(7, categorie.getNoCategorie());
					pstmtArticle.setInt(8, user.getNoUtilisateur());
				
				
					pstmtArticle.executeUpdate();
					ResultSet rs = pstmtArticle.getGeneratedKeys();
					if(rs.next())
					{
						article.setNoArticle(rs.getInt(1));
					}
					
				}
					
				}//Fermeture automatique de la connexion
				catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	
	
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



	@Override
	public List<Article> getArticleEnCours() {
		List<Article> listeArticleEnCours = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETARTCILEENCOURS);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{

			ResultSet rs = pstmtArticle.executeQuery();
			while(rs.next())
			{
				
				pstmtCategorie.setInt(1,rs.getInt("no_categorie"));

				ResultSet rsCat = pstmtCategorie.executeQuery();
				while(rsCat.next()) {
				 categorie = new Categorie(rsCat.getInt("no_categorie"),rsCat.getString("libelle"));

				}
				pstmtUser.setInt(1,rs.getInt("no_utilisateur"));

				ResultSet rsUser = pstmtUser.executeQuery();
				while(rsUser.next()) {
					user = new Utilisateur(rsUser.getInt("no_utilisateur"),
							rsUser.getString("pseudo"),
							rsUser.getString("Nom"), 
							rsUser.getString("Prenom"),
							rsUser.getString("email"),
							rsUser.getString("telephone"),
							rsUser.getString("rue"),
							rsUser.getString("code_postal"),
							rsUser.getString("ville"),
							rsUser.getString("mot_de_passe"),
							rsUser.getInt("credit"),
							rsUser.getBoolean("administrateur")
							);

				}
				Article article = new Article(rs.getInt("no_article"),
										rs.getString("nom_article"), 
										rs.getString("description"), 
										rs.getDate("date_debut_encheres"),
										rs.getDate("date_fin_encheres"),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeArticleEnCours.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeArticleEnCours;
	}



	
	
	@Override
	public List<Article> getArticleByCategorieSearch(String categorie, String search) {
		List<Article> listeArticleByCat = new ArrayList<Article>();
		Categorie uneCategorie = null;
		Utilisateur user = null;
		String req = null;
		
		if(categorie != null && search != null) {
			 req = GETBYCATSEARCH;
		}
		else if(categorie == null && search != null) {
			req = GETBYSEARCH;
		}
		else if(categorie != null && search == null) {
			req = GETBYCAT;
		}
		else {
			req = GETARTCILEENCOURS;
		}
		
		
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(req);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{

			pstmtUser.setString(1, categorie);

			ResultSet rs = pstmtArticle.executeQuery();
			while(rs.next())
			{
				
				pstmtCategorie.setInt(1,rs.getInt("no_categorie"));

				ResultSet rsCat = pstmtCategorie.executeQuery();
				while(rsCat.next()) {
				 uneCategorie = new Categorie(rsCat.getInt("no_categorie"),rsCat.getString("libelle"));

				}
				pstmtUser.setInt(1,rs.getInt("no_categorie"));

				ResultSet rsUser = pstmtUser.executeQuery();
				while(rsUser.next()) {
					user = new Utilisateur(rsUser.getInt("noUtilisateur"),
							rsUser.getString("pseudo"),
							rsUser.getString("Nom"), 
							rsUser.getString("Prenom"),
							rsUser.getString("email"),
							rsUser.getString("telephone"),
							rsUser.getString("rue"),
							rsUser.getString("code_postal"),
							rsUser.getString("ville"),
							rsUser.getString("mot_de_passe"),
							rsUser.getInt("credit"),
							rsUser.getBoolean("administrateur")
							);

				}
				Article article = new Article(rs.getInt("noArticle"),
										rs.getString("nom_article"), 
										rs.getString("description"), 
										rs.getDate("date_debut_encheres"),
										rs.getDate("date_fin_encheres"),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 uneCategorie,
										 user);
				listeArticleByCat.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeArticleByCat;
	}

	@Override
	public void deleteArticle(Article article) {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtUser = cnx.prepareStatement(DELETEARTICLE);)
			{
				pstmtUser.setInt(1, article.getNoArticle());

				ResultSet rs = pstmtUser.executeQuery();
				
			}//Fermeture automatique de la connexion
			catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}
