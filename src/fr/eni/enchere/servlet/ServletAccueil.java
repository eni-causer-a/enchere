package fr.eni.enchere.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.ArticleDaoJdbcImpl;

import javax.servlet.RequestDispatcher;


/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/Accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletAccueil() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArticleManager am = new ArticleManager(new ArticleDaoJdbcImpl());
		List<Article> lesArticles = null;
		lesArticles = am.getArticleEnCours();
		request.setAttribute("lesArticles", lesArticles);

		CategorieManager cm = new CategorieManager();
		List<Categorie> lesCategories = null;
		lesCategories = cm.getListCategorie();
		request.setAttribute("lesCategories", lesCategories);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cat = request.getParameter("categorie");
		String search = request.getParameter("filtre");

		ArticleManager am = new ArticleManager(new ArticleDaoJdbcImpl());
		List<Article> lesArticles = null;
		lesArticles = am.getArticleByCategorieSearch(cat, search);
		request.setAttribute("lesArticles", lesArticles);

		CategorieManager cm = new CategorieManager();
		List<Categorie> lesCategories = null;
		lesCategories = cm.getListCategorie();
		request.setAttribute("lesCategories", lesCategories);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}

}
