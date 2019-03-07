package fr.eni.enchere.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

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
		
	
		
		ArticleManager am = new ArticleManager();
		List<Article> lesArticles = null;
		List<Article> lesArticlesPagination =new ArrayList();
		int selectPage=1;
		lesArticles = am.getEnchereOuverteAll();
		int nbrPage = (int)(Math.ceil(lesArticles.size()/6.0));
		for(Article article: lesArticles) {
			am.getEtatVente(article);
		}
		
		if(request.getParameter("page")!=null) {
			selectPage=Integer.parseInt(request.getParameter("page"));
		}
		if(selectPage>nbrPage || selectPage<0) {
			selectPage=1;
		}
		request.setAttribute("nbrPage", nbrPage);
		if(nbrPage==0) {
			request.setAttribute("nbrPage", 1);
			nbrPage=nbrPage+1;
		}
		for(int i=selectPage*6-6;i<selectPage*6;i++) {
			if(lesArticles.size()>i) {
				lesArticlesPagination.add(lesArticles.get(i));
			}
		}
		
		
		
		
		request.setAttribute("lesArticles", lesArticlesPagination);
		CategorieManager cm = new CategorieManager();
		List<Categorie> lesCategories = null;
		lesCategories = cm.getListCategorie();
		request.setAttribute("lesCategories", lesCategories);
		
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		request.setAttribute("utilisateur", utilisateur);
		
		request.setAttribute("select", "value1");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		
		
		filtre(request, response);
		
		

		/*String cat = request.getParameter("categorie");
		List<Article> lesArticles = null;
		if(StringUtils.isEmpty(request.getParameter("filtre"))) {
			lesArticles = am.getArticleByCategorieSearch(cat, null);

		}
		else {
			String search = request.getParameter("filtre");
			lesArticles = am.getArticleByCategorieSearch(cat, search);

		}
		request.setAttribute("lesArticles", lesArticles);

		CategorieManager cm = new CategorieManager();
		List<Categorie> lesCategories = null;
		lesCategories = cm.getListCategorie();
		request.setAttribute("lesCategories", lesCategories);
		
		
		request.setAttribute("utilisateur", utilisateur);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);*/
	}
	
	
	@SuppressWarnings("null")
	public void filtre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbrPage=0;
		
		List<Article> lesArticlesPagination =new ArrayList();
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		List<Article> lesArticles = null;
		List<Article> result=null;
		int selectPage=1;
		CategorieManager cm = new CategorieManager();
		List<Categorie> lesCategories = null;
		lesCategories = cm.getListCategorie();
		
		ArticleManager am = new ArticleManager();
		
		
		String param11 = (String)request.getParameter("param11");
		String param12 = (String)request.getParameter("param12");
		String param13 = (String)request.getParameter("param13");

		
		String param21 = (String)request.getParameter("param21");
		String param22 = (String)request.getParameter("param22");		
		String param23 = (String)request.getParameter("param23");

		if(param11 != null && param12 != null && param13 != null) {
			lesArticles = am.getEnchereOuverteAll();
			

		}
		else if(param11 != null && param12 != null && param13 == null) {
			lesArticles = am.getEnchereEnCoursOuverte(utilisateur);
			
		}
		else if(param11 != null && param12 == null && param13 == null) {
			lesArticles = am.getEnchereOuverte(utilisateur);
			
		}
		else if(param11 == null && param12 != null && param13 == null) {
			lesArticles = am.getEnchereEnCours(utilisateur);
			

		}
		else if(param11 != null && param12 == null && param13 != null) {
			lesArticles = am.getEnchereOuvertRemp(utilisateur);
			

		}
		else if(param11 == null && param12 != null && param13 != null) {
			lesArticles = am.getEnchereEnCourRemp(utilisateur);
			

		}
		else if(param11 == null && param12 == null && param13 != null) {
			lesArticles = am.getEnchereRemporte(utilisateur);
			

		}
		else {
			lesArticles = am.getEnchereOuverteAll();
			
		}
		
		//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		
		if(param21 != null && param22 != null && param23 != null) {
			lesArticles = am.getEnchereOuverteAll();
			

		}
		else if(param21 != null && param22 != null && param23 == null) {
			lesArticles = am.getVenteNonDebEnCours(utilisateur);
			
		}
		else if(param21 != null && param22 == null && param23 == null) {
			lesArticles = am.getVenteEnCours(utilisateur);
			
		}
		else if(param21 == null && param22 != null && param23 == null) {
			lesArticles = am.getVenteDebute(utilisateur);
			
		}
		else if(param21 != null && param22 == null && param23 != null) {
			lesArticles = am.getVenteEnCoursTermine(utilisateur);
			

		}
		else if(param21 == null && param22 != null && param23 != null) {
			lesArticles = am.getVenteNonDebTermine(utilisateur);
			

		}
		else if(param21 == null && param22 == null && param23 != null) {
			lesArticles = am.getVenteTermine(utilisateur);
			

		}
		else {
			//lesArticles = am.getArticleEnCours();
		}
		
		
		String cat = request.getParameter("categorie");
		if(!cat.equalsIgnoreCase("Toutes") && StringUtils.isEmpty(request.getParameter("filtre"))) {
			List<Article> lesArticlesTrie = new ArrayList<Article>();

			for(Article article: lesArticles) {
				am.getEtatVente(article);

				if(am.trieWithCat(cat,article)!= null) {
					lesArticlesTrie.add(am.trieWithCat(cat,article));

				}

			}
			
			result=lesArticlesTrie;
		}
		else if(StringUtils.isNotEmpty(request.getParameter("filtre")) && !cat.equalsIgnoreCase("Toutes")) {
			List<Article> lesArticlesTrie = new ArrayList<Article>();

			String search = request.getParameter("filtre");
			for(Article article: lesArticles) {
				am.getEtatVente(article);
				if(am.trieWithCatSearch(cat,search,article)!=null) {
					lesArticlesTrie.add(am.trieWithCatSearch(cat,search,article));
					System.out.println(search);
				}

			}
			result=lesArticlesTrie;
		}
		else if(StringUtils.isNotEmpty(request.getParameter("filtre")) && cat.equalsIgnoreCase("Toutes")) {
			List<Article> lesArticlesTrie = new ArrayList<Article>();
			String search = request.getParameter("filtre");
			for(Article article: lesArticles) {
				am.getEtatVente(article);

				if(am.trieWithSearch(search,article)!= null) {
					lesArticlesTrie.add(am.trieWithSearch(search,article));

				}

			}
			result=lesArticlesTrie;

		}
		else {
			for(Article article: lesArticles) {
				am.getEtatVente(article);
			}
			result=lesArticles;
		}
		
		if(request.getParameter("page")!=null) {
			selectPage=Integer.parseInt(request.getParameter("page"));
		}
		
		
		
		nbrPage = (int)(Math.ceil((result.size())/6.0));
		
		if(selectPage>nbrPage || selectPage<0) {
			selectPage=1;
		}
		if(nbrPage==0) {
			nbrPage=1;
		}
		
		for(int i=selectPage*6-6;i<selectPage*6;i++) {
			if(result.size()>i) {
				lesArticlesPagination.add(result.get(i));
			}
		}
		request.setAttribute("lesArticles", lesArticlesPagination);
		request.setAttribute("nbrPage", nbrPage);
		
		
		
		
		
		request.setAttribute("parame11", param11);
		request.setAttribute("parame12", param12);
		request.setAttribute("parame13", param13);
		request.setAttribute("parame21", param21);
		request.setAttribute("parame22", param22);
		request.setAttribute("parame23", param23);
		request.setAttribute("cat", cat);
		request.setAttribute("filtre", request.getParameter("filtre"));
		request.setAttribute("select", request.getParameter("groupe1"));
		//System.out.println("parame11 : "+ param11+" | parame12 : "+ param12+" | parame13 : "+ param13+" | parame21 : "+ param21+" | parame22 : "+ param22+" | parame22 : "+ param22+" | parame23 : "+ param23+" | cat : "+ cat+" | filtre : "+ request.getParameter("filtre")+" | select : "+ request.getParameter("groupe1"));
		
		request.setAttribute("lesCategories", lesCategories);
		
		
		request.setAttribute("utilisateur", utilisateur);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	
	}

}
