/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.dados;

/**
 *
 * @author 12104861
 */
public interface CancelaDAOGerencial{
    double totalRecebido(int ano, int mes,int dia)throws CancelaDAOException;
    int numeroTicketsPagos(int ano, int mes,int dia)throws CancelaDAOException;
    int numeroTicketsLiberadosSemPagamento(int ano, int mes,int dia)throws CancelaDAOException;
    int liberaTodosTickets()throws CancelaDAOException;

}
