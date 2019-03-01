package fr.eni.enchere.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private EtatVente etatVente;
	private Categorie categorie;
	private Utilisateur proprietaire;
	private Retrait retrait;
	private Utilisateur gagnant;
	private boolean retire;
	
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public EtatVente getEtatVente() {
		return etatVente;
	}
	public void setEtatVente(EtatVente etatVente) {
		this.etatVente = etatVente;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Utilisateur getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Utilisateur proprietaire) {
		this.proprietaire = proprietaire;
	}
	public Retrait getRetrait() {
		return retrait;
	}
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	public Utilisateur getGagnant() {
		return gagnant;
	}
	public void setGagnant(Utilisateur gagnant) {
		this.gagnant = gagnant;
	}
	public boolean isRetire() {
		return retire;
	}
	public void setRetire(boolean retire) {
		this.retire = retire;
	}
	public Article() {
		super();
	}
	
	public Article(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, EtatVente etatVente, Categorie categorie,Utilisateur proprietaire) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.categorie = categorie;
		this.proprietaire = proprietaire;
		this.retire = false;
	}
	
	public Article(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, Categorie categorie,Utilisateur proprietaire) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorie = categorie;
		this.proprietaire = proprietaire;
		this.retire = false;
	}
	
	public void updateEtat() {
		Date currentDate = new Date();
		if (this.retire) {
			this.etatVente = EtatVente.RETRAIT_EFFECTUE;
		}
		else if(currentDate.before(this.dateDebutEncheres)) {
			this.etatVente = EtatVente.CREE;
		}
		else if(currentDate.after(this.dateDebutEncheres)&& currentDate.before(dateFinEncheres)) {
			this.etatVente = EtatVente.EN_COURS;
		}
		else if(currentDate.after(this.dateFinEncheres)) {
			this.etatVente = EtatVente.ENCHERE_TERMINE;
		}
	}
	
	public String printDateDebutEnchere() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		return sdf.format(this.dateDebutEncheres);
	}
	
	public String printDateFinEnchere() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		return sdf.format(this.dateFinEncheres);
	}
}
