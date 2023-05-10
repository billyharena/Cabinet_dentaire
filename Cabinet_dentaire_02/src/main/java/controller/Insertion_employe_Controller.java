package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Employe;
import models.Employe_specialite;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "Insertion_employe_Controller", value = "/Insertion_employe_Controller")
public class Insertion_employe_Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idemp = Integer.parseInt(request.getParameter("idemp"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        Date date_naissance = Date.valueOf(request.getParameter("date_naissance"));
        Integer idgenre = Integer.parseInt(request.getParameter("genre"));
        Integer idniveau_etude = Integer.parseInt(request.getParameter("niveau_etude"));
        Employe emp = new Employe();
        String[] listCB = request.getParameterValues("specialite[]");
        int tailleCB = listCB.length;
        Integer[] specialites = new Integer[tailleCB];
        Employe_specialite[] les = new Employe_specialite[tailleCB];
        if (tailleCB != 0){
            try {
                emp.setId(idemp);
                emp.setNom(nom);
                emp.setPrenom(prenom);
                emp.setDate_naissance(date_naissance);
                emp.setIdgenre(idgenre);
                emp.setIdniveau_etude(idniveau_etude);
                emp.insererEmploye();
                for (int i = 0; i < tailleCB; i++){
                    specialites[i] = Integer.parseInt(listCB[i]);
                    les[i] = new Employe_specialite();
                    les[i].setIdemploye(idemp);
                    les[i].setIdspecialite(specialites[i]);
                    les[i].insererEmployeSpecialite();
                }
                RequestDispatcher rd;
                rd = request.getRequestDispatcher("/listeEmploye.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
        else {
            Exception ex = new Exception("Vous devez avoir au moins une spécialité !");
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
