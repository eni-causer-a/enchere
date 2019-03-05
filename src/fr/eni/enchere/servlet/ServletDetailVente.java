package fr.eni.enchere.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/DetailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDetailVente() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Article article=null;
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		request.setAttribute("utilisateur", utilisateur);
		EnchereManager em = new EnchereManager();
		 request.setAttribute("em", em);
		ArticleManager manager = new ArticleManager();
		String id = request.getParameter("idArticle");
		 try {
			 article = manager.getArticleById(Integer.parseInt(id));
			 request.setAttribute("article", article);
		} catch (NumberFormatException e) {
			// TODO Gestion d'exception à faire piairyck !!!
		}
		Date date=new Date();
		 
		//System.out.println("DAEZRAR: "+article.getDateDebutEncheres().after(date));
		//System.out.println(article.getDateDebutEncheres().after(date));
		request.setAttribute("after", article.getDateDebutEncheres().after(date));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		ArticleManager am = new ArticleManager();
		EnchereManager em = new EnchereManager();
		
		em.encherir((Utilisateur) session.getAttribute("Utilisateur"), am.getArticleById(Integer.parseInt(request.getParameter("idArticle"))), Integer.parseInt(request.getParameter("miseAPrix")));
		doGet(request, response);
	}
	

}
