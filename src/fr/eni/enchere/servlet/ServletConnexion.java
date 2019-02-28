package fr.eni.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
		
			for (Cookie cookie : cookies) {
				String nom=cookie.getName();
			
				if (nom.equals("idCompte")) {
					request.setAttribute("pseudo",cookie.getValue() );					
				}
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("Identifiant");
		String mdp=request.getParameter("MotDePasse");
		String seSouvenirDeMoi=request.getParameter("SeSouvenirDeMoi");
		
		UtilisateurManager utilisateurManager=new UtilisateurManager();
		Utilisateur utilisateur=utilisateurManager.getUtilisateur(id,mdp);
		System.out.println(utilisateur);
		if(utilisateur==null) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			
		}else {
			HttpSession session= request.getSession();
			session.setAttribute("Utilisateur", utilisateur);
			
			if(seSouvenirDeMoi!=null) {
				Cookie cookie=new Cookie("idCompte",id);
				cookie.setVersion(1);
				cookie.setMaxAge(1000);
				cookie.setPath("/Enchere/Connexion");
				response.addCookie(cookie);
			}else {
				Cookie cookie=new Cookie("idCompte","");
				cookie.setVersion(1);
				cookie.setMaxAge(1000);
				cookie.setPath("/Enchere/Connexion");
				response.addCookie(cookie);
			}
			
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}
	}

}
