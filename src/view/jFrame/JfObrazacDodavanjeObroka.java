/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jFrame;

import controler.misc.MetodeMisc;
import controler.misc.Postavke;
import controler.obrok.MetodeObroka;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import model.ComboItem;
import model.Obrok;
import view.jPanel.JpObrazacObavijestiIPostavki;
import view.jPanel.JpObrazacRasporedaObroka;

/**
 *
 * @author Ahilis
 */
public class JfObrazacDodavanjeObroka extends JFrame{
    
    TextField tfDan = new TextField();
    TextField tfNaziv = new TextField();
    TextField tfVrijeme = new TextField();
    TextArea taOpis = new TextArea();
    TextField tfPodsjetnik = new TextField();
    ArrayList<ComboItem> listaDana = new ArrayList<>();
    JComboBox<String> jcDan = new JComboBox<>();

    public JfObrazacDodavanjeObroka(){
    }
    
    /**
     * generira obrazac
     */
    public void generirajObrazac(){
        JFrame jfObrazacDodavanjaObroka = new JFrame();
        
        //postavljanje poravnanja i layouta jFramea
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;

        jfObrazacDodavanjaObroka.setLayout(new GridBagLayout());
        jfObrazacDodavanjaObroka.setLocation(Integer.parseInt(Postavke.getPostavke().getProperty("koordinateAplikacijeX")), Integer.parseInt(Postavke.getPostavke().getProperty("koordinateAplikacijeY")));
        
        
        //dodavanje comboboxa za dan
        c.gridx = 0;
        c.gridy = 0;
        jfObrazacDodavanjaObroka.add(new Label("Dan obroka: "), c);
        
        c.gridx = 1;
        c.gridy = 0;
        
        listaDana.add(new ComboItem("0","Ponedjeljak"));
        listaDana.add(new ComboItem("1","Utorak"));
        listaDana.add(new ComboItem("2","Srijeda"));
        listaDana.add(new ComboItem("3","Četvrtak"));
        listaDana.add(new ComboItem("4","Petak"));
        listaDana.add(new ComboItem("5","Subota"));
        listaDana.add(new ComboItem("6","Nedjelja"));
        
        for (ComboItem comboItem : listaDana) {
            jcDan.addItem(comboItem.getValue());
        }
        
        jfObrazacDodavanjaObroka.add(jcDan, c);
        
        //dodavanje naziva obroka
        c.gridx = 0;
        c.gridy = 1;
        jfObrazacDodavanjaObroka.add(new Label("Naziv: "), c);
        
        c.gridx = 1;
        c.gridy = 1;
        jfObrazacDodavanjaObroka.add(tfNaziv, c);
        
        //dodavanje sata
        c.gridx = 0;
        c.gridy = 2;
        jfObrazacDodavanjaObroka.add(new Label("Vrijeme: "), c);
        
        c.gridx = 1;
        c.gridy = 2;
        jfObrazacDodavanjaObroka.add(tfVrijeme, c);
        
        //dodavanje podsjetnika
        c.gridx = 0;
        c.gridy = 3;
        jfObrazacDodavanjaObroka.add(new Label("Podsjetnik: "), c);
        
        c.gridx = 1;
        c.gridy = 3;
        jfObrazacDodavanjaObroka.add(tfPodsjetnik, c);
        
        //dodavanje opisa
        c.gridx = 0;
        c.gridy = 4;
        jfObrazacDodavanjaObroka.add(new Label("Opis: "), c);
        
        c.gridx = 1;
        c.gridy = 4;
        jfObrazacDodavanjaObroka.add(taOpis, c);
        
        //dodavanje gumba za dodavanje obroka
        c.gridx = 1;
        c.gridy = 5;
        
        JButton jBtnDodajObrok = new javax.swing.JButton();
        jBtnDodajObrok.setText("Dodaj");
        jBtnDodajObrok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                //provjera ispravnosti unesenih podataka
                String strPoruka = "";
                
                //provjera ako je naziv ostao prazan
                if(tfNaziv.getText().equals("")){
                    strPoruka += "Naziv ne smije ostati prazan. ";
                }
                
                //provjera ispravnosti vremena
                if(tfVrijeme.getText().equals("")){
                    strPoruka += "Vrijeme ne smije ostati prazno. ";
                }
                
                else if(!MetodeMisc.provjeraFormataVremena(tfVrijeme.getText())){
                    strPoruka += "Neispravni format vremena, format je HH:mm.";
                }
                
                //provjera ispravnosti podsjetnika
                if(!MetodeMisc.provjeraFormataVremena(tfPodsjetnik.getText())){
                    strPoruka += "Neispravni format vremena, format je HH:mm.";
                }
                
                //provjera ako je opis ostao prazan        
                if(taOpis.getText().equals("")){
                    strPoruka += "Opis ne smije ostati prazan. ";
                }
                
                //ako je sve u redu, dodaje se novi obrok
                if(strPoruka.equals("")) {
                    Obrok obrok = new Obrok(String.valueOf(jcDan.getSelectedIndex()), tfNaziv.getText(), tfVrijeme.getText(), taOpis.getText(), tfPodsjetnik.getText());
                    Obrok.getListaObroka().add(obrok);
                    getTfDan().setText("");
                    getTfVrijeme().setText("");
                    getTfNaziv().setText("");
                    getTaOpis().setText("");
                    getTfPodsjetnik().setText("");

                    MetodeObroka.upisJsonUDatoteku();
                    strPoruka = "Dodan obrok: " + obrok.getNaziv();
                    JpObrazacRasporedaObroka.generirajObrazac();
                }
                
                JpObrazacObavijestiIPostavki.getJtObavijest().setText(strPoruka);
            }
        });
        jfObrazacDodavanjaObroka.add(jBtnDodajObrok, c);
        
        jfObrazacDodavanjaObroka.revalidate();
        jfObrazacDodavanjaObroka.pack();
        jfObrazacDodavanjaObroka.setVisible(true);
        
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

    public TextArea getTaOpis() {
        return taOpis;
    }

    public void setTaOpis(TextArea taOpis) {
        this.taOpis = taOpis;
    }

    public TextField getTfPodsjetnik() {
        return tfPodsjetnik;
    }

    public void setTfPodsjetnik(TextField tfPodsjetnik) {
        this.tfPodsjetnik = tfPodsjetnik;
    }
}
