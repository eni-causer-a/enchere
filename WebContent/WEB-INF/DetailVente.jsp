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
		
		<h5 class="my-5 text-center">Détail vente</h5>
		
		
	    <div class="row">
	    	<div class="col"></div>
			<div class="col">
		  		<h6>${article.getNomArticle()}</h6>
		  	</div>
		  	<div class="col"></div>
			<div class="col"></div>
		  	<div class="w-100"></div>
		  	<div class="col-3"></div>
		  	<div class="col-3">
		  		<label>Description :</label>
		  	</div>
		  	<div class="col-3">
				<label name="description">${article.getDescription()}</label>
			</div>
			<div class="col-3"></div>
			<div class="w-100"></div>
			<div class="col"></div>
		  	<div class="col">
		  		<label>Catégorie :</label>
		  	</div>
		  	<div class="col">
				<label name="categorie">${article.getCategorie().getLibelle()}</label>
			</div>
			<div class="col"></div>
			<div class="w-100"></div>
			<div class="col"></div>
		  	<div class="col">
		  		<label>Meilleur offre :</label>
		  	</div>
		  	<div class="col">
				<label name="meilleurOffre">${article.getPrixVente()} points par ${article.proprietaire.pseudo}  </label>
			</div>
			<div class="col"></div>
			<div class="w-100"></div>
			<div class="col"></div>
		  	<div class="col">
		  		<label>Mise à prix :</label>
		  	</div>
		  	<div class="col">
				<label name="miseAPrix">${article.getMiseAPrix()} points</label>
			</div>
			<div class="col"></div>
			<div class="w-100"></div>
			<div class="col"></div>
		  	<div class="col">
		  		<label>Fin de l'enchère :</label>
		  	</div>
		  	<div class="col">
				<label name="finEnchere">${article.getDateFinEncheres()}</label>
			</div>
			<div class="col"></div>
			<div class="w-100"></div>
			<div class="col"></div>
		  	<div class="col">
		  		<label>Retrait :</label>
		  	</div>
		  	<div class="col">
				<label name="retrait">${article.getRetrait().getRue()} ${article.getRetrait().getCode_postale()} ${article.getRetrait().getVille()}</label>
			</div>
			<div class="col"></div>
			<div class="w-100"></div>
			<div class="col"></div>
		  	<div class="col">
		  		<label>Vendeur :</label>
		  	</div>
		  	<div class="col">
				<a href="<%=request.getContextPath()%>/profil?user=${article.getProprietaire().getNoUtilisateur()}">${article.getProprietaire().getPseudo()}</a>
			</div>
			<div class="col"></div>
		</div>
		<c:if test="${utilisateur!=null and utilisateur.getPseudo()!=article.getProprietaire().getPseudo()}">
			<form method="post" action="<%=request.getContextPath()%>/DetailVente?idArticle=<%=request.getParameter("idArticle")%>">
				<div class="row">
					<div class="col"></div>
				  	<div class="col">
				  		<label>Ma proposition :</label>
				  	</div>
				  	
				  	<div class="col">
						<input type="number" min="${article.getPrixVente()+1}" value="${article.getPrixVente()+1}" name="miseAPrix">
					</div>
					<div class="col">
						<button>Enchérir</button>
					</div>
					<div class="col"></div>
				</div>
			</form>
		</c:if>
		
		<c:if test="${utilisateur.getPseudo()==article.getProprietaire().getPseudo()}">
			<br>
			<div class="row">
				<div class="col-4"></div>
			  	<div class="col-2">
			  		<a href="#ModifierVente" class="btn btn-secondary">Modifier vente</a>
			  	</div>
				<div class="col"></div>
			</div>
		
		</c:if>
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