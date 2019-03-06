package fr.eni.enchere.bll;

import java.text.ParseException;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDao;
import fr.eni.enchere.dal.DAOFactory;

public class ArticleManager {
	private ArticleDao articleDao;
	
	public ArticleManager() {
		this.articleDao = DAOFactory.getArticleDao();
	}
	
	public void insert(Article article) {
		if (article != null) {
			this.articleDao.insert(article);
		}
	}
	public List<Article> getArticleEnCours() {
		return articleDao.getArticleEnCours();
	}
	
	public void getEtatVente(Article article) {
		articleDao.getEtat(article);
	}
	

	public List<Article> getArticleByCategorieSearch(String categorie, String search) {
		return articleDao.getArticleByCategorieSearch(categorie, search);   
	}
	
	public void deleteArticle(Article article) {
		if (article != null) {
			articleDao.deleteArticle(article);
		}
	}
	
	public Article getArticleById(int id) {
		Article res = this.articleDao.getArticleById(id);
		if (res != null) {
			res.updateEtat();
		}
		return res;
	}

	public List<Article> getEnchereEnCoursOuverte(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getEnchereEnCoursOuverte(utilisateur);
	}

	public List<Article> getEnchereOuverte(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getEnchereOuverte(utilisateur);
	}

	public List<Article> getEnchereEnCours(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getEnchereEnCours(utilisateur);
	}

	public List<Article> getEnchereOuvertRemp(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getEnchereOuvertRemp(utilisateur);
	}

	public List<Article> getEnchereEnCourRemp(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getEnchereEnCourRemp(utilisateur);
	}

	public List<Article> getEnchereRemporte(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getEnchereRemporte(utilisateur);
	}

	public List<Article> getVenteNonDebEnCours(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getVenteNonDebEnCours(utilisateur);
	}

	public List<Article> getVenteDebute(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getVenteDebute(utilisateur);
	}

	public List<Article> getVenteEnCoursTermine(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getVenteEnCoursTermine(utilisateur);
	}

	public List<Article> getVenteNonDebTermine(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getVenteNonDebTermine(utilisateur);
	}

	public List<Article> getVenteTermine(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getVenteTermine(utilisateur);
	}

	public List<Article> getVenteEnCours(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return articleDao.getVenteEnCours(utilisateur);
	}

	public Article trieWithCat(String cat, Article article) {
		// TODO Auto-generated method stub
		return articleDao.trieWithCat(cat,article);
	}

	public Article trieWithCatSearch(String cat, String search, Article article) {
		// TODO Auto-generated method stub
		return articleDao.trieWithCatSearch(cat,search,article);
	}

	public Article trieWithSearch(String search, Article article) {
		// TODO Auto-generated method stub
		return articleDao.trieWithSearch(search, article);
	}
	
	public void updateArticle(Article art) {
		if (art != null) {
			articleDao.updateArticle(art);
		}
	}
	
	public void retractArticle(Article article) {
		if (!article.isRetire()) {
			article.setRetire(true);
			this.articleDao.updateArticle(article);
			
			Utilisateur proprietaire = article.getProprietaire();
			proprietaire.setCredit(proprietaire.getCredit()+ article.getPrixVente());
			DAOFactory.getUtilisateurDao().updateUser(proprietaire);
		}
	}
	
}
