/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author italo
 */
public class CancelaDAOJavaDbGerencial implements CancelaDAOGerencial{

    public CancelaDAOInstace ref;
    
    public CancelaDAOJavaDbGerencial() throws CancelaDAOException{
         ref = CancelaDAOInstace.getInstance();
    }
    
    @Override
    public double totalRecebido(int ano, int mes,int dia) throws CancelaDAOException {
        try {
            Connection con = ref.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT SUM(PRECO) as total FROM ticket where "
                    + "DATA <= ? and "
                    + "STATUS in (SELECT STATUS FROM status where DESCRICAO = \'Pago\' or DESCRICAO = \'Extraviado\')"
                    );
            stmt.setTimestamp(1,new Timestamp(ano - 1900, mes, dia, 0, 0, 0, 0));
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
            Connection con = ref.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT COUNT(*) as total FROM ticket where "
                    + "DATA <= ? and "
                    + "STATUS = (SELECT STATUS FROM status where DESCRICAO = \'Extraviado\')"
                    );
            stmt.setTimestamp(1,new Timestamp(ano - 1900, mes, dia, 0, 0, 0, 0));
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
            Connection con = ref.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT COUNT(*) as total FROM ticket where "
                    + "DATA <= ? and "
                    + "STATUS = (SELECT STATUS FROM status where DESCRICAO = \'Pago\')"
                    );
            stmt.setTimestamp(1,new Timestamp(ano - 1900, mes, dia, 0, 0, 0, 0));
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
}
