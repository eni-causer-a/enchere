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
      			<label for="inputPseudo"><fmt:message key="pseudo" bundle="${langue}"/></label>
      			<c:if test="${pseudo==null}">
      				<input type="text" class="form-control" value="${utilisateur.pseudo}" name="pseudo" id="inputPseudo" placeholder="<fmt:message key="pseudo" bundle="${langue}"/>">
    			</c:if>
    			<c:if test="${pseudo!=null}">
      				<input type="text" class="form-control" value="${pseudo}" name="pseudo" id="inputPseudo" placeholder="<fmt:message key="pseudo" bundle="${langue}"/>">
    			</c:if>
    		</div>
    		<div class="form-group col-md-6">
      			<label for="inputNom"><fmt:message key="nom" bundle="${langue}"/></label>
      			<c:if test="${nom==null}">
      				<input type="text" class="form-control" id="inputNom" name="nom" value="${utilisateur.nom}" placeholder="<fmt:message key="nom" bundle="${langue}"/>">
    			</c:if>
    			<c:if test="${nom!=null}">
      				<input type="text" class="form-control" id="inputNom" name="nom" value="${nom}" placeholder="<fmt:message key="nom" bundle="${langue}"/>">
    			</c:if>
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
    			<c:if test="${prenom==null}">
      				<input type="text" class="form-control" id="inputPrenom" name="prenom" value="${utilisateur.prenom}" placeholder="<fmt:message key="prenom" bundle="${langue}"/>">
    			</c:if>
    			<c:if test="${prenom!=null}">
      				<input type="text" class="form-control" id="inputPrenom" name="prenom" value="${prenom}" placeholder="<fmt:message key="prenom" bundle="${langue}"/>">
    			</c:if>
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputEmail"><fmt:message key="email" bundle="${langue}"/></label>
    			<c:if test="${email==null}">
      				<input type="email" class="form-control" id="inputEmail" name="email" value="${utilisateur.email}" placeholder="<fmt:message key="email" bundle="${langue}"/>">
    			</c:if>
    			<c:if test="${email!=null}">
      				<input type="email" class="form-control" id="inputEmail" name="email" value="${email}" placeholder="<fmt:message key="email" bundle="${langue}"/>">
    			</c:if>
    			
    		</div>
    	</div>
    	<div class="form-row">
    		<div class="form-group  col-md-6">
    			<label for="inputTel"><fmt:message key="tel" bundle="${langue}"/></label>
    			<c:if test="${telephone==null}">
      				<input type="text" class="form-control" id="inputTel" name="telephone" value="${utilisateur.telephone}" placeholder="<fmt:message key="tel" bundle="${langue}"/>">
    			</c:if>
    			<c:if test="${telephone!=null}">
      				<input type="text" class="form-control" id="inputTel" name="telephone" value="${telephone}" placeholder="<fmt:message key="tel" bundle="${langue}"/>">
    			</c:if>
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputRue"><fmt:message key="rue" bundle="${langue}"/></label>
    			<c:if test="${rue==null}">
      				<input type="text" class="form-control" id="inputRue" name="rue" value="${utilisateur.rue}" placeholder="<fmt:message key="rue" bundle="${langue}"/>">
    			</c:if>
    			<c:if test="${rue!=null}">
      				<input type="text" class="form-control" id="inputRue" name="rue" value="${rue}" placeholder="<fmt:message key="rue" bundle="${langue}"/>">
    			</c:if>
    		</div>
    	</div>
    	<div class="form-row">
    		<div class="form-group  col-md-6">
    			<label for="inputCp"><fmt:message key="cp" bundle="${langue}"/></label>
    			<c:if test="${codePostal==null}">
      				<input type="text" class="form-control" id="inputCp" name="codePostal" value="${utilisateur.codePostale}" placeholder="<fmt:message key="cp" bundle="${langue}"/>">
    			</c:if>
    			<c:if test="${codePostal!=null}">
      				<input type="text" class="form-control" id="inputCp" name="codePostal" value="${codePostal}" placeholder="<fmt:message key="cp" bundle="${langue}"/>">
    			</c:if>
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputVille"><fmt:message key="ville" bundle="${langue}"/></label>
    			<c:if test="${ville==null}">
      				<input type="text" class="form-control" id="inputVille" name="ville" value="${utilisateur.ville}" placeholder="<fmt:message key="ville" bundle="${langue}"/>">
    			</c:if>
    			<c:if test="${ville!=null}">
      				<input type="text" class="form-control" id="inputVille" name="ville" value="${ville}" placeholder="<fmt:message key="ville" bundle="${langue}"/>">
    			</c:if>
    		</div>
    	</div>
    	<div class="form-group">
    			<label for="inputMdp"><fmt:message key="mdp_actuel" bundle="${langue}"/></label>
      				<input type="password" class="form-control" id="inputMdp" name="motDePasse"  placeholder="<fmt:message key="mdp_actuel" bundle="${langue}"/>" required>
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
      				<input type="password" class="form-control" id="inputMdpNew" name="newMotDePasse"  placeholder="<fmt:message key="new_mdp" bundle="${langue}"/>">
    		</div>
    		<div class="form-group  col-md-6">
    			<label for="inputMdpNewC"><fmt:message key="mdp_conf" bundle="${langue}"/></label>
    		
      			<input type="password" class="form-control" id="inputMdpNewC" name="confirmationMotDePasse"  placeholder="<fmt:message key="mdp_conf" bundle="${langue}"/>">
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




    <!-- Bootstrap core JavaScript -->
    <script src="../../../vendor/jquery/jquery.min.js"></script>
    <script src="../../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>