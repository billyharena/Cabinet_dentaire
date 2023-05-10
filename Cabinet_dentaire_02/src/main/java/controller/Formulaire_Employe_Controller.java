package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Employe;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "Formulaire_Employe_Controller", value = "/Formulaire_Employe_Controller")
public class Formulaire_Employe_Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        Date date_naissance = Date.valueOf(request.getParameter("date_naissance"));
        Integer idgenre = Integer.parseInt(request.getParameter("genre"));
        Integer idniveau_etude = Integer.parseInt(request.getParameter("niveau_etude"));
        Employe emp = new Employe();
        try {
            Integer idemp = emp.getIdLastEmploye();
            emp.setId(idemp+1);
            emp.setNom(nom);
            emp.setPrenom(prenom);
            emp.setDate_naissance(date_naissance);
            emp.setIdgenre(idgenre);
            emp.setIdniveau_etude(idniveau_etude);
            request.setAttribute("emp", emp);
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/choixSpecialite.jsp");
            rd.forward(request, response);
        }catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
