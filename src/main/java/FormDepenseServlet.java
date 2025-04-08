package formDepense;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Depense;
import model.Prevision;



public class FormDepenseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                Depense depense = Depense.getById(Integer.parseInt(idParam));
                request.setAttribute("depense", depense);
            }
            
            List<Prevision> previsions = Prevision.getAll();
            request.setAttribute("previsions", previsions);
            
            request.getRequestDispatcher("/formDepense.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erreur lors du chargement du formulaire", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Depense depense = new Depense();
            
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                depense.setId(Integer.parseInt(idParam));
            }
            
            depense.setIdPrevision(Integer.parseInt(request.getParameter("id_prevision")));
            depense.setMontant(Integer.parseInt(request.getParameter("montant")));
            depense.setDateCrea(request.getParameter("date_crea"));
            
            if (depense.getId() != null) {
                depense.update();
            } else {
                depense.save();
            }
            
            response.sendRedirect("listeDepenses");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
        }
    }
}