/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jPanel;

import view.jFrame.JfObrazacPostavki;
import view.jFrame.JfObrazacDodavanjeObroka;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ahilis
 */
public class JpObrazacObavijestiIPostavki extends JPanel{
    
    static JTextField jtObavijest = new JTextField(30);
    
    
    static JpObrazacObavijestiIPostavki obrazacObavijestiIOpcija = new JpObrazacObavijestiIPostavki();

    public JpObrazacObavijestiIPostavki() {
    }
    
    /**
     * generiranje jPanela
     * @return 
     */
    public static JpObrazacObavijestiIPostavki generirajObrazac(){
    
        obrazacObavijestiIOpcija.removeAll();
        obrazacObavijestiIOpcija.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Obavijesti i opcije", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        
        //postavljanje poravnanja i layouta jFramea
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;

        obrazacObavijestiIOpcija.setLayout(new GridBagLayout());
        
        //textfield za obavijesti
        c.gridx = 0;
        c.gridy = 0;
        getJtObavijest().setPreferredSize(new Dimension(120, 20));
        getJtObavijest().setEditable(false);
//        obrazacObavijestiIOpcija.revalidate();
//        obrazacObavijestiIOpcija.repaint();
        obrazacObavijestiIOpcija.add(getJtObavijest(), c);
        
        //dodavanje gumba za postavke
        c.gridx = 1;
        c.gridy = 0;
        
        JButton jBtnPostavke = new javax.swing.JButton();
        jBtnPostavke.setText("Postavke");
        jBtnPostavke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //kreiranje i prikaz obrasca postavki
                JfObrazacPostavki opPostavke = new JfObrazacPostavki();
                opPostavke.generirajObrazac();
            }
        });
        obrazacObavijestiIOpcija.add(jBtnPostavke, c);
        
        //dodavanje gumba za dodavanje obroka
        c.gridx = 2;
        c.gridy = 0;
        
        JButton jBtnDodajObrok = new javax.swing.JButton();
        jBtnDodajObrok.setText("Dodaj obrok");
        jBtnDodajObrok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //kreiranje i prikaz obrasca postavki
                JfObrazacDodavanjeObroka odoObrok = new JfObrazacDodavanjeObroka();
                odoObrok.generirajObrazac();
                
            }
        });
        obrazacObavijestiIOpcija.add(jBtnDodajObrok, c);
        
        return obrazacObavijestiIOpcija;
    }

    public static JTextField getJtObavijest() {
        return jtObavijest;
    }
}
