<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<title>ENI-Encheres</title>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/4-col-portfolio.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body class="container">
	<header class="py-3 bg-dark header-demodule fixed-top">
	    <div class="container text-center text-white">
	        <h5 class="my-5 text-left">ENI-Encheres</h5>
	        <a href="#connection">S'inscrire - Se connecter</a>
	         
	    </div>
	</header>

	<div>
	    <h5 class="my-5 text-center">Liste des enchères</h5>
		<div class="row">
			<h3>Filtres :</h3>
			<div class="col-12">
				<input type="text" name="filtre" placeHolder="Le nom de l'article contient">
				<h6>Catégorie :</h6>
				<select>
				   <option value="Toutes"> Toutes
				   <option value="Informatique"> Informatique
				   <option value="Ameublement"> Ameublement
				   <option value="Vêtement"> Vêtement
				   <option value="Sport&Loisirs"> Sport&Loisirs
				</option>
			</div>
			<button>Rechercher</button>
		</div>
		
	    <div class="row-10">
	        <ul class="list-group col-12">
	            <!--  
				<c:forEach var="article" items="${listePanier}">
					<li class="list-group-item d-flex justify-content-between align-items-center">${panier.nomPanier}
		                <div>
		                    <a href="<%=request.getContextPath()%>/ServletPanier?id=${panier.idPanier}"><i class="material-icons">shopping_cart</i></a>
		                    <a href="#supprimer" class="badge text-danger" title="Supprimer"><i class="material-icons">delete</i></a>
		                </div>
	            	</li>
            	</c:forEach>
				
				-->
			</ul>
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