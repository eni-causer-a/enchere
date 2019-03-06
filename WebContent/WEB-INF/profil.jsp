<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Profil</title>
<link rel="shortcut icon" href="image/eni.ico">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
	
	<div style="postion: relative; margin-left: 450px">
	<h5 class="my-5"><fmt:message key="titre_info_pf" bundle="${langue}"/></h5>
	<div style="position: relative; margin-top: -15px;" class="form-row ">
			<div class="form-group col-md-2 "><label><fmt:message key="pseudo" bundle="${langue}"/> </label></div>
			<div>${user.pseudo}</div>
	</div>
	<div style="position: relative; margin-top: -15px;" class="form-row">
			<div class="form-group col-md-2"><label><fmt:message key="nom" bundle="${langue}"/></label></div>
			<div>${user.nom}</div>
	</div>
	<div style="position: relative; margin-top: -15px;" class="form-row">
			<div class="form-group col-md-2"><label><fmt:message key="prenom" bundle="${langue}"/> </label></div>
			<div>${user.prenom}</div>
	</div>
	<div style="position: relative; margin-top: -15px;" class="form-row">
			<div class="form-group col-md-2"><label><fmt:message key="email" bundle="${langue}"/> </label></div>
			<div>${user.email}</div>
	</div>
	<div style="position: relative; margin-top: -15px;" class="form-row">
			<div class="form-group col-md-2"><label><fmt:message key="tel" bundle="${langue}"/> </label></div>
			<div>${user.telephone}</div>
	</div>
	<div style="position: relative; margin-top: -15px;" class="form-row">
			<div class="form-group col-md-2"><label><fmt:message key="rue" bundle="${langue}"/> </label></div>
			<div>${user.rue}</div>
	</div>						
	<div style="position: relative; margin-top: -15px;" class="form-row">
			<div class="form-group col-md-2"><label><fmt:message key="cp" bundle="${langue}"/> </label></div>
			<div>${user.codePostale}</div>
	</div>
	<div style="position: relative; margin-top: -15px;" class="form-row">
			<div class="form-group col-md-2"><label><fmt:message key="ville" bundle="${langue}"/></label></div>
			<div> ${user.ville}</div>
	</div>		
			
	
	<div class="form-row">
		<c:if test="${sessionScope.Utilisateur.getNoUtilisateur() == user.getNoUtilisateur()}">
			<form action="modifProfil">
				<div><button class="btn btn-secondary"><fmt:message key="modifier" bundle="${langue}"/></button></div>
			</form>
		</c:if>
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