package fr.eni.enchere.servlet;

import java.io.IOException;
import java.text.ParseException;
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
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifieVente
 */
@WebServlet("/ModifieVente")
public class ServletModifieVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		 System.out.println(utilisateur.getPseudo());
		 System.out.println(article.getProprietaire().getPseudo());
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
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		
		if(request.getParameter("boutonAnnuler")!=null) {
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}
		else if(request.getParameter("boutonEnregistrer") !=null){
			Article art = new Article();
			art.setNomArticle(request.getParameter("nomArticle"));
			art.setDescription(request.getParameter("description"));
			//Catégorie
			art.setCategorie(new Categorie(Integer.parseInt(request.getParameter("categorie")),""));
			
			art.setMiseAPrix(Integer.parseInt(request.getParameter("miseAPrix")));
			art.setPrixVente(art.getMiseAPrix());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
			try {
				Date date = sdf.parse(request.getParameter("debutEnchere"));
				Date time = sdf2.parse(request.getParameter("debutEnchereTime"));
				date.setHours(time.getHours());
				date.setMinutes(time.getMinutes());
				art.setDateDebutEncheres(date);
				
				date = sdf.parse(request.getParameter("finEnchere"));
				time = sdf2.parse(request.getParameter("finEnchereTime"));
				date.setHours(time.getHours());
				date.setMinutes(time.getMinutes());
				art.setDateFinEncheres(date);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			art.setProprietaire(utilisateur);
			
			Retrait retrait = new Retrait();
			retrait.setRue(request.getParameter("rue"));
			retrait.setCode_postale(request.getParameter("codePostal"));
			retrait.setVille(request.getParameter("ville"));
			
			art.setRetrait(retrait);
			
			ArticleManager am = new ArticleManager();
			am.updateArticle(art);
			
			response.sendRedirect(request.getContextPath()+"/Accueil");
			
		}
		doGet(request, response);
	}

}
