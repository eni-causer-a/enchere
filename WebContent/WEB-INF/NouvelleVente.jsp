<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<title>ENI-Encheres</title>
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
		<h5 class="my-5 text-center">Nouvelle vente</h5>
		<form method="post" action="<%=request.getContextPath()%>/NouvelleVente">
		    <div class="row">
		    	<div class="col"></div>
				<div class="col">
			  		<label>Article :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="nomArticle" required>
				</div>
				<div class="col"></div>
			  	<div class="w-100"></div>
			  	<div class="col"></div>
			  	<div class="col">
			  		<label>Description :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="description">
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Cat�gorie :</label>
			  	</div>
			  	<div class="col">
					<select name="categorie")>
						<c:forEach var="categorie" items="${lesCategories}">
							<option value="${categorie.getNoCategorie()}">${categorie.getLibelle()}
						</c:forEach>		
					</select>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Photo de l'article :</label>
			  	</div>
			  	<div class="col">
					<input type="button" value="Uploader">
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Mise � prix :</label>
			  	</div>
			  	<div class="col">
					<input type="number" min="0" name="miseAPrix">
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>D�but de l'ench�re :</label>
			  	</div>
			  	<div class="col">
					<input type="date" name="debutEnchere" required>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Fin de l'ench�re :</label>
			  	</div>
			  	<div class="col">
					<input type="date" name="finEnchere" required>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row list-group-item  d-flex">
				<div class="col-3"></div>
				<div class="col">
					<h6>Retrait :</h6>
				</div>
				<div class="w-100"></div>
				<div class="col-4"></div>
			  	<div class="col">
			  		<label>Rue :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="rue" value="${utilisateur.getRue()}" required>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col-4"></div>
			  	<div class="col">
			  		<label>Code postal :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="codePostal" value="${utilisateur.getCodePostale()}" required>
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col-4"></div>
			  	<div class="col">
			  		<label>Ville :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="ville" value="${utilisateur.getVille()} required">
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col-4"></div>
				<div class="col-1">
					<button name="boutonEnregistrer" class="btn btn-secondary">Enregistrer</button>
				</div>
				<div class="col-1"></div>
				<div class="col-1">
					<a class="btn btn-secondary" href="<%=request.getContextPath()%>/Accueil" role="button" name="boutonAnnuler">Annuler</a>
				</div>
				<div class="col-5"></div>
			</div>
			
		</form>			
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
</html>