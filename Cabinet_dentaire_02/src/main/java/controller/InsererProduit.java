package controller;

import function.Function;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Produit;
import models.V_produit;

import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "InsererProduit", value = "/InsererProduit")
public class InsererProduit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomproduit = request.getParameter("nomProduit");
        Double prixproduit = Double.parseDouble(request.getParameter("prixProduit"));
        try {
            Connection connection = Function.getConnection();
            Produit p = new Produit();
            p.setNom(nomproduit);
            p.setPrixUnitaire(prixproduit);
            p.insererProduit(connection);
            V_produit pr = new V_produit();
            V_produit[] list = new V_produit().selection(pr, connection);
//            request.setAttribute("listproduit", list);
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/listeProduit.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
