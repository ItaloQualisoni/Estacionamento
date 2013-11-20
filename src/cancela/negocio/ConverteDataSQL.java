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
public class ConverteDataSQL {
    
    public static Timestamp getCurrentTimeStamp(GregorianCalendar date) {
        return new Timestamp(date.getTimeInMillis());
    }
    public static Timestamp getCurrentTimeStamp(String date) {
        return Timestamp.valueOf(date);
    }
    
    public static GregorianCalendar getCurrentGregorianCalendar(Timestamp date){
        GregorianCalendar ret = new GregorianCalendar();
        ret.setTimeInMillis(date.getTime());
        return ret;
    }
}
