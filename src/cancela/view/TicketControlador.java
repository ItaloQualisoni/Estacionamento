/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.view;

import cancela.dados.CancelaDAOException;
import cancela.negocio.CancelaFachada;

/**
 *
 * @author italo
 */
public class TicketControlador{
    private CancelaFachada fac;

    public TicketControlador() throws CancelaDAOException {
            this.fac = new CancelaFachada();
    }

    public TicketControlador(CancelaFachada fac) {
        this.fac = fac;
    }
    
    
}
