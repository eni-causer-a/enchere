package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.EnchereDao;
import fr.eni.enchere.dal.UtilisateurDao;

public class EnchereManager {
	private EnchereDao enchereDao;
	
	public EnchereManager() {
		this.enchereDao = DAOFactory.getEnchereDao();
	}
	
	public void insert(Enchere enchere) {
		this.enchereDao.insert(enchere);
	}
	
	public Enchere getLastEnchere(Article article) {
		return enchereDao.getLastEnchere(article);
	}
}
