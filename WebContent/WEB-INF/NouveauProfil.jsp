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
	    <div class="row">
	   		<div class="col-6">	
	   			<div class="container text-center text-white">
	   				<h3>ENI-Encheres</h3>
	   			</div>
	   		</div>
	   	</div>
	</header>
	
	<div>
		<h5 class="my-5 text-center">Mon Profil</h5>
		<form method="post" action="<%=request.getContextPath()%>/ServletNouveauProfil">
		    <div class="row">
		    	<div class="col"></div>
				<div class="col">
			  		<label>Pseudo :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="pseudo">
				</div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Nom :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="nom">
				</div>
				<div class="col"></div>
			  	<div class="w-100"></div>
			  	<div class="col"></div>
			  	<div class="col">
			  		<label>Prénom :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="prenom">
				</div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Email :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="email">
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Téléphone :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="telephone">
				</div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Rue :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="rue">
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Code Postal :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="codePostal">
				</div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Ville :</label>
			  	</div>
			  	<div class="col">
					<input type="text" name="ville">
				</div>
				<div class="col"></div>
				<div class="w-100"></div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Mot de passe :</label>
			  	</div>
			  	<div class="col">
					<input type="password" name="motDePasse">
				</div>
				<div class="col"></div>
			  	<div class="col">
			  		<label>Confirmation :</label>
			  	</div>
			  	<div class="col">
					<input type="password" name="confirmationMotDePasse">
				</div>
				<div class="col"></div>
			</div>
			<div class="row">
				<div class="col-5"></div>
				<div class="col-1">
					<button name="boutonCreer">Créer</button>
				</div>
				<div class="col-1">
					<button name="boutonAnnuler">Annuler</button>
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