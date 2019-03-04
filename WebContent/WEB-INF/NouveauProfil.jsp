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
	   		<div class="col-6">	
	   			<div class="container text-center text-white">
	   				<h3><a class="hn_clicable" href="<%=request.getContextPath()%>/Accueil">ENI-Encheres</a></h3>
	   			</div>
	   		</div>
	   	</div>
	</header>
	
	<div>
		<h5 class="my-5 text-center">Mon Profil</h5>
		<form method="post" action="<%=request.getContextPath()%>/NouveauProfil">
		    <div class="row">
		    	<div class="col"></div>
				<div class="col">
			  		<label>Pseudo :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="pseudo" value="${pseudo}" required>
				</div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Nom :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="nom" value="${nom}" required>
				</div>
				<div class="col"></div>
				<c:if test="${pseudoError!=null}">
					<div class="w-100"></div>
					<div class="col-3"></div>
				  	<div class="col">
				  		<label style="color: red;" class="label-danger">${pseudoError}</label>
				  	</div>
				  	<div class="col"></div>
				  	<div class="col"></div>
				</c:if>
			  	<div class="w-100"></div>
			  	<div class="col"></div>
			  	<div class="col">
			  		<label>Prénom :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="prenom" value="${prenom}" required>
				</div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Email :</label>
			  	</div>
			  	<div class="col">
					<input type="email" name="email" value="${email}" required>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Téléphone :</label>
			  	</div>
			  	<div class="col">
					<input type="tel" name="telephone" value="${telephone}" required>
				</div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Rue :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="rue" value="${rue}" required>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Code Postal :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="codePostal" value="${codePostal}" required>
				</div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Ville :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="ville" value="${ville}" required>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Mot de passe :</label>
			  	</div>
			  	<div class="col">
					<input type="password" name="motDePasse" required>
				</div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Confirmation :</label>
			  	</div>
			  	<div class="col">
					<input type="password" name="confirmationMotDePasse" required>
				</div>
				<div class="col"></div>
				<c:if test="${mdpError!=null}">
				<div class="w-100"></div>
				<div class="col-4"></div>
			  	<div class="col-6">
			  		<label style="color: red;" class="label-danger">${mdpError}</label>
			  	</div>
			  	<div class="col"></div>
			  	<div class="col"></div>
			</c:if>
			</div>
			
			
			<br>
			<div class="row">
				<div class="col-5"></div>
				<div class="col-1">
					<button name="boutonCreer" class="btn btn-secondary">Créer</button>
				</div>
				<div class="col-1">
					<!--<button name="boutonAnnuler">Annuler</button>-->
					<a class="btn btn-secondary" href="<%=request.getContextPath()%>/Accueil" role="button" name="boutonAnnuler">Annuler</a>
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