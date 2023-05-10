<%@ page import="java.sql.Connection" %>
<%@ page import="function.Function" %>
<%@ page import="models.V_produit" %><%--
  Created by IntelliJ IDEA.
  User: billy
  Date: 26/01/2023
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Integer idproduit = Integer.parseInt(request.getParameter("id"));
  V_produit vp = new V_produit();
  vp.setId(idproduit);
  V_produit[] liste = new V_produit().selectionAvance(vp);
  Double benefice = liste[0].benefice();
  Double prixestimee = liste[0].prix_conseiller();
%>
<html>
<head>
    <title>Info produit</title>
</head>
<body>
<h1><%=liste[0].getNom()%></h1>
<p>prix d'achat:  <%= liste[0].getPrixUnitaire()%> </p>
<p>prix de vente: <%= prixestimee%> </p>
<p>Bénéfice: <%= benefice %></p>
<button onclick="window.history.back()">Retour</button>
<a href="index.html">Accueil</a>
</body>
</html>
