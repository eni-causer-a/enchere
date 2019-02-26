package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.dal.ArticleDao;

public class ArticleManager {
	private ArticleDao articleDao;
	
	public ArticleManager(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public List<Article> getArticleEnCours() {
		return articleDao.getArticleEnCours();
	}
	
	public List<Article> getArticleByCategorieSearch(String categorie, String search) {
		return articleDao.getArticleByCategorieSearch(categorie, search);   
	}
}
