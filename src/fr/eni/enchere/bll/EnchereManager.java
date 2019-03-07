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
		Enchere lastEnchere = getLastEnchere(article);
		Utilisateur lastUser =null;
		boolean getEnoughtPoint = false;
		if (lastEnchere != null) {
			lastUser = lastEnchere.getUtilisateur();
		}
		if (lastUser != null && lastUser.getPseudo().equals(user.getPseudo())) {
			getEnoughtPoint = (value-lastEnchere.getMontant_enchere()< user.getCredit());
			lastUser = user;
		}
		else {
			getEnoughtPoint = value<user.getCredit();
		}
		
		if (getEnoughtPoint) {
			if (lastUser !=null) {
				// On rend les points au précédent enchérisseurs
				lastUser.setCredit(lastUser.getCredit()+lastEnchere.getMontant_enchere());
				DAOFactory.getUtilisateurDao().updateUser(lastUser);
			}
			
			// On retire les points au nouvel enchérisseur
			user.setCredit(user.getCredit() - value);
			DAOFactory.getUtilisateurDao().updateUser(user);
			
			// Maj du prix de l'article
			article.setPrixVente(value);
			article.setGagnant(user);
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
