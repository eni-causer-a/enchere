package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieDao;
import fr.eni.enchere.dal.DAOFactory;

public class CategorieManager {

	private CategorieDao categorieDao;
		
	public CategorieManager() {
		this.categorieDao = DAOFactory.getCategorieDao();
	}
	
	public List<Categorie> getListCategorie() {
		return categorieDao.getListCategorie();
	}

}