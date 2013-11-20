/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.dados;

import cancela.negocio.Codigo;
import cancela.negocio.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author italo
 */
public class CancelaDAOJavaDbGerencial extends CancelaDAOJavaDb implements CancelaDAOGerencial{

    private CancelaDAOJavaDb normal;
    public CancelaDAOJavaDbGerencial() throws CancelaDAOException{
        normal = new CancelaDAOJavaDb();
    }
    
    @Override
    public double totalRecebido(int ano, int mes,int dia) throws CancelaDAOException {
        try {
            Connection con = CancelaDAOInstace.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT SUM(PRECO) as total FROM ticket where "
                    + "DATA >= ? and "
                    + "STATUS in (SELECT STATUS FROM status where DESCRICAO = \'Pago\' or DESCRICAO = \'Extraviado\')"
                    );
            stmt.setString(1,ano+"-"+mes+"-"+dia+" 00:00:00.0");
            ResultSet resultado = stmt.executeQuery();
            resultado.next();
            double res = resultado.getDouble("total");
            con.close();
            stmt.close();
            return res;
            
        } catch (SQLException ex) {
            throw new CancelaDAOException("Falha ao buscar.", ex);
        }   
    }

    @Override
    public int numeroTicketsLiberadosSemPagamento(int ano, int mes,int dia) throws CancelaDAOException {
        try {
            Connection con = CancelaDAOInstace.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT COUNT(*) as total FROM ticket where "
                    + "DATA >= ? and "
                    + "STATUS = (SELECT STATUS FROM status where DESCRICAO = \'Extraviado\')"
                    );
            stmt.setString(1,ano+"-"+mes+"-"+dia+" 00:00:00.0");
            ResultSet resultado = stmt.executeQuery();
            resultado.next();
            int res = resultado.getInt("total");
            con.close();
            stmt.close();
            return res;
        } catch (SQLException ex) {
            throw new CancelaDAOException("Falha ao buscar.", ex);
        }
    }
    @Override
    public int numeroTicketsPagos(int ano, int mes, int dia) throws CancelaDAOException {
         try {
            Connection con = CancelaDAOInstace.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT COUNT(*) as total FROM ticket where "
                    + "DATA >= ? and "
                    + "STATUS = (SELECT STATUS FROM status where DESCRICAO = \'Pago\')"
                    );
            stmt.setString(1,ano+"-"+mes+"-"+dia+" 00:00:00.0");
            ResultSet resultado = stmt.executeQuery();
            resultado.next();
            int res = resultado.getInt("total");
            con.close();
            stmt.close();
            return res;
            
        } catch (SQLException ex) {
            throw new CancelaDAOException("Falha ao buscar.", ex);
        }
    }
    @Override
    public boolean adicionar(Ticket p) throws CancelaDAOException {
        return normal.adicionar(p);
    }

    @Override
    public Ticket getTicketPorCodigo(Codigo n) throws CancelaDAOException {
        return normal.getTicketPorCodigo(n);
    }
    
    @Override
    public List<Ticket> getTodos() throws CancelaDAOException {
        return normal.getTodos();
    }
    
    @Override
    public boolean validaTicket(String c) throws CancelaDAOException {
       return normal.validaTicket(c);
       
    }

    @Override
    public List<Ticket> getTodosExtraviados() throws CancelaDAOException {
       return normal.getTodosExtraviados();
    }

    @Override
    public List<Ticket> getTodosNaoPagos() throws CancelaDAOException {
       return normal.getTodosNaoPagos();
    }
    @Override
    public void liberaTicket(String codigo, double valorPago) throws CancelaDAOException {
       normal.liberaTicket(codigo, valorPago);
    }

}
