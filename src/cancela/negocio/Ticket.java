/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.negocio;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 *
 * @author italo
 */
public class Ticket {
    
    private Codigo codigo;
    private GregorianCalendar date;
    private int status;
    
    public Ticket(Codigo codigo, GregorianCalendar date,int status) {
        this.codigo = codigo;
        this.date = date;
        this.status = status;
    }
    
    public Ticket(Codigo codigo) {
        this.codigo = codigo;
        this.date = new GregorianCalendar();
        this.status = 1;
    }
    
    
    public Codigo getCodigo() {
        return codigo;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            final Ticket compare = (Ticket)obj;
            if (getCodigo().getCodigo().equals(compare.getCodigo().getCodigo())) {
                return true;
            }    
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        hash = 61 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 61 * hash + this.status;
        return hash;
    }

    @Override
    public String toString() {
        return "Ticket codigo: " + codigo.getCodigo();
    }

    public int getStatus() {
        return this.status;
    }
    
}
