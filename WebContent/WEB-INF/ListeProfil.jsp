<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Liste des profils</title>
<link rel="shortcut icon" href="image/eni.ico">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="css/custom.css" rel="stylesheet">

<!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/4-col-portfolio.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body class="container">
	<jsp:include page="/WEB-INF/header.jsp" />
	<div style="position: relative; margin-top: 50px;">
	<c:forEach var="user" items="${listUser}">
	<div class="form-row">
		<div class="form-group col-md-3">
		<a href="<%=request.getContextPath()%>/profil?user=${user.getNoUtilisateur()}">${user.getPseudo()}</a>
		${user.getNom()}
		${user.getPrenom()}
		</div>
		<c:if test="${user.isActivate()==true}">
		<div class="form-group">
			<p><span  class="badge badge-pill badge-success">Activée</span></p>
		</div>
		<div class="form-group col-md-2">
			<a class="btn btn-secondary" href="<%=request.getContextPath()%>/ServletSupprimerProfil?userId=${user.getNoUtilisateur()}" role="button" name="boutonSupprimer">Désactiver</a>
		</div>
		</c:if>
		<c:if test="${user.isActivate()==false}">
			<div class="form-group">
				<p><span  class="badge badge-pill badge-danger">Désactivé</span></p>
			</div>
			<div class="form-group col-md-2">
				<a class="btn btn-secondary" href="<%=request.getContextPath()%>/ServletSupprimerProfil?userId=${user.getNoUtilisateur()}" role="button" name="boutonSupprimer">Activer</a>
			</div>
		</c:if>
	</div>
	</c:forEach>
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