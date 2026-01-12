<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
<h2>Connexion</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

    <form action="<c:url value='/login'/>" method="post">
        <label>Identifiant :</label><br/>
        <input type="text" name="identifiant"/><br/><br/>

        <label>Mot de passe :</label><br/>
        <input type="password" name="motDePasse"/><br/><br/>

        <input type="submit" value="Se connecter"/>
    </form>
</body>
</html>
