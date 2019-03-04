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
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

public class ArticleDaoJdbcImpl implements ArticleDao{

	private static final String GETETAT = "select date_debut_encheres, date_fin_encheres from ARTICLES_VENDUS where no_article = ? "; 
	private static final String GETUSER = "select * from UTILISATEURS where no_utilisateur = ? ;";
	private static final String INSERT_ARTICLE = "insert into ARTICLES_VENDUS(nom_article, description, date_debut_encheres,date_fin_encheres,prix_initial, prix_vente,no_utilisateur,no_categorie)\r\n" + 
			"values(?,?,?,?,?,?,?,?)";
	private static final String INSERT_RETRAIT = "insert into RETRAITS(no_article,rue,code_postal,ville)"+
			"values(?,?,?,?);";
	
	private static final String GETCATEGORIE = "select * from CATEGORIES where no_categorie = ?";
	
	private static final String GETARTCILEENCOURS = "select * from ARTICLES_VENDUS where cast(date_fin_encheres AS DATETIME) - GETDATE() > 0;";
	private static final String GETBYCAT = "select * from ARTICLES_VENDUS av join CATEGORIES c on (av.no_categorie = c.no_categorie) where c.libelle = ?";
	private static final String GETBYCATSEARCH = "select * from ARTICLES_VENDUS av join CATEGORIES c on (av.no_categorie = c.no_categorie) where c.libelle = ? and av.nom_article like %?%";
	private static final String GETBYSEARCH = "select * from ARTICLES_VENDUS where nom_article like '?'";
	
	private static final String GET_ARTCILE_BY_ID = "select ARTICLES_VENDUS.no_article as no_article,\r\n" + 
			"ARTICLES_VENDUS.nom_article as nom_article,\r\n" + 
			"ARTICLES_VENDUS.description as description,\r\n" + 
			"ARTICLES_VENDUS.date_debut_encheres as date_debut_encheres,\r\n" + 
			"ARTICLES_VENDUS.date_fin_encheres as date_fin_encheres,\r\n" + 
			"ARTICLES_VENDUS.prix_initial as prix_initial,\r\n" + 
			"ARTICLES_VENDUS.prix_vente as prix_vente,\r\n" + 
			"ARTICLES_VENDUS.no_gagnant as no_gagnant,\r\n" + 
			"ARTICLES_VENDUS.retire as retire,\r\n" + 
			"ARTICLES_VENDUS.no_utilisateur as no_utilisateur,\r\n" + 
			"UTILISATEURS.pseudo as pseudo_proprietaire,\r\n" + 
			"CATEGORIES.no_categorie as no_categorie,\r\n" + 
			"CATEGORIES.libelle as libelle_categorie,\r\n" + 
			"RETRAITS.rue as rue_retrait,\r\n" + 
			"RETRAITS.code_postal as code_postal_retrait,\r\n" + 
			"RETRAITS.ville as ville_retrait\r\n" + 
			" from ARTICLES_VENDUS\r\n" + 
			"LEFT JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie\r\n" + 
			"LEFT JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article\r\n" + 
			"LEFT JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur\r\n" + 
			" where ARTICLES_VENDUS.no_article = ?;";
	
	private static final String UPDATE_ARTICLE ="UPDATE ARTICLES_VENDUS\r\n" + 
			"   SET nom_article = ?\r\n" + 
			"      ,description = ?\r\n" + 
			"      ,date_debut_encheres = ?\r\n" + 
			"      ,date_fin_encheres = ?\r\n" + 
			"      ,prix_initial = ?\r\n" + 
			"      ,prix_vente = ?\r\n" + 
			"      ,no_categorie = ?\r\n" + 
			"      ,retire = ?\r\n" + 
			" WHERE no_article =?;";
	private static final String UPDATE_CATEGORIE ="UPDATE RETRAITS\r\n" + 
			"   SET rue = ?\r\n" + 
			"      ,code_postal = ?\r\n" + 
			"      ,ville = ?\r\n" + 
			" WHERE no_article =?";
	
	private static final String DELETEARTICLE = "delete * from ARTICLES_VENDUS where no_article = ?";
	
