<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<header class="py-3 bg-dark header-demodule fixed-top"> 
	   	<div class="row">
	   		<div class="col-5">	
	   			<div class="container text-center text-white">
	   				<!-- <h3>ENI-Encheres</h3> -->
	   				<h3><a class="hn_clicable" href="<%=request.getContextPath()%>/Accueil">ENI-Encheres</a></h3>
	   			</div>
	   		</div>
	   		<c:if test="${utilisateur==null}">
	   			<div class="col-1"></div>
		   		<div class="col-6">
		        	<a href="<%=request.getContextPath()%>/Connexion">S'inscrire - Se connecter</a>
		       	</div>
	   		</c:if>
	   		<c:if test="${utilisateur!=null}">
	        	<div class="col-2"><h5 style="color: white;">${sessionScope.Utilisateur.getPseudo()} ${sessionScope.Utilisateur.getCredit()} Crédits</h5></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/NouvelleVente">Vendre un article</a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/profil?user=${sessionScope.Utilisateur.getNoUtilisateur()}">Mon profil</a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/ezMoney">Ajouter des crédits</a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/Deconnexion">Déconnexion</a></div>
		    
	   		</c:if>
	   		
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
				 
	        	<div class="col-2"><h5 style="color: white;">${sessionScope.Utilisateur.getPseudo()} ${sessionScope.Utilisateur.getCredit()} Crédits</h5></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/NouvelleVente"><fmt:message key="menu_vendre" bundle="${langue}"/></a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/profil?user=${sessionScope.Utilisateur.getNoUtilisateur()}"><fmt:message key="menu_profil" bundle="${langue}"/></a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/ezMoney"><fmt:message key="menu_credit" bundle="${langue}"/></a></div>
	        	<div class="col-1"><a href="<%=request.getContextPath()%>/Deconnexion"><fmt:message key="menu_deco" bundle="${langue}"/></a></div>
		    
	   		</c:if>
	   		
		</div>  
	</header> -->