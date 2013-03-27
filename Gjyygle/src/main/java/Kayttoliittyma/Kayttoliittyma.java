/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Bibtex.*;
import gjyygle.BibtexTietokanta;
import gjyygle.xml.XmlTietokanta;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author ivahamaa
 */
public class Kayttoliittyma {

    private Scanner reader;
    private BibtexTietokanta tietokanta;

    public Kayttoliittyma() {
        this.reader = new Scanner(System.in);
        this.tietokanta = new XmlTietokanta(new File("src/main/resources/TestIn.xml"),new File("src/main/resources/TestOut.xml"));
        
    }

    public Kayttoliittyma(Scanner reader) {
        this.reader = reader;
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
        System.out.println("");
        System.out.println("Tervetuloa uudelleen!");
    }

    private void tulostaValikko() {
        System.out.println("Valitse toiminto:");
        System.out.println("1. Syötä viite");
        System.out.println("2. Generoi bibtex-tiedosto");
        System.out.println("3. Lopeta");
        System.out.print("-");
    }

    private void lisaaViite() {
        tulostaViiteValikko();
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
                tulostaViiteValikko();
                komento = lue();
            }
        }
    }

    private void tulostaViiteValikko() {
        System.out.println("");
        System.out.println("Valitse viitetyyppi");
        System.out.println("");
        System.out.println("1. Artikkeli");
        System.out.println("2. Päävalikkoon");
        System.out.print("-");
    }

    private void lisaaArtikkeli() {
        // Required fields: author, title, journal, year
        BibtexEntry uusi = new BibtexEntry(BibtexEntryType.ARTICLE);
        for (BibtexField type : BibtexEntryType.ARTICLE.getRequiredFields()) {
            System.out.print(type.getName() + ": ");
            uusi.setValue(type, lue());
        }
        System.out.println("");
        System.out.println("Haluatko lisätä valinnaisia tietoja? (k/e)");
        String komento = lue();
        while (true) {
            if (komento.equals("k")) {
                lisaaArtikkeliValinnaiset(uusi);
                break;
            } else if (komento.equals("e")) {
                break;
            } else {
                System.out.println("Vastaa k tai e");
                komento = lue();
            }
        }

        // TODO Interface jolle BibtexArtikkeli-olio syötetään        
        tietokanta.lisaaArtikkeli(uusi);
        try {
            tietokanta.tallenna();
        } catch (Exception e) {
            
        }

        System.out.println("");
        System.out.println("Artikkeli lisätty");
        System.out.println("");
    }

    

    private void lisaaArtikkeliValinnaiset(BibtexEntry uusi) {
        // Optional fields: volume, number, pages, month, note, key
        System.out.println("Artikkelin valinnaiset tiedot:");

        for (BibtexField type : BibtexEntryType.ARTICLE.getOptionalFields()) {
            System.out.print(type.getName() + ": ");
            uusi.setValue(type, lue());
        }
        

        System.out.println("Valinnaiset tiedot lisätty");
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
