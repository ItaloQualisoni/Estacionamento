
import cancela.dados.CancelaDAOJavaDbGerencial;
import cancela.model.CodigoSimples;
import cancela.model.Ticket;
import cancela.services.CalculoSimples;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author italo
 */
public class CancelaTF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CalculoSimples cs = new CalculoSimples();
        GregorianCalendar aux = new GregorianCalendar();
     
       Ticket a = new Ticket(new CodigoSimples(),new GregorianCalendar(2013, 11, 18, 0, 0 ),1);
    
       Ticket b = new Ticket(new CodigoSimples(),new GregorianCalendar(2013, 11, 2, 0, 33 ),1);
       Ticket c = new Ticket(new CodigoSimples(),new GregorianCalendar(2013, 11, 10, 0, 33 ),1);
       Ticket d = new Ticket(new CodigoSimples(),new GregorianCalendar(2013, 11, 4, 0, 33 ),1);
       //Ticket e = new Ticket(new CodigoSimples(),new GregorianCalendar(2013, 11, 16, 0, 33 ),1);
       Ticket f = new Ticket(new CodigoSimples(),new GregorianCalendar(2013, 11, 16, 1, 5 ),1);
       Ticket g = new Ticket(new CodigoSimples(),new GregorianCalendar(2013, 11, 16, 1, 33 ),1);
       Ticket h = new Ticket(new CodigoSimples(),new GregorianCalendar(2013, 11, 15, 23,55 ),1);
        
        /*
       System.out.println(cs.calculaPreco(a));
        System.out.println(cs.calculaPreco(b));
         System.out.println(cs.calculaPreco(c));
          System.out.println(cs.calculaPreco(d));
           System.out.println(cs.calculaPreco(e));
            System.out.println(cs.calculaPreco(f));
             System.out.println(cs.calculaPreco(g));
              System.out.println(cs.calculaPreco(h));
        */
      
        
        
        List<Ticket> add = new ArrayList<Ticket>();
        add.add(a);
        for (int i = 0; i < 10; i++) {
            add.add(new Ticket(new CodigoSimples()));
        }
        try {
            CancelaDAOJavaDbGerencial bd = CancelaDAOJavaDbGerencial.getInstance();
            for (Ticket ticket : add) {
                //System.out.println("mais um :P");
                bd.adicionar(ticket);
            }
            List<Ticket> list = bd.getTodos();
            for (Ticket ticket : list) {
                System.out.println(ticket);
            }
           
            System.out.println("TT validado ? " + bd.validaTicket(a.getCodigo().getCodigo()));
            System.out.println("Valor devido do tt a: "+ cs.calculaPreco(c));
            bd.liberaTicket(a.getCodigo().getCodigo(), cs.calculaPreco(c));
            System.out.println("TT validado ? " + bd.validaTicket(a.getCodigo().getCodigo()));
            
            /*
            System.out.println("Numero de TT pagos : " + bd.numeroTicketsPagos(2012,5,3));
            System.out.println("Numeros de TT liberados sem Pagamento: " + bd.numeroTicketsLiberadosSemPagamento(2013,3,8));
            System.out.println("Total recebido: : " + bd.totalRecebido(2013,3,8));
            System.out.println("Total de TT liberados sem pagamento : " + bd.numeroTicketsLiberadosSemPagamento(2013,3,8));
            */
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
