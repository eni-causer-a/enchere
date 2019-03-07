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
import java.util.Random;

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
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		Utilisateur utilisateur=(Utilisateur) session.getAttribute("Utilisateur");
		request.setAttribute("utilisateur", utilisateur);
		Date aujourdhui=new Date();
		Boolean Error=false;
		
		Random rd = new Random();
		int valeur = 1 + rd.nextInt(100000 - 1);

		
		//CategorieManager cm = new CategorieManager();
		//Categorie uneCat = null ;
			
			Article art = new Article();
			Retrait retrait = new Retrait();
			Date date = null;
		    Date dateFin = null;
		    Date time = null;
		    Date timeFin = null;
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat sdf2= new SimpleDateFormat("hh:mm");
			ArticleManager am = new ArticleManager();
		
			
		if(!ServletFileUpload.isMultipartContent(request)){
			throw new ServletException("Content type is not multipart/form-data");
		}
		response.setContentType("text/html");
		try {
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			while(fileItemsIterator.hasNext()){
				
				FileItem fileItem = fileItemsIterator.next();
				
				if (fileItem.isFormField()) {
				    String name = fileItem.getFieldName();
				    String value = fileItem.getString();
	
				    switch(name) {
				    case "nomArticle":
				    	art.setNomArticle(value);
				    	break;
				    case "description":
				    	art.setDescription(value);
				    	break;
				    	
				    case "categorie":
						art.setCategorie(new Categorie(Integer.parseInt(value),""));
						break;
						
				    case "miseAPrix":
				    	art.setMiseAPrix(Integer.parseInt(value));
				    	art.setPrixVente(Integer.parseInt(value));
				    	break;
				    case "debutEnchere":
						date = sdf.parse(value);
				    	break;
				    case "debutEnchereTime":
						time = sdf2.parse(value);
						date.setHours(time.getHours());
						date.setMinutes(time.getMinutes());
						art.setDateDebutEncheres(date);
						break;
						
				    case "finEnchere":
						dateFin = sdf.parse(value);
				    	break;
				    	
				    case "finEnchereTime":
						timeFin = sdf2.parse(value);
						dateFin.setHours(timeFin.getHours());
						dateFin.setMinutes(timeFin.getMinutes());
						art.setDateFinEncheres(dateFin);
							
						break;
				    case "rue":
						retrait.setRue(value);
						break;
				    case "codePostal":
				    	retrait.setCode_postale(value);
				    	break;
				    case "ville":
				    	retrait.setVille(value);
				    	break;
				   /* case "fileName":
				    	File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
						System.out.println("Absolute Path at server="+file.getAbsolutePath());
						fileItem.write(file);
						break;*/
				    }
				}
				//request.getServletContext().getAttribute("FILES_DIR")+
				if(fileItem.getFieldName().equalsIgnoreCase("fileName")) {

					if(fileItem.getName() != null) {
				

					if(fileItem.getName().isEmpty()) {

					File file = new File("//10.51.0.254/Outils/groupe6Image"+File.separator+valeur+fileItem.getName());
					art.setPhoto(valeur+fileItem.getName());
					fileItem.write(file);
					}
					
		
				
			}
			if(art.getDateDebutEncheres().before(aujourdhui)){
				Error=true;
				request.setAttribute("dateDebutError", "true");
			}
		    if(art.getDateDebutEncheres().after(art.getDateFinEncheres())){
				Error=true;
				request.setAttribute("dateFinError", "true");
			}
			

		} catch (FileUploadException e) {
		} catch (Exception e) {
		}
		if(Error==false) {
			art.setRetrait(retrait);
			art.setProprietaire(utilisateur);
	
			am.insert(art);
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}else {
			CategorieManager cm=new CategorieManager();
			
			request.setAttribute("lesCategories", cm.getListCategorieWithoutToutes());
			request.setAttribute("nomArticle",art.getNomArticle());
			request.setAttribute("description",art.getDescription());
			request.setAttribute("cat",art.getCategorie().getNoCategorie());
			request.setAttribute("miseAPrix",art.getMiseAPrix());
			request.setAttribute("debutEnchere", sdf.format(date));
			request.setAttribute("debutEnchereTime",sdf2.format(time));
			request.setAttribute("finEnchere",sdf.format(dateFin));
			request.setAttribute("finEnchereTime",sdf2.format(timeFin));
			request.setAttribute("rue",retrait.getRue());
			request.setAttribute("codeP",retrait.getCode_postale());
			request.setAttribute("ville",retrait.getVille());
			
			

			
			
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/NouvelleVente.jsp");
			reqDisp.forward(request, response);
			
			
		}

			
		}
	

}
