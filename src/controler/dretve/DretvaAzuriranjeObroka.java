/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.dretve;

import controler.misc.Postavke;
import controler.obrok.MetodeObroka;
import java.text.SimpleDateFormat;
import java.util.Date;
import view.jFrame.JfPrikazObroka;
import view.jPanel.JpObrazacObavijestiIPostavki;

/**
 *
 * @author iduras
 */
public class DretvaAzuriranjeObroka extends Thread{
    
    private static DretvaAzuriranjeObroka dretva = new DretvaAzuriranjeObroka();
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
    
    private DretvaAzuriranjeObroka() {
    }
            
    public static DretvaAzuriranjeObroka getInstanca(){
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
                Thread.sleep(1000 * 60 * Integer.parseInt(Postavke.getPostavke().getProperty("intervalUMinutama")));
                JfPrikazObroka.azurirajObrazac(MetodeObroka.dajPrviSljedeciObrok());
                JpObrazacObavijestiIPostavki.getJtObavijest().setText("Ažurirano: " + formatter.format(new Date(System.currentTimeMillis())));
            } 

            catch (Exception e) {
//                Pocetna.getjTextFieldPoruka().setText("Greška u dretvi ažuriranja.");
//                MetodeTajmera.dodajULog("DretvaAzuriranjeKalendara - Greška u dretvi ažuriranja.");
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    public static DretvaAzuriranjeObroka getDretva() {
        return dretva;
    }
}
