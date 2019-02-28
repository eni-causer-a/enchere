package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDao;

public class UtilisateurManager {
	
	private UtilisateurDao utilisateurDao;
	
	public UtilisateurManager() {
		this.utilisateurDao = DAOFactory.getUtilisateurDao();
	}
	
	public void createUtilisateur(Utilisateur user) {
		//Verification de la non nullité des valeurs
		if (user != null) {
			utilisateurDao.insert(user);
		}
	}
	
	public Utilisateur getUtilisateur(String pseudo, String mdp) {
		return utilisateurDao.getUser(pseudo, mdp);
	}
	
	public List<Article> getListVentes(Utilisateur user){
		if (user!=null) {
			return utilisateurDao.getListVente(user);
		}
		return null;
	}
	
	public Utilisateur findUserById(int id) {
		return utilisateurDao.findUserById(id);
	}
	
	public void update(Utilisateur user) {
		utilisateurDao.updateUser(user);
	}
	
	public void delete(Utilisateur user) {
		utilisateurDao.deleteUser(user);
	}	
}
