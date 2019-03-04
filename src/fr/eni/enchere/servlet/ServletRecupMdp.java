package fr.eni.enchere.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import fr.eni.enchere.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletMdpOublie
 */
@WebServlet("/changeMdp")
public class ServletRecupMdp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRecupMdp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager um = new UtilisateurManager();
		
		if(request.getParameter("id") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);
		}else if(StringUtils.isNumeric(request.getParameter("id"))) {
			if(um.isRecup(Integer.parseInt(request.getParameter("id")))) {
				request.setAttribute("id", request.getParameter("id"));
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/RecPass.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
				rd.forward(request, response);
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager um = new UtilisateurManager();
		um.endRecup(Integer.parseInt(request.getParameter("id")), request.getParameter("newpass"));
		
	}

}
