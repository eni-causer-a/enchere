package fr.eni.enchere.dal;

public abstract class DAOFactory {

	public static CategorieDao getCategorieDao()
	{
		return new CategorieDaoJdbcImpl();
	}
	public static ArticleDao getArticleDao()
	{
		return new ArticleDaoJdbcImpl();
	}
	public static EnchereDao getEnchereDao()
	{
		return new EnchereDaoJdbcImpl();
	}
	public static UtilisateurDao getUtilisateurDao()
	{
		return new UtilisateurDaoJdbcImpl();
	}
}
