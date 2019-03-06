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
	<table>
	<c:forEach var="user" items="${listUser}">
	<tr>
		<td><a href="<%=request.getContextPath()%>/profil?user=${user.getNoUtilisateur()}">${user.getPseudo()}</a></td>
		<td>${user.getNom()}</td>
		<td>${user.getPrenom()}</td>
		<c:if test="${user.isActivate()==true}">
			<td><p style="postion: relative; padding-top:5px;"><span  class="badge badge-pill badge-success">Activ�e</span></p></td>
			<td><a class="btn btn-secondary" href="<%=request.getContextPath()%>/ServletSupprimerProfil?userId=${user.getNoUtilisateur()}" role="button" name="boutonSupprimer">D�sactiver</a></td>
		</c:if>
		<c:if test="${user.isActivate()==false}">
			<td><p style="postion: relative; padding-top:5px;"><span  class="badge badge-pill badge-danger">D�sactiv�</span></p></td>
			<td><a class="btn btn-secondary" href="<%=request.getContextPath()%>/ServletSupprimerProfil?userId=${user.getNoUtilisateur()}" role="button" name="boutonSupprimer">Activer</a></td>
		</c:if>
	<tr/>
	</c:forEach>
	</table>
	
	<!-- Footer -->
    <footer class="row bg-dark footer-demodule fixed-bottom py-1">
    	
        <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="../../../vendor/jquery/jquery.min.js"></script>
    <script src="../../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>