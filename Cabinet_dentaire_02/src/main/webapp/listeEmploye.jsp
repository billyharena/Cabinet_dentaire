<%--
  Created by IntelliJ IDEA.
  User: billy
  Date: 24/01/2023
  Time: 09:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Employe" %>
<html>
<head>
    <title>Liste Employé</title>
</head>
<body>
    <h1>Liste des employés </h1>
    <a href="insertion_Employe.jsp">Insérer un employé</a>
    <%
        Employe[] emp = new Employe().selectEmploye();
        if (emp.length != 0){
    %>
    <table>
        <tr>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Date de naissance</th>
            <th></th>
        </tr>
        <%
            int taille = emp.length;
            for (int i = 0; i < taille; i++){
        %>
        <tr>
            <td><%=emp[i].getNom()%></td>
            <td><%=emp[i].getPrenom()%></td>
            <td><%=emp[i].getDate_naissance()%></td>
            <td><a href="">Voir fiche</a></td>
        </tr>
        <% } %>
    </table>
    <% } else {%>
    <h3>Aucun employé à afficher !</h3>
    <% } %>
</body>
</html>
