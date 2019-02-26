<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Profil</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	    </div>
	</header>
	<h5 class="my-5 text-center">Informations Profil</h5>
	<div class="col-12">
			<label>Pseudo :</label>
			${user.pseudo}
			<br>
			<label>Nom :</label>
			${user.nom}
			<br>
			<label>Prénom :</label>
			${user.prenom}
			<br>
			<label>Email :</label>
			${user.email}
			<br>
			<label>Telephone :</label>
			${user.telephone}
			<br>
			<label>Rue :</label>
			${user.rue}
			<br>
			<label>Code Postal :</label>
			${user.codePostale}
			<br>
			<label>Ville :</label>
			${user.ville}
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