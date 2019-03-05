package fr.eni.enchere.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.EtatVente;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletRetrait
 */
@WebServlet("/ServletRetrait")
public class ServletRetrait extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRetrait() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article = null;
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		
		ArticleManager manager = new ArticleManager();
		String id = request.getParameter("idArticle");
		try {
			 article = manager.getArticleById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// TODO Gestion d'exception 
		}
		if (article.getEtatVente() == EtatVente.ENCHERE_TERMINE && article.getGagnant().getPseudo().equals(utilisateur.getPseudo())) {
			manager.retractArticle(article);
		}
		
		response.sendRedirect(request.getContextPath()+"/DetailVente?idArticle="+article.getNoArticle());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
