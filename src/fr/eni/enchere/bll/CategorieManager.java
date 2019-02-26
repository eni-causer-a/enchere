package fr.eni.enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieDao;

public class CategorieManager {

	
private CategorieDao categorieDao;
	
public List<Categorie> getListCategorie() {
	List<Categorie> listCategorie = new ArrayList<Categorie>();
	
	listCategorie = categorieDao.getListCategorie();
	
	return listCategorie;
}


}
