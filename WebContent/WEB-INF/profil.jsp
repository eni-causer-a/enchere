<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Profil</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="css/custom.css" rel="stylesheet">

<!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/4-col-portfolio.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body class="container">
	<header class="py-3 bg-dark header-demodule fixed-top">
	    <<div class="row">
	   		<div class="col-6">	
	   			<div class="container text-center text-white">
	   				<h3><a class="hn_clicable" href="<%=request.getContextPath()%>/Accueil">ENI-Encheres</a></h3>
	   			</div>
	   		</div>
	   	</div>
	</header>
	<h5 class="my-5 text-center">Informations Profil</h5>
	<div class="row">
			<div class="col-4"></div>
			<div class="col-2"><label>Pseudo :</label></div>
			<div class="col-2">${user.pseudo}</div>
			<div class="col"></div>		
		<div class="w-100"></div>
			<div class="col-4"></div>
			<div class="col-2"><label>Nom :</label></div>
			<div class="col-2">${user.nom}</div>
			<div class="col"></div>		
		<div class="w-100"></div>
			<div class="col-4"></div>
			<div class="col-2"><label>Prénom :</label></div>
			<div class="col-2">${user.prenom}</div>
			<div class="col"></div>	
		<div class="w-100"></div>
			<div class="col-4"></div>
			<div class="col-2"><label>Email :</label></div>
			<div class="col-2">${user.email}</div>
			<div class="col"></div>	
		<div class="w-100"></div>
			<div class="col-4"></div>
			<div class="col-2"><label>Telephone :</label></div>
			<div class="col-2">${user.telephone}</div>
			<div class="col"></div>	
		<div class="w-100"></div>
			<div class="col-4"></div>
			<div class="col-2"><label>Rue :</label></div>
			<div class="col-2">${user.rue}</div>
			<div class="col"></div>	
		<div class="w-100"></div>
			<div class="col-4"></div>
			<div class="col-2"><label>Code Postal :</label></div>
			<div class="col-2">${user.codePostale}</div>
			<div class="col"></div>	
		<div class="w-100"></div>
			<div class="col-4"></div>
			<div class="col-2"><label>Ville :</label></div>
			<div class="col-2">${user.ville}</div>
			<div class="col"></div>	
	</div>
		
		<c:if test="${sessionScope.Utilisateur.getNoUtilisateur() == user.getNoUtilisateur()}">
			<form action="modifProfil">
			<br>
			<div class="row">
				<div class="col"></div>
				<div class="col"><button class="btn btn-secondary">Modifier</button></div>
				<div class="col"></div>
				</div>
			</form>
		</c:if>
	
		
			
	<!-- Footer -->
    <footer class="row bg-dark footer-demodule fixed-bottom py-1">
    	
        <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="../../../vendor/jquery/jquery.min.js"></script>
    <script src="../../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>