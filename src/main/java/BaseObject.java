package model;

import connexion.UtilDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseObject {
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected abstract String getTableName();
    protected abstract Map<String, Object> getFieldValues();
    protected abstract BaseObject createInstanceFromResultSet(ResultSet rs) throws SQLException;


    public void save() throws Exception {
        Connection conn = null;

        try {
            conn = getConnection();
            save(conn);
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la sauvegarde dans " + getTableName(), e);
        } finally {
            closeConn(conn);
        }
    }

    public void save(Connection conn) throws SQLException, Exception {
        PreparedStatement state = null;

        try {
            Map<String, Object> fieldValues = getFieldValues();
            StringBuilder fieldsBuilder = new StringBuilder();
            StringBuilder valuesBuilder = new StringBuilder();

            for (String field : fieldValues.keySet()) {
                if (!field.equals("id")) {
                    if (fieldsBuilder.length() > 0) {
                        fieldsBuilder.append(", ");
                        valuesBuilder.append(", ");
                    }
                    fieldsBuilder.append(field);
                    valuesBuilder.append("?");
                }
            }
            
            String sql = "insert into " + getTableName() + " (" + fieldsBuilder.toString() + ") VALUES (" + valuesBuilder.toString() + ")";
            state = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            int paramIndex = 1;
            for (Map.Entry<String, Object> entry : fieldValues.entrySet()) {
                if (!entry.getKey().equals("id")) {
                    state.setObject(paramIndex++, entry.getValue());
                }
            }

            state.executeUpdate();
            
            // Récupérer l'ID généré
            try (ResultSet rs = state.getGeneratedKeys()) {
                if (rs.next()) {
                    this.setId(rs.getInt(1));
                }
            }
        } finally {
            closeState(state);
        }
    }


    public void update() throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            update(conn);
            
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la mise à jour dans " + getTableName(), e);
        } finally {
            closeConn(conn);
        }
    }

    public void update(Connection conn) throws SQLException {
        PreparedStatement state = null;
        
        try {
            Map<String, Object> fieldValues = getFieldValues();
            StringBuilder setClauseBuilder = new StringBuilder();
            
            for (String field : fieldValues.keySet()) {
                if (!field.equals("id")) {
                    if (setClauseBuilder.length() > 0) {
                        setClauseBuilder.append(", ");
                    }
                    setClauseBuilder.append(field).append(" = ?");
                }
            }
            
            String sql = "UPDATE " + getTableName() + " SET " + setClauseBuilder.toString() + " WHERE  " + getIdColumnName() + " = ?";
            state = conn.prepareStatement(sql);
            
            int paramIndex = 1;
            for (Map.Entry<String, Object> entry : fieldValues.entrySet()) {
                if (!entry.getKey().equals("id")) {
                    state.setObject(paramIndex++, entry.getValue());
                }
            }
            state.setInt(paramIndex, this.getId());
            
            state.executeUpdate();
        } finally {
            try {
                closeState(state);
            } catch (Exception ex) {
            }
        }
    }

    public void remove() throws Exception {
        Connection conn = null;
        PreparedStatement state = null;
        
        try {
            conn = getConnection();
            String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
            state = conn.prepareStatement(sql);
            state.setInt(1, this.getId());
            state.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la suppression dans " + getTableName(), e);
        } finally {
            closeResources(conn, state, null);
        }
    }

    public BaseObject findById(Integer id) throws Exception {
        Connection conn = null;
        
        try {
            conn = getConnection();
            return findById(id, conn);
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération par ID dans " + getTableName(), e);
        } finally {
            closeConn(conn);
        }
    }

    protected String getIdColumnName() {
        return "id"; 
    }

    public BaseObject findById(Integer id, Connection conn) throws SQLException, Exception {
        PreparedStatement state = null;
        ResultSet res = null;
        
        try {
            String sql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
            state = conn.prepareStatement(sql);
            state.setInt(1, id);
            res = state.executeQuery();
            
            if (res.next()) {
                return createInstanceFromResultSet(res);
            }
            
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResourceS(state, res);
        }
    }

    

    // Méthode findAll() généralisée
    public List<BaseObject> findAll() throws Exception {
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet res = null;
        List<BaseObject> objects = new ArrayList<>();
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM " + getTableName();
            state = conn.prepareStatement(sql);
            res = state.executeQuery();
            
            while (res.next()) {
                objects.add(createInstanceFromResultSet(res));
            }
            
            return objects;
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération de tous les éléments dans " + getTableName(), e);
        } finally {
            closeResources(conn, state, res);
        }
    }

    public List<BaseObject> findAllWithPagination(int count, int offset) throws Exception {
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet res = null;
        List<BaseObject> objects = new ArrayList<>();
        
        try {
            conn = getConnection();
            String sql = "SELECT * FROM " + getTableName() + " ORDER BY id LIMIT ? OFFSET ?";
            state = conn.prepareStatement(sql);
            state.setInt(1, count);
            state.setInt(2, offset);
            res = state.executeQuery();
            
            while (res.next()) {
                objects.add(createInstanceFromResultSet(res));
            }
            
            return objects;
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération paginée dans " + getTableName(), e);
        } finally {
            closeResources(conn, state, res);
        }
    }
    
    protected Connection getConnection() throws SQLException {
        return UtilDB.getConn();
    }
    
    protected void closeResources(Connection conn, PreparedStatement state, ResultSet res) throws Exception {
        try {
            if (res != null) res.close();
            if (state != null) state.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la fermeture des ressources", e);
        }
    }

    protected void closeResourceS(PreparedStatement state, ResultSet res) throws Exception {
        try {
            if (res != null) res.close();
            if (state != null) state.close();
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la fermeture des ressources", e);
        }
    }

    protected void closeConn(Connection conn) throws Exception {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la fermeture de connexion", e);
        }
    }
    
    protected void closeState(PreparedStatement state) throws SQLException {
        if (state != null) state.close();
    }

}