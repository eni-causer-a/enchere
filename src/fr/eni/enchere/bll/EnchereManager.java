package fr.eni.enchere.bll;

import java.util.Date;

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
	
	public boolean encherir(Utilisateur user, Article article, int value) {
		boolean res = true;
		if (user.getCredit() >= value) {
			Enchere lastEnchere = getLastEnchere(article);
			if (lastEnchere != null) {
				Utilisateur lastUser = lastEnchere.getUtilisateur();
				if (lastUser.getPseudo().equals(user.getPseudo())) {
					lastUser = user;
				}
				lastUser.setCredit(lastUser.getCredit()+lastEnchere.getMontant_enchere());
				
				DAOFactory.getUtilisateurDao().updateUser(lastUser);
			}
			
			user.setCredit(user.getCredit() - value);
			DAOFactory.getUtilisateurDao().updateUser(user);
			
			article.setPrixVente(value);
			DAOFactory.getArticleDao().updateArticle(article);
			
			Enchere newEnchere = new Enchere(user, new Date(), value, article);
			insert(newEnchere);
		}
		else {
			res = false;
		}
		return res;
	}
}
