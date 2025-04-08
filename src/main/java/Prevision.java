package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Prevision extends BaseObject {
    private String libeller;
    private Integer montant;

    @Override
    protected String getTableName() {
        return "prevision";
    }

    @Override
    protected String getIdColumnName() {
        return "id_prevision";
    }

    @Override
    protected Map<String, Object> getFieldValues() {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("id_prevision", this.getId());
        fields.put("libeller", this.getLibeller());
        fields.put("montant", this.getMontant());
        return fields;
    }

    @Override
    protected BaseObject createInstanceFromResultSet(ResultSet rs) throws SQLException {
        Prevision prevision = new Prevision();
        prevision.setId(rs.getInt("id_prevision"));
        prevision.setLibeller(rs.getString("libeller"));
        prevision.setMontant(rs.getInt("montant"));
        return prevision;
    }

    public static Prevision getById(Integer id) throws Exception {
        Prevision prevision = new Prevision();
        return (Prevision) prevision.findById(id);
    }

    public static List<Prevision> getAll() throws Exception {
        Prevision prevision = new Prevision();
        List<BaseObject> baseObjects = prevision.findAll();
        List<Prevision> previsions = new ArrayList<>();
        
        for (BaseObject obj : baseObjects) {
            previsions.add((Prevision) obj);
        }
        
        return previsions;
    }

    // Getters et Setters
    public String getLibeller() {
        return libeller;
    }

    public void setLibeller(String libeller) {
        this.libeller = libeller;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }
}