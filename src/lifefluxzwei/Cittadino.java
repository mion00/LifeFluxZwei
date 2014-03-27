package lifefluxzwei;

import static java.lang.Math.random;
import java.util.ArrayList;

/**Classe derivata dall'estensione di Entita e che aggiunge un boolean per la morte del cittadino e un ID intero
 * Può vivere o lavorare interagendo con altri cittadini o con aziende.
 * 
 * @author etrunon
 */
public class Cittadino extends Entita {

    /**Booleano, falso se il cittadino NON E' morto, vero se E' morto.*/
    boolean morto;
    /**Id univoco del cittadino*/
    int id;
    
    /**Costruttore che crea un cittadino tramite il costruttore base delle entità.
     * Prende in input un intero che diventa l'id del cittadino stesso.
     * 
     * @param id intero, possibilmente non sdoppiati per diversificare il cittadino
     */
    public Cittadino(int id)
    {
        //Chiama il costruttore di default dell'entità
        super();        

        morto=false;
        this.id = id;
    }
    
    /**Costruttore specifico per creare cittadini partendo da dei parametri di input. Notare, l'id è ancora obbligatorio
     * e sarebbe meglio non sdoppiarli!
     * 
     * @param id
     * @param soldi
     * @param tempo
     * @param servizi
     * @param felicità 
     */
    public Cittadino(int id, int soldi, int tempo, int servizi, int felicità) 
    {
        //Si chiama il costruttore PARAMETRIZZATO dell'entità
        super(soldi, tempo, servizi, felicità);
        morto=false;
        this.id = id;
    }

    /**Sovrascrittura del metodo equals per farlo funzionare anche con i cittadini, verifica l'uguaglianza 
     * dell'ID
     * 
     * @param altroCittadino
     * @return true se gli ID sono uguali
     */
    @Override
    public boolean equals(Object altroCittadino)
    {
        if(altroCittadino==null)
            return false;
        return this.id == ((Cittadino)altroCittadino).id;
    }
    
    /**Sovrascrittura del metodo toString per stampare un cittadino come una stringa contenente in un paio di righe 
     * tutti i parametri.
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return ( "Tempo " +ritornaTempo() + ", Soldi " + ritornaSoldi() + ", Servizi " + ritornaServizi() +
                ", Felicità " + ritornaFelicità() + "\n");
    }
    

    /**Presi in input la popolazione e le aziende della nazione il cittadino "decide" con un tiro randomico (TODO per ora)
     * se lavorare o cercare amici.
     * 
     * @param popolazione viene passato alla f cercaAmici
     * @param aziende viene passato alla f lavora
     */
    public void vivi(ArrayList<Cittadino> popolazione, ArrayList<Azienda> aziende)
    {
        double random = random();
        
        if(random<=0.5)
            cercaAmici(popolazione);
        else
            lavora(aziende);
    }
    
    /**Funzione che trova un altro cittadino (diverso da se stesso) e instaura una "relazione" di amicizia
     * Si controlla che il cittadino E l'amico non siano morti sia all'inizio sia alla fine, segnandolo in caso lo 
     * siano alla fine.
     * 
     * @param popolazione l'array list con tutti i cittadini
     */
    private void cercaAmici(ArrayList<Cittadino> popolazione)
    {

        //Se il cittadino non è morto allora...
        if(morto==false)
        {
            int indice;
            do{ //Sparo a caso su tutta la popolazione fino a quando non trovo un cittadino diverso da me e vivo.
                indice = (int)(random()*((double) popolazione.size()));
            }while (indice==this.id || popolazione.get(indice).morto==true);

            sommaTempo(-1);
            sommaFelicità(3);
            sommaSoldi(-2);
            
            popolazione.get(indice).sommaServizi(-2);

            testMorte();
            popolazione.get(indice).testMorte();
        }
    }

    /**Il cittadino lavora, ovvero se è vivo, sceglie a caso un azienda che non sia fallita e scambia dei valori per
     * simulare il lavoro. Si controlla sia all'inizio che alla fine che le entità coinvolte non siano fallite.
     * 
     * @param aziende 
     */
    private void lavora(ArrayList<Azienda> aziende)
    {
        if(morto==false)
        {
            int indice;
            do{
                indice = (int)(random()*((double)aziende.size()));
            }while (aziende.get(indice).fallita==true);

            sommaTempo(-1);
            sommaFelicità(-2);
            sommaSoldi(3);
            
            aziende.get(indice).sommaTempo();
            aziende.get(indice).sommaSoldi(-3);
            aziende.get(indice).sommaDifferenzaTempo(1);
            
            
            testMorte();
            aziende.get(indice).testFallimento();
            
        }
    }
    
    
    /**Funzione che controlla lo stato del cittadino e le segna come morto nell'evenienza
     * Si è morti SE: i soldi sono <= 0 OPPURE la felicità è <= 0 OPPURE i servizi sono <= 0
     */
        public void testMorte()
        {
            //TODO cambiare l' == con il <=
            morto = ritornaSoldi()==0 || ritornaFelicità()==0 || ritornaServizi()==0;
        }
}
