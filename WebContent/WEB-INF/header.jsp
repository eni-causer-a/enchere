<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:useBean id="LOCALE" scope="session" class="java.lang.String"/>
<fmt:setLocale value="${LOCALE}"/>	
<fmt:setBundle basename="fr.eni.enchere.lang.langue" var ="langue"/>
<header class="py-3 bg-dark header-demodule fixed-top"> 
	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
		<a class="navbar-brand mb-0 h1" href="<%=request.getContextPath()%>/Accueil">ENI-Encheres</a>
	  
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	  	</button>
	  	
	  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		    	<c:if test="${utilisateur!=null}">
		    		<c:if test="${utilisateur.isAdministareur()}">
				     <li class="nav-item active">
				        <a class="nav-item nav-link" href="<%=request.getContextPath()%>/ServletListeProfil">Liste profils<span class="sr-only">(current)</span></a>
				      </li>
				     </c:if>
				      <li class="nav-item">
				       <a class="nav-item nav-link" href="<%=request.getContextPath()%>/NouvelleVente"><fmt:message key="menu_vendre" bundle="${langue}"/> <span class="sr-only">(current)</span></a>
				      </li>
				      <li class="nav-item">
				       <a class="nav-item nav-link" href="<%=request.getContextPath()%>/ezMoney"><fmt:message key="menu_credit" bundle="${langue}"/></a>
				      </li>
				</c:if>
		    </ul>
		    <form class="form-inline my-2 my-lg-0" method="post" action="<%=request.getContextPath()%>/SwitchLang">
		    	<c:if test="${utilisateur!=null}">
			    	<span class="navbar-text navbar-right">
				    	${sessionScope.Utilisateur.getPseudo()} ${sessionScope.Utilisateur.getCredit()} <fmt:message key="crd" bundle="${langue}"/>
				    </span>
				    <a class="nav-item nav-link" style="color:rgba(0,0,0,.5);" href="<%=request.getContextPath()%>/profil?user=${sessionScope.Utilisateur.getNoUtilisateur()}"><fmt:message key="menu_profil" bundle="${langue}"/></a>
			    	<a class="nav-item nav-link" style="color:rgba(0,0,0,.5);" href="<%=request.getContextPath()%>/Deconnexion"><fmt:message key="menu_deco" bundle="${langue}"/></a>
		    	</c:if>
		    	<c:if test="${utilisateur==null}">
		    		<a href="<%=request.getContextPath()%>/Connexion"><fmt:message key="menu_co" bundle="${langue}"/></a>
		    	</c:if>
		    	<button style="background: none; border: none"><fmt:message key="lang" bundle="${langue}"/></button>
		    </form>
		</div>
	</nav>
	</header>
</html>
