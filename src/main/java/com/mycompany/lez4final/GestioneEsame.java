/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lez4final;

import javax.swing.JOptionPane;
import jdk.jshell.execution.Util;

/**
 *
 * @author tss
 */
public class GestioneEsame {

    static String nomeEsame;
    static int listaVoti[];
    static String[] listaCognomi;
    static String[] listaNomi;
    static int soglia;
    static int maxvoto;
    static String msg_ok;
    static String msg_ko;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // fase 1 richiamo preparaEsame
        preparaEsame();
        //fase 2 inizio sessione
        sessioneEsame();
        //fase 3 correzione e assegnamento voti
        correzioneEsami();
        //fase 4 preparazione tabellone risultati
        visTabellone();

    }

    static void preparaEsame() {
        // inizializza l'esame ovvero crea registro
        nomeEsame = JOptionPane.showInputDialog("inserisci nome esame");
        int quanti = Utils.askInt("inserisci max numero alunni", 1, 100);
        //creo registro
        listaCognomi = new String[quanti];
        listaNomi = new String[quanti];
        listaVoti = new int[quanti];
        maxvoto=Utils.askInt("valore massimo per esame", 0,100);
        soglia=Utils.askInt("valore minimo per promozione", 0,maxvoto);
        msg_ko=JOptionPane.showInputDialog(null,"messaggio sotto soglia (es.bocciato)","bocciato");
        msg_ko=msg_ko.toUpperCase();
        msg_ok=JOptionPane.showInputDialog(null,"messaggio sopra soglia (es. promosso)", "promosso");
        msg_ok=msg_ok.toUpperCase();
        
    }

    private static void sessioneEsame() {
        //caricare il regizstro coi partecipanti

        for (int i = 0; i < listaCognomi.length; i++) {
            listaCognomi[i] = JOptionPane.showInputDialog("dimmi il cognome " + (1 + i) + " di " + listaNomi.length);
            listaCognomi[i]=Utils.setFirstCapitalize(listaCognomi[i]);
            //listaNomi[i] = JOptionPane.showInputDialog("dimmi il nome");
            //listaNomi[i]=Utils.setFirstCapitalize(listaNomi[i]);
            listaNomi[i]=Utils.setFirstCapitalize(JOptionPane.showInputDialog("dimmi il nome"));
        }

    }

    private static void correzioneEsami() {
        // docente fornisce indietro gli esami di carta con il suo voto
        //ogni esame ha sopra nome e cognome
        //caricamento voti
        for (int i = 0; i < listaCognomi.length; i++) {
            String nom = listaNomi[i];
            String cog = listaCognomi[i];
            String msg = "inserisci il voto di " + nom + " " + cog;
            listaVoti[i] = Utils.askInt(msg, 0, maxvoto);
        }

    }

    private static void visTabellone() {
        String ris = "VOTAZIONI\n---------\n" 
                + nomeEsame.toUpperCase()
                + "\n-------\n";
        for (int i = 0; i < listaCognomi.length; i++) {
            // guerrini luca - voto = 73 ammesso
            // rossi mario - voto = 90 non ammesso
            ris = ris + listaCognomi[i] + " " + listaNomi[i];
            ris += " - voto = " + listaVoti[i];
            if (listaVoti[i]>=soglia) //caso ok
            {
               ris+= " " + msg_ok + "\n"; 
            }
            else //caso ko
            {
               ris+= " " + msg_ko + "\n"; 
                
            }
            
        }
        JOptionPane.showMessageDialog(null, ris);
    }
}
