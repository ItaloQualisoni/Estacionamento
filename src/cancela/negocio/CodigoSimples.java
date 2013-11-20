/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.negocio;

/**
 *
 * @author italo
 */
import java.util.UUID;


public class CodigoSimples implements Codigo {
    String codigo;

    public CodigoSimples(String codigo) {
        this.codigo = codigo;
    }
    
    public CodigoSimples() {
        this.codigo = String.valueOf(UUID.randomUUID());
    }
    
    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public String toString() {
        return getCodigo();
    }
    

    
}
