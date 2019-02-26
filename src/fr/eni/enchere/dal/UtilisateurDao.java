package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.Article;


public interface UtilisateurDao {

	
	public void insert(Utilisateur utilisateur);
	public Utilisateur getUser(String pseudo, String mdp);
	public List<Article> getListVente(Utilisateur user);
	
	public List<Article> getListAchat(Utilisateur user);

	
	public void updateUser(Utilisateur user);

	public void deleteUser(Utilisateur user);
}
