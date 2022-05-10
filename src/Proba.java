
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import model.Obrok;
import view.jPanel.JpObrazacRasporedaObroka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahilis
 */
public class Proba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("proba");
        dajPrviSljedeciObrok();
       
        Calendar cal = Calendar.getInstance(new Locale("en","UK"));
//        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date(System.currentTimeMillis()));
        
        //-------------------------------------------------------------------
        
        //-------------------------------------------------------------------
//        
//        if(JpObrazacRasporedaObroka.getListaObrokaPoTjednu().get(danUTjednu).isEmpty()){
//        
//        
//        }
        
//        System.out.println("erdsas "+(cal.get(Calendar.DAY_OF_WEEK)));
        
        
        
    }
    
    public static Obrok dajPrviSljedeciObrok(){
        Obrok sljedeciObrok = new Obrok();
        sljedeciObrok.setIdObrok(0);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        
        
        
        
        
        
        
        int danUTjednu = cal.get(Calendar.DAY_OF_WEEK) - 2;
        
        if(danUTjednu == -1){
            danUTjednu = 6;
        }   
        
        int danUTjednuPomocni = danUTjednu;
        
        
        System.out.println("nekaj");
        
        //-------------------------------------------------------------------
        while (true) {
            
            
            if(!JpObrazacRasporedaObroka.getListaObrokaPoTjednu().get(danUTjednuPomocni).isEmpty()){
                danUTjednuPomocni++;
                
                if(danUTjednuPomocni == 7){
                    danUTjednuPomocni = 0;
                }   
                
                if (danUTjednu == danUTjednuPomocni) {
                    break;
                }

            }
            
            else{
            
                for (Obrok obrok : JpObrazacRasporedaObroka.getListaObrokaPoTjednu().get(danUTjednu)) {
                    
                    //uzimanje vremena obroka i parsiranje za usporedbu
                    LocalTime vrijemeObroka = LocalTime.parse(obrok.getVrijeme());
                    
                    //trenutno vrijeme parsirano za usporedbu
                    LocalTime vrijemeSad = LocalTime.parse(cal.HOUR_OF_DAY + ":" + cal.MINUTE);

                    
                    //provjera je li obrok prvi u danu i je li vrijeme početka nakon trenutnog vremena
                    if(obrok.equals(JpObrazacRasporedaObroka.getListaObrokaPoTjednu().get(danUTjednu).get(0)) && vrijemeObroka.isAfter(vrijemeSad)){
                        sljedeciObrok = obrok;
                        break;
                    }
                }
                
            }
            
            
            
        }
        
        
        //-------------------------------------------------------------------
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

        
        
        LocalTime vrijemeObroka = LocalTime.parse("11:22");
        
        LocalTime vrijemeSad = LocalTime.parse(cal.HOUR_OF_DAY+":"+cal.MINUTE);
        System.out.println(vrijemeObroka.isAfter(vrijemeSad));
        
        return sljedeciObrok;
    }
}
