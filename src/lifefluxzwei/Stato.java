package lifefluxzwei;

import static java.lang.Math.random;
import java.util.ArrayList;

/**Classe che simula lo stato. Derivata dall'Entita, aggiunge il booleano bancarotta.
 * Fornisce i metodi tassa e servi per interagire coi cittadini. 
 *
 * 
 * @author etrunon
 */
public class Stato extends Entita{
    /**Tiene traccia del fallimento dello stato*/
    boolean bancarotta;
    
    /**Costruttore che crea lo stato con il costruttore parametrico di Entita, tramite i parametri passati.
     * 
     * @param soldi
     * @param tempo
     * @param servizi
     * @param felicità 
     */
    public Stato(int soldi, int tempo, int servizi, int felicità)
    {
        //Costruttore della superclasse con i parametri in ingresso
       super(soldi, tempo, servizi, felicità);
       bancarotta=false;
       
    }
    
    /**ToString dello stato. Ritorna una stringa di 2 linee.
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return ( "Tempo " +ritornaTempo() + ", Soldi " + ritornaSoldi() + ", Servizi " + ritornaServizi() +
                ", Felicità " + ritornaFelicità() + "\n");
  
    }
    
    /**Preso in input un array di cittadini, si cicla su tutti i cittadini e randomicamente si decide se tassare o servire
     * ogni cittadino.
     * 
     * @param popolazione 
     */
    public void funziona(ArrayList<Cittadino> popolazione)
    {
        for(int i=0; i<popolazione.size(); i++)
        {
            double random = random();
            
            if(random<=0.5)
                tassa(popolazione, i);
            else
                servi(popolazione, i);
        }
    }

    /**Presa in input la lista di cittadini si tassa il cittadino con l'ID in input
     * 
     * @param popolazione, arraylist
     * @param id, int
     */
    private void tassa(ArrayList<Cittadino> popolazione, int id)
    {
        if (popolazione.get(id).morto==false)
        {
            popolazione.get(id).sommaSoldi(-2);
        }
        sommaSoldi(2);
    }
    
    /**Preso un cittadino dall'Arraylist popolazione e gli dò servizi.
     * 
     * @param popolazione
     * @param id 
     */
    private void servi(ArrayList<Cittadino> popolazione, int id)
    {
        if (popolazione.get(id).morto==false)
        {
            popolazione.get(id).sommaServizi(2);
        }
        sommaServizi(-2);
    }
}
