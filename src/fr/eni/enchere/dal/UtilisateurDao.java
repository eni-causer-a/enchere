package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDao {

	
	public void insert(Utilisateur utilisateur);
	public Utilisateur getUtilisateur(String pseudo, String mdp);

}
