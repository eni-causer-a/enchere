<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<div>
		<h5 class="my-5 text-center"><fmt:message key="mon_pf" bundle="${langue}"/></h5>
		<form method="post" action="<%=request.getContextPath()%>/modifProfil">
		
		<div class="form-row">
    		<div class="form-group col-md-6">
      			<label for="inputPseudo">Pseudo</label>
      			<input type="text" class="form-control" value="${user.pseudo}" name="pseudo" id="inputPseudo" placeholder="Pseudo">
    		</div>
    		<div class="form-group col-md-6">
      			<label for="inputNom"><fmt:message key="nom" bundle="${langue}"/></label>
      			<input type="text" class="form-control" id="inputNom" name="nom" value="${user.nom}" placeholder="Nom">
    		</div>
    	</div>
    	<c:if test="${pseudoError!=null}">
	    	<div class="form-row">
	    		<div class="form-group col-md-6">
	      			<label style="color: red;" class="label-danger"><fmt:message key="errNewUser.exist" bundle="${errMessage}"/></label>
	    		</div>
	    	</div>
	    </c:if>
    	<div class="form-row">
    		<div class="form-group  col-md-6">
    			<label for="inputPrenom"><fmt:message key="prenom" bundle="${langue}"/></label>
      			<input type="text" class="form-control" id="inputPrenom" name="prenom" value="${user.prenom}" placeholder="Prenom">
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputEmail"><fmt:message key="email" bundle="${langue}"/></label>
      			<input type="email" class="form-control" id="inputEmail" name="email" value="${user.email}" placeholder="Email">
    		</div>
    	</div>
    	<div class="form-row">
    		<div class="form-group  col-md-6">
    			<label for="inputTel"><fmt:message key="tel" bundle="${langue}"/></label>
      			<input type="text" class="form-control" id="inputTel" name="telephone" value="${user.telephone}" placeholder="T�l�phone">
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputRue"><fmt:message key="rue" bundle="${langue}"/></label>
      			<input type="text" class="form-control" id="inputRue" name="rue" value="${user.rue}" placeholder="Rue">
    		</div>
    	</div>
    	<div class="form-row">
    		<div class="form-group  col-md-6">
    			<label for="inputCp"><fmt:message key="cp" bundle="${langue}"/></label>
      			<input type="text" class="form-control" id="inputCp" name="codePostal" value="${user.codePostale}" placeholder="Code Postal">
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputVille"><fmt:message key="ville" bundle="${langue}"/></label>
      			<input type="text" class="form-control" id="inputVille" name="ville" value="${user.ville}" placeholder="Ville">
    		</div>
    	</div>
    	<div class="form-group">
    			<label for="inputMdp"><fmt:message key="mdp_actuel" bundle="${langue}"/></label>
      			<input type="password" class="form-control" id="inputMdp" name="motDePasse"  placeholder="Mot de passe">
    	</div>
    	<c:if test="${mdpError!=null}">
	    	<div class="form-row">
	    		<div class="form-group col-md-6">
	      			<label style="color: red;" class="label-danger"><fmt:message key="errLog.Pass" bundle="${errMessage}"/></label>
	    		</div>
	    	</div>
	    </c:if>
    	<div class="form-row">
    		<div class="form-group  col-md-6">
    			<label for="inputMdpNew"><fmt:message key="new_mdp" bundle="${langue}"/></label>
      			<input type="password" class="form-control" id="inputMdpNew" name="newMotDePasse"  placeholder="Nouveau Mot de passe">
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputMdpNewC"><fmt:message key="mdp_conf" bundle="${langue}"/></label>
      			<input type="password" class="form-control" id="inputMdpNewC" name="confirmationMotDePasse"  placeholder="Confirmation">
    		</div>
    	</div>
    	<c:if test="${mdpConfError!=null}">
	    	<div class="form-row">
	    		<div class="form-group col-md-6">
	      			<label style="color: red;" class="label-danger"><fmt:message key="errConf.badConf" bundle="${errMessage}"/></label>
	    		</div>
	    	</div>
	    </c:if>
		<div class="form-row">
    		<div class="form-group  col-md-6">
    			<button name="boutonCreer" class="btn btn-secondary"><fmt:message key="save" bundle="${langue}"/></button>		
    		</div>
    		<div class="form-group  col-md-6">
    			<button name="boutonSupprimer" class="btn btn-secondary"><fmt:message key="del_ac" bundle="${langue}"/></button>
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