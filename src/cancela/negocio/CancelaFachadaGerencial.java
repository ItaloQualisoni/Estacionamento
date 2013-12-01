/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cancela.negocio;

import cancela.dados.CancelaDAOException;
import cancela.dados.CancelaDAOGerencial;
import cancela.dados.CancelaDAOJavaDbGerencial;

/**
 *
 * @author italo
 */
public class CancelaFachadaGerencial {
   private CancelaDAOGerencial dao;
   private CancelaFachada can;
   public CancelaFachadaGerencial(CancelaFachada cancela) throws CancelaDAOException {
        try {
            can = cancela;
            dao = new CancelaDAOJavaDbGerencial();
        } catch (CancelaDAOException e) {
            throw new CancelaDAOException("Falha de criação da fachada gerencial!", e);
        }
   }
    
    public int numeroTicketPago(int ano, int mes, int dia) throws CancelaDAOException{
        return dao.numeroTicketsPagos(ano, mes, dia);
    }
    public int numeroTicketsLiberadosSemPagamento(int ano, int mes,int dia) throws CancelaDAOException{
        return dao.numeroTicketsLiberadosSemPagamento(ano, mes, dia);
    }
   
    public double totalRecebido(int ano, int mes, int dia) throws CancelaDAOException {
        return dao.totalRecebido(ano, mes, dia);
    }
    public int liberaTodosTickets() throws CancelaDAOException {
        int ret = dao.liberaTodosTickets();
        can.fireElementoAlterado();
        return ret;
    }
  
           
}
