package fr.eni.enchere.dal;

import java.text.ParseException;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

public interface ArticleDao {
	
	public void insert(Article article);
	public void getEtat(Article article) throws ParseException;
	public List<Article> getArticleEnCours();
	public List<Article> getArticleByCategorieSearch(String categorie, String search);
	public void deleteArticle(Article article);
	public Article getArticleById(int id);
	
	public List<Article> getEnchereEnCoursOuverte(Utilisateur utilisateur);
	public List<Article> getEnchereOuverte(Utilisateur utilisateur);
	public List<Article> getEnchereEnCours(Utilisateur utilisateur);
	public List<Article> getEnchereOuvertRemp(Utilisateur utilisateur);
	public List<Article> getEnchereEnCourRemp(Utilisateur utilisateur);
	public List<Article> getEnchereRemporte(Utilisateur utilisateur);
	
	public List<Article> getVenteNonDebEnCours(Utilisateur utilisateur);
	public List<Article> getVenteDebute(Utilisateur utilisateur);
	public List<Article> getVenteEnCoursTermine(Utilisateur utilisateur);
	public List<Article> getVenteNonDebTermine(Utilisateur utilisateur);
	public List<Article> getVenteTermine(Utilisateur utilisateur);
	public List<Article> getVenteEnCours(Utilisateur utilisateur);
	public Article trieWithCat(String cat, Article article);
	public Article trieWithCatSearch(String cat, String search, Article article);
	public Article trieWithSearch(String search, Article article);

}
