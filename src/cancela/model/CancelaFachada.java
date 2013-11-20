/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.model;

import cancela.dados.CancelaDAO;
import cancela.dados.CancelaDAOException;
import cancela.dados.CancelaDAOJavaDb;
import cancela.services.Calculo;
import cancela.services.CalculoSimples;
import java.util.List;

/**
 *
 * @author 12104799
 */
public class CancelaFachada {
   private CancelaDAO dao;

   public CancelaFachada() throws CancelaDAOException {
        try {
            dao = new CancelaDAOJavaDb();
        } catch (CancelaDAOException e) {
            throw new CancelaDAOException("Falha de criação da fachada!", e);
        }
    }
   public boolean adicionaTicket() throws CancelaDAOException{
       try {
          return dao.adicionar(new Ticket(new CodigoSimples()));
       } catch (CancelaDAOException e) {
             throw new CancelaDAOException("Falha para adiciona o ticket", e);
       }
   }
   public boolean validaTicket(String codigo) throws CancelaDAOException{
       try {
          return dao.validaTicket(codigo);
       } catch (CancelaDAOException e) {
             throw new CancelaDAOException("Falha para validar o ticket", e);
       }
   }
   public void liberaTicket(String codigo, double valorPago) throws CancelaDAOException{
       try {
          dao.liberaTicket(codigo, valorPago);
       } catch (CancelaDAOException e) {
             throw new CancelaDAOException("Falha para liberar o ticket", e);
       }
   }
   public List<Ticket> getTodos() throws CancelaDAOException{
       try {
          return dao.getTodos();
       } catch (CancelaDAOException e) {
             throw new CancelaDAOException("Falha para buscar todos os ticket", e);
       }
   }   
   public List<Ticket> getTodosExtraviados() throws CancelaDAOException{
       try {
          return dao.getTodosExtraviados();
       } catch (CancelaDAOException e) {
             throw new CancelaDAOException("Falha para buscar todos os ticket extraviados", e);
       }
   }
   public List<Ticket> getTodosNaoPagos() throws CancelaDAOException{
       try {
          return dao.getTodosNaoPagos();
       } catch (CancelaDAOException e) {
             throw new CancelaDAOException("Falha para buscar todos os ticket não pagos", e);
       }
   }    
   
   public Ticket getTicketPorCodigo(Codigo codigo) throws CancelaDAOException{
       try {
          return dao.getTicketPorCodigo(codigo);
       } catch (CancelaDAOException e) {
             throw new CancelaDAOException("Falha para validar o ticket", e);
       }
   }
   public List<Ticket> getTodosPagos() throws CancelaDAOException{
       /*IMPLEMENTAR*/
       return null;
   } 
}
