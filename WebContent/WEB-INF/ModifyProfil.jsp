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
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<div>
		<h5 class="my-5 text-center">Mon Profil</h5>
		<form method="post" action="<%=request.getContextPath()%>/modifProfil">
		
		<div class="form-row">
    		<div class="form-group col-md-6">
      			<label for="inputPseudo">Pseudo</label>
      			<input type="text" class="form-control" value="${user.pseudo}" name="pseudo" id="inputPseudo" placeholder="Pseudo">
    		</div>
    		<div class="form-group col-md-6">
      			<label for="inputNom">Nom</label>
      			<input type="text" class="form-control" id="inputNom" name="nom" value="${user.nom}" placeholder="Nom">
    		</div>
    	</div>
    	<div class="form-row">
    		<div class="form-group  col-md-6">
    			<label for="inputPrenom">Prénom</label>
      			<input type="text" class="form-control" id="inputPrenom" name="prenom" value="${user.prenom}" placeholder="Prenom">
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputEmail">Email</label>
      			<input type="email" class="form-control" id="inputEmail" name="email" value="${user.email}" placeholder="Email">
    		</div>
    	</div>
    	<div class="form-row">
    		<div class="form-group  col-md-6">
    			<label for="inputTel">Téléphone</label>
      			<input type="text" class="form-control" id="inputTel" name="telephone" value="${user.telephone}" placeholder="Téléphone">
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputRue">Rue</label>
      			<input type="text" class="form-control" id="inputRue" name="rue" value="${user.rue}" placeholder="Rue">
    		</div>
    	</div>
    	<div class="form-row">
    		<div class="form-group  col-md-6">
    			<label for="inputCp">Code Postal</label>
      			<input type="text" class="form-control" id="inputCp" name="codePostal" value="${user.codePostale}" placeholder="Code Postal">
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputVille">Ville</label>
      			<input type="text" class="form-control" id="inputVille" name="ville" value="${user.ville}" placeholder="Ville">
    		</div>
    	</div>
    	<div class="form-group">
    			<label for="inputMdp">Mot de passe actuel</label>
      			<input type="password" class="form-control" id="inputMdp" name="motDePasse"  placeholder="Mot de passe">
    	</div>
    	<div class="form-row">
    		<div class="form-group  col-md-6">
    			<label for="inputMdpNew">Nouveau Mot de passe</label>
      			<input type="password" class="form-control" id="inputMdpNew" name="motDePasses"  placeholder="Nouveau Mot de passe">
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputMdpNewC">Confirmation</label>
      			<input type="password" class="form-control" id="inputMdpNewC" name="confirmationMotDePasse"  placeholder="Confirmation">
    		</div>
    	</div>
		<div class="form-row">
    		<div class="form-group  col-md-6">
    			<button name="boutonCreer" class="btn btn-secondary">Enregistrer</button>		
    		</div>
    		<div class="form-group  col-md-6">
    			<button name="boutonAnnuler" class="btn btn-secondary">Supprimer mon compte</button>
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