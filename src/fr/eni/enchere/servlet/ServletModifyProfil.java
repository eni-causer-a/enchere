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
		
		request.setAttribute("utilisateur", session.getAttribute("Utilisateur"));
		if(request.getAttribute("utilisateur")==null) {
			response.sendRedirect(request.getContextPath()+"/Accueil");
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
		Boolean Error=false;
		HttpSession session= request.getSession(); 
		UtilisateurManager um = new UtilisateurManager();
		if(request.getParameter("boutonSupprimer")!=null) {
			System.out.println("supr");
			um.delete((Utilisateur) session.getAttribute("Utilisateur"));
			session.removeAttribute("Utilisateur");
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}
		else {
			
			
			Utilisateur user;
			user = new Utilisateur();
			
			if((((Utilisateur) session.getAttribute("Utilisateur")).getMotDePasse().equals( request.getParameter("motDePasse"))) && (!um.pseudoIsTaken(request.getParameter("pseudo")) || request.getParameter("pseudo").equals(((Utilisateur) session.getAttribute("Utilisateur")).getPseudo())) && request.getParameter("newMotDePasse").equals("")) {
				user = new Utilisateur(request.getParameter("pseudo"),request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("email"),request.getParameter("telephone"),request.getParameter("rue"),request.getParameter("codePostal"),request.getParameter("ville"),((Utilisateur) session.getAttribute("Utilisateur")).getMotDePasse());
				user.setCredit(((Utilisateur) session.getAttribute("Utilisateur")).getCredit());
			}else {
				if(um.pseudoIsTaken(request.getParameter("pseudo")) && !request.getParameter("pseudo").equals(((Utilisateur) session.getAttribute("Utilisateur")).getPseudo())) {
					request.setAttribute("pseudoError", "true");
					Error=true;
				}
				
				if(!request.getParameter("motDePasse").equals("")) {
					if(!(((Utilisateur) session.getAttribute("Utilisateur")).getMotDePasse().equals( request.getParameter("motDePasse")))) {
						
							request.setAttribute("mdpError", "true");
							Error=true;
						
					}
					
					if(!(request.getParameter("newMotDePasse") .equals( request.getParameter("confirmationMotDePasse")))) {
	
							request.setAttribute("mdpConfError", "true");
							Error=true;
					}
				}
				
				if(request.getParameter("newMotDePasse").equals(request.getParameter("confirmationMotDePasse")) && (!um.pseudoIsTaken(request.getParameter("pseudo")) || request.getParameter("pseudo").equals(((Utilisateur) session.getAttribute("Utilisateur")).getPseudo()))) {
					System.out.println("modif");
					
					user = new Utilisateur(request.getParameter("pseudo"),request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("email"),request.getParameter("telephone"),request.getParameter("rue"),request.getParameter("codePostal"),request.getParameter("ville"),request.getParameter("newMotDePasse"));
					user.setCredit(((Utilisateur) session.getAttribute("Utilisateur")).getCredit());
				}
			}
			if(Error==false) {
				user.setNoUtilisateur(((Utilisateur) session.getAttribute("Utilisateur")).getNoUtilisateur());
				um.update(user);
				session.setAttribute("Utilisateur", user);
				RequestDispatcher rd = request.getRequestDispatcher("/profil?user="+((Utilisateur) session.getAttribute("Utilisateur")).getNoUtilisateur());
				rd.forward(request, response);
			}else{
				request.setAttribute("pseudo", request.getParameter("pseudo"));
				request.setAttribute("nom", request.getParameter("nom"));
				request.setAttribute("prenom", request.getParameter("prenom"));
				request.setAttribute("email", request.getParameter("email"));
				request.setAttribute("telephone", request.getParameter("telephone"));
				request.setAttribute("rue", request.getParameter("rue"));
				request.setAttribute("codePostal", request.getParameter("codePostal"));
				request.setAttribute("ville", request.getParameter("ville"));
				
				request.setAttribute("utilisateur", session.getAttribute("Utilisateur"));
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ModifyProfil.jsp");
				rd.forward(request, response);
			}
			
		}
			
			
		
	}

}
