/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.dados;

import cancela.negocio.Codigo;
import cancela.negocio.CodigoSimples;
import cancela.negocio.Ticket;
import cancela.services.Calculo;
import cancela.services.CalculoSimples;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author italo
 */
public class CancelaDAOJavaDb implements CancelaDAO{

    public CancelaDAOInstace ref;
    
    public CancelaDAOJavaDb() throws CancelaDAOException{
        ref = CancelaDAOInstace.getInstance();
    } 
    
    
    @Override
    public boolean adicionar(Ticket p) throws CancelaDAOException {
        try {
            
            Connection con = ref.getConnection();
            
            PreparedStatement stmt = con.prepareStatement("INSERT INTO ticket (CODIGO, DATA,STATUS) VALUES (?,?,?)");
            
            stmt.setString(1, p.getCodigo().toString());
            
            Timestamp data = cancela.negocio.ConverteDataSQL.getCurrentTimeStamp(p.getDate());
            
            stmt.setString(2, data.toString());
            
            stmt.setInt(3, p.getStatus());
            
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new CancelaDAOException("Falha ao adicionar.", ex);
        }
    }

    @Override
    public Ticket getTicketPorCodigo(Codigo n) throws CancelaDAOException {
        try {
            Connection con = ref.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM ticket WHERE CODIGO=?"
                    );
            stmt.setString(1, n.toString());
            ResultSet resultado = stmt.executeQuery();
            Ticket p = null;
            if(resultado.next()) {
                String data = resultado.getString("DATA");
                Timestamp tm = cancela.negocio.ConverteDataSQL.getCurrentTimeStamp(data);
                GregorianCalendar cal = cancela.negocio.ConverteDataSQL.getCurrentGregorianCalendar(tm);
                int status = resultado.getInt("STATUS");
                p = new Ticket(n, cal,status);
            }
            stmt.close();
            return p;
        } catch (SQLException ex) {
            throw new CancelaDAOException("Falha ao buscar.", ex);
        }
    }
    
    @Override
    public List<Ticket> getTodos() throws CancelaDAOException {
        try {
            Connection con = ref.getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM ticket");
            List<Ticket> lista = new ArrayList<Ticket>();
            while(resultado.next()) {
                String data = resultado.getString("DATA");
                Codigo cod = new CodigoSimples(resultado.getString("CODIGO"));
                Timestamp tm = cancela.negocio.ConverteDataSQL.getCurrentTimeStamp(data);
                GregorianCalendar cal = cancela.negocio.ConverteDataSQL.getCurrentGregorianCalendar(tm);
                int status = resultado.getInt("STATUS");
                Ticket p = new Ticket(cod, cal,status);
                lista.add(p);
            }
            stmt.close();
            return lista;
        } catch (SQLException ex) {
            throw new CancelaDAOException("Falha ao buscar.", ex);
        }    
    }

    @Override
    public boolean validaTicket(String c) throws CancelaDAOException {
      try {
           Calculo cs = new CalculoSimples();
           Connection con = ref.getConnection();
           PreparedStatement stmt = con.prepareStatement(
                  "SELECT STATUS FROM Ticket "
                   + " WHERE CODIGO = ?"
                   );
            stmt.setString(1, c);
            ResultSet resultado = stmt.executeQuery();
            boolean valida = false;
            if(resultado.next()) {
                if(resultado.getInt("STATUS")!= 1)
                    valida = true;
            }
           stmt.close();
           return valida;
       }
       catch(SQLException ex) { 
           throw new CancelaDAOException("Falha ao liberar ticket.", ex);
       }
    }  
     @Override
    public List<Ticket> getTodosExtraviados() throws CancelaDAOException {
        try {
            Connection con = ref.getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM ticket where STATUS = 0");
            List<Ticket> lista = new ArrayList<Ticket>();
            while(resultado.next()) {
                String data = resultado.getString("DATA");
                Codigo cod = new CodigoSimples(resultado.getString("CODIGO"));
                Timestamp tm = cancela.negocio.ConverteDataSQL.getCurrentTimeStamp(data);
                GregorianCalendar cal = cancela.negocio.ConverteDataSQL.getCurrentGregorianCalendar(tm);
                int status = resultado.getInt("STATUS");
                Ticket p = new Ticket(cod, cal,status);
                lista.add(p);
            }
            stmt.close();
            con.close();
            return lista;
        } catch (SQLException ex) {
            throw new CancelaDAOException("Falha ao buscar.", ex);
        }   
    }

    @Override
    public List<Ticket> getTodosNaoPagos() throws CancelaDAOException {
        try {
            Connection con = ref.getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM ticket where STATUS = 1");
            List<Ticket> lista = new ArrayList<Ticket>();
            while(resultado.next()) {
                String data = resultado.getString("DATA");
                Codigo cod = new CodigoSimples(resultado.getString("CODIGO"));
                Timestamp tm = cancela.negocio.ConverteDataSQL.getCurrentTimeStamp(data);
                GregorianCalendar cal = cancela.negocio.ConverteDataSQL.getCurrentGregorianCalendar(tm);
                int status = resultado.getInt("STATUS");
                Ticket p = new Ticket(cod, cal,status);
                lista.add(p);
            }
            stmt.close();
            return lista;
        } catch (SQLException ex) {
            throw new CancelaDAOException("Falha ao buscar.", ex);
        }       
    }

    @Override
    public void liberaTicket(String codigo, double valorPago) throws CancelaDAOException {
       try {
            Connection con = ref.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                  "UPDATE TICKET SET PRECO = ?, STATUS = 2 WHERE CODIGO = ?");
            stmt.setDouble(1, valorPago);
            stmt.setString(2, codigo);
            stmt.executeUpdate();
            stmt.close();
       }catch (SQLException ex) {
            throw new CancelaDAOException(ex.getMessage());
        }
    }

    @Override
    public void liberaTicketExtraviado(String codigo) {
        try {       
            Connection con = ref.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE TICKET SET STATUS = 0 WHERE CODIGO = ?");
            stmt.setString(1, codigo);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CancelaDAOJavaDb.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }


}
