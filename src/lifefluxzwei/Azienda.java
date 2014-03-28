package lifefluxzwei;

import static java.lang.Math.random;

/**Classe che simula le aziende. Sono un estensione delle Entita e aggiungono un bool per il fallimento,
 * un int ID (importante!).
 * In più per il calcolo delle risorse prodotte e vendute vengono aggiunti due int privati:
 *  -differenzaTempo, che viene aumentato per ogni unità di tempo che l'azienda guadagna tra una produzione e l'altra;
 *  -differenzaServizi, che tiene traccia dei servizi prodotti tra una vendita e l'altra.
 *
 * @author etrunon
 */
public class Azienda extends Entita{
    
    /**Booleano salva lo stato dell'azienda. Falso se NON FALLITA, vero se FALLITA.*/
    boolean fallita;
    /**ID univoco delle aziende*/
    private int id;
    /**Intero che rappresenta le ore guadagnate dall'ultima produzione. Serve per il calcolo dei servizi prodotti.*/
    private int differenzaTempo;
    /**Servizi guadagnati dall'ultima vendita. Sono le ore di differenzaTempo trasformate con ogni ciclo di produzione. */
    private int differenzaServizi;
    
    /**Costruttore generico che inizializza l'azienda a valori fissi, determinati a codice!
     * Prende in input l'ID e lo assegna all'azienda creata.
     * L'ID viene invece assegnato qui, e il fallimento viene segnato falso, le differenze di servizi e tempo vengono
     * inizializzate a 0.
     * 
     * @param id 
     */
    public Azienda(int id)
    {
        super(1000, 100, 50, 50); //soldi, tempo, servizi, felicità
        fallita=false;
        this.id=id;
        differenzaServizi=0;
        differenzaTempo=0;
    }

    /**Costruttore specifico che crea un azienda a partire da dai parametri passati. Questi vengono a loro volta passati
     * al costruttore della classe Entita.
     * L'ID viene invece assegnato qui, e il fallimento viene segnato falso, le differenze di servizi e tempo vengono
     * inizializzate a 0.
     * 
     * @param id
     * @param soldi
     * @param tempo
     * @param servizi
     * @param felicità 
     */
    public Azienda(int id, int soldi, int tempo, int servizi, int felicità) 
    {
        //Si chiama il costruttore PARAMETRIZZATO dell'entità
        super(soldi, tempo, servizi, felicità);
        fallita=false;
        this.id=id;
    }
    
    /**Aggiunge n alla differenzaTempo attuale
     * 
     * @param n 
     */
    public void sommaDifferenzaTempo(int n)
    {
        differenzaTempo+=n;
    }
    
    /**E' la funzione VIVI portata alle aziende. Decide randomicamente se durante il turno l'azienda produrrà oppure
     * venderà i suoi prodotti allo stato.
     * 
     * @param stato 
     */
    public void funziona(Stato stato)
    {
      double random = random();
      if(random <= 0.5)
        produci();
      else
        vendi(stato);
  
    }
    
    /**La funzione produci trasforma ore-lavoro, salvate nella differenzaTempo in servizi, guadagnate dall'ultima vendita, 
     * se l'azienda ha avuto cittadini che hanno lavorato dall'ultima volta che ha prodotto servizi
     */
    private void produci()
    {
       if(fallita==false)
       {
         
         if(differenzaTempo>0)
         { 
         
           sommaTempo(-differenzaTempo);
           sommaServizi(differenzaTempo*2);
           differenzaServizi+=2*differenzaTempo;  
           differenzaTempo=0;
         }
       }
    }
    
    /**L'azienda ora vende i servizi prodotti allo stato.
     * 
     * @param stato Lo stato a cui si vende
     */
    private void vendi(Stato stato)
    {
        if (fallita==false)
        {
            
            if(differenzaServizi>0)
            {
            sommaServizi(-differenzaServizi);
            sommaSoldi(differenzaServizi);
            sommaTempo(-1);
            
            stato.sommaSoldi(-differenzaServizi);
            stato.sommaServizi(differenzaServizi);
            
            differenzaServizi=0;
            }
        }
    }
       
    /**Verifica se l'azienda è fallita. E' fallita se ha soldi OPPURE servizi minori o uguali a 0.
     * 
     */
    public void testFallimento()
        {
            fallita = ritornaSoldi()<=0 || ritornaServizi()<=0;
        }
    
    /**Override della funzione toString... ritorna una stringa con i parametri della azienda su due linee
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return ( "Tempo " +ritornaTempo() + ", Soldi " + ritornaSoldi() + ", Servizi " + ritornaServizi() + ", Felicità " + ritornaFelicità() +
                " Difftempo: " + differenzaTempo + " Diffserv: " + differenzaServizi);
    }
}
