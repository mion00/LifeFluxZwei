package lifefluxzwei;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**La classe nazione è quella che racchiude l'intera popolazione, le aziende e lo stato. Permette di accedere a ciascuna di esse con
 * i suoi metodi. In più contiene i metodi per la stampa. 
 *
 * @author etrunon
 */
public class Nazione {
    
    /**Numero dei cittadini gestiti*/
    private int numPopolazione;
    /**ArrayList contente tutti i cittadini */
    public ArrayList<Cittadino> popolazione;
    
    /**Numero delle aziende. Derivato dal numro dei citadini in rapporto 10:1*/
    private int numAziende;
    /**ArrayList contente tutte le aziende*/
    public ArrayList<Azienda> aziende;
    
    /**Stato interno alla Nazione*/
    public Stato stato;
    
    /**Unico costruttore generico per la classe Nazione
     * 
     */
    public Nazione()
    {
        //***********Costruisco la popolazione ************
        numPopolazione = 1000000;
        popolazione = new ArrayList<>();
        
        for(int i=0; i<=numPopolazione; i++)
        {
            Cittadino nuovo = new Cittadino(i);
            popolazione.add(nuovo);
        }
        
        //*******Costruisco le aziende *********
        numAziende = numPopolazione/10;
        aziende = new ArrayList<>();
        
        for(int i=0; i<=numAziende; i++)
        {
            Azienda nuovo = new Azienda(i);
            aziende.add(nuovo);
        }

        // *****Costruisco lo Stato ******
        stato = new Stato(1000, 200, 1000, 200);
    }
    
    /**Funzione che stampa i cittadini e aggiunge una scritta se sono morti o meno
     * 
     */
    public void stampaCittadini()
    {
        for(int i=0; i<popolazione.size(); i++)
     {
         System.out.print("Cittadino " + i);
         if(popolazione.get(i).morto==true)
             System.out.print("   MORTO!!!");
         System.out.println("\n" + popolazione.get(i));
     }
    }
    
    /**Stampa le aziende con i loro parametri e aggiunge se sono fallite
     * 
     */
    public void stampaAziende()
    {
        for(int i=0; i<aziende.size(); i++)
          {
              System.out.println("Azienda " + i);
              if(aziende.get(i).fallita==true)
                  System.out.print("   FALLITA!!");
              System.out.println(aziende.get(i) + "\n");
          }
    }
    
    /**Stampa lo stato dello Stato
     * 
     */
    public void stampaStato()
    {
        System.out.println("*********************************************************\n" 
                + "Stato dello Stato (lol): \n" + stato);
    }

    /**Funzione che ritorna true se TUTTI i cittadini della nazione sono morti
     * 
     * @return vero= morti TUTTI
     */
    public boolean testMorteNazionale()
    {
        boolean test = true;
        for(int i=0; i<popolazione.size() && test==true; i++)
        {
            if(popolazione.get(i).morto==false)
                test=false;
        }
        return test;
    }
    
    /**Funzione che ritorna true se TUTTE le aziende della nazione sono fallite, se anche solo una non lo è ritorna false
     * 
     * @return vero= fallite TUTTE
     */
    public boolean testFallimentoNazionale()
    {
        boolean test = true;
        for(int i=0; i<aziende.size() && test==true; i++)
        {
            if(aziende.get(i).fallita==false)
                test=false;
        }
        return test;
    }
    
    /**Funzione che ritorna vero se O tutte le aziende sono fallite, o tutti cittadini sono morti
     * 
     * @return vero se TUTTI i cittadini OPPURE TUTTE le aziende sono morti/fallite
     */
    public boolean testMorte()
    {
        return testFallimentoNazionale() || testMorteNazionale();
    }
}
