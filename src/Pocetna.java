
import controler.dretve.DretvaAzuriranjeObroka;
import controler.dretve.DretvaProvjerePodsjetnika;
import controler.misc.Postavke;
import view.jFrame.JfPrikazObroka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahilis
 */
public class Pocetna {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Postavke.ucitajKonfiguraciju();
        DretvaAzuriranjeObroka.getInstanca().start();
        DretvaProvjerePodsjetnika.getInstanca().start();
        
        JfPrikazObroka.generirajObrazac();
        
        
        
        //todo obr
//        Obrok obrok = MetodeObroka.dajPrviSljedeciObrok();
//                
//                System.out.println("Obrok: " + obrok.getNaziv() + " je u " + obrok.getVrijeme() + " sati.");
//        JfObrazacPodsjetnika jfObrazacPodsjetnika = new JfObrazacPodsjetnika(MetodeObroka.dajPrviSljedeciObrok());
//        jfObrazacPodsjetnika.setVisible(true);
        
        
    }
    
}
