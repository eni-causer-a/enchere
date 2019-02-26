package fr.eni.enchere.dal;

import java.text.ParseException;
import java.util.List;

import fr.eni.enchere.bo.Article;

public interface ArticleDao {
	
	public void getEtat(Article article) throws ParseException;
	public List<Article> getArticleEnCours();
	public List<Article> getArticleByCategorieSearch(String categorie, String search);
	public void deleteArticle(Article article);

}