	private static final String GETTOPENCHERE = "select distinct no_article, no_utilisateur from ENCHERES where no_utilisateur=?";
	private static final String GETENCHEREENCOURS = "select * from ARTICLES_VENDUS where cast(date_debut_encheres AS DATETIME) - GETDATE() < 0 and no_article = ? ;";
	private static final String GETENCHEREOUVERTE = "select * from ARTICLES_VENDUS where cast(date_debut_encheres AS DATETIME) - GETDATE() < 0; ";
	private static final String GETENCHEREREMP = "select * from ARTICLES_VENDUS where no_gagnant = ? ;";
	private static final String ENCHEREENCOURSOUVERTE = "select * from ARTICLES_VENDUS where cast(date_debut_encheres AS DATETIME) - GETDATE() < 0 and no_utilisateur = ? union select * from ARTICLES_VENDUS where cast(date_debut_encheres AS DATETIME) - GETDATE() < 0; ";
	private static final String ENCHEREOUVERTEREMP = "select * from ARTICLES_VENDUS where cast(date_debut_encheres AS DATETIME) - GETDATE() < 0 union select * from ARTICLES_VENDUS where no_gagnant = ? ;";
	private static final String ENCHEREENCOURSREMP = "select * from ARTICLES_VENDUS where cast(date_debut_encheres AS DATETIME) - GETDATE() < 0 and no_utilisateur = ? union select * from ARTICLES_VENDUS where no_gagnant = ? ";
	
	private static final String GETVENTEENCOURS = "select * from ARTICLES_VENDUS where no_utilisateur = ? and cast(date_debut_encheres AS DATETIME) - GETDATE() < 0 and cast(date_fin_encheres AS DATETIME) - GETDATE() > 0 ;" ;
	private static final String GETVENTEDEBUTE = "select * from ARTICLES_VENDUS where no_utilisateur = ? and cast(date_debut_encheres AS DATETIME) - GETDATE() > 0 ;" ;
	private static final String GETVENTEFIN = "select * from ARTICLES_VENDUS where no_utilisateur = ? and cast(date_fin_encheres AS DATETIME) - GETDATE() < 0 ;";
	private static final String VENTEENCOURSNONDEB = "select * from ARTICLES_VENDUS where no_utilisateur = ? and cast(date_debut_encheres AS DATETIME) - GETDATE() < 0 and cast(date_fin_encheres AS DATETIME) - GETDATE() > 0 union select * from ARTICLES_VENDUS where no_utilisateur = ? and cast(date_debut_encheres AS DATETIME) - GETDATE() > 0 ;" ;
	private static final String VENTEENCOURSFIN = "select * from ARTICLES_VENDUS where no_utilisateur = ? and cast(date_debut_encheres AS DATETIME) - GETDATE() < 0 and cast(date_fin_encheres AS DATETIME) - GETDATE() > 0 union select * from ARTICLES_VENDUS where no_utilisateur = ? and cast(date_fin_encheres AS DATETIME) - GETDATE() < 0 ";
	private static final String VENTEDEBUTFIN = "select * from ARTICLES_VENDUS where no_utilisateur = ? and cast(date_debut_encheres AS DATETIME) - GETDATE() > 0 union select * from ARTICLES_VENDUS where no_utilisateur = ? and cast(date_fin_encheres AS DATETIME) - GETDATE() < 0 ";
	
	
	private static final String GETCAT = "select * from CATEGORIES where libelle = ? ;";
	private static final String GETARTICLE= "select * from ARTICLES_VENDUS where no_categorie = ? and no_article = ?";
	private static final String GETARTICLECATSEARCH = "select * from ARTICLES_VENDUS where no_categorie = ? and no_article = ? and nom_article like ? ";
	private static final String GETARTICLESEARCH = "select * from ARTICLES_VENDUS where no_article = ? and nom_article like ? ;";
	
