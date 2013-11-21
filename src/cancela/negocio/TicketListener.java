/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.negocio;

import java.awt.event.ActionListener;

/**
 *
 * @author italo
 */
public interface TicketListener extends ActionListener {
    void elementoAlterado(TicketEvent evt);   
}