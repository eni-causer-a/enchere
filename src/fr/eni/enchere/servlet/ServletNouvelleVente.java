package fr.eni.enchere.servlet;

import java.io.IOException;
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
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	List<Categorie> lesCategories;
    public ServletNouvelleVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session= request.getSession();
		CategorieManager cm  = new CategorieManager();
		lesCategories= cm.getListCategorieWithoutToutes(); 
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		if(utilisateur==null) {
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}else {

			request.setAttribute("utilisateur", utilisateur);
			request.setAttribute("lesCategories", lesCategories);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/NouvelleVente.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		Date aujourdhui=new Date();
		Boolean Error=false;
		
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
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
			try {
				Date dateDebut = sdf.parse(request.getParameter("debutEnchere"));
				Date time = sdf2.parse(request.getParameter("debutEnchereTime"));
				dateDebut.setHours(time.getHours());
				dateDebut.setMinutes(time.getMinutes());
				art.setDateDebutEncheres(dateDebut);
				
				if(dateDebut.before(aujourdhui)){
					Error=true;
					request.setAttribute("dateDebutError", "La date saisie est enterieur à la date d'aujourd'hui");
				}
				
				Date dateFin = sdf.parse(request.getParameter("finEnchere"));
				time = sdf2.parse(request.getParameter("finEnchereTime"));
				dateFin.setHours(time.getHours());
				dateFin.setMinutes(time.getMinutes());
				art.setDateFinEncheres(dateFin);
				
				if(dateDebut.after(dateFin)){
					Error=true;
					request.setAttribute("dateFinError", "La date saisie est enterieur à la date de début de l'enchère");
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(Error==false) {
				art.setProprietaire(utilisateur);
				
				Retrait retrait = new Retrait();
				retrait.setRue(request.getParameter("rue"));
				retrait.setCode_postale(request.getParameter("codePostal"));
				retrait.setVille(request.getParameter("ville"));
				
				art.setRetrait(retrait);
				
				ArticleManager am = new ArticleManager();
				am.insert(art);
				
				response.sendRedirect(request.getContextPath()+"/Accueil");
			}else {
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
				request.setAttribute("lesCategories", lesCategories);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/NouvelleVente.jsp");
				rd.forward(request, response);
			}
			
		}
	}

}
