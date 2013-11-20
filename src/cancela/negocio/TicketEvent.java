/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.negocio;
import java.util.EventObject;

/**
 *
 * @author italo
 */
public class TicketEvent extends EventObject {
    public Ticket ticket;

    public TicketEvent(Object source,Ticket ticket) {
        super(source);
        this.ticket = ticket;
    }
    public Ticket getTicket(){
        return this.ticket;
    }

}