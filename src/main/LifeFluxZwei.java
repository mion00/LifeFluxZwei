package main;

import lifefluxzwei.Nazione;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author etrunon
 */

public class LifeFluxZwei {

    public static void main(String args[])
    {
        LifeFluxZwei gioco = new LifeFluxZwei();
    }

    public LifeFluxZwei() 
    {
        Nazione italia = new Nazione();
        //TODO: fix     
        int anni = 100;
        //boolean vita = true;
        System.out.println("Tempo iniziale: " + anni); //Debug
        
        //Ciclo che continua SE: c'è ancora tempo per i cittadini, SE almeno un cittadino è vivo e SE almeno
        //un azienda è aperta!
        for(int j=0; j<anni && !italia.testMorte(); j++)
        {
                //Faccio vivere i cittadini
                for(int i=0; i<italia.popolazione.size(); i++)
                {
                    if (!italia.testMorte())
                        italia.popolazione.get(i).vivi(italia.popolazione, italia.aziende);
                }

                //Faccio lavorare le aziende
                for(int i=0; i<italia.aziende.size(); i++)
                {
                    if (!italia.testMorte())
                        italia.aziende.get(i).funziona(italia.stato);
                }

                //Gestisco lo stato
                if (!italia.testMorte())
                    italia.stato.funziona(italia.popolazione);           
        }

        italia.stampaCittadini();
        System.out.println("\n");
        italia.stampaAziende();
        System.out.println("\n");
        italia.stampaStato();
    }
    
    
}
