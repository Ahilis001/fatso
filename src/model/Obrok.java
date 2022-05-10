/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import view.jFrame.JfPrikazObroka;
import view.jFrame.JfObrazacAzuriranjeObroka;
import view.jPanel.JpObrazacPrikazDetalja;

/**
 *
 * @author Ahilis
 */
public class Obrok {
    
    int idObrok;
    String dan;
    String podsjetnik;
    String naziv;
    String vrijeme;
    String opis;
    JButton jbOdaberiObrok = new JButton();
    JButton jbPromijeniObrok = new JButton();
    
    static ArrayList<Obrok> listaObroka = new ArrayList();

    public Obrok() {
    }
    
    public Obrok(int id, String dan, String naziv, String vrijeme, String opis, String podsjetnik) {
        this.idObrok = id;
        this.dan = dan;
        this.naziv = naziv;
        this.vrijeme = vrijeme;
        this.opis = opis;
        this.podsjetnik = podsjetnik;
        
        this.jbOdaberiObrok.setText(naziv);
        this.jbOdaberiObrok.setPreferredSize(new Dimension(60, 20));
        Obrok obrok = this;
        this.jbOdaberiObrok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dodavanje akcije za ažuriranje prikaza
                JpObrazacPrikazDetalja.generirajObrazac(obrok);
                JfPrikazObroka.azurirajObrazac(obrok);
            }
        });
        
        jbPromijeniObrok.setText("Promijeni");
        this.jbPromijeniObrok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JfObrazacAzuriranjeObroka.generirajObrazac(obrok);
            }
        });
    }
    
    public Obrok(String dan, String naziv, String vrijeme, String opis, String podsjetnik) {
        this.dan = dan;
        this.naziv = naziv;
        this.vrijeme = vrijeme;
        this.opis = opis;
        this.podsjetnik = podsjetnik;
        
        this.jbOdaberiObrok.setText(naziv);
        this.jbOdaberiObrok.setPreferredSize(new Dimension(60, 20));
        Obrok obrok = this;
        this.jbOdaberiObrok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dodavanje akcije za ažuriranje prikaza
                JpObrazacPrikazDetalja.generirajObrazac(obrok);
                JfPrikazObroka.azurirajObrazac(obrok);
            }
        });
        
        jbPromijeniObrok.setText("Promijeni");
        this.jbPromijeniObrok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JfObrazacAzuriranjeObroka.generirajObrazac(obrok);
            }
        });
    }
    
    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public static ArrayList<Obrok> getListaObroka() {
        return listaObroka;
    }

    public static void setListaObroka(ArrayList<Obrok> listaObroka) {
        Obrok.listaObroka = listaObroka;
    }

    public JButton getJbOdaberiObrok() {
        return jbOdaberiObrok;
    }

    public void setJbOdaberiObrok(JButton jbOdaberiObrok) {
        this.jbOdaberiObrok = jbOdaberiObrok;
    }

    public JButton getJbPromijeniObrok() {
        return jbPromijeniObrok;
    }

    public void setJbPromijeniObrok(JButton jbPromijeniObrok) {
        this.jbPromijeniObrok = jbPromijeniObrok;
    }

    public int getIdObrok() {
        return idObrok;
    }   

    public void setIdObrok(int idObrok) {
        this.idObrok = idObrok;
    }

    public String getPodsjetnik() {
        return podsjetnik;
    }

    public void setPodsjetnik(String podsjetnik) {
        this.podsjetnik = podsjetnik;
    }
}
