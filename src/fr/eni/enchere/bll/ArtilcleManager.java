package fr.eni.enchere.bll;

import fr.eni.enchere.dal.ArticleDao;

public class ArtilcleManager {
	private ArticleDao articleDao;
	
	public ArtilcleManager(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	
}
