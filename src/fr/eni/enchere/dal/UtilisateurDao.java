package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.Article;


public interface UtilisateurDao {

	
	public void insert(Utilisateur utilisateur);
	public Utilisateur getUtilisateur(String pseudo, String mdp);
	public List<Article> getListVente(int idUser);
	
	public List<Article> getListAchat(int idUser);

	
	public void updateUser(Utilisateur utilisateur);

}
