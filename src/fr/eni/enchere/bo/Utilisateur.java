package fr.eni.enchere.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostale;
	private String ville;
	private String motDePasse;
	private int credit;
	private boolean administareur;
	
	private List<Article> articlesEnVente;
	
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostale() {
		return codePostale;
	}
	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean isAdministareur() {
		return administareur;
	}
	public void setAdministareur(boolean administareur) {
		this.administareur = administareur;
	}
	
	public List<Article> getArticlesEnVente() {
		return articlesEnVente;
	}
	public void setArticlesEnVente(List<Article> articlesEnVente) {
		this.articlesEnVente = articlesEnVente;
	}
	public Utilisateur() {
		super();
		this.articlesEnVente = new ArrayList<Article>();
	}
	
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostale, String ville, String motDePasse, int credit, boolean administareur) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostale = codePostale;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administareur = administareur;
		this.articlesEnVente = new ArrayList<Article>();
	}

}
