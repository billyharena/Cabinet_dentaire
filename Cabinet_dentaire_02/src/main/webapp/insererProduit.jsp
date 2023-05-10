<%--
  Created by IntelliJ IDEA.
  User: billy
  Date: 26/01/2023
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insertion produit</title>
</head>
<body>
<h1>Insertion produit</h1>
<form action="InsererProduit" method="POST">
    <div>
        <label for="nomProduit">Nom du produit</label>
        <input type="text" id="id" name="nomProduit">
    </div>
    <div>
        <label for="prixProduit">Prix du produit</label>
        <input type="number" name="prixProduit">
    </div>
    <input type="submit" value="Enregistrer">
    <a href="index.html">Accueil</a>
</form>
</body>
</html>
