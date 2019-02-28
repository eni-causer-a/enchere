package fr.eni.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouveauProfil
 */
@WebServlet("/NouveauProfil")
public class ServletNouveauProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNouveauProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/NouveauProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("boutonCreer")!=null) {
			if(request.getParameter("motDePasse").equalsIgnoreCase(request.getParameter("confirmationMotDePasse"))) {
				System.out.println("ok");

				UtilisateurManager um = new UtilisateurManager();
				Utilisateur user = new Utilisateur(request.getParameter("pseudo"),request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("email"),request.getParameter("telephone"),request.getParameter("rue"),request.getParameter("codePostal"),request.getParameter("ville"),request.getParameter("motDePasse"));
				um.createUtilisateur(user);
				response.sendRedirect(request.getContextPath()+"/Connexion");
			}else {
				System.out.println(request.getParameter("pseudo"));
				request.setAttribute("pseudo", request.getParameter("pseudo"));
				request.setAttribute("nom", request.getParameter("nom"));
				request.setAttribute("prenom", request.getParameter("prenom"));
				request.setAttribute("email", request.getParameter("email"));
				request.setAttribute("telephone", request.getParameter("telephone"));
				request.setAttribute("rue", request.getParameter("rue"));
				request.setAttribute("codePostal", request.getParameter("codePostal"));
				request.setAttribute("ville", request.getParameter("ville"));
				request.setAttribute("mdpError", "Le mot de passe est diffenrent de Confirmation");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/NouveauProfil.jsp");
				rd.forward(request, response);
			}
			
			
		}
	}

}
