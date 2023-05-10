<%-- 
    Document   : detailService
    Created on : Jan 17, 2023, 3:39:41 PM
    Author     : judi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.V_specialite_service" %>
<%@page import="models.V_produit_service" %>
<%@ page import="database.DatabaseConnexion" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="models.Service" %>
<%@ page import="function.Function" %>
<%
    int idservice = Integer.parseInt(request.getParameter("id"));
    Connection conn = Function.getConnection();
    V_specialite_service vuesse = new V_specialite_service();
    vuesse.setIdservice(idservice);
    V_specialite_service[] listSpecialite = new V_specialite_service().selectionAvance(vuesse, conn);
    V_produit_service vpse = new V_produit_service();
    vpse.setIdservice(idservice);
    V_produit_service[] listeProduit = new V_produit_service().selectionAvance(vpse, conn);
    Double marge = listSpecialite[0].getMargebenef();
    Double revientmateriel = V_produit_service.revientmaterielparservice(idservice, conn);
    Double revientsalarial = V_specialite_service.revientsalarialparservice(idservice, conn);
    Double prixMarge = ((revientsalarial+revientmateriel)*marge)/100;
    Double prixService = (revientsalarial+revientmateriel)+prixMarge;
    conn.close();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details service</title>
    </head>
    <body>
        <h1>Fiche de service</h1>
        <table>
            <tr>
                <td>Duree de service</td>
                <td><%=listSpecialite[0].getDuree()%></td>
            </tr>
            <tr>
                <td>Revient salarial</td>
                <td><%=revientsalarial%></td>
            </tr>
            <tr>
                <td>Revient materiel</td>
                <td><%=revientmateriel%></td>
            </tr>
            <tr>
                <td>Total revient</td>
                <td><%=revientsalarial+revientmateriel%></td>
            </tr>
            <tr>
                <td>Marge bénéfice</td>
                <td><%=marge%></td>
            </tr>
            <tr>
                <td>Estimation prix service</td>
                <td><%=prixService%></td>
            </tr>
        </table>
    <div>
        <h1>Spécialité</h1>
        <table>
            <tr>
                <th>Specialite</th>
                <th>Salaire/h</th>
                <th>Duree de travail</th>
            </tr>
            <%
                int taille = listSpecialite.length;
                for (int i = 0; i < taille; i++){
            %>
            <tr>
                <td><%=listSpecialite[i].getSpecialite()%></td>
                <td><%=listSpecialite[i].getSalaireheure()%></td>
                <td><%=listSpecialite[i].getHeuretravail()%></td>
            </tr><%}%>
        </table>
    </div>
        <div>
            <h1>Produit</h1>
            <table>
                <tr>
                    <th>Produit</th>
                    <th>Quantite</th>
                    <th>Prix unitaire</th>
                </tr>
                <%
                    int tailleP = listeProduit.length;
                    for (int j = 0; j < tailleP; j++){
                %>
                <tr>
                    <td><%=listeProduit[j].getNomproduit()%></td>
                    <td><%=listeProduit[j].getQuantiteproduit()%></td>
                    <td><%=listeProduit[j].getPrixunitaire()%></td>
                </tr><%}%>
            </table>
        </div>
        <button onclick="window.history.back()">Retour</button>
        <a href="index.html">Accueil</a>
    </body>
</html>

