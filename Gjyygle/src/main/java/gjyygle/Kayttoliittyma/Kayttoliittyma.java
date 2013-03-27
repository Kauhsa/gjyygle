/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohturyhmis;

import java.util.Scanner;

/**
 *
 * @author ivahamaa
 */
public class Kayttoliittyma {

    private Scanner reader;

    public Kayttoliittyma() {
        this.reader = new Scanner(System.in);
    }

    public void kaynnista() {
        System.out.println("Tervetuloa viitteiden hallintaan");
        System.out.println("");

        while (true) {
            tulostaValikko();
            String komento = lue();

            if (komento.equals("1")) {
                lisaaViite();
            } else if (komento.equals("2")) {
                
            } else if (komento.equals("3")) {
                break;
            } else {
                System.out.println("Virheellinen komento");
                System.out.println("");
            }

        }
    }

    private void tulostaValikko() {
        System.out.println("Valitse toiminto:");
        System.out.println("1. Syötä viite");
        System.out.println("2. Generoi bibtex-tiedosto");
        System.out.println("3. Lopeta");
        System.out.print("-");
    }

    private void lisaaViite() {
        System.out.println("Valitse viitetyyppi");
        System.out.println("");
        System.out.println("1. Artikkeli");
        System.out.println("2. Lopeta");
        System.out.print("-");
        String komento = lue();
        if (komento.equals("1")) {
            lisaaArtikkeli();
        } else if (komento.equals("2")) {
            System.out.println("");
        } else {
            System.out.println("Virheellinen komento");
            System.out.println("");
        }
    }

    private void lisaaArtikkeli() {
        // Required fields: author, title, journal, year
        // Optional fields: volume, number, pages, month, note, key
        
   
        System.out.println("Artikkelin pakolliset tiedot:");
        System.out.print("Author:");
        
        
        
    }
    
    private String lue() {
       return reader.nextLine(); 
    }
}
