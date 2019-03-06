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
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<div style="position: relative; margin-top: 30px;">
		<h5  class="my-5 text-center"><fmt:message key="detail" bundle="${langue}"/></h5>
	<div>
	<div class="form-row">
		<c:if test="${article.getPhoto() != null}">
	    	<div class="form-group col-md-6">
				  <img src="http://10.51.101.6:8080/MesDocuments/${article.getPhoto()}" width="300" height="250"/>
			</div>
		</c:if>	
	    	
	    	<div class="form-group col-md-6">
	    		<div class="form-row">
	    			<div class="form-group">
						<h6>${article.getNomArticle()}</h6>
					</div>
				</div>
				<div class="form-row">
	    			<div class="form-group">
						<label><fmt:message key="meilleur_o" bundle="${langue}"/></label>
						<label name="meilleurOffre">${article.getPrixVente()} points <c:if test="${article.miseAPrix != article.prixVente }">par <a href="<%=request.getContextPath()%>/profil?user=${em.getLastEnchere(article).utilisateur.getNoUtilisateur()}">${em.getLastEnchere(article).utilisateur.pseudo}</a></c:if>   </label>
					</div>
				</div>
				<div class="form-row">
	    			<div class="form-group">
						<label><fmt:message key="map" bundle="${langue}"/></label>
						<label name="miseAPrix">${article.getMiseAPrix()} points</label>
					</div>
				</div>
				<div class="form-row">
	    			<div class="form-group">
						<c:if test="${utilisateur!=null and utilisateur.getPseudo()!=article.getProprietaire().getPseudo() and enCours}">
							<form method="post" action="<%=request.getContextPath()%>/DetailVente?idArticle=<%=request.getParameter("idArticle")%>">
								<c:if test="${Utilisateur.pseudo != article.proprietaire.pseudo}">
									<div class="form-row">
										<div class="form-group col-md-4">
											<label><fmt:message key="proposition" bundle="${langue}"/></label>
										</div>
										<div class="form-group col-md-4">
											<input class="form-control" type="number" min="${article.getPrixVente()+1}" value="${article.getPrixVente()+1}" name="miseAPrix">
										</div>
										<div class="form-group col-md-4">
											<button class="btn btn-secondary">Enchérir</button>
										</div>
									</div>
								</c:if>
							</form>
						</c:if>	
					</div>
				</div>
				
						
			</div>

	</div>
	<div class="form-row">
	    	<div class="form-group col-md-6">
				<h6>${article.getNomArticle()}</h6>
			</div>
	</div>
	<div class="form-row">
	    	<div class="form-group col-md-6">
				<label><fmt:message key="desc" bundle="${langue}"/></label>
				<label name="description">${article.getDescription()}</label>
			</div>
	</div>
	<div class="form-row">
	    	<div class="form-group col-md-6">
				<label><fmt:message key="cat" bundle="${langue}"/></label>
				<label name="categorie">${article.getCategorie().getLibelle()}</label>
			</div>
	</div>
	<div class="form-row">
	    	<div class="form-group col-md-6">
				<label><fmt:message key="debut" bundle="${langue}"/></label>
				<label name="debutEnchere">${article.printDateDebutEnchere()}</label>
			</div>
	</div>
	<div class="form-row">
	    	<div class="form-group col-md-6">
				<label><fmt:message key="fin" bundle="${langue}"/></label>
				<label name="finEnchere">${article.printDateFinEnchere()}</label>
			</div>
	</div>
	<div class="form-row">
	    	<div class="form-group col-md-6">
				<label><fmt:message key="retrait" bundle="${langue}"/></label>
				<label name="retrait">${article.getRetrait().getRue()} ${article.getRetrait().getCode_postale()} ${article.getRetrait().getVille()}</label>
			</div>
	</div>
	<div class="form-row">
	    	<div class="form-group col-md-6">
				<label><fmt:message key="vendeur" bundle="${langue}"/></label>
				<a href="<%=request.getContextPath()%>/profil?user=${article.getProprietaire().getNoUtilisateur()}">${article.getProprietaire().getPseudo()}</a>
			</div>
	</div>
	<div class="form-row">
	    	<div class="form-group col-md-6">
				<c:if test="${utilisateur.getPseudo()==article.getProprietaire().getPseudo() and cree==true}">
			  		<a href="<%=request.getContextPath()%>/ModifieVente?idArticle=${article.getNoArticle()}" class="btn btn-secondary">Modifier vente</a>
				</c:if>
			</div>
	</div>
	<div class="form-row">
	    	<div class="form-group col-md-6">
				<c:if test="${ended ==true and article.getGagnant().getPseudo().equals(utilisateur.getPseudo())}">
			  		<a href="<%=request.getContextPath()%>/ServletRetrait?idArticle=${article.getNoArticle()}" class="btn btn-secondary">Retiré</a>
				</c:if>
			</div>
	</div>
	

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
</html>