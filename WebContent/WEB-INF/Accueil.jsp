<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="fr.eni.enchere.bo.Article"%>
<%@page import="fr.eni.enchere.bo.Categorie"%>
<%@page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<jsp:useBean id="LOCALE" scope="session" class="java.lang.String"/>
	<fmt:setLocale value="${LOCALE}"/>	
	<fmt:setBundle basename="fr.eni.enchere.lang.langue" var ="langue"/>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<form method="post" action="<%=request.getContextPath()%>/Accueil">
	
	<h3 class="my-5 text-center"><fmt:message key="liste_en" bundle="${langue}"/></h3>
	<c:if test="${utilisateur!=null}">	
		<fieldset id="group1">
			<div class="form-row">
				<div class="col-md-4">
					<c:if test="${select=='value1'}">
				    	<input type="radio"  value="value1" name="groupe1" onclick="activeDesactive(this,1);" checked>
				    	<label style="color: black;" id="achat"  for="groupe1"><fmt:message key="achat" bundle="${langue}"/></label>
				    </c:if>
				    <c:if test="${select!='value1'}">
				    	<input type="radio"  value="value1" name="groupe1" onclick="activeDesactive(this,1);">
				    	<label style="color: black;" id="achat"  for="groupe1"><fmt:message key="achat" bundle="${langue}"/></label>
				    </c:if>
			    	
			    </div>
			    <div class="col-md-4">
			    	<c:if test="${select=='value2'}">
				    	<input type="radio" value="value2" name="groupe1" onclick="activeDesactive(this,2);" checked>
				    	<label style="color: black;" id="vente"  for="groupe1"><fmt:message key="vente" bundle="${langue}"/></label>
				    </c:if>
				    <c:if test="${select!='value2'}">
				    	<input type="radio" value="value2" name="groupe1" onclick="activeDesactive(this,2);">
				    	<label style="color: black;" id="vente"  for="groupe1"><fmt:message key="vente" bundle="${langue}"/></label>
				    </c:if>
			    	
			    </div>
			</div>
		</fieldset>
		<div class="form-row">
			<div class="col-md-4">
				<c:if test="${select=='value1' and parame11!=null}">
					<input type="checkbox" value="param11" name="param11" checked />
					<label style="color: black;" id="param111"><fmt:message key="ouverte" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select=='value1' and parame11==null}">
					<input type="checkbox" value="param11" name="param11"/>
					<label style="color: black;" id="param111"><fmt:message key="ouverte" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select!='value1' and parame11==null}">
					<input type="checkbox" value="param11" name="param11" disabled/>
					<label style="color: #ccc;" id="param111"><fmt:message key="ouverte" bundle="${langue}"/></label>
				</c:if>
			</div>
			<div class="col-md-4">
				<c:if test="${select=='value2' and parame21!=null}">
					<input type="checkbox" value="param21" name="param21"  checked/>
					<label style="color: black;" id="param211"><fmt:message key="en_cours" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select=='value2' and parame21==null}">
					<input type="checkbox" value="param21" name="param21" />
					<label style="color: black;" id="param211"><fmt:message key="en_cours" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select!='value2' and parame21==null}">
					<input type="checkbox" value="param21" name="param21"  disabled/>
					<label style="color: #ccc;" id="param211"><fmt:message key="en_cours" bundle="${langue}"/></label>
				</c:if>
			
			</div>
			</div>
			<div class="form-row">
			<div class="col-md-4">
				<c:if test="${select=='value1' and parame12!=null}">
					<input type="checkbox" value="param12" name="param12" checked/>
					<label style="color: black;" id="param121"><fmt:message key="en_cours_c" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select=='value1' and parame12==null}">
					<input type="checkbox" value="param12" name="param12"/>
					<label style="color: black;" id="param121"><fmt:message key="en_cours_c" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select!='value1' and parame12==null}">
					<input type="checkbox" value="param12" name="param12"disabled/ >
					<label style="color: #ccc;" id="param121"><fmt:message key="en_cours_c" bundle="${langue}"/></label>
				</c:if>
			</div>
			<div class="col-md-4">
				<c:if test="${select=='value2' and parame22!=null}">
					<input type="checkbox" value="param22" name="param22" checked/>
					<label style="color: black;" id="param221"><fmt:message key="non_débuté" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select=='value2' and parame22==null}">
					<input type="checkbox" value="param22" name="param22" />
					<label style="color: black;" id="param221"><fmt:message key="non_débuté" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select!='value2' and parame22==null}">
					<input type="checkbox" value="param22" name="param22" disabled/>
					<label style="color: #ccc;" id="param221"><fmt:message key="non_débuté" bundle="${langue}"/></label>
				</c:if>
			</div>
			</div>
			<div class="form-row">
			<div class="col-md-4">
				<c:if test="${select=='value1' and parame13!=null}">
					<input type="checkbox" value="param13" name="param13" checked />
					<label style="color: black;" id="param131"><fmt:message key="remporté" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select=='value1' and parame13==null}">
					<input type="checkbox" value="param13" name="param13" />
					<label style="color: black;" id="param131"><fmt:message key="remporté" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select!='value1' and parame13==null}">
					<input type="checkbox" value="param13" name="param13" disabled />
					<label style="color: #ccc;" id="param131"><fmt:message key="remporté" bundle="${langue}"/></label>
				</c:if>
			</div>
			<div class="col-md-4">
				<c:if test="${select=='value2' and parame23!=null}">
					<input type="checkbox" value="param23" name="param23" checked/>
					<label style="color: black;" id="param231"><fmt:message key="terminé" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select=='value2' and parame23==null}">
					<input type="checkbox" value="param23" name="param23" />
					<label style="color: black;" id="param231"><fmt:message key="terminé" bundle="${langue}"/></label>
				</c:if>
				<c:if test="${select!='value2' and parame23==null}">
					<input type="checkbox" value="param23" name="param23" disabled/>
					<label style="color: #ccc;" id="param231"><fmt:message key="terminé" bundle="${langue}"/></label>
				</c:if>
			</div>
			</div>
			
		
	</c:if>
	<br>
	<h3><fmt:message key="filtre" bundle="${langue}"/></h3>
		<div class="row">
			<div class="col-4">
				<input class="form-control" type="text" name="filtre" placeHolder="<fmt:message key="ph_filtre" bundle="${langue}"/>" value="${filtre}">
			</div>
			<div style="postion: relative; margin-top: -31px;" class="col-4">
				<label><fmt:message key="categorie" bundle="${langue}"/></label>
				<select class="form-control" name="categorie">
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
				<button class="btn btn-secondary"><fmt:message key="rechercher" bundle="${langue}"/></button>
			</div>
	
		</div>
		<nav style="position: relative; margin-left: 450px; margin-top: 25px;" aria-label="Page navigation example">
			  <ul class="pagination">
			    <c:forEach var="page" begin="1" end="${nbrPage}">
			    	<li  class="page-item"><input class="page-link" name="page" type="submit" value="${page}"></li>
			    </c:forEach>			    
			  </ul>
			</nav>
	</form>
	
	<div class="form-row">
		<c:forEach var="article" items="${lesArticles}">
			<div class="form-group col-md-6">
				<div class="card" style="width: 25rem;">
				  <div class="card-body">
				   <h5 class="card-title"><a href="<%=request.getContextPath()%>/DetailVente?idArticle=${article.getNoArticle()}">${article.getNomArticle().toUpperCase()}</a></h5>
				    	<c:if test="${article.getPhoto() != null}">
				    		<img src="http://10.51.101.6:8080/MesDocuments/${article.getPhoto()}" width="300" height="250"/>
				    	</c:if>
				    		<p style="position: relative; margin-top: 10px;" class="card-text">${article.getDescription()}</p>
				    	<c:if test="${article.getEtatVente() == 'CREE'}">
				    		<p style="postion: relative; padding-top:5px;"><span  class="badge badge-pill badge-primary"><fmt:message key="e_c" bundle="${langue}"/></span></p>
				    	</c:if>
				    	<c:if test="${article.getEtatVente() == 'EN_COURS'}">
				    		<p style="postion: relative; padding-top:5px;"><span  class="badge badge-pill badge-success"><fmt:message key="e_ec" bundle="${langue}"/></span></p>
				    	</c:if>
				    	<c:if test="${article.getEtatVente() == 'ENCHERE_TERMINE'}">
				    		<p style="postion: relative; padding-top:5px;"><span class="badge badge-pill badge-warning"><fmt:message key="e_t" bundle="${langue}"/></span></p>
				    	</c:if>

					    <p><label style="font-weight : bold;"><fmt:message key="categorie" bundle="${langue}"/></label> ${article.getCategorie().getLibelle()}</p>
					    <p><label style="font-weight : bold;"><fmt:message key="meilleur_o" bundle="${langue}"/></label> ${article.getPrixVente()} points</p>
					    <p><label style="font-weight : bold;"><fmt:message key="a_debut" bundle="${langue}"/></label> ${article.printDateDebutEnchere()}</p>
					    <p><label style="font-weight : bold;"><fmt:message key="a_fin" bundle="${langue}"/></label> ${article.printDateFinEnchere()}</p>
					    <!--  <p>Vendeur : <a href="<%=request.getContextPath()%>/profil?user=${article.getProprietaire().getNoUtilisateur()}">${article.getProprietaire().getPseudo()}</a></p>-->

				  </div>
				</div>
			</div>
		</c:forEach>
	</div>

	<br>
	<br>

    <!-- Footer -->
    

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