<%-- 
    Document   : choixSpecialite
    Created on : 17 janv. 2023, 05:50:04
    Author     : billy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Specialite"%>
<%@page import="models.Employe"%>
<%
    Employe emp = (Employe) request.getAttribute("emp");
    Specialite[] specialites = new Specialite().selectSpecialite();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page choix specialite</title>
    </head>
    <body>
    <form action="Insertion_employe_Controller" method="post" >
        <%
            int tailleS = specialites.length;
            for(int i = 0; i < tailleS; i++){
        %>
        <input type="checkbox" name="specialite[]"value="<%=specialites[i].getId()%>"><%=specialites[i].getSpecialite()%>
        <%}%>
        <input type="text" name="idemp" value="<%=emp.getId()%>" placeholder="<%=emp.getId()%>">
        <input type="text" name="nom" value="<%=emp.getNom()%>" placeholder="<%=emp.getNom()%>">
        <input type="text" name="prenom" value="<%=emp.getPrenom()%>" placeholder="<%=emp.getPrenom()%>">
        <input type="text" name="date_naissance" value="<%=emp.getDate_naissance()%>" placeholder="<%=emp.getDate_naissance()%>">
        <input type="text" name="genre" value="<%=emp.getIdgenre()%>" placeholder="<%=emp.getIdgenre()%>">
        <input type="text" name="niveau_etude" value="<%=emp.getIdniveau_etude()%>" placeholder="<%=emp.getIdniveau_etude()%>">
        <input type="submit" value="INSERER">
    </form>
    <button onclick="window.history.back()">Retour</button>
    <a href="index.html">Accueil</a>
    </body>
</html>
