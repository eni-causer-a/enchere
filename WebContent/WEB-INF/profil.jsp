<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<div style="postion: relative; margin-left: 450px">
	<h5 class="my-5">Informations Profil</h5>
	<div class="form-row">
			<div><label>Pseudo : ${user.pseudo}</label></div>
	</div>
	<div class="form-row">
			<div><label>Nom : ${user.nom}</label></div>
	</div>
	<div class="form-row">
			<div><label>Nom : ${user.prenom}</label></div>
	</div>
	<div class="form-row">
			<div ><label>Email : ${user.email}</label></div>
	</div>
	<div class="form-row">
			<div><label>Téléphone : ${user.telephone}</label></div>
	</div>
	<div class="form-row">
			<div><label>Rue : ${user.rue}</label></div>
	</div>						
	<div class="form-row">
			<div><label>Code Postal : ${user.codePostale}</label></div>
	</div>
	<div class="form-row">
			<div><label>Ville : ${user.ville}</label></div>
	</div>		
			
	
	<div class="form-row">
		<c:if test="${sessionScope.Utilisateur.getNoUtilisateur() == user.getNoUtilisateur()}">
			<form action="modifProfil">
				<div><button class="btn btn-secondary">Modifier</button></div>
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