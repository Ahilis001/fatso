/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jFrame;

import controler.misc.Postavke;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ahilis
 */
public class JfObrazacPostavki extends JFrame{
    
    TextField tfInterval, tfDatotekaObroka, tfCoX, tfCoY;
    JComboBox<String> jcSortiranje = new JComboBox<>();
//    ArrayList<ComboItem> listaSortiranja = new ArrayList<>();
    
    public JfObrazacPostavki(){
    }
    
    /**
     * Generira obrazac postavki.
     */
    public void generirajObrazac(){
        
        //inicijalizacija jFramea postavki
        JfObrazacPostavki jfObrazacPostavki = new JfObrazacPostavki();
        
        //postavljanje poravnanja i layouta jFramea
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;
        
        jfObrazacPostavki.setLayout(new GridBagLayout());
        jfObrazacPostavki.setLocation(Integer.parseInt(Postavke.getPostavke().getProperty("koordinateAplikacijeX")), Integer.parseInt(Postavke.getPostavke().getProperty("koordinateAplikacijeY")));
        
        //jPanel za ključne riječi
        JPanel jpPostavke = new JPanel(new GridBagLayout());
        jpPostavke.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Postavke", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        
        //tf za interval
        c.gridx = 0;
        c.gridy = 1;
        jpPostavke.add(new JLabel("Ažuriranje obroka u min:"), c);
        
        tfInterval = new TextField(Postavke.getPostavke().getProperty("intervalUMinutama"));
        c.gridx = 1;
        c.gridy = 1;
        jpPostavke.add(tfInterval, c);
        
        //tf za putanju datoteke
        c.gridx = 0;
        c.gridy = 2;
        jpPostavke.add(new JLabel("Datoteka obroka:"), c);
        
        c.gridx = 1;
        c.gridy = 2;
        tfDatotekaObroka = new TextField(Postavke.getPostavke().getProperty("putanjaDatotekeObroka"));
        
        jpPostavke.add(tfDatotekaObroka, c);
        
        //tf za x
        c.gridx = 0;
        c.gridy = 3;
        jpPostavke.add(new JLabel("Koordinata X:"), c);
        
        tfCoX = new TextField(Postavke.getPostavke().getProperty("koordinateAplikacijeX"));
        c.gridx = 1;
        c.gridy = 3;
        jpPostavke.add(tfCoX, c);
        
        //tf za interval
        c.gridx = 0;
        c.gridy = 4;
        jpPostavke.add(new JLabel("Koordinata Y:"), c);
        
        tfCoY = new TextField(Postavke.getPostavke().getProperty("koordinateAplikacijeY"));
        c.gridx = 1;
        c.gridy = 4;
        jpPostavke.add(tfCoY, c);
        
        //dodavanje jButtona za spremanje i zatvaranje
        JButton jbSpremiSveIZatvori = new JButton("Spremi i zatvori");
        jbSpremiSveIZatvori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //ako nije unesen interval ili nije broj
                try {
                    Integer.parseInt(tfInterval.getText());
                    if (tfInterval.getText().equals("") ) {
                        tfInterval.setText("60");
                    }
                } catch (Exception e) {
                    tfInterval.setText("60");
                }
                
                //ako nije unesen naziv datoteke
                if (tfDatotekaObroka.getText().equals("") ) {
                    tfDatotekaObroka.setText("obroci.txt");
                }
                
                //ako nije unesena koordinata X ili nije broj
                try {
                    Integer.parseInt(tfCoX.getText());
                    if (tfCoX.getText().equals("") ) {
                        tfCoX.setText("0");
                    }
                } catch (Exception e) {
                    tfCoX.setText("0");
                }

                //ako nije unesena koordinata Y ili nije broj
                try {
                    Integer.parseInt(tfCoY.getText());
                    if (tfCoY.getText().equals("") ) {
                        tfCoY.setText("0");
                    }
                } catch (Exception e) {
                    tfCoY.setText("0");
                }
                
                Postavke.ucitajKonfiguraciju();
                jbSpremiSveIZatvorictionPerformed(jfObrazacPostavki, evt);
            }
        });
        
        //todo pogledati y
        c.gridx = 0;
        c.gridy = 7;
        jfObrazacPostavki.add(jbSpremiSveIZatvori, c);

        //dodavanje panela u jFrame
        c.gridx = 0;
        c.gridy = 0;
        jfObrazacPostavki.add(jpPostavke, c);
        
        jfObrazacPostavki.setUndecorated(true);
        jfObrazacPostavki.revalidate();
        jfObrazacPostavki.pack();
        jfObrazacPostavki.setVisible(true);
    }    
    
    /**
     * metoda spremanje postavki i zatvaranje obrasca postavki
     * @param jfObrazac
     * @param evt 
     */
    private void jbSpremiSveIZatvorictionPerformed(JFrame jfObrazac, java.awt.event.ActionEvent evt) { 
        Postavke.azurirajKonfiguraciju(Postavke.getPostavke(), this);
        jfObrazac.dispose();
//        MetodeMisc.azurirajOglase(Postavke.getPocetna());
    } 

    public TextField getTfInterval() {
        return tfInterval;
    }

    public void setTfInterval(TextField tfInterval) {
        this.tfInterval = tfInterval;
    }

    public TextField getTfCoX() {
        return tfCoX;
    }

    public void setTfCoX(TextField tfCoX) {
        this.tfCoX = tfCoX;
    }

    public TextField getTfCoY() {
        return tfCoY;
    }

    public void setTfCoY(TextField tfCoY) {
        this.tfCoY = tfCoY;
    }

    public TextField getTfDatotekaObroka() {
        return tfDatotekaObroka;
    }

    public void setTfDatotekaObroka(TextField tfDatotekaObroka) {
        this.tfDatotekaObroka = tfDatotekaObroka;
    }
}
