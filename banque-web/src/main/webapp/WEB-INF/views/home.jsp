<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>Hello world !</h1>
    <p>The time on the server is ${serverTime}</p>

    <h2>Bienvenue ${sessionScope.client.nom}</h2>

    <p>Connexion réussie 🎉</p>

    <a href="logout">Se déconnecter</a>
</body>
</html>
