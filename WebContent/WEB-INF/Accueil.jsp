<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="fr.eni.enchere.bo.Article"%>
<%@page import="fr.eni.enchere.bo.Categorie"%>
<%@page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<title>ENI-Encheres</title>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/4-col-portfolio.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body class="container">
	<header class="py-3 bg-dark header-demodule fixed-top">  
	   	<div class="row">
	   		<div class="col-6">	
	   			<div class="container text-center text-white">
	   				<h3>ENI-Encheres</h3>
	   			</div>
	   		</div>
	   		<c:if test="${utilisateur==null}">
		   		<div class="col-6">
		        	<a href="<%=request.getContextPath()%>/ServletConnexion">S'inscrire - Se connecter</a>
		       	</div>
	   		</c:if>
	   		<c:if test="${utilisateur!=null}">
	   			
	        	<div class="col-1"><a href="#Enchere">Enchères</a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/NouvelleVente">Vendre un article</a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/profil">Mon profil</a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/Deconnexion">Déconnexion</a></div>
		       	
	   		</c:if>
	   		
		</div>  
	</header>
	<h3 class="my-5 text-center">Liste des enchères</h3>
	<c:if test="${utilisateur!=null}">
		
		<fieldset id="group1">
			<div class="row">
				<div class="col-3">
			    	<input type="radio" value="value1" name="groupe1" onclick="activeDesactive(this,1);" checked>Achats
			    </div>
			    <div class="col-3">
			    	<input type="radio" value="value2" name="groupe1" onclick="activeDesactive(this,2);">Mes Ventes
			    </div>
			</div>
		</fieldset>
			
		<div class="row">
			<div class="col-3">
				<c:if test="${groupe1==value1}">
					<input type="checkbox" value="value1" name="param11">Enchères ouvertes
				</c:if>
			</div>
			<div class="col-3">
				<input type="checkbox" value="value2" name="param21" disabled>Mes ventes en cours
			</div>
			<div class="w-100"></div>
			<div class="col-3">
				<input type="checkbox" value="value2" name="param12">Mes enchères en cours
			</div>
			<div class="col-3">
				<input type="checkbox" value="value2" name="param22" disabled>Ventes non débutées
			</div>
			<div class="w-100"></div>
			<div class="col-3">
				<input type="checkbox" value="value2" name="param13">Mes enchères remportées
			</div>
			<div class="col-3">
				<input type="checkbox" value="value2" name="param23" disabled>Ventes terminées
			</div>
			
			
			
		</div>
	</c:if>
	<h3>Filtres :</h3>
	<form method="post" action="<%=request.getContextPath()%>/Accueil">
		<div class="row">
			<div class="col-4">
				<input type="text" name="filtre" placeHolder="Le nom de l'article contient">
			</div>
			<div class="col-4">
				<label>Catégorie :</label>
				<select name="categorie")>
					<c:forEach var="categorie" items="${lesCategories}">
						<option value="${categorie.getLibelle()}">${categorie.getLibelle()}
					</c:forEach>
						
				</select>	
			</div>
			<div class="col-4">
				<button>Rechercher</button>
			</div>	
		</div>
	</form>
    <div class="row">
        <ul class="list-group col-12">
           	<c:forEach var="article" items="${lesArticles}">
           		<li class="list-group-item d-flex justify-content-between align-items-center">${article.getNomArticle()}
	        	<div>  
                    <div class="col-12">Prix : ${article.getPrixVente()} points</div>      
                    <div class="col-12">Fin de l'enchère : ${article.getDateFinEncheres()}</div>
                    <div class="col-12">Vendeur : ${article.getProprietaire().getNom()}</div>
	        	</div> 
           		</li>
           </c:forEach>
          
		
		</ul>
	</div>
</div>
	
	

    <!-- Footer -->
    <footer class="row bg-dark footer-demodule fixed-bottom py-1">
    	
        <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="../../../vendor/jquery/jquery.min.js"></script>
    <script src="../../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

<script type='text/javascript'>
 /*
function active1(radio) {
	if(radio.checked) {
    	document.getElementsByName("param11")[0].disabled = false;
    	document.getElementsByName("param12")[0].disabled = false;
    	document.getElementsByName("param13")[0].disabled = false;
    	document.getElementsByName("param21")[0].disabled = true;
    	document.getElementsByName("param21")[0].disabled = true;
    	document.getElementsByName("param21")[0].disabled = true;
   }*/
function activeDesactive(radio,id) {
    if(radio.checked && id==1) {
    	document.getElementsByName("param11")[0].disabled = false;
    	document.getElementsByName("param12")[0].disabled = false;
    	document.getElementsByName("param13")[0].disabled = false;
    	document.getElementsByName("param21")[0].disabled = true;
    	document.getElementsByName("param22")[0].disabled = true;
    	document.getElementsByName("param23")[0].disabled = true;
    }
    else if(radio.checked && id==2){
    	document.getElementsByName("param11")[0].disabled = true;
    	document.getElementsByName("param12")[0].disabled = true;
    	document.getElementsByName("param13")[0].disabled = true;
    	document.getElementsByName("param21")[0].disabled = false;
    	document.getElementsByName("param22")[0].disabled = false;
    	document.getElementsByName("param23")[0].disabled = false;
    }
}

</script>

</html>