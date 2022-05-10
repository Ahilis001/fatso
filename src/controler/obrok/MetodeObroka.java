/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.obrok;

import controler.misc.MetodeMisc;
import controler.misc.Postavke;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import model.Obrok;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import view.jPanel.JpObrazacObavijestiIPostavki;

/**
 *
 * @author Ahilis
 */
public class MetodeObroka {
    /**
     * upisuje sadrzaj u json formatu
     */
    public static void upisJsonUDatoteku(){
        
        //Kreiranje liste za JSON objekte za upis u datoteku
        JSONArray listaPomocnihObrokaZaUpis = new JSONArray();
                
        for (Obrok obrok : Obrok.getListaObroka()) {
            //ako je naziv ili naredba ostalo prazno, uredjaj se ne sprema
            JSONObject obrokZaUpis = new JSONObject();
            obrokZaUpis.put("dan", obrok.getDan());
            obrokZaUpis.put("vrijeme", obrok.getVrijeme());
            obrokZaUpis.put("podsjetnik", obrok.getPodsjetnik());
            obrokZaUpis.put("naziv", obrok.getNaziv());
            obrokZaUpis.put("opis", obrok.getOpis());

            listaPomocnihObrokaZaUpis.add(obrokZaUpis);
        }
        
        //brisanje stare datoteke uredjaja
        File datotekaUredjaja = new File(Postavke.getPostavke().getProperty("putanjaDatotekeObroka"));
        datotekaUredjaja.delete();
        
        //kreiranje nove datoteke uredjaja
        try (FileWriter file = new FileWriter(Postavke.getPostavke().getProperty("putanjaDatotekeObroka"))) {
            file.write(listaPomocnihObrokaZaUpis.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Ucitava obroke iz datoteke i vraca listu obroka.
     * @return 
     */
    public static List<Obrok> ucitajObroke(){
        List<Obrok> liPomocniPopisObroka = new ArrayList();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(Postavke.getPostavke().getProperty("putanjaDatotekeObroka")));
            StringBuilder builder = new StringBuilder();
            String line = null;
            
            while ( (line = br.readLine()) != null) {
                    builder.append(line);
                    builder.append(System.getProperty("line.separator"));
            }
            
            //kreiranje json objekta iz stringa
            JsonArray body = Json.createReader(new StringReader(builder.toString())).readArray();
            
            int id = 1;
            //kreiranje objekata Uredjaja za svaki uredjaj iz datoteke
            for (JsonValue jsonValue : body) {
                JsonObject ja = (JsonObject) jsonValue;
                
                Obrok obrok = new Obrok(id, ja.getString("dan"), ja.getString("naziv"), ja.getString("vrijeme"), ja.getString("opis"), ja.getString("podsjetnik"));
                id++;       
                liPomocniPopisObroka.add(obrok);
            }
            
            Obrok.getListaObroka().clear();
            for (Obrok obrok : liPomocniPopisObroka) {
                Obrok.getListaObroka().add(obrok);
            }
            
            JpObrazacObavijestiIPostavki.getJtObavijest().setText("Učitani obroci.");
                  
        } catch (Exception e) {
            
            JpObrazacObavijestiIPostavki.getJtObavijest().setText("Obroci nisu učitani.");
//            e.printStackTrace();
        }
        
        //sortiranje po danu pa po vremenu
        MetodeMisc.sortiranjeListe(Obrok.getListaObroka());        
        return liPomocniPopisObroka;
    }
    
    /**
     * vraca prvi sljedeci obrok
     * @return 
     */
    public static Obrok dajPrviSljedeciObrok(){
        
        //inicijalizacija praznog obroka
        Obrok sljedeciObrok = new Obrok();
        sljedeciObrok.setIdObrok(0);
        
        //inicijalizacija trenutnog vremena
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        
        //inicijalizacija trenutnog dana
        //ned je prvi dan (-1), 
        //da bi se odmah pozicioniralo na odgovarajući index liste za dan (-2)
        int danUTjednu = cal.get(Calendar.DAY_OF_WEEK) - 2;
        
        //korekcija dana
        if(danUTjednu == -1){
            danUTjednu = 6;
        }   
        
        //određivanje sljedećeg obroka
        for (int i = 0; i < Obrok.getListaObroka().size(); i++) {
            Obrok obrok = Obrok.getListaObroka().get(i);
            
            //ako je dan obroka jednak trenutnom danu i ako postoji obrok za taj dan
            if (obrok.getDan().equals(String.valueOf(danUTjednu))) {
                
                //uzimanje vremena obroka i parsiranje za usporedbu
                LocalTime vrijemeObroka = LocalTime.parse(obrok.getVrijeme());

                //trenutno vrijeme parsirano za usporedbu
                LocalTime vrijemeSad = LocalTime.parse(formatter.format(date));

                //provjera je li vrijeme početka nakon trenutnog vremena
                if(vrijemeObroka.isAfter(vrijemeSad)){
                    sljedeciObrok = obrok;
                    break;
                }
                
                //ako ne postoji obrok koji je trenutni dan i da zadovoljava
                //uvijet da počinje nakon trenutnog sata 
                else{
                
                    //sljedeći obrok je onaj koji je sljedeći na redu u listi
                    try {
                        sljedeciObrok = Obrok.getListaObroka().get(++i);
                        break;
                    } 
                    
                    catch (Exception e) {
                        //ukoliko ne postoji sljedeći u listi
                        try {
                            
                            //sljedeći obrok je prvi u listi
                            sljedeciObrok = Obrok.getListaObroka().get(0);
                        } catch (Exception ex) {
                            
                            //ukoliko je lista prazna, odn. nije unesen niti jedan obrok,
                            //ispisuje se poruka
                            JpObrazacObavijestiIPostavki.getJtObavijest().setText("Dodaj barem jedan obrok.");
                        }
                    }
                }
            }
            
            else if (Integer.valueOf(obrok.getDan()) > danUTjednu) {
                //sljedeći obrok je onaj koji je sljedeći na redu u listi
                    try {
                        sljedeciObrok = Obrok.getListaObroka().get(i);
                        break;
                    } 
                    
                    catch (Exception e) {
                        //ukoliko ne postoji sljedeći u listi
                        try {
                            
                            //sljedeći obrok je prvi u listi
                            sljedeciObrok = Obrok.getListaObroka().get(0);
                        } catch (Exception ex) {
                            
                            //ukoliko je lista prazna, odn. nije unesen niti jedan obrok,
                            //ispisuje se poruka
                            JpObrazacObavijestiIPostavki.getJtObavijest().setText("Dodaj barem jedan obrok.");
                        }
                    }
            } 
            
            else if (i == Obrok.getListaObroka().size() - 1) {
                //ukoliko ne postoji sljedeći u listi
                try {

                    //sljedeći obrok je prvi u listi
                    sljedeciObrok = Obrok.getListaObroka().get(0);
                } catch (Exception ex) {

                    //ukoliko je lista prazna, odn. nije unesen niti jedan obrok,
                    //ispisuje se poruka
                    JpObrazacObavijestiIPostavki.getJtObavijest().setText("Dodaj barem jedan obrok.");
                }
            }
        }
        
        return sljedeciObrok;
    }
}
