package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilDB {
    public static Connection getConn() throws SQLException {
       /*  String url = "jdbc:mysql://localhost:3307/exam";
        String user = "root";
        String password = " "; */

        /* String url = "jdbc:mysql://localhost:3307/exam";
        String user = "ETU003273";
        String password = "x2PABuUG";
 */
        String url = "jdbc:mysql://localhost:3307/db_s2_ETU003273";
        String user = "ETU003273";
        String password = "x2PABuUG";


        
        //  CREATE USER 'ETU003273'@'localhost' IDENTIFIED BY 'x2PABuUG';
        // GRANT ALL PRIVILEGES ON exam.* TO 'ETU003273'@'localhost';
        // FLUSH PRIVILEGES;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connexion à la base de données...");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL introuvable");
            throw new SQLException("Driver MySQL introuvable", e);
        }              
    }
}
