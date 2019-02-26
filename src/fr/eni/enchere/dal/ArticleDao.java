package fr.eni.enchere.dal;

import java.text.ParseException;

import fr.eni.enchere.bo.Article;

public interface ArticleDao {
	
	public void getEtat(Article article) throws ParseException;

}
