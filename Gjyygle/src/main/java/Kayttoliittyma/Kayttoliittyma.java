/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Bibtex.BibtexArtikkeli;
import gjyygle.BibtexArtikkeli;
import java.text.ParseException;
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
                virheSyote();
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
        System.out.println("");
        System.out.println("Valitse viitetyyppi");
        System.out.println("");
        System.out.println("1. Artikkeli");
        System.out.println("2. Lopeta");
        System.out.print("-");
        String komento = lue();
        while (true) {
            if (komento.equals("1")) {
                lisaaArtikkeli();
                break;
            } else if (komento.equals("2")) {
                System.out.println("");
                break;
            } else {
                virheSyote();
            }
        }
    }

    private void lisaaArtikkeli() {
        // Required fields: author, title, journal, year
        // Optional fields: volume, number, pages, month, note, key

        System.out.println("Artikkelin pakolliset tiedot:");
        System.out.print("Author: ");
        String author = lue();

        System.out.print("Title: ");
        String title = lue();

        System.out.print("Journal: ");
        String journal = lue();

        int year = lueVuosi();

        // TODO Interface jolle BibtexArtikkeli-olio syötetään
        BibtexArtikkeli uusi = new BibtexArtikkeli(author, title, journal, year);

        System.out.println("");
        System.out.println("Artikkeli lisätty");
        System.out.println("");
    }
    
    private int lueVuosi() {
        int year = 0;
        while (true) {
            System.out.print("Year: ");
            String yearString = lue();

            try {
                year = Integer.parseInt(yearString);
            } catch (Exception e) {
                System.out.println("Vuosiluku virheellinen");
                System.out.println("");
                continue;
            }
            break;
        }
        return year;
    }

    private String lue() {
        return reader.nextLine();
    }

    private void virheSyote() {
        System.out.println("Virheellinen komento");
        System.out.println("");
    }
}