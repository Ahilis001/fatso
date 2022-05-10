/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jFrame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.Obrok;

/**
 *
 * @author Ahilis
 */
public class JfObrazacPodsjetnika {

    public JfObrazacPodsjetnika(Obrok obrok) {
        
        JFrame jfPodsjetnik = new JFrame();
        jfPodsjetnik.setTitle("Podsjetnik");
        
        //postavljanje poravnanja i layouta jFramea
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;

        jfPodsjetnik.setLayout(new GridBagLayout());
        
        c.gridx = 0;
        c.gridy = 0;
        jfPodsjetnik.add(new JLabel("<html><p style=\"font-size:12px\">Obrok: " + obrok.getNaziv() + " je u " + obrok.getVrijeme() + " sati.</p></html>"), c);
        
        jfPodsjetnik.revalidate();
        jfPodsjetnik.pack();
        jfPodsjetnik.repaint();
        jfPodsjetnik.setLocationRelativeTo(null);
        jfPodsjetnik.setVisible(true);
        
    }
}
