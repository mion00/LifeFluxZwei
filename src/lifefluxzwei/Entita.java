package lifefluxzwei;

/**Classe astratta che contiene le variabili generiche delle entità come PRIVATE e fornisce i metodi per intervenire a 
 * modificarle
 *
 * @author etrunon
 */
public abstract class Entita {

    /**Rappresenta la quantità di "soldi" posseduti*/
    private int soldi;    
    /**Tempo dell'entità. Il tempo dei cittadini influisce anche sulla durata stessa della simulazione.*/
    private int tempo;
    /**Quantità dei servizi posseduti dall'entità. Le aziende li producono e vendono allo stato, lo stato li
    * fornisce ai cittadini che infine li consumano.*/
    private int servizi;    
    /**Valore che rappresenta la felicità dell'entità. Attualmente inutile per aziende e stati. */
    private int felicità;

    /**Costruttore generico che inizializza l'entità a soldi 50 tempo 100 servizi 50 felicità 50
     * 
     */
    public Entita() 
    {
        soldi=50;
        tempo=100;
        servizi=50;
        felicità=50;
    }
    
    /**Costruttore che inizializza i valori ai parametri passati
     * 
     * @param soldi
     * @param tempo
     * @param servizi
     * @param felicità 
     */
    public Entita(int soldi, int tempo, int servizi, int felicità) 
    {
        this.soldi=soldi;
        this.tempo=tempo;
        this.servizi=servizi;
        this.felicità=felicità;
    }
    
    void sommaSoldi()
    {
        soldi++;
    }
    
    void sommaTempo()
    {
        tempo++;
    }
    void sommaServizi()
    {
        servizi++;
    }
    
    void sommaFelicità()
    {
        felicità++;
    }
    
     void sommaSoldi(int n)
    {
        soldi+=n;
    }
     
     void sommaTempo(int n)
    {
        tempo+=n;
    }
    
    void sommaServizi(int n)
    {
        servizi+=n;
    }
    
    void sommaFelicità(int n)
    {
        felicità+=n;
    }
    int ritornaSoldi()
    {
        return soldi;
    }
    int ritornaTempo()
    {
        return tempo;
    }
    int ritornaServizi()
    {
        return servizi;
    }
    int ritornaFelicità()
    {
        return felicità;
    }
}
