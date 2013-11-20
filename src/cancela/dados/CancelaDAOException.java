/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.dados;

/**
 *
 * @author italo
 */
public class CancelaDAOException extends Exception {

    public CancelaDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public CancelaDAOException(String message) {
        super(message);
    } 
    
}
