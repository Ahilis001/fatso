/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dretve;

import controler.obrok.MetodeObroka;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Obrok;
import view.jFrame.JfObrazacPodsjetnika;

/**
 *
 * @author iduras
 */
public class DretvaProvjerePodsjetnika extends Thread{
    
    private static DretvaProvjerePodsjetnika dretva = new DretvaProvjerePodsjetnika();
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    
    private DretvaProvjerePodsjetnika() {
    }
            
    public static DretvaProvjerePodsjetnika getInstanca(){
        return dretva;
    }
    
    @Override
    public void interrupt() {
        System.out.println("Dretva je završila s radom.");
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
//        long brojSekundi = 0;
        super.run(); //To change body of generated methods, choose Tools | Templates.
        while (true) {  
            
            try {
                //sleep 1 min
                Thread.sleep(1000 * 60 * 1);
                for (Obrok obrok : Obrok.getListaObroka()) {
                    if (obrok.getPodsjetnik().equals(formatter.format(new Date(System.currentTimeMillis())))) {
                        JfObrazacPodsjetnika jfObrazacPodsjetnika = new JfObrazacPodsjetnika(obrok);
                    }
                }
            } 

            catch (Exception e) {
                
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    public static DretvaProvjerePodsjetnika getDretva() {
        return dretva;
    }
}
