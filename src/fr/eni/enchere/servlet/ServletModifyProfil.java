package fr.eni.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouveauProfil
 */
@WebServlet("/modifProfil")
public class ServletModifyProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifyProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession(); 
		request.setAttribute("user", session.getAttribute("Utilisateur"));
		if(request.getAttribute("user")==null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
			rd.forward(request, response);
		}else {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifyProfil.jsp");
		rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("boutonAnnuler")!=null) {
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}
		else {
			
			System.out.println("ok");
			HttpSession session= request.getSession(); 
			
			UtilisateurManager um = new UtilisateurManager();
			Utilisateur user;
			user = new Utilisateur();
			if(request.getParameter("motDePasse").equals("")) {
				System.out.println("1");
				user = new Utilisateur(request.getParameter("pseudo"),request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("email"),request.getParameter("telephone"),request.getParameter("rue"),request.getParameter("codePostal"),request.getParameter("ville"),((Utilisateur) session.getAttribute("Utilisateur")).getMotDePasse());
			}else {

				if(!(((Utilisateur) session.getAttribute("Utilisateur")).getMotDePasse().equals( request.getParameter("motDePasse")))) {
					System.out.println("Mot de passe actuel FAUX");
				}else if(!(request.getParameter("motDePasses") .equals( request.getParameter("confirmationMotDePasse")))) {
					
					System.out.println("Mot de passe non identique");
				}else {
					System.out.println("modif");
					user = new Utilisateur(request.getParameter("pseudo"),request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("email"),request.getParameter("telephone"),request.getParameter("rue"),request.getParameter("codePostal"),request.getParameter("ville"),request.getParameter("motDePasses"));
				}
				
			}
			
				
			user.setNoUtilisateur(((Utilisateur) session.getAttribute("Utilisateur")).getNoUtilisateur());
			um.update(user);
			session.setAttribute("Utilisateur", user);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
			rd.forward(request, response);
		
	}

}
