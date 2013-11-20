/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.dados;

import cancela.negocio.Codigo;
import cancela.negocio.Ticket;
import java.util.List;

/**
 *
 * @author italo
 */
public interface CancelaDAO {
    
    boolean adicionar(Ticket t) throws CancelaDAOException;
    Ticket getTicketPorCodigo(Codigo n) throws CancelaDAOException;
    List<Ticket> getTodosExtraviados() throws CancelaDAOException;
    List<Ticket> getTodosNaoPagos() throws CancelaDAOException;
    List<Ticket> getTodos() throws CancelaDAOException;
    boolean validaTicket(String c) throws CancelaDAOException;
    void liberaTicket(String c , double valorPago) throws CancelaDAOException;
    
}
