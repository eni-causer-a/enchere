package fr.eni.enchere.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
    private ServletFileUpload uploader = null;
	@Override
	public void init() throws ServletException{
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
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
		List<Categorie> lesCategories= cm.getListCategorieWithoutToutes(); 
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		request.setAttribute("utilisateur", utilisateur);
		request.setAttribute("lesCategories", lesCategories);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		
		//CategorieManager cm = new CategorieManager();
		//Categorie uneCat = cm.getCategorie(request.getParameter("categorie"));
			
			Article art = new Article();
			art.setNomArticle(request.getParameter("nomArticle"));
			art.setDescription(request.getParameter("description"));
			//Catégorie
			//art.setCategorie(uneCat);

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
			am.insert(art);
			
		System.out.println("ok1");
		if(!ServletFileUpload.isMultipartContent(request)){
			throw new ServletException("Content type is not multipart/form-data");
		}
		System.out.println("ok2");
		response.setContentType("text/html");
		System.out.println("ok3");
		try {
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			while(fileItemsIterator.hasNext()){
				
				FileItem fileItem = fileItemsIterator.next();
				if(fileItem.getFieldName().equalsIgnoreCase("fileName")) {
				File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
				System.out.println("Absolute Path at server="+file.getAbsolutePath());
				fileItem.write(file);
				
				
				}
			}
		} catch (FileUploadException e) {
		} catch (Exception e) {
		}
		
		response.sendRedirect(request.getContextPath()+"/Accueil");

			
		}
	

}
