package fr.eni.enchere.bo;

import java.util.Date;
import java.util.List;

public class Enchere {
	private Date dateEnchere;
	private int montant_enchere;
	private ArticleVendu article_vendu;
	
	private List<Utilisateur> utilisateurs;

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

	public ArticleVendu getArticle_vendu() {
		return article_vendu;
	}

	public void setArticle_vendu(ArticleVendu article_vendu) {
		this.article_vendu = article_vendu;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Enchere(Date dateEnchere, int montant_enchere, ArticleVendu article_vendu, List<Utilisateur> utilisateurs) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.article_vendu = article_vendu;
		this.utilisateurs = utilisateurs;
	}
	
	
}
