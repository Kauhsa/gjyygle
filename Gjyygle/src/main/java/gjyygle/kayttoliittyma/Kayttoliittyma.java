/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.kayttoliittyma;

import gjyygle.BibtexTietokanta;
import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.BibtexEntryType;
import gjyygle.bibtex.BibtexField;


/**
 *
 * @author ivahamaa
 */
public class Kayttoliittyma {

    private IO io;
    private BibtexTietokanta tietokanta;
  
    public Kayttoliittyma(IO reader, BibtexTietokanta tietokanta) {
        this.io = reader;
        this.tietokanta = tietokanta;
    }

    public void kaynnista() {
        io.println("Tervetuloa viitteiden hallintaan");
        io.println("");

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
        io.println("");
        io.println("Tervetuloa uudelleen!");
    }

    private void tulostaValikko() {
        io.println("Valitse toiminto:");
        io.println("1. Syötä viite");
        io.println("2. Generoi bibtex-tiedosto");
        io.println("3. Lopeta");
        io.print("-");
    }

    private void lisaaViite() {
        tulostaViiteValikko();
        String komento = lue();
        while (true) {
            if (komento.equals("1")) {
                lisaaArtikkeli();
                break;
            } else if (komento.equals("2")) {
                io.println("");
                break;
            } else {
                virheSyote();
                tulostaViiteValikko();
                komento = lue();
            }
        }
    }

    private void tulostaViiteValikko() {
        io.println("");
        io.println("Valitse viitetyyppi");
        io.println("");
        io.println("1. Artikkeli");
        io.println("2. Päävalikkoon");
        io.print("-");
    }

    private void lisaaArtikkeli() {
        // Required fields: author, title, journal, year
        BibtexEntry uusi = new BibtexEntry(BibtexEntryType.ARTICLE);
        for (BibtexField type : BibtexEntryType.ARTICLE.getRequiredFields()) {
            io.print(type.getName() + ": ");
            uusi.setValue(type, lue());
        }
        io.println("");
        io.println("Haluatko lisätä valinnaisia tietoja? (k/e)");
        String komento = lue();
        while (true) {
            if (komento.equals("k")) {
                lisaaArtikkeliValinnaiset(uusi);
                break;
            } else if (komento.equals("e")) {
                break;
            } else {
                io.println("Vastaa k tai e");
                komento = lue();
            }
        }

        tietokanta.lisaaArtikkeli(uusi);
        try {
            tietokanta.tallenna();
            io.println("");
            io.println("Artikkeli lisätty");
            io.println("");
        } catch (Exception e) {
            io.println("");
            io.println("Tallennus epäonnistui");
            io.println("");
        }


    }

    private void lisaaArtikkeliValinnaiset(BibtexEntry uusi) {
        // Optional fields: volume, number, pages, month, note, key
        io.println("Artikkelin valinnaiset tiedot:");
        for (BibtexField type : BibtexEntryType.ARTICLE.getOptionalFields()) {
            io.print(type.getName() + ": ");
            uusi.setValue(type, lue());
        }
        io.println("Valinnaiset tiedot lisätty");
    }


    private String lue() {
        return io.nextLine();
    }

    private void virheSyote() {
        io.println("Virheellinen komento");
        io.println("");
    }
}
