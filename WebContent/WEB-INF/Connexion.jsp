<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<jsp:useBean id="LOCALE" scope="session" class="java.lang.String"/>
	<fmt:setLocale value="${LOCALE}"/>	
	<fmt:setBundle basename="fr.eni.enchere.lang.langue" var ="langue"/>
	<fmt:setBundle basename="fr.eni.enchere.Error.Message.ErrorMessage" var ="errMessage"/>
	<header class="py-3 bg-dark header-demodule fixed-top">
	   <jsp:include page="/WEB-INF/header.jsp" />  
	</header>
	<h3 class="my-5 text-center"><fmt:message key="cnx" bundle="${langue}"/></h3>
	<div class="container" style="position: relative; margin-left: 425px;">
		<form method="post" action="<%=request.getContextPath()%>/Connexion">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="id"><fmt:message key="id" bundle="${langue}"/></label>
					<input type="text" id="id" class="form-control w-50" name="Identifiant" value="${Identifiant}" required>
				</div>
				
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="mdp"><fmt:message key="mdp" bundle="${langue}"/></label>
					<input type="password" id="mdp" class="form-control w-50" name="MotDePasse" required>
				</div>
			</div>
			
			<c:if test="${loginError!=null}">
				<div class="form-row">
					<div class="form-group col-md-6">					
				  		<label style="color: red;" class="label-danger"><fmt:message key="errLog.BadLog" bundle="${errMessage}"/></label>
					</div>
				</div>
			</c:if>
			<div class="form-row">
				<div class="form-group col-md-6">
					<button class="btn btn-secondary"><fmt:message key="cnx" bundle="${langue}"/></button>
				</div>
				
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<input type="checkbox" id="SeSouvenirDeMoi" name="SeSouvenirDeMoi" checked>
					<label for="SeSouvenirDeMoi"><fmt:message key="souvenir" bundle="${langue}"/></label>
				</div>
			</div>
			<div class="col-5"></div>
			<div class="w-100"></div>
			<div class="col-5"></div>
			<div class="col-2">
				<a href="mdpoublie"><fmt:message key="mdp_oublie" bundle="${langue}"/></a> <!-- <%=request.getContextPath()%>/ServletConnexion -->
			</div>
			<a href="<%=request.getContextPath()%>/NouveauProfil"><fmt:message key="creer_compte" bundle="${langue}"/> </a>
		
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