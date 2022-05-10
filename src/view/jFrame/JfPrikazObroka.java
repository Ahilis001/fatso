/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jFrame;

import controler.misc.Postavke;
import controler.obrok.MetodeObroka;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import model.Obrok;
import view.jPanel.JpObrazacObavijestiIPostavki;
import view.jPanel.JpObrazacRasporedaObroka;
import view.jPanel.JpObrazacPrikazDetalja;

/**
 *
 * @author Ahilis
 */
public class JfPrikazObroka extends JFrame{

//    static JTextField jtObavijest = new JTextField();
    
    static JfPrikazObroka prikazObroka = new JfPrikazObroka();
    
    public JfPrikazObroka() {
    }
    
    public static void generirajObrazac(){
        
        //učitavanje obroka iz datoteke
        MetodeObroka.ucitajObroke();
                
        //postavljanje poravnanja i layouta jFramea
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;

        prikazObroka.setLayout(new GridBagLayout());
        prikazObroka.setLocation(Integer.parseInt(Postavke.getPostavke().getProperty("koordinateAplikacijeX")), Integer.parseInt(Postavke.getPostavke().getProperty("koordinateAplikacijeY")));
        
        prikazObroka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //dodavanje panela za prikaz obavijesti i opcija
        c.gridx = 0;
        c.gridy = 0;
        prikazObroka.add(JpObrazacObavijestiIPostavki.generirajObrazac(), c);
        
        //dodavanje panela za prikaz obroka
        c.gridx = 0;
        c.gridy = 1;
//        c.gridwidth = 1;     
        prikazObroka.add(JpObrazacRasporedaObroka.generirajObrazac(), c);
        
        //dodavanje panela za prikaz obroka
        c.gridx = 0;
        c.gridy = 2;
        
        JpObrazacPrikazDetalja.generirajObrazac(MetodeObroka.dajPrviSljedeciObrok());
        prikazObroka.add(JpObrazacPrikazDetalja.getObrazacPrikazDetalja(), c);
        
        c.gridwidth = 1;
        
        postaviIzgled(prikazObroka);
        prikazObroka.revalidate();
        prikazObroka.pack();
        prikazObroka.repaint();
        prikazObroka.setVisible(true);
    
    }
    
    /**
     * Ažurira jFrame
     * @param obrok 
     */
    public static void azurirajObrazac(Obrok obrok){
        MetodeObroka.ucitajObroke();
                
        //postavljanje poravnanja i layouta jFramea
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;
        
        prikazObroka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        c.gridx = 0;
        c.gridy = 0;
        prikazObroka.add(JpObrazacObavijestiIPostavki.generirajObrazac(), c);
                
        //dodavanje panela za prikaz obroka
        c.gridx = 0;
        c.gridy = 1;
        prikazObroka.add(JpObrazacRasporedaObroka.generirajObrazac(), c);
        
        //dodavanje panela za prikaz obroka
        c.gridx = 0;
        c.gridy = 2;
        prikazObroka.add(JpObrazacPrikazDetalja.generirajObrazac(obrok), c);
        
        postaviIzgled(prikazObroka);
        prikazObroka.revalidate();
        prikazObroka.repaint();
        prikazObroka.pack();
        prikazObroka.setVisible(true);
    }
    
    /**
     * postavlja izgled na "Windows"
     * @param jframe 
     */
    private static void postaviIzgled (JFrame jframe){
     /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jframe.setVisible(true);
            }
        });
    }

    public static JfPrikazObroka getPrikazObroka() {
        return prikazObroka;
    }

    public static void setPrikazObroka(JfPrikazObroka prikazObroka) {
        JfPrikazObroka.prikazObroka = prikazObroka;
    }
    
    
}
