/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jPanel;

import controler.misc.MetodeMisc;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Obrok;

/**
 *
 * @author Ahilis
 */
public class JpObrazacPrikazDetalja extends JPanel{

    static TextField tfDan = new TextField();
    static TextField tfNaziv = new TextField();
    static TextField tfVrijeme = new TextField();
    static TextField tfPodsjetnik = new TextField();
    static TextArea tfOpis = new TextArea();
    static JButton jBtnSpremiObrok = new javax.swing.JButton();
    
    static JpObrazacPrikazDetalja obrazacPrikazDetalja = new JpObrazacPrikazDetalja();
    
    public JpObrazacPrikazDetalja() {
    }
    
    public static JpObrazacPrikazDetalja generirajObrazac(Obrok obrok){
        obrazacPrikazDetalja.removeAll();
        obrazacPrikazDetalja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalji obroka", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        
        //postavljanje poravnanja i layouta jFramea
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;

        obrazacPrikazDetalja.setLayout(new GridBagLayout());
        
        //dodavanje dana
        c.gridx = 0;
        c.gridy = 0;
        obrazacPrikazDetalja.add(new Label("Dan obroka: "), c);
        
        c.gridx = 1;
        c.gridy = 0;
        tfDan.setText(MetodeMisc.dajDan(String.valueOf(obrok.getDan())));
        tfDan.setEditable(false);
        obrazacPrikazDetalja.add(tfDan, c);
        
        //dodavanje obroka
        c.gridx = 0;
        c.gridy = 1;
        obrazacPrikazDetalja.add(new Label("Naziv: "), c);
        
        c.gridx = 1;
        c.gridy = 1;
        tfNaziv.setText(obrok.getNaziv());
        tfNaziv.setEditable(false);
        obrazacPrikazDetalja.add(tfNaziv, c);
        
        //dodavanje sata
        c.gridx = 0;
        c.gridy = 2;
        obrazacPrikazDetalja.add(new Label("Vrijeme: "), c);
        
        c.gridx = 1;
        c.gridy = 2;
        tfVrijeme.setText(obrok.getVrijeme());
        tfVrijeme.setEditable(false);
        obrazacPrikazDetalja.add(tfVrijeme, c);
        
        //dodavanje podsjetnika
        c.gridx = 0;
        c.gridy = 3;
        obrazacPrikazDetalja.add(new Label("Podsjetnik: "), c);
        
        c.gridx = 1;
        c.gridy = 3;
        tfPodsjetnik.setText(obrok.getPodsjetnik());
        tfPodsjetnik.setEditable(false);
        obrazacPrikazDetalja.add(tfPodsjetnik, c);
        
        //dodavanje opisa
        c.gridx = 0;
        c.gridy = 4;
        obrazacPrikazDetalja.add(new Label("Opis: "), c);
        
        c.gridx = 1;
        c.gridy = 4;
        tfOpis.setText(obrok.getOpis());
        tfOpis.setEditable(false);
        obrazacPrikazDetalja.add(tfOpis, c);
        
        //dodavanje gumba za užuriranje
        c.gridx = 1;
        c.gridy = 5;
        obrazacPrikazDetalja.add(obrok.getJbPromijeniObrok(), c);
        
        obrazacPrikazDetalja.revalidate();
        obrazacPrikazDetalja.setVisible(true);
        
        return obrazacPrikazDetalja;
    }

    public TextField getTfDan() {
        return tfDan;
    }

    public void setTfDan(TextField tfDan) {
        this.tfDan = tfDan;
    }

    public TextField getTfNaziv() {
        return tfNaziv;
    }

    public void setTfNaziv(TextField tfNaziv) {
        this.tfNaziv = tfNaziv;
    }

    public TextField getTfVrijeme() {
        return tfVrijeme;
    }

    public void setTfVrijeme(TextField tfVrijeme) {
        this.tfVrijeme = tfVrijeme;
    }

    public TextArea getTfOpis() {
        return tfOpis;
    }

    public void setTfOpis(TextArea tfOpis) {
        this.tfOpis = tfOpis;
    }

    public static JpObrazacPrikazDetalja getObrazacPrikazDetalja() {
        return obrazacPrikazDetalja;
    }

    public static void setObrazacPrikazDetalja(JpObrazacPrikazDetalja obrazacPrikazDetalja) {
        JpObrazacPrikazDetalja.obrazacPrikazDetalja = obrazacPrikazDetalja;
    }

    public JButton getjBtnSpremiObrok() {
        return jBtnSpremiObrok;
    }

    public void setjBtnSpremiObrok(JButton jBtnSpremiObrok) {
        this.jBtnSpremiObrok = jBtnSpremiObrok;
    }
    
    
}
