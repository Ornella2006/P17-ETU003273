package dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Depense;
import model.Prevision;

public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Prevision> previsions = Prevision.getAll();
            List<Depense> depenses = Depense.getAll();
            
            List<DashboardItem> items = new ArrayList<>();
            
            for (Prevision prev : previsions) {
                DashboardItem item = new DashboardItem();
                item.setLibeller(prev.getLibeller());
                item.setMontantPrevu(prev.getMontant());
                
                int totalDepense = depenses.stream()
                    .filter(d -> d.getIdPrevision().equals(prev.getId()))
                    .mapToInt(Depense::getMontant)
                    .sum();
                
                item.setMontantDepense(totalDepense);
                item.setReste(item.getMontantPrevu() - totalDepense);
                
                items.add(item);
            }
            
            request.setAttribute("items", items);
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erreur lors du chargement du dashboard", e);
        }
    }
}

class DashboardItem {
    private String libeller;
    private int montantPrevu;
    private int montantDepense;
    private int reste;
    
    // Getters et Setters
    public String getLibeller() { return libeller; }
    public void setLibeller(String libeller) { this.libeller = libeller; }
    public int getMontantPrevu() { return montantPrevu; }
    public void setMontantPrevu(int montantPrevu) { this.montantPrevu = montantPrevu; }
    public int getMontantDepense() { return montantDepense; }
    public void setMontantDepense(int montantDepense) { this.montantDepense = montantDepense; }
    public int getReste() { return reste; }
    public void setReste(int reste) { this.reste = reste; }
}