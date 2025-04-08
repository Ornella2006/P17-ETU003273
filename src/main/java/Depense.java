package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Prevision;

public class Depense extends BaseObject {
    private Integer idPrevision;
    private Integer montant;
    private String dateCrea;

    @Override
    protected String getTableName() {
        return "depense";
    }

    @Override
    protected String getIdColumnName() {
        return "id_depense";
    }

    @Override
    protected Map<String, Object> getFieldValues() {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("id_depense", this.getId());
        fields.put("id_prevision", this.getIdPrevision());
        fields.put("montant", this.getMontant());
        fields.put("date_crea", this.getDateCrea());
        return fields;
    }

    @Override
    protected BaseObject createInstanceFromResultSet(ResultSet rs) throws SQLException {
        Depense depense = new Depense();
        depense.setId(rs.getInt("id_depense"));
        depense.setIdPrevision(rs.getInt("id_prevision"));
        depense.setMontant(rs.getInt("montant"));
        depense.setDateCrea(rs.getString("date_crea"));
        return depense;
    }

    public static Depense getById(Integer id) throws Exception {
        Depense depense = new Depense();
        return (Depense) depense.findById(id);
    }

    public static List<Depense> getAll() throws Exception {
        Depense depense = new Depense();
        List<BaseObject> baseObjects = depense.findAll();
        List<Depense> depenses = new ArrayList<>();
        
        for (BaseObject obj : baseObjects) {
            depenses.add((Depense) obj);
        }
        
        return depenses;
    }

    public void save() throws Exception {
        Prevision prevision = Prevision.getById(this.idPrevision);
        List<Depense> depenses = Depense.getAll().stream()
            .filter(d -> d.getIdPrevision().equals(this.idPrevision))
            .collect(Collectors.toList());
        
        int totalDepense = depenses.stream()
            .mapToInt(Depense::getMontant)
            .sum();
        
        int nouveauTotal = totalDepense + this.montant;
        
        if (nouveauTotal > prevision.getMontant()) {
            throw new Exception("Le montant de la dépense dépasse le solde disponible pour cette prévision");
        }
        
        super.save();
}

    // Getters et Setters
    public Integer getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(Integer idPrevision) {
        this.idPrevision = idPrevision;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public String getDateCrea() {
        return dateCrea;
    }

    public void setDateCrea(String dateCrea) {
        this.dateCrea = dateCrea;
    }
}