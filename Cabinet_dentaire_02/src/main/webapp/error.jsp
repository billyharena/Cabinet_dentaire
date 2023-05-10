<%-- 
    Document   : error
    Created on : 14 janv. 2023, 17:03:54
    Author     : billy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= request.getAttribute("error") %></h1>
        <button onclick="window.history.back()">Retour</button>
    </body>
</html>
