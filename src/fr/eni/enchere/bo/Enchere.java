package fr.eni.enchere.bo;

import java.util.Date;
import java.util.List;

public class Enchere {
	private Utilisateur utilisateur;
	private Date dateEnchere;
	private int montant_enchere;
	private Article article;

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public Article getArticle_vendu() {
		return article;
	}

	public void setArticle_vendu(Article article_vendu) {
		this.article = article_vendu;
	}
	
	public Enchere() {
		super();
	}

	public Enchere(Utilisateur utilisateur, Date dateEnchere, int montant_enchere, Article article_vendu) {
		super();
		this.utilisateur = utilisateur;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.article = article_vendu;
	}
	
	
}