	public void insert(Article article) {
		Categorie categorie =  article.getCategorie();
		Utilisateur user = article.getProprietaire();
		Retrait retrait = article.getRetrait();
		
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(INSERT_ARTICLE,Statement.RETURN_GENERATED_KEYS))
				{				
				if(article != null) {
					
					pstmtArticle.setString(1, article.getNomArticle());
					pstmtArticle.setString(2, article.getDescription());
					pstmtArticle.setTimestamp(3,new java.sql.Timestamp( article.getDateDebutEncheres().getTime()));
					pstmtArticle.setTimestamp(4, new java.sql.Timestamp(article.getDateFinEncheres().getTime()));
					pstmtArticle.setInt(5, article.getMiseAPrix());
					pstmtArticle.setInt(6, article.getMiseAPrix());
					pstmtArticle.setInt(7, user.getNoUtilisateur());
					pstmtArticle.setInt(8, categorie.getNoCategorie());
				
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
		
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement ps = cnx.prepareStatement(INSERT_RETRAIT))
			{
				if(article != null) {
					
					ps.setInt(1, article.getNoArticle());
					ps.setString(2, retrait.getRue());
					ps.setString(3, retrait.getCode_postale());
					ps.setString(4, retrait.getVille());
				
					ps.executeUpdate();
					
				}
					
			}//Fermeture automatique de la connexion
			catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public Article getArticleById(int id) {
		Article art = null;
		
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement ps = cnx.prepareStatement(GET_ARTCILE_BY_ID);)
		{
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Retrait retrait = new Retrait();
				Categorie categorie = new Categorie();
				Utilisateur proprietaire = new Utilisateur();
				
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle_categorie"));
				
				retrait.setRue(rs.getString("rue_retrait"));
				retrait.setCode_postale(rs.getString("code_postal_retrait"));
				retrait.setVille(rs.getString("ville_retrait"));
				
				proprietaire.setNoUtilisateur(Integer.parseInt(rs.getString("no_utilisateur")));
				proprietaire.setPseudo(rs.getString("pseudo_proprietaire"));
				
				art = new Article();
				art.setNoArticle(rs.getInt("no_article"));
				art.setNomArticle(rs.getString("nom_article"));
				art.setDescription(rs.getString("description"));
				art.setDateDebutEncheres(new Date(rs.getTimestamp("date_debut_encheres").getTime()));
				art.setDateFinEncheres(new Date(rs.getTimestamp("date_fin_encheres").getTime()));
				art.setMiseAPrix(rs.getInt("prix_initial"));
				art.setPrixVente(rs.getInt("prix_vente"));
				
				art.setCategorie(categorie);
				art.setRetrait(retrait);
				art.setProprietaire(proprietaire);
				
				System.out.println(retrait.toString());
			}
			
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
				
		return art;
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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
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
		
		if(categorie.equalsIgnoreCase("Toutes") && search != null) {
			req = GETBYSEARCH;
		}
		else if(categorie.equalsIgnoreCase("Toutes") && search == null) {
			List<Article> listeArticle = new ArrayList<Article>();

			listeArticle = getArticleEnCours();
			return listeArticle;
		}
		else if(categorie != null && search != null) {
			
			req = GETBYCATSEARCH;
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
			if(req == GETBYCATSEARCH ) {
				pstmtArticle.setString(1, categorie);
				pstmtArticle.setString(2, "%"+search+"%");

			}
			else if(req == GETBYSEARCH) {
				pstmtArticle.setString(1, "%"+search+"%");
			}
			else if(req == GETBYCAT) {
				pstmtArticle.setString(1, categorie);
			}
			

			ResultSet rs = pstmtArticle.executeQuery();
			while(rs.next())
			{
				
				pstmtCategorie.setInt(1,rs.getInt("no_categorie"));

				ResultSet rsCat = pstmtCategorie.executeQuery();
				while(rsCat.next()) {
				 uneCategorie = new Categorie(rsCat.getInt("no_categorie"),rsCat.getString("libelle"));

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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
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

				pstmtUser.executeQuery();
				
			}//Fermeture automatique de la connexion
			catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void updateArticle(Article article) {
		
		try(Connection cnx = ConnectionProvider.getConnection();)
		{
			PreparedStatement ps = cnx.prepareStatement(UPDATE_ARTICLE);
			ps.setString(1, article.getNomArticle());
			ps.setString(2, article.getDescription());
			ps.setTimestamp(3, new java.sql.Timestamp( article.getDateDebutEncheres().getTime()));
			ps.setTimestamp(4, new java.sql.Timestamp( article.getDateFinEncheres().getTime()));
			ps.setInt(5, article.getPrixVente());
			ps.setInt(6, article.getPrixVente());
			ps.setInt(7, article.getCategorie().getNoCategorie());
			ps.setByte(8, new Byte(article.isRetire()?"0":"1"));
			ps.setInt(9, article.getNoArticle());
			
			ps.executeUpdate();
			
			ps = cnx.prepareStatement(UPDATE_CATEGORIE);
			ps.setString(1, article.getRetrait().getRue());
			ps.setString(2, article.getRetrait().getCode_postale());
			ps.setString(3, article.getRetrait().getVille());
			ps.setInt(4, article.getNoArticle());
			
			ps.executeUpdate();
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Article> getEnchereEnCoursOuverte(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(ENCHEREENCOURSOUVERTE);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{

			
			pstmtArticle.setInt(1,utilisateur.getNoUtilisateur());

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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeEnchere;
	}
	

	@Override
	public List<Article> getEnchereOuverte(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETENCHEREOUVERTE);
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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}
	
	@Override
	public List<Article> getEnchereEnCours(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmtTop = cnx.prepareStatement(GETTOPENCHERE);
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETENCHEREENCOURS);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{

			pstmtTop.setInt(1,utilisateur.getNoUtilisateur() );
			ResultSet rsTop = pstmtTop.executeQuery();

			while(rsTop.next()) {
			
			pstmtArticle.setInt(1,rsTop.getInt("no_article"));

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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}

	@Override
	public List<Article> getEnchereOuvertRemp(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(ENCHEREOUVERTEREMP);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{

			
			pstmtArticle.setInt(1,utilisateur.getNoUtilisateur());

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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}

	@Override
	public List<Article> getEnchereEnCourRemp(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(ENCHEREENCOURSREMP);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{

			
			pstmtArticle.setInt(1,utilisateur.getNoUtilisateur());

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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}

	@Override
	public List<Article> getEnchereRemporte(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETENCHEREREMP);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{

			
			pstmtArticle.setInt(1,utilisateur.getNoUtilisateur());

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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}

	
	
	@Override
	public List<Article> getVenteNonDebEnCours(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(VENTEENCOURSNONDEB);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{
			pstmtArticle.setInt(1,utilisateur.getNoUtilisateur());
			pstmtArticle.setInt(2,utilisateur.getNoUtilisateur());



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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}

	@Override
	public List<Article> getVenteDebute(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETVENTEDEBUTE);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{
			pstmtArticle.setInt(1,utilisateur.getNoUtilisateur());

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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}

	@Override
	public List<Article> getVenteEnCoursTermine(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(VENTEENCOURSFIN);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{
			pstmtArticle.setInt(1,utilisateur.getNoUtilisateur());
			pstmtArticle.setInt(2,utilisateur.getNoUtilisateur());



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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}

	@Override
	public List<Article> getVenteNonDebTermine(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(VENTEDEBUTFIN);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{
			pstmtArticle.setInt(1,utilisateur.getNoUtilisateur());
			pstmtArticle.setInt(2,utilisateur.getNoUtilisateur());



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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}

	@Override
	public List<Article> getVenteTermine(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETVENTEFIN);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{
			pstmtArticle.setInt(1,utilisateur.getNoUtilisateur());


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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}

	@Override
	public List<Article> getVenteEnCours(Utilisateur utilisateur) {
		List<Article> listeEnchere = new ArrayList<Article>();
		Categorie categorie = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETVENTEENCOURS);
				PreparedStatement pstmtCategorie = cnx.prepareStatement(GETCATEGORIE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{
			pstmtArticle.setInt(1,utilisateur.getNoUtilisateur());


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
										new Date(rs.getTimestamp("date_debut_encheres").getTime()),
										new Date(rs.getTimestamp("date_fin_encheres").getTime()),
										rs.getInt("prix_initial"),
										rs.getInt("prix_vente"),
										 categorie,
										 user);
				listeEnchere.add(article);
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listeEnchere;
	}

	@Override
	public Article trieWithCat(String cat, Article article) {
		Categorie categorie = null;
		Article unArticle = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtCat = cnx.prepareStatement(GETCAT);
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETARTICLE);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{
			pstmtCat.setString(1,cat);


			ResultSet rs = pstmtCat.executeQuery();
			while(rs.next())
			{
				categorie = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));

				pstmtUser.setInt(1,article.getProprietaire().getNoUtilisateur());

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
				pstmtArticle.setInt(1,rs.getInt("no_categorie"));
				pstmtArticle.setInt(2,article.getNoArticle());


				ResultSet rsArticle = pstmtArticle.executeQuery();
				while(rsArticle.next()) {
					 unArticle = new Article(rsArticle.getInt("no_article"),
							 rsArticle.getString("nom_article"), 
							rsArticle.getString("description"), 
							new Date(rs.getTimestamp("date_debut_encheres").getTime()),
							new Date(rs.getTimestamp("date_fin_encheres").getTime()),
							rsArticle.getInt("prix_initial"),
							rsArticle.getInt("prix_vente"),
							 categorie,
							 user);
				}
				
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return unArticle;
	}
	@Override
	public Article trieWithCatSearch(String cat,String search, Article article) {
		Categorie categorie = null;
		Article unArticle = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtCat = cnx.prepareStatement(GETCAT);
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETARTICLECATSEARCH);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{
			pstmtCat.setString(1,cat);


			ResultSet rs = pstmtCat.executeQuery();
			while(rs.next())
			{
				categorie = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle")); 
				
				pstmtUser.setInt(1, article.getProprietaire().getNoUtilisateur());

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
				pstmtArticle.setInt(1,rs.getInt("no_categorie"));
				pstmtArticle.setInt(2,article.getNoArticle());
				pstmtArticle.setString(3,'%'+search+'%');


				ResultSet rsArticle = pstmtArticle.executeQuery();
				while(rsArticle.next()) {
					 unArticle = new Article(rsArticle.getInt("no_article"),
							 rsArticle.getString("nom_article"), 
							 rsArticle.getString("description"), 
							 new Date(rs.getTimestamp("date_debut_encheres").getTime()),
								new Date(rs.getTimestamp("date_fin_encheres").getTime()),
							 rsArticle.getInt("prix_initial"),
							 rsArticle.getInt("prix_vente"),
							 categorie,
							 user);
				}
				
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return unArticle;
	}
	@Override
	public Article trieWithSearch(String search, Article article) {
		Categorie categorie = null;
		Article unArticle = null;
		Utilisateur user = null;

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmtCat = cnx.prepareStatement(GETCAT);
				PreparedStatement pstmtArticle = cnx.prepareStatement(GETARTICLESEARCH);
				PreparedStatement pstmtUser = cnx.prepareStatement(GETUSER))
			{
			pstmtCat.setString(1,article.getCategorie().getLibelle());


			ResultSet rs = pstmtCat.executeQuery();
			while(rs.next())
			{
				categorie = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
				
				pstmtUser.setInt(1, article.getProprietaire().getNoUtilisateur());


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
				

				pstmtArticle.setInt(1,article.getNoArticle());
				pstmtArticle.setString(2,'%'+search+'%');


				ResultSet rsArticle = pstmtArticle.executeQuery();
				while(rsArticle.next()) {
					 unArticle = new Article(rsArticle.getInt("no_article"),
							 rsArticle.getString("nom_article"), 
							 rsArticle.getString("description"), 
							 new Date(rs.getTimestamp("date_debut_encheres").getTime()),
								new Date(rs.getTimestamp("date_fin_encheres").getTime()),
							 rsArticle.getInt("prix_initial"),
							 rsArticle.getInt("prix_vente"),
							 categorie,
							 user);
				}
				
			}
		}//Fermeture automatique de la connexion
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return unArticle;
	}
	
}
