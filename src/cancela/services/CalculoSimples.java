/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cancela.services;

import cancela.negocio.Ticket;
import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 *
 * @author italo
 */
public class CalculoSimples implements Calculo {
    public final static Time fechaEstacionamento = new Time(2,0,0);
    public final static Time abreEstacionamento = new Time(8,0,0);
    public final static double precoExtraviado = 50.00;
   
    
    
    @Override
    
   public double calculaPreco(Ticket ticket){ 
        //Ticket extraviado
        if(ticket.getStatus() == 0) { return precoExtraviado; }
        //Ticket previamente pago
        if(ticket.getStatus() == 2) { return 0.0; }
        GregorianCalendar aux = new GregorianCalendar();
        double retorno = 0.0; 
        Date system = new Date(); 
        Date tic = ticket.getDate().getTime(); 
        int control; 
        //caso tenha extraviado o ticket
        
        //uma pessoa pode ter entrado no ultimo dia do mes e saido no primeiro dia de outro mes... 
        if(tic.getMonth() < system.getMonth()) {
            control = (aux.getActualMaximum(tic.getMonth()) + system.getDay()) - tic.getDay();          
        }
        else {
            control = system.getDate() - tic.getDate();
        }
     
        switch(control)  { 
          //entrou e saiu no mesmo dia
            case 0: int horas = system.getHours() - tic.getHours(); 
           
                        if(system.getHours() == 2) { retorno = 50;} 
                        else if(horas == 0) {
                                if(system.getMinutes()- tic.getMinutes()  <= 15){
                                    return retorno; 
                                } 
                                 else { 
                                    retorno = 3.5; 
                                } 
                        } 
                        else if(horas <= 3) { 
                                 retorno = 3.5; 
                        }
                        else if(horas >= 3) { 
                            retorno = 10; 
                        } 
              break; 
            //entrou num dia e saiu no dia seguinte
            case 1: int horasDif = 0;
                if(tic.getHours() < 2 && system.getHours() >= 2) {
                    return 100;
                }
                else if(tic.getHours() < 2 && system.getHours() >= 8) { 
                    return 50; 
                }
                else if(tic.getHours() >= 8) {
                    horasDif = (system.getHours() + 24) - tic.getHours(); 
                }
         
               
                if(horasDif >= 24) {
                    retorno = 50;
                }
                else if(horasDif >= 3) {
                    retorno = 10;
                }
                else if(horasDif <= 3 && horasDif >= 1) {
                   retorno = 3.5;
                }
                else if(horasDif == 1){
                    if( tic.getMinutes() > system.getMinutes() ) {
                        int min = (tic.getMinutes() + 60) - system.getMinutes();
                        if(min <= 15) { retorno = 0; }
                        else { retorno = 3.5; }
                    }
                    else {
                        int min = system.getMinutes() - tic.getMinutes();
                        if(min <= 15) { retorno = 0; }
                        else { retorno = 3.5; }
                    }
                }
              break; 
            //ficou mais de 1 dia
            default:
                 if(tic.getHours() < 2 && system.getHours() >= 2) { 
                    control++;
                 }
                 else if(tic.getHours() >= 8 && system.getHours() < 2){
                     control--;
                 }
                
                retorno = control * 50;
        } 
        
        return retorno; 
    }

}
