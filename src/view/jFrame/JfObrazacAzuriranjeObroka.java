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
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.ComboItem;
import model.Obrok;
import view.jPanel.JpObrazacObavijestiIPostavki;
import view.jPanel.JpObrazacPrikazDetalja;
import view.jPanel.JpObrazacRasporedaObroka;

/**
 *
 * @author Ahilis
 */
public class JfObrazacAzuriranjeObroka extends JFrame{
    
//    private static TextField tfDan = new TextField();
    private static TextField tfNaziv = new TextField();
    private static TextField tfVrijeme = new TextField();
    private static TextField tfPodsjetnik = new TextField();
    private static TextArea taOpis = new TextArea();
    private static ArrayList<ComboItem> listaDana = new ArrayList<>();
    private static JComboBox<String> jcbDan = new JComboBox<>();

    public JfObrazacAzuriranjeObroka(){
    }
    
    /**
     * generira obrazac
     * @param obrok
     */
    public static void generirajObrazac(Obrok obrok){
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
        
        //jcombobox za odabir dana
        listaDana.clear();
        listaDana.add(new ComboItem("0","Ponedjeljak"));
        listaDana.add(new ComboItem("1","Utorak"));
        listaDana.add(new ComboItem("2","Srijeda"));
        listaDana.add(new ComboItem("3","Četvrtak"));
        listaDana.add(new ComboItem("4","Petak"));
        listaDana.add(new ComboItem("5","Subota"));
        listaDana.add(new ComboItem("6","Nedjelja"));
        
        for (ComboItem comboItem : listaDana) {
            jcbDan.addItem(comboItem.getValue());
            if (comboItem.getKey().equals(obrok.getDan())) {
                jcbDan.setSelectedIndex(jcbDan.getItemCount() - 1);
            }
        }
        
        jfObrazacDodavanjaObroka.add(jcbDan, c);
        
        //dodavanje naziva obroka
        c.gridx = 0;
        c.gridy = 1;
        jfObrazacDodavanjaObroka.add(new Label("Naziv: "), c);
        
        c.gridx = 1;
        c.gridy = 1;
        tfNaziv.setText(obrok.getNaziv());
        jfObrazacDodavanjaObroka.add(tfNaziv, c);
        
        //dodavanje vremena
        c.gridx = 0;
        c.gridy = 2;
        jfObrazacDodavanjaObroka.add(new Label("Vrijeme: "), c);
        
        c.gridx = 1;
        c.gridy = 2;
        tfVrijeme.setText(obrok.getVrijeme());
        jfObrazacDodavanjaObroka.add(tfVrijeme, c);
        
        //dodavanje podsjetnika
        c.gridx = 0;
        c.gridy = 3;
        jfObrazacDodavanjaObroka.add(new Label("Podsjetnik: "), c);
        
        c.gridx = 1;
        c.gridy = 3;
        tfPodsjetnik.setText(obrok.getPodsjetnik());
        jfObrazacDodavanjaObroka.add(tfPodsjetnik, c);
        
        //dodavanje opisa
        c.gridx = 0;
        c.gridy = 4;
        jfObrazacDodavanjaObroka.add(new Label("Opis: "), c);
        
        c.gridx = 1;
        c.gridy = 4;
        taOpis.setText(obrok.getOpis());
        jfObrazacDodavanjaObroka.add(taOpis, c);
        
        //jp za gumbove
        JPanel jpGumbovi = new JPanel();
        jpGumbovi.setLayout(new GridBagLayout());
        
        //dodavanje gumba za ažuriranje obroka
        c.gridx = 0;
        c.gridy = 0;
        
        //jbtn za brisanje
        JButton jBtnObrisiObrok = new javax.swing.JButton();
        jBtnObrisiObrok.setText("Obrisi");
        jBtnObrisiObrok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                //brisanje obroka
                for (Iterator<Obrok> iterator = Obrok.getListaObroka().iterator(); iterator.hasNext();) {
                    Obrok obrok1 = iterator.next();
                    if (obrok1.getIdObrok() == obrok.getIdObrok()) {
                        iterator.remove();
                    }
                }                
                
                //ažuriranje prikaza
                MetodeObroka.upisJsonUDatoteku();
                JpObrazacRasporedaObroka.generirajObrazac();
                JpObrazacObavijestiIPostavki.getJtObavijest().setText("Obrisan obrok: " + obrok.getNaziv());
                jfObrazacDodavanjaObroka.dispose();   
            }
        });
        jpGumbovi.add(jBtnObrisiObrok, c);
        
        
        //dodavanje gumba za ažuriranje obroka
        c.gridx = 1;
        c.gridy = 0;
        
        //jbtn za azuriranje
        JButton jBtnDodajObrok = new javax.swing.JButton();
        jBtnDodajObrok.setText("Spremi");
        jBtnDodajObrok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                
                //provjera ispravnosti unesenih podataka
                String strPoruka = "";
                
                //provjera ako je naziv ostao prazan
                if(tfNaziv.getText().equals("")){
                    strPoruka += "Naziv ne smije ostati prazan. ";
                }
                
                if(tfVrijeme.getText().equals("")){
                    strPoruka += "Vrijeme ne smije ostati prazno. ";
                }
                
                else if(!MetodeMisc.provjeraFormataVremena(tfVrijeme.getText())){
                    strPoruka += "Neispravni format vremena, format je HH:mm. ";
                }
                
                //provjera ako je opis ostao prazan        
                if(taOpis.getText().equals("")){
                    strPoruka += "Opis ne smije ostati prazan. ";
                }
                
                //ako je sve u redu, dodaje se novi obrok
                if(strPoruka.equals("")) {
                    //traženje obroka u glavnoj listi obroka
                    for (Obrok obrok1 : Obrok.getListaObroka()) {
                    
                        //ako se podudara s id-em (koji se dodijeljuje pri učitavanju)
                        //mijenja se s njim
                        if (obrok1.getIdObrok() == obrok.getIdObrok()) {
                            obrok1.setDan(String.valueOf(jcbDan.getSelectedIndex()));
                            obrok1.setNaziv(tfNaziv.getText());
                            obrok1.setVrijeme(tfVrijeme.getText());
                            obrok1.setOpis(taOpis.getText());
                            obrok1.getJbOdaberiObrok().setText(tfNaziv.getText());
                            obrok1.setPodsjetnik(tfPodsjetnik.getText());
                        }
                    }
                    
                    //ažuriranje prikaza
                    MetodeObroka.upisJsonUDatoteku();
                    JpObrazacRasporedaObroka.generirajObrazac();
                    JpObrazacPrikazDetalja.generirajObrazac(Obrok.getListaObroka().get(obrok.getIdObrok()-1));
                    JpObrazacObavijestiIPostavki.getJtObavijest().setText("Ažuriran obrok: " + obrok.getNaziv());
                }
                
                //prikaz poruke
                JpObrazacObavijestiIPostavki.getJtObavijest().setText(strPoruka);
            }
        });
        jpGumbovi.add(jBtnDodajObrok, c);
               
        //dodavanje jp na jf
        c.gridx = 1;
        c.gridy = 5;
        jfObrazacDodavanjaObroka.add(jpGumbovi, c);
        
        jfObrazacDodavanjaObroka.revalidate();
        jfObrazacDodavanjaObroka.pack();
        jfObrazacDodavanjaObroka.setVisible(true);
        
    }

    public TextField getTfNaziv() {
        return tfNaziv;
    }

    public void setTfNaziv(TextField tfNaziv) {
        this.tfNaziv = tfNaziv;
    }

    public TextArea getTaOpis() {
        return taOpis;
    }

    public void setTaOpis(TextArea taOpis) {
        this.taOpis = taOpis;
    }

    public TextField getTfVrijeme() {
        return tfVrijeme;
    }

    public void setTfVrijeme(TextField tfVrijeme) {
        this.tfVrijeme = tfVrijeme;
    }

    public ArrayList<ComboItem> getListaDana() {
        return listaDana;
    }

    public void setListaDana(ArrayList<ComboItem> listaDana) {
        this.listaDana = listaDana;
    }

    public JComboBox<String> getJcbDan() {
        return jcbDan;
    }

    public void setJcbDan(JComboBox<String> jcbDan) {
        this.jcbDan = jcbDan;
    }

    
    
}
