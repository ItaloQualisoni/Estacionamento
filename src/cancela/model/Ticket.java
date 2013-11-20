/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.model;
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
    
    /*
     *   STATUS 
     * 0 - EXTRAVIADO
     * 1 - NÃO PAGO
     * 2 - PAGO
     */ 

    
    //USADO PARA RECONSTRUIR O OBJETO COM OS DADOS DO BANCO DE DADOS 
    public Ticket(Codigo codigo, GregorianCalendar date,int status) {
        this.codigo = codigo;
        this.date = date;
        this.status = status;
    }
    //USADO PARA CONSTRUIR O OBJETO PELA PRIMEIRA VEZ!
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
        Timestamp ts = new Timestamp(date.getTimeInMillis());
        return "Ticket codigo: " + codigo.getCodigo() + " Hora:" + ts.toString()+ " Status " + status ;
    }

    public int getStatus() {
        return this.status;
    }
    
}
