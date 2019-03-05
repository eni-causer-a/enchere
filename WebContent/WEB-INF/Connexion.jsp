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
	<header class="py-3 bg-dark header-demodule fixed-top">
	    <div class="row">
	   		<div class="col-5">	
	   			<div class="container text-center text-white">
	   				<h3><a class="hn_clicable" href="<%=request.getContextPath()%>/Accueil">ENI-Encheres</a></h3>
	   			</div>
	   		</div>
	   		<div class="col-1"></div>
	   		<div class="col-6">
	        	<a href="<%=request.getContextPath()%>/NouveauProfil">Créer un compte </a>
	       	</div>
		</div>  
	</header>
	<h3 class="my-5 text-center">Connexion</h3>
	<div class="container">
		<form method="post" action="<%=request.getContextPath()%>/Connexion">
			<div class="row">
				<div class="col-4"></div>
				<div class="col-2">
			  		<label>Identifiant :</label>
			  	</div>
			  	<div class="col-2">
			  		<input type="text" name="Identifiant" value="${Identifiant}" required>
				</div>
				<div class="col-4"></div>
				<div class="w-100"></div>
				<div class="col-4"></div>
				<div class="col-2">
				  	<label>Mot de passe :</label>
				</div>
				<div class="col-2">
				  	<input type="password" name="MotDePasse" required>
				</div>
				<div class="col-4"></div>
				<c:if test="${loginError!=null}">
					<div class="w-100"></div>
					<div class="col-5"></div>
				  	<div class="col-6">
				  		<label style="color: red;" class="label-danger">${loginError}</label>
				  	</div>
				  	<div class="col"></div>
				  	<div class="col"></div>
				</c:if>
			</div>
			<br>
			<div class="row">
				<div class="col-6"></div>
				<div class="col-2">
					<button class="btn btn-secondary">Connexion</button>
				</div>
				<div class="col-5"></div>
			<div class="w-100"></div>
			<div class="col-5"></div>
			<div class="col-2">
				<input type="checkbox" id="SeSouvenirDeMoi" name="SeSouvenirDeMoi" checked>
				<label for="SeSouvenirDeMoi">Se souvenir de moi</label>
			</div>
			<div class="col-5"></div>
			<div class="w-100"></div>
			<div class="col-5"></div>
			<div class="col-2">
				<a href="mdpoublie">Mot de passe oublié</a> <!-- <%=request.getContextPath()%>/ServletConnexion -->
			</div>
			<div class="col-5"></div>
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