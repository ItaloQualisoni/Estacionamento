/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.dados;

import java.sql.*;

/**
 *
 * @author 12104799
 */
public class CancelaDAOInstace {
     private static CancelaDAOInstace ref;
    
    public static CancelaDAOInstace getInstance() throws CancelaDAOException {
        if (ref == null) {
            ref = new CancelaDAOInstace();
            ref.dropDB();
            ref.createDB();
        }
        return ref;
    }
    
    private CancelaDAOInstace() throws CancelaDAOException {
        try {
             Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            throw new CancelaDAOException("JdbcOdbDriver not found!!");
        }
    }
    
    private void createDB() throws CancelaDAOException {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            Statement sta = con.createStatement();
            String sql = "CREATE TABLE status ("
                    + "STATUS INTEGER NOT NULL PRIMARY KEY,"
                    + "DESCRICAO VARCHAR(100) NOT NULL"
                    + ")";
            sta.executeUpdate(sql);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO status (STATUS,DESCRICAO) VALUES (0,\'Extraviado\'),"
                                                        + "(1,\'Nao pago\'),"
                                                        + "(2,\'Pago\')");
            stmt.executeUpdate();            
            sql = "CREATE TABLE ticket ("
                    + "CODIGO VARCHAR(100) NOT NULL PRIMARY KEY,"
                    + "DATA TIMESTAMP NOT NULL,"
                    + "PRECO DOUBLE DEFAULT 0.00,"
                    + "STATUS INTEGER NOT NULL,"
                    + "FOREIGN KEY(STATUS) REFERENCES status(status)"
                    + ")";
            sta.executeUpdate(sql);
            sta.close();
            con.close();
        } catch (SQLException ex) {
            throw new CancelaDAOException(ex.getMessage());
        }
    }
    
   private void dropDB() throws CancelaDAOException {
        try {
            System.out.println("Limpando sessão antiga :P");
            Connection con = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            Statement sta = con.createStatement();
            String sql2 = "DROP TABLE ticket";
            sta.executeUpdate(sql2);
            String sql = "DROP TABLE status";
            sta.executeUpdate(sql);
            sta.close();
            con.close();
            System.out.println("Tabelas Dropadas");
        } catch (SQLException ex) {
            throw new CancelaDAOException(ex.getMessage());
        }
    }
    public Connection getConnection() throws SQLException {
        //derbyDB sera o nome do diretorio criado localmente
        return DriverManager.getConnection("jdbc:derby:derbyDB");
    }
}
