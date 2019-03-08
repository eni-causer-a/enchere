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
		<h5 class="my-5 text-center"><fmt:message key="new_v" bundle="${langue}"/></h5>
		<form method="post" action="<%=request.getContextPath()%>/NouvelleVente" enctype="multipart/form-data">
		    <div class="form-row">
		    	<div class="form-group col-md-6">
		    		<div class="form-row">
			  			<label><fmt:message key="article" bundle="${langue}"/></label>
			  			<input type="text" class="form-control" name="nomArticle" value="${nomArticle}" required>
			  		</div>
			  		<div class="form-row">
			  		<label><fmt:message key="cat" bundle="${langue}"/></label>
					<select class="form-control" name="categorie">
						<c:forEach var="categorie" items="${lesCategories}">
							<!-- <option value="${categorie.getNoCategorie()}">${categorie.getLibelle()} -->
							<c:if test="${cat==categorie.getNoCategorie()}">
								<option value="${categorie.getNoCategorie()}" selected>${categorie.getLibelle()}</option>
							</c:if>
							<c:if test="${cat!=categorie.getNoCategorie()}">
								<option value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
							</c:if>
						</c:forEach>		
					</select>
			  		</div>
			  		 <div class="form-row">
			  			<label style="padding-top: 15px;"><fmt:message key="photo" bundle="${langue}"/></label>
						<input style="padding-left: 10px; padding-top: 10px;" class="btn" type="file" name="fileName" value="Uploader">
			  		</div>
			  		<div class="form-row">
			  		<label><fmt:message key="map" bundle="${langue}"/></label>
					<input class="form-control" type="number" min="0" name="miseAPrix" value="${miseAPrix}" required>
			  		</div>
		    	</div>		  		
			  	<div class="form-group col-md-6">
			  		<label><fmt:message key="desc" bundle="${langue}"/></label>
					<textarea class="form-control" rows="9" cols="25" wrap="hard" name="description" maxlength="1000">${description}</textarea>
			  	</div>
		    </div>
		  
			<div class="form-row">
			<div class="form-group col-md-2">
			  		<label><fmt:message key="debut" bundle="${langue}"/></label>
			  </div>
				<div class="form-group col-md-10">
					<div class="form-row">
				
					<div class="form-group col-md-4">
					<label><fmt:message key="le" bundle="${langue}"/></label>
					<input class="form-control" type="date" name="debutEnchere" value="${debutEnchere}" required>
					</div>
					<div class="form-group col-md-3">
					<label><fmt:message key="a" bundle="${langue}"/> </label>
					<input class="form-control" type="time" name="debutEnchereTime" value="${debutEnchereTime}" required>
					</div>
			  		<div class="form-group col-md-3">
					<c:if test="${dateDebutError!=null}">
				  		<label style="color: red;" class="label-danger"><fmt:message key="errDate.Debut" bundle="${errMessage}"/></label>			  	
					</c:if>
					</div>
					</div>
				</div>
				<div class="form-group col-md-2">
			  		<label><fmt:message key="fin" bundle="${langue}"/></label>
			  	</div>
				<div class="form-group col-md-10">
				
				<div class="form-row">
					<div class="form-group col-md-4">
						<label><fmt:message key="le" bundle="${langue}"/></label>  
						<input class="form-control" type="date" name="finEnchere" value="${finEnchere}" required>
					</div>
					<div class="form-group col-md-3">
						<label><fmt:message key="a" bundle="${langue}"/></label>
						<input class="form-control" type="time" name="finEnchereTime" value="${finEnchereTime}" required>
					</div>
			  		<div class="form-group col-md-3">
					<c:if test="${dateFinError!=null}">
				  		<label style="color: red;" class="label-danger"><fmt:message key="errDate.Fin" bundle="${errMessage}"/></label>			  	
					</c:if>
					</div>
				</div>
				</div>
		    </div>
		    
		    
			
		    <div class="form-row list-group-item  d-flex">
		    	<div class="form-row">
					<h6><fmt:message key="retrait" bundle="${langue}"/></h6>
				</div>
				<div style="postion: relative; margin-left: 20px;" class="form-row">		
		    		<div class="form-group col-md-4">
			  			<label><fmt:message key="rue" bundle="${langue}"/></label>
			  			<c:if test="${rue==null}">
							<input class="form-control" type="text" name="rue" value="${utilisateur.getRue()}" required>
						</c:if>
						<c:if test="${rue!=null}">
							<input class="form-control" type="text" name="rue" value="${rue}" required>
						</c:if>
			  		</div>
		    		<div class="form-group col-md-4">
			  			<label><fmt:message key="cp" bundle="${langue}"/></label>
			  			<c:if test="${codeP==null}">
							<input class="form-control" type="text" name=codePostal value="${utilisateur.getCodePostale()}" required>
						</c:if>
						<c:if test="${codeP!=null}">
							<input class="form-control" type="text" name="codePostal" value="${codeP}" required>
						</c:if>
		   			</div>
		    		<div class="form-group col-md-4">
			  			<label><fmt:message key="ville" bundle="${langue}"/></label>
			  			<c:if test="${ville==null}">
							<input class="form-control" type="text" name="ville" value="${utilisateur.getVille()}" required>
						</c:if>
						<c:if test="${ville!=null}">
							<input class="form-control" type="text" name="ville" value="${ville}" required>
						</c:if>
			  		</div>
			  		</div>
		   </div>
		   
			 <div style="position : relative; margin-left: 400px; margin-top: 15px;" class="form-row">
		    	<div class="form-group col-md-2">
			  		<button name="boutonEnregistrer" class="btn btn-secondary"><fmt:message key="save" bundle="${langue}"/></button>		
			  	</div>
			  	<div class="form-group col-md-2">
			  		<a class="btn btn-secondary" href="<%=request.getContextPath()%>/Accueil" role="button" name="boutonAnnuler"><fmt:message key="annuler" bundle="${langue}"/></a>	
			  	</div>
		    </div>
	
		</form>			
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