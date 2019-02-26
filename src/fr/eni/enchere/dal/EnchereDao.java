package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;

public interface EnchereDao {

	public void insert(Enchere enchere);
	
	public Enchere getLastEnchere(Article article);

	
}
