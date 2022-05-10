/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Obrok;

/**
 *
 * @author Ahilis
 */
public class MetodeMisc {
    
    static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
    
    public static String dajDan(String redniBrojDana){
        String dan = "";
        
        switch (redniBrojDana) {
            case "0":{
                dan = "ponedjeljak";
                break;
            }
            
            case "1":{
                dan = "utorak";
                break;
            }
            
            case "2":{
                dan = "srijeda";
                break;
            }
            
            case "3":{
                dan = "četvrtak";
                break;
            }
            
            case "4":{
                dan = "petak";
                break;
            }
            
            case "5":{
                dan = "subota";
                break;
            }
            
            case "6":{
                dan = "nedjelja";
                break;
            }
            
            default:{
                dan = "nije odabran";
                break;
            }
        }
        
        return dan;
    }
    
    /**
     * Čita sadržaj datoteke s putanje.
     * @param putanja
     * @return 
     */
    static public StringBuilder citanjeIzDatoteke(String putanja){
        
        try {
            //čitanje iz datoteke
            BufferedReader br = new BufferedReader(new FileReader(putanja));
            StringBuilder builder = new StringBuilder();
            String line = null;

            while ( (line = br.readLine()) != null) {
                    builder.append(line);
                    builder.append(System.getProperty("line.separator"));
            }
            
            return builder;
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * provjera ispravnosti formata vremena
     * @param strDate
     * @return 
     */
    public static boolean provjeraFormataVremena(String strDate){
	
        //ako je prazni string
	if (strDate.trim().equals("")){
	    return false;
	}
        
        //ako nije prazni string
	else{
	    //željeni format
	    SimpleDateFormat sdfrmt = new SimpleDateFormat("HH:mm");
	    sdfrmt.setLenient(false);
	    
            //provjera
	    try{
                //ako prolazi provjeru vraća true
	        sdfrmt.parse(strDate); 
                return true;
	    }
	    
            //inače vraća false
	    catch (ParseException e){
	        return false;
	    }
	}
    }
    
    /**
     * sortiranje obroka po danu pa po vremenu
     * @param persons 
     */
    public static void sortiranjeListe(List<Obrok> alObroka) {

        Collections.sort(alObroka, new Comparator() {

            public int compare(Object o1, Object o2) {

                String x1 = ((Obrok) o1).getDan();
                String x2 = ((Obrok) o2).getDan();
                int sComp = x1.compareTo(x2);

                if (sComp != 0) {
                   return sComp;
                } 

                String x11 = ((Obrok) o1).getVrijeme();
                String x22 = ((Obrok) o2).getVrijeme();
                return x11.compareTo(x22);
        }});
    }
}
