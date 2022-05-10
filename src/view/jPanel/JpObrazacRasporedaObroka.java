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
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Obrok;

/**
 *
 * @author Ahilis
 */
public class JpObrazacRasporedaObroka extends JPanel{
    
    static ArrayList<ArrayList<Obrok>> listaObrokaPoTjednu = new ArrayList();
    
    static JpObrazacRasporedaObroka obrazacRasporedaObroka = new JpObrazacRasporedaObroka();

    public JpObrazacRasporedaObroka() {
    }
    
    /**
     * generira obrazac
     * @return obrazacRasporedaObroka
     */
    public static JpObrazacRasporedaObroka generirajObrazac(){
        
        obrazacRasporedaObroka.removeAll();
        obrazacRasporedaObroka.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Raspored obroka", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        //postavljanje poravnanja i layouta jPanela
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 20;
        c.ipady = 2;

        obrazacRasporedaObroka.setLayout(new GridBagLayout());
        
        razvrstavanjeObroka();
        
        //dodavanje dana u tjednu
        c.gridx = 0;
        c.gridy = 0;
        for (int i = 0; i < 7; i++) {
            obrazacRasporedaObroka.add(new Label(MetodeMisc.dajDan(String.valueOf(i))), c);
            c.gridx++;
        }
        
        //dodavanje obroka
        c.gridx = 0;
        c.gridy++;
        
        try {
            for (int i = 0; i < 7; i++) {
            
                for (Obrok obrok : listaObrokaPoTjednu.get(i)) {
                    c.gridx = i;
                    obrazacRasporedaObroka.add(obrok.getJbOdaberiObrok(),c);
                    c.gridy++;
                }

                c.gridy = 1;
            }
            
        } catch (Exception e) {
            JpObrazacObavijestiIPostavki.getJtObavijest().setText("Greška kod učitavanja obroka.");
        }
        
        obrazacRasporedaObroka.revalidate();
        obrazacRasporedaObroka.setVisible(true);
        
        return obrazacRasporedaObroka;
    }
    
    /**
     * razvrstava obroke u određenu listu
     */
    private static void razvrstavanjeObroka(){
        
        //čišćenje liste
        listaObrokaPoTjednu.clear();
        
        //ako lista nije prazna
        if (!Obrok.getListaObroka().isEmpty()) {
            
            //dodavanje nove liste za svaki dan
            for (int i = 0; i < 7; i++) {
                ArrayList<Obrok> pomocnaListaObroka = new ArrayList();
                listaObrokaPoTjednu.add(pomocnaListaObroka);
            }
            
            //dodavanje obroka u odgovarajuću listu
            for (Obrok obrok : Obrok.getListaObroka()) {
                listaObrokaPoTjednu.get(Integer.parseInt(obrok.getDan())).add(obrok);
            }
        }
        
    }

    public static JpObrazacRasporedaObroka getObrazacRasporedaObroka() {
        return obrazacRasporedaObroka;
    }

    public static void setObrazacRasporedaObroka(JpObrazacRasporedaObroka obrazacRasporedaObroka) {
        JpObrazacRasporedaObroka.obrazacRasporedaObroka = obrazacRasporedaObroka;
    }

    public static ArrayList<ArrayList<Obrok>> getListaObrokaPoTjednu() {
        return listaObrokaPoTjednu;
    }

    public void setListaObrokaPoTjednu(ArrayList<ArrayList<Obrok>> listaObrokaPoTjednu) {
        this.listaObrokaPoTjednu = listaObrokaPoTjednu;
    }
    
    
}
