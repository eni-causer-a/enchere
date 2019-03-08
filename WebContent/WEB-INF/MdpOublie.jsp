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
	<header class="py-3 bg-dark header-demodule fixed-top">
	    <div class="row">
	   		<div class="col-5">	
	   			<div class="container text-center text-white">
	   				<h3><a class="hn_clicable" href="<%=request.getContextPath()%>/Accueil">ENI-Encheres</a></h3>
	   			</div>
	   		</div>
	   		
		</div>  
	</header>
	<h3 class="my-5 text-center"><fmt:message key="mdpo_titre" bundle="${langue}"/></h3>
	<div style="postion: relative; margin-left:425px;" class="container">
		<form method="post" action="<%=request.getContextPath()%>/mdpoublie">
			<c:if test="${loginError!=null}">
					<div class="form-row">
						<div class="form-group">
				  			<label class="label-danger">${loginError}</label>
				  		</div>
				  	</div>
			</c:if>
			<div class="form-row">
				<div class="form-group">
			  		<label for="email"><fmt:message key="email" bundle="${langue}"/></label>
			  		<input class="form-control" id="email" type="email" name="email" required>
			  	</div>				
			</div>
			<div class="form-row">		
				<div class="form-group"> 
					<button class="btn btn-secondary"><fmt:message key="valider" bundle="${langue}"/></button>
				</div>
			</div>
		</form>
	</div>

    <!-- Bootstrap core JavaScript -->
    <script src="../../../vendor/jquery/jquery.min.js"></script>
    <script src="../../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>