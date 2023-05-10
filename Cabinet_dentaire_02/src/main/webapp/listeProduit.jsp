<%@ page import="models.Produit" %><%--
  Created by IntelliJ IDEA.
  User: billy
  Date: 26/01/2023
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Produit produit = new Produit();
    Produit[] produits = new Produit().selection(produit);
%>
<html>
<head>
    <title>Liste Produit</title>
</head>
<body>
<h1>Liste des produits</h1>
<a href="insererProduit.jsp">Inserer un produit</a>
<table>
    <tr>
        <th>Produit</th>
        <th>Prix unitaire</th>
        <th>Prix vente</th>
        <th>Bénéfice</th>
        <th></th>
    </tr>
    <%
        int taille = produits.length;
        for (int i = 0; i < taille; i++){
    %>
    <tr>
        <td><%=produits[i].getNom()%></td>
        <td><%=produits[i].getPrixUnitaire()%></td>
        <td><%=produits[i].getPrixvente()%></td>
        <td><%=produits[i].benefice()%></td>
        <td><a href="infoProduit.jsp?id=<%=produits[i].getId()%>">detail</a></td>
    </tr><%}%>
</table>
<button onclick="window.history.back()">Retour</button>
<a href="index.html">Accueil</a>
</body>
</html>
