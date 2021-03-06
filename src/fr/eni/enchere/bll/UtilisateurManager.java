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
		Utilisateur user =utilisateurDao.getUser(pseudo, mdp);
		Utilisateur res = null;
		if (user != null && user.isActivate()) {
			res = user;
		}
		return res;
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
	
	public boolean pseudoIsTaken(String pseudo) {
		return this.utilisateurDao.pseudoTaken(pseudo);
	}
	
	public void update(Utilisateur user) {
		utilisateurDao.updateUser(user);
	}
	
	public void delete(Utilisateur user) {
		utilisateurDao.deleteUser(user);
	}	
	
	public void launchRecup(int nb, String mail) {
		utilisateurDao.launchRecup(nb, mail);
	}
	
	public boolean isRecup(int nb) {
		return utilisateurDao.setRecup(nb);
	}
	
	public void endRecup(int nb, String mdp) {
		utilisateurDao.endRecup(nb, mdp);
	}
	
	public List<Utilisateur> getAllUtilisateur(){
		return this.utilisateurDao.getAllUtilisateurs();
	}
	
	public void activate(Utilisateur user) {
		if (user !=null) {
			this.utilisateurDao.setActivate(user, true);
		}
	}
	public void desactivate(Utilisateur user) {
		if (user !=null) {
			this.utilisateurDao.setActivate(user, false);
		}
	}
}
