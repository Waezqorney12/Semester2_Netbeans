/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConfigDB;
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import  java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author ASUS
 */
public class Config {
     private static final String DB__NAME = "semester2_project";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/semester2_project";
    public static Connection configDB() throws  SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
        return conn;
    }
        public static boolean isConnected(){
            try {
                configDB();
                System.out.println("Database Terkoneksi");
                return true;
            } catch (Exception e) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE,null,e);
                return false;
            }
        }
    public static void main(String[] args) {
        System.out.println("Cek");
        isConnected();
    }

    public Config() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class statement {

        public statement() {
        }
    }
}
