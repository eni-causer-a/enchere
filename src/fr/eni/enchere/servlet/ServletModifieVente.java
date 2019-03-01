package fr.eni.enchere.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifieVente
 */
@WebServlet("/ModifieVente")
public class ServletModifieVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SimpleDateFormat formaterTime=new SimpleDateFormat("HH:mm");
	SimpleDateFormat formaterDate=new SimpleDateFormat("yyyy-MM-dd");
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifieVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Article article = null;
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		request.setAttribute("utilisateur", utilisateur);
		
		ArticleManager manager = new ArticleManager();
		String id = request.getParameter("idArticle");
		 try {
			 article = manager.getArticleById(Integer.parseInt(id));
			 request.setAttribute("article", article);
		} catch (NumberFormatException e) {
			// TODO Gestion d'exception à faire piairyck !!!
			
		}
		
		 
		 request.setAttribute("formaterTime", formaterTime);
		 request.setAttribute("formaterDate", formaterDate);
		
		if(utilisateur!=null && utilisateur.getPseudo().contentEquals(article.getProprietaire().getPseudo())) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifieVente.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
