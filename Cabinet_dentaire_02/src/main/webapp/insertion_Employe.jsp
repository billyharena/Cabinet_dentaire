<%-- 
    Document   : InsertionEmploye
    Created on : Jan 12, 2023, 3:48:49 PM
    Author     : judi
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Niveau_etude"%>
<%@page import="database.DatabaseConnexion"%>
<%@page import="java.sql.Connection"%>
<%@ page import="function.Function" %>
<%Connection conn = Function.getConnection();%>
<% Niveau_etude[] ne = new Niveau_etude().selectDiplome(conn);

    conn.close();
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Page d'insertion employe</title>
    <!---Custom CSS File--->
    <link rel="stylesheet" href="asset/css/InsersionEmploye.css" />
  </head>
  <body>
    <section class="container">
      <header>Insérez un employé</header>
      <form action="Formulaire_Employe_Controller" method="post" class="form">
        <div class="input-box">
          <label>Nom</label>
          <input type="text" name="nom" placeholder="Veuillez entrer votre nom" required />
        </div>

        <div class="input-box">
          <label>Prénom</label>
          <input type="text" name="prenom" placeholder="Veuillez entrer votre prénom" required />
        </div>

        <div class="column">
          <div class="input-box">
            <label>Date de naissance</label>
            <input type="date" placeholder="Enter birth date"name="date_naissance" required />
          </div>
        </div>
        <div class="gender-box">
          <h3>Genre</h3>
          <div class="gender-option">
            <div class="gender">
                <input type="radio" id="check-male" name="genre" checked value="1"/>
              <label for="check-male">homme</label>
            </div>
            <div class="gender">
                <input type="radio" id="check-female" name="genre" value="2" />
              <label for="check-female">femme</label>
            </div>
          </div>
        </div>
        <div class="input-box address">
          <label>Niveau d'étude</label>
          <div class="column">
            <div class="select-box">
                <select name="niveau_etude">
                <% int taille = ne.length;
                for (int j = 0; j < taille; j++){
                %>
                <option value="<%= ne[j].getId()%>"><%= ne[j].getNiveau_etude()%></option>
                <%}%>
            </select>
            </div>
          </div>          
        </div>
        <button>SUBMIT</button>
      </form>
    </section>
  </body>
</html>

