<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>
	<%@ include file="menu.jsp"%>

	<c:if test="${ !empty erreur }">
		<p style="color: red;">
			<c:out value="${ erreur }" />
		</p>
	</c:if>

	<c:if test="${ !empty nom2 }">
		<p>
			<c:out value="Bonjour, vous vous appelez ${ nom2 }" />
		</p>
	</c:if>

	<c:if test="${ !empty sessionScope.prenom && !empty sessionScope.nom }">
		<p>Vous êtes ${ sessionScope.prenom } ${ sessionScope.nom } !</p>
	</c:if>

	<c:if test="${ !empty form.resultat }">
		<p>
			<c:out value=" ${ form.resultat }" />
		</p>
	</c:if>

	<p>
		<c:out value="${ prenom }" />
	</p>

	<form method="post" action="bonjour">
		<p>
			<label for="nom">Nom : </label> <input type="text" name="nom"
				id="nom" />
		</p>
		<p>
			<label for="prenom">Prénom : </label> <input type="text"
				name="prenom" id="prenom" />
		</p>

		<input type="submit" />
	</form>


	<ul>
		<c:forEach var="utilisateur" items="${ utilisateurs }">
			<li><c:out value="${ utilisateur.prenom }" /> <c:out
					value="${ utilisateur.nom }" /></li>
		</c:forEach>
	</ul>



	<!--  	<form method="post" action="bonjour">
		<p>
			<label for="login">login : </label>
			<input type="text" name="login"id="login" />
		</p>		
		<p>
			<label for="pass">Mot de passe : </label>
			<input type="password" name="pass" id="pass" />			
		</p>
		    <input type="submit" />
	</form>
	
-->

	<!-- 
 	<p>
	<c:if test="${ 50 < 10 }" var="variable">
     	C'est vrai........ !
 	</c:if>
	</p>

	<p>
		<c:out value="Bonjour JSTL !" />
	</p>
	<p>
		<c:out value="${ variable }">Valeur par défaut</c:out>
	</p>

	<p>
		<c:out value="${ variable2 }">Valeur par défaut</c:out>
	</p>


	<c:set var="pseudo" value="Mateo21" scope="page" />

	<p>
		<c:out value="${ pseudo }">Valeur pseudo par défaut </c:out>
	</p>

	<c:remove var="pseudo" scope="page" />

	<p>
		<c:out value="${ pseudo }">Valeur pseudo par défaut </c:out>
	</p>
	
		<c:if test="${ !empty fichier }">
		<p>
			<c:out
				value="Le fichier ${ fichier } (${ description }) a été uploadé !" />
		</p>
	</c:if>
	<form method="post" action="bonjour" enctype="multipart/form-data">
		<p>
			<label for="description">Description du fichier : </label> <input
				type="text" name="description" id="description" />
		</p>
		<p>
			<label for="fichier">Fichier à envoyer : </label> <input type="file"
				name="fichier" id="fichier" />
		</p>

		<input type="submit" />
	</form>


 -->






</body>
</html>