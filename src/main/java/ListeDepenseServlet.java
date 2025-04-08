package listDepense;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Depense;
import model.Prevision;

public class ListeDepenseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Depense> depenses = Depense.getAll();
            List<Prevision> previsions = Prevision.getAll();
            
            request.setAttribute("depenses", depenses);
            request.setAttribute("previsions", previsions);
            
            // Log pour débogage
            System.out.println("Nombre de dépenses: " + depenses.size());
            System.out.println("Nombre de prévisions: " + previsions.size());
            
            request.getRequestDispatcher("/listeDepenses.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de la récupération des dépenses", e);
        }
    }
}