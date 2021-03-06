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
	<fmt:setBundle basename="fr.eni.enchere.Error.Message.ErrorMessage" var ="errMessage"/>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<div>
		<h5 class="my-5 text-center"><fmt:message key="mv_titre" bundle="${langue}"/></h5>
		<form method="post" action="<%=request.getContextPath()%>/ModifieVente">
		    <div class="row">
		    <input type="hidden" name="idArticle" value="${article.getNoArticle()}${idArticle}">
		    	<div class="col"></div>
				<div class="col">
			  		<label><fmt:message key="article" bundle="${langue}"/></label>
			  	</div>
			  	<div class="col">
					<input type="text" name="nomArticle" value="${article.getNomArticle()}${nomArticle}" required>
				</div>
				<div class="col"></div>
			  	<div class="w-100"></div>
			  	<div class="col"></div>
			  	<div class="col">
			  		<label><fmt:message key="desc" bundle="${langue}"/></label>
			  	</div>
			  	<div class="col">
					<textarea class="form-control" rows="10" cols="25" wrap="hard" name="description" maxlength="1000">${article.getDescription()}${description}</textarea>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label><fmt:message key="categorie" bundle="${langue}"/></label>
			  	</div>
			  	<div class="col">
					<select name="categorie")>
						<c:forEach var="categorie" items="${lesCategories}">
							
							<c:if test="${article.getCategorie().getLibelle()==categorie.getLibelle() or cat==categorie.getNoCategorie()}">
								<option value="${categorie.getNoCategorie()}" selected>${categorie.getLibelle()}</option>
							</c:if>
							<c:if test="${article.getCategorie().getLibelle()!=categorie.getLibelle()}">
								<option value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
							</c:if>
						</c:forEach>		
					</select>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label><fmt:message key="photo" bundle="${langue}"/></label>
			  	</div>
			  	<div class="col">
					<input type="button" value="<fmt:message key="up" bundle="${langue}"/>">
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label><fmt:message key="map" bundle="${langue}"/></label>
			  	</div>
			  	<div class="col">
					<input type="number" min="0" name="miseAPrix" value="${article.getMiseAPrix()}${miseAPrix}" required>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col-3"></div>
			  	<div class="col-3">
			  		<label><fmt:message key="debut" bundle="${langue}"/></label>
			  	</div>
			  	
			  	<div class="col-5">
					<label><fmt:message key="le" bundle="${langue}"/></label>  <input type="date" name="debutEnchere" value="${formaterDate.format(article.getDateDebutEncheres())}${debutEnchere}" required> <label><fmt:message key="a" bundle="${langue}"/></label> <input type="time" name="debutEnchereTime" value="${formaterTime.format(article.getDateDebutEncheres())}${debutEnchereTime}" required>
				</div> 
				<div class="col-1"></div>
				<c:if test="${dateDebutError!=null}">
					<div class="w-100"></div>
					<div class="col-4"></div>
				  	<div class="col-6">
				  		<label style="color: red;" class="label-danger"><fmt:message key="errDate.Debut" bundle="${errMessage}"/></label>
				  	</div>
				  	<div class="col"></div>
				  	<div class="col"></div>
				</c:if>
				<div class="w-100"></div>
				<div class="col-3"></div>
			  	<div class="col-3">
			  		<label><fmt:message key="fin" bundle="${langue}"/></label>
			  	</div>
			  	<div class="col-5">
					<label><fmt:message key="le" bundle="${langue}"/></label>  <input type="date" name="finEnchere" value="${formaterDate.format(article.getDateFinEncheres())}${finEnchere}" required> <label><fmt:message key="a" bundle="${langue}"/></label> <input type="time" name="finEnchereTime" value="${formaterTime.format(article.getDateFinEncheres())}${finEnchereTime}" required>
				</div>
				<div class="col-1"></div>
				<c:if test="${dateFinError!=null}">
					<div class="w-100"></div>
					<div class="col-4"></div>
				  	<div class="col-6">
				  		<label style="color: red;" class="label-danger"><fmt:message key="errDate.Fin" bundle="${errMessage}"/></label>
				  	</div>
				  	<div class="col"></div>
				  	<div class="col"></div>
				</c:if>
			</div>
			
			<br>
			<div class="row list-group-item  d-flex">
				<div class="col-3"></div>
				<div class="col">
					<h6><fmt:message key="retrait" bundle="${langue}"/></h6>
				</div>
				<div class="w-100"></div>
				<div class="col-4"></div>
			  	<div class="col">
			  		<label><fmt:message key="rue" bundle="${langue}"/></label>
			  	</div>
			  	<div class="col">
					<input type="text" name="rue" value="${article.getRetrait().getRue()}${rue}" required>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col-4"></div>
			  	<div class="col">
			  		<label><fmt:message key="cp" bundle="${langue}"/></label>
			  	</div>
			  	<div class="col">
					<input type="text" name="codePostal" value="${article.getRetrait().getCode_postale()}${codeP}" required>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col-4"></div>
			  	<div class="col">
			  		<label><fmt:message key="ville" bundle="${langue}"/></label>
			  	</div>
			  	<div class="col">
					<input type="text" name="ville" value="${article.getRetrait().getVille()}${ville}" required">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col-4"></div>
				<div class="col-1">
					<button name="boutonEnregistrer" class="btn btn-secondary"><fmt:message key="save" bundle="${langue}"/></button>
				</div>
				<div class="col-1"></div>
				<div class="col-3">
					<button name="boutonSupprimer" class="btn btn-secondary"><fmt:message key="mv_del" bundle="${langue}"/></button>
				</div>
				<div class="col-1"></div>
				<div class="col-1">
					<a class="btn btn-secondary" href="<%=request.getContextPath()%>/Accueil" role="button" name="boutonAnnuler"><fmt:message key="annuler" bundle="${langue}"/></a>
				</div>
				<div class="col-5"></div>
			</div>
			
		</form>			
	</div>
	<br>
	<br>


    <!-- Bootstrap core JavaScript -->
    <script src="../../../vendor/jquery/jquery.min.js"></script>
    <script src="../../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>