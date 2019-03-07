<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:useBean id="LOCALE" scope="session" class="java.lang.String"/>
<fmt:setLocale value="${LOCALE}"/>	
<fmt:setBundle basename="fr.eni.enchere.lang.langue" var ="langue"/>
<header class="py-3 bg-dark header-demodule fixed-top"> 
	   	<div class="form-row">
	   		<div style="position: relative; margin-left: 100px;" class="form-group col-md-3">	
	   				<!-- <h3>ENI-Encheres</h3> -->
	   				<h3><a class="hn_clicable" href="<%=request.getContextPath()%>/Accueil">ENI-Encheres</a></h3>
	   		</div>
	   		<c:if test="${utilisateur==null}">
		   		<div class="col-md-6">
		        	<a href="<%=request.getContextPath()%>/Connexion"><fmt:message key="menu_co" bundle="${langue}"/></a>
		       	</div>
	   		</c:if>
	   		
	   		<c:if test="${utilisateur!=null}">
	   			<c:if test="${utilisateur.isAdministareur()}">
	   				<div class="col-md-1"><a href="<%=request.getContextPath()%>/ServletListeProfil">Liste profils</a></div>
	   			</c:if>
	        	<div class="col-md-2"><h5 style="color: white;">${sessionScope.Utilisateur.getPseudo()} ${sessionScope.Utilisateur.getCredit()} <fmt:message key="crd" bundle="${langue}"/></h5></div>
	        	<div class="col-md-1"><a href="<%=request.getContextPath()%>/NouvelleVente"><fmt:message key="menu_vendre" bundle="${langue}"/></a></div>
	        	<div class="col-md-1"><a href="<%=request.getContextPath()%>/profil?user=${sessionScope.Utilisateur.getNoUtilisateur()}"><fmt:message key="menu_profil" bundle="${langue}"/></a></div>
	        	<div class="col-md-1"><a href="<%=request.getContextPath()%>/ezMoney"><fmt:message key="menu_credit" bundle="${langue}"/></a></div>
	        	<div class="col-md-1"><a href="<%=request.getContextPath()%>/Deconnexion"><fmt:message key="menu_deco" bundle="${langue}"/></a></div>
		    	
		    	
		    	
	   		</c:if>
	   		<form method="post" action="<%=request.getContextPath()%>/SwitchLang" ><div class="col-md-1"><button style="background: none; border: none"><fmt:message key="lang" bundle="${langue}"/></button></div></form>
	   		
		</div>  
	</header>
</html>
<!-- 	<header class="py-3 bg-dark header-demodule fixed-top">  
	   	<div class="row">
	   		<div class="col-5">	
	   			<div class="container text-center text-white">
	   				<!-- <h3>ENI-Encheres</h3> 
	   				<h3><a class="hn_clicable" href="<%=request.getContextPath()%>/Accueil">ENI-Encheres</a></h3>
	   			</div>
	   		</div>
	   		<c:if test="${utilisateur==null}">
	   			<div class="col-1"></div>
		   		<div class="col-6">
		        	<a href="<%=request.getContextPath()%>/Connexion"><fmt:message key="menu_co" bundle="${langue}"/></a>
		       	</div>
	   		</c:if>
	   		<c:if test="${utilisateur!=null}">
				 
	        	<div class="col-2"><h5 style="color: white;">${sessionScope.Utilisateur.getPseudo()} ${sessionScope.Utilisateur.getCredit()} Cr�dits</h5></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/NouvelleVente"><fmt:message key="menu_vendre" bundle="${langue}"/></a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/profil?user=${sessionScope.Utilisateur.getNoUtilisateur()}"><fmt:message key="menu_profil" bundle="${langue}"/></a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/ezMoney"><fmt:message key="menu_credit" bundle="${langue}"/></a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/Deconnexion"><fmt:message key="menu_deco" bundle="${langue}"/></a></div>
		    
	   		</c:if>
	   		
		</div>  
	</header> -->