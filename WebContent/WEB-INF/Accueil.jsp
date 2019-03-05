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
<link rel="shortcut icon" href="image/eni.ico">
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

	<link href="css/custom.css" rel="stylesheet">
	
    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/4-col-portfolio.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body class="container">
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<form method="post" action="<%=request.getContextPath()%>/Accueil">
	
	<h3 class="my-5 text-center">Liste des enchères</h3>
	<c:if test="${utilisateur!=null}">
		
		<fieldset id="group1">
		<br>
			<div class="row">
				<div class="col-3">
					<c:if test="${select=='value1'}">
				    	<input type="radio"  value="value1" name="groupe1" onclick="activeDesactive(this,1);" checked>
				    	<label style="color: black;" id="achat"  for="groupe1">Achats</label>
				    </c:if>
				    <c:if test="${select!='value1'}">
				    	<input type="radio"  value="value1" name="groupe1" onclick="activeDesactive(this,1);">
				    	<label style="color: black;" id="achat"  for="groupe1">Achats</label>
				    </c:if>
			    	
			    </div>
			    <div class="col-3">
			    	<c:if test="${select=='value2'}">
				    	<input type="radio" value="value2" name="groupe1" onclick="activeDesactive(this,2);" checked>
				    	<label style="color: black;" id="vente"  for="groupe1">Mes Ventes</label>
				    </c:if>
				    <c:if test="${select!='value2'}">
				    	<input type="radio" value="value2" name="groupe1" onclick="activeDesactive(this,2);">
				    	<label style="color: black;" id="vente"  for="groupe1">Mes Ventes</label>
				    </c:if>
			    	
			    </div>
			</div>
		</fieldset>
		<div class="row">
			<div class="col-3">
				<c:if test="${select=='value1' and parame11!=null}">
					<input type="checkbox" value="param11" name="param11" checked />
					<label style="color: black;" id="param111">Enchères ouvertes</label>
				</c:if>
				<c:if test="${select=='value1' and parame11==null}">
					<input type="checkbox" value="param11" name="param11"/>
					<label style="color: black;" id="param111">Enchères ouvertes</label>
				</c:if>
				<c:if test="${select!='value1' and parame11==null}">
					<input type="checkbox" value="param11" name="param11" disabled/>
					<label style="color: #ccc;" id="param111">Enchères ouvertes</label>
				</c:if>
			</div>
			<div class="col-3">
				<c:if test="${select=='value2' and parame21!=null}">
					<input type="checkbox" value="param21" name="param21"  checked/>
					<label style="color: black;" id="param211">Mes ventes en cours</label>
				</c:if>
				<c:if test="${select=='value2' and parame21==null}">
					<input type="checkbox" value="param21" name="param21" />
					<label style="color: black;" id="param211">Mes ventes en cours</label>
				</c:if>
				<c:if test="${select!='value2' and parame21==null}">
					<input type="checkbox" value="param21" name="param21"  disabled/>
					<label style="color: #ccc;" id="param211">Mes ventes en cours</label>
				</c:if>
			
			</div>
			<div class="w-100"></div>
			<div class="col-3">
				<c:if test="${select=='value1' and parame12!=null}">
					<input type="checkbox" value="param12" name="param12" checked/>
					<label style="color: black;" id="param121">Mes enchères en cours</label>
				</c:if>
				<c:if test="${select=='value1' and parame12==null}">
					<input type="checkbox" value="param12" name="param12"/>
					<label style="color: black;" id="param121">Mes enchères en cours</label>
				</c:if>
				<c:if test="${select!='value1' and parame12==null}">
					<input type="checkbox" value="param12" name="param12"disabled/ >
					<label style="color: #ccc;" id="param121">Mes enchères en cours</label>
				</c:if>
			</div>
			<div class="col-3">
				<c:if test="${select=='value2' and parame22!=null}">
					<input type="checkbox" value="param22" name="param22" checked/>
					<label style="color: black;" id="param221">Ventes non débutées</label>
				</c:if>
				<c:if test="${select=='value2' and parame22==null}">
					<input type="checkbox" value="param22" name="param22" />
					<label style="color: black;" id="param221">Ventes non débutées</label>
				</c:if>
				<c:if test="${select!='value2' and parame22==null}">
					<input type="checkbox" value="param22" name="param22" disabled/>
					<label style="color: #ccc;" id="param221">Ventes non débutées</label>
				</c:if>
			</div>
			<div class="w-100"></div>
			<div class="col-3">
				<c:if test="${select=='value1' and parame13!=null}">
					<input type="checkbox" value="param13" name="param13" checked />
					<label style="color: black;" id="param131">Mes enchères remportées</label>
				</c:if>
				<c:if test="${select=='value1' and parame13==null}">
					<input type="checkbox" value="param13" name="param13" />
					<label style="color: black;" id="param131">Mes enchères remportées</label>
				</c:if>
				<c:if test="${select!='value1' and parame13==null}">
					<input type="checkbox" value="param13" name="param13" disabled />
					<label style="color: #ccc;" id="param131">Mes enchères remportées</label>
				</c:if>
			</div>
			<div class="col-3">
				<c:if test="${select=='value2' and parame23!=null}">
					<input type="checkbox" value="param23" name="param23" checked/>
					<label style="color: black;" id="param231">Ventes terminées</label>
				</c:if>
				<c:if test="${select=='value2' and parame23==null}">
					<input type="checkbox" value="param23" name="param23" />
					<label style="color: black;" id="param231">Ventes terminées</label>
				</c:if>
				<c:if test="${select!='value2' and parame23==null}">
					<input type="checkbox" value="param23" name="param23" disabled/>
					<label style="color: #ccc;" id="param231">Ventes terminées</label>
				</c:if>
			</div>
			
		</div>
	</c:if>
	<br>
	<h3>Filtres :</h3>
		<div class="row">
			<div class="col-4">
				<input type="text" name="filtre" placeHolder="Le nom de l'article contient" value="${filtre}">
			</div>
			<div class="col-4">
				<label>Catégorie :</label>
				<select name="categorie")>
					<c:forEach var="categorie" items="${lesCategories}">
					 	<c:if test="${categorie.getLibelle()==cat}">
							<option value="${categorie.getLibelle()}" selected>${categorie.getLibelle()}</option>
						</c:if>
						<c:if test="${categorie.getLibelle()!=cat}">
							<option value="${categorie.getLibelle()}">${categorie.getLibelle()}</option>
						</c:if>
					</c:forEach>
						
				</select>	
			</div>
			<div class="col-4">
				<button class="btn btn-secondary">Rechercher</button>
			</div>
	
		</div>
	</form>
	<br>
    <!-- <div class="row">
        <ul class="list-group col-12">
           	<c:forEach var="article" items="${lesArticles}">
           		<li class="list-group-item d-flex justify-content-between align-items-center"><a href="<%=request.getContextPath()%>/DetailVente?idArticle=${article.getNoArticle()}">${article.getNomArticle()}</a>
	        	<div>  
                    <div class="col-12">Prix : ${article.getPrixVente()} points</div>      
                    <div class="col-12">Fin de l'enchère : ${article.printDateFinEnchere()}</div>
                    <div class="col-12">Vendeur : <a href="<%=request.getContextPath()%>/profil?user=${article.getProprietaire().getNoUtilisateur()}">${article.getProprietaire().getPseudo()}</a></div>
	        	</div> 
           		</li>
           </c:forEach>
		</ul>
	</div>-->
	<br>
	<div class="row">
		<c:forEach var="article" items="${lesArticles}">
			<div class="col-6" style="margin-bottom: 2%;">
				<div class="card" style="width: 25rem;">
				  <div class="card-body">
				    <h5 class="card-title"><a href="<%=request.getContextPath()%>/DetailVente?idArticle=${article.getNoArticle()}">${article.getNomArticle()}</a></h5>
				    <p class="card-text">${article.getDescription()}</p>
				    <p><label style="font-weight : bold;">Catégorie : </label>${article.getCategorie().getLibelle()}</p>
				    <p><label style="font-weight : bold;">Meilleur Offre : </label>${article.getPrixVente()} points</p>
				    <p><label style="font-weight : bold;">Début : </label> ${article.printDateDebutEnchere()}</p>
				    <p><label style="font-weight : bold;">Fin : </label>${article.printDateFinEnchere()}</p>
				    <!--  <p>Vendeur : <a href="<%=request.getContextPath()%>/profil?user=${article.getProprietaire().getNoUtilisateur()}">${article.getProprietaire().getPseudo()}</a></p>-->
				  </div>
				  <!--  
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item">MeilleurOffre : ${article.getPrixVente()} points</li>
					    <li class="list-group-item">Début : ${article.printDateDebutEnchere()}</li>
					    <li class="list-group-item">Fin : ${article.printDateFinEnchere()}</li>
					    <li class="list-group-item">Vendeur : <a href="<%=request.getContextPath()%>/profil?user=${article.getProprietaire().getNoUtilisateur()}">${article.getProprietaire().getPseudo()}</a></li>
					    <!--<li class="list-group-item">Vendeur : <a href="<%=request.getContextPath()%>/profil?user=${article.getProprietaire().getNoUtilisateur()}">${article.getProprietaire().getPseudo()}</a></li>
					  </ul>
					 -->
				</div>
			</div>
		</c:forEach>

	</div>

	<br>
	<br>

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
	    	document.getElementsByName("param21")[0].checked = false;
	    	document.getElementsByName("param22")[0].checked = false;
	    	document.getElementsByName("param23")[0].checked = false;
	    	changeColorEnable();
	    }
	    else if(radio.checked && id==2){

	    	document.getElementsByName("param11")[0].disabled = true;
	    	document.getElementsByName("param12")[0].disabled = true;
	    	document.getElementsByName("param13")[0].disabled = true;
	    	document.getElementsByName("param21")[0].disabled = false;
	    	document.getElementsByName("param22")[0].disabled = false;
	    	document.getElementsByName("param23")[0].disabled = false;
	    	document.getElementsByName("param11")[0].checked = false;
	    	document.getElementsByName("param12")[0].checked = false;
	    	document.getElementsByName("param13")[0].checked = false;
	    	changeColorDisable();
	    }
	}
   function changeColorDisable()
   {
   	document.getElementById("param111").style.color = "#ccc";
	document.getElementById("param121").style.color = "#ccc";
	document.getElementById("param131").style.color = "#ccc";
	document.getElementById("param211").style.color = "black";
	document.getElementById("param221").style.color = "black";
	document.getElementById("param231").style.color = "black";


   }
   function changeColorEnable()
   {
	   document.getElementById("param111").style.color = "black";
		document.getElementById("param121").style.color = "black";
		document.getElementById("param131").style.color = "black";
		document.getElementById("param211").style.color = "#ccc";
		document.getElementById("param221").style.color = "#ccc";
		document.getElementById("param231").style.color = "#ccc";
   }

</script>

</html>