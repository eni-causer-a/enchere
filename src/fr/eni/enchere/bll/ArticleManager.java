package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Article;
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
	

	public List<Article> getArticleByCategorieSearch(String categorie, String search) {
		return articleDao.getArticleByCategorieSearch(categorie, search);   
	}
	
	public void deleteArticle(Article article) {
		if (article != null) {
			articleDao.deleteArticle(article);
		}
	}
	
	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}
}
