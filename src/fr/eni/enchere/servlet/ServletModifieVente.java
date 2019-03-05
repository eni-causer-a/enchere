package fr.eni.enchere.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
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
	
	SimpleDateFormat formaterTime=new SimpleDateFormat("HH:mm");
	SimpleDateFormat formaterDate=new SimpleDateFormat("yyyy-MM-dd");
	String id;
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
		id = request.getParameter("idArticle");
		CategorieManager cm  = new CategorieManager();
		List<Categorie> lesCategories= cm.getListCategorieWithoutToutes(); 
		request.setAttribute("lesCategories", lesCategories);
		 try {
			 article = manager.getArticleById(Integer.parseInt(id));
			 request.setAttribute("article", article);
		} catch (NumberFormatException e) {
			// TODO Gestion d'exception à faire piairyck !!!
			
		}
		
		 //System.out.println("jnjnjnjljljkjjlkjjlkjlk:"+formaterTime.format(article.getDateDebutEncheres()));
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
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		ArticleManager am = new ArticleManager();
		Date aujourdhui=new Date();
		Boolean Error=false;
		
		if(request.getParameter("boutonSupprimer")!=null) {
			Article art = new Article();
			art.setNoArticle(Integer.parseInt(request.getParameter("idArticle")));
			am.deleteArticle(art);
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}
		else if(request.getParameter("boutonEnregistrer") !=null){
			Article art = new Article();
			art.setNoArticle(Integer.parseInt(request.getParameter("idArticle")));
			art.setNomArticle(request.getParameter("nomArticle"));
			art.setDescription(request.getParameter("description"));
			//Catégorie
			art.setCategorie(new Categorie(Integer.parseInt(request.getParameter("categorie")),""));
			
			art.setMiseAPrix(Integer.parseInt(request.getParameter("miseAPrix")));
			art.setPrixVente(art.getMiseAPrix());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
			try {
				Date dateDebut = sdf.parse(request.getParameter("debutEnchere"));
				Date time = sdf2.parse(request.getParameter("debutEnchereTime"));
				dateDebut.setHours(time.getHours());
				dateDebut.setMinutes(time.getMinutes());
				art.setDateDebutEncheres(dateDebut);
				
				if(dateDebut.before(aujourdhui)){
					Error=true;
					request.setAttribute("dateDebutError", "La date saisie est anterieur à la date d'aujourd'hui");
				}
				
				Date dateFin = sdf.parse(request.getParameter("finEnchere"));
				time = sdf2.parse(request.getParameter("finEnchereTime"));
				dateFin.setHours(time.getHours());
				dateFin.setMinutes(time.getMinutes());
				art.setDateFinEncheres(dateFin);
				
				if(dateDebut.after(dateFin)){
					Error=true;
					request.setAttribute("dateFinError", "La date saisie est anterieur à la date de début de l'enchère");
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			art.setProprietaire(utilisateur);
			if(Error==false) {
				Retrait retrait = new Retrait();
				retrait.setRue(request.getParameter("rue"));
				retrait.setCode_postale(request.getParameter("codePostal"));
				retrait.setVille(request.getParameter("ville"));
				
				art.setRetrait(retrait);
				
				am.updateArticle(art);
				
				response.sendRedirect(request.getContextPath()+"/Accueil");
			
			}else {
				CategorieManager cm=new CategorieManager();
				request.setAttribute("idArticle", request.getParameter("idArticle"));
				request.setAttribute("lesCategories", cm.getListCategorieWithoutToutes());
				request.setAttribute("nomArticle",request.getParameter("nomArticle"));
				request.setAttribute("description",request.getParameter("description"));
				request.setAttribute("cat",request.getParameter("categorie"));
				request.setAttribute("miseAPrix",request.getParameter("miseAPrix"));
				request.setAttribute("debutEnchere",request.getParameter("debutEnchere"));
				request.setAttribute("debutEnchereTime",request.getParameter("debutEnchereTime"));
				request.setAttribute("finEnchere",request.getParameter("finEnchere"));
				request.setAttribute("finEnchereTime",request.getParameter("finEnchereTime"));
				request.setAttribute("rue",request.getParameter("rue"));
				request.setAttribute("codeP",request.getParameter("codePostal"));
				request.setAttribute("ville",request.getParameter("ville"));
				
				

				
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifieVente.jsp");
				rd.forward(request, response);
				
				
			}
			
		}
	}

}
