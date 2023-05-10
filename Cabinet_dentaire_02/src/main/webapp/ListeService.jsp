<%-- 
    Document   : ListeService
    Created on : Jan 17, 2023, 2:55:29 PM
    Author     : judi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Service"%>
<%
    Service[] services = new Service().selectService();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des services</title>
    </head>
    <body>
        <h1>Liste des services</h1>
        <table>
            <tr>
                <th>Service</th>
                <th></th>
            </tr>
            <%
                int taille = services.length;
                for (int i = 0; i < taille; i++){
            %>
            <tr>
                <td><%=services[i].getService()%></td>
                <td><a href="detailService.jsp?id=<%=services[i].getId()%>">detail</a></td>
            </tr><%}%>
        </table>
        <button onclick="window.history.back()">Retour</button>
        <a href="index.html">Accueil</a>
    </body>
</html>
