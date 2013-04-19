/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.kayttoliittyma;

import gjyygle.BibtexTietokanta;
import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.BibtexEntryType;
import gjyygle.bibtex.BibtexField;
import gjyygle.bibtex.BibtexGen;
import gjyygle.bibtex.ValidationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author ivahamaa
 */
public class Kayttoliittyma {

    private IO io;
    private BibtexTietokanta tietokanta;
    private BibtexGen generaattori;

    public Kayttoliittyma(IO reader, BibtexTietokanta tietokanta) {
        this.io = reader;
        this.tietokanta = tietokanta;
        this.generaattori = new BibtexGen(tietokanta);
    }

    public Kayttoliittyma(IO reader, BibtexTietokanta tietokanta, BibtexGen generaattori) {
        this.io = reader;
        this.tietokanta = tietokanta;
        this.generaattori = generaattori;
    }

    public void listaaViitteet() {
        List<BibtexEntry> viitteet = tietokanta.listaaArtikkelit();
        for (BibtexEntry entry : viitteet) {
            entry.poistaTyhjat();
            io.println("Type: " + entry.getType().getName());
            Set<Entry<BibtexField, String>> vals = entry.getAllValues().entrySet();
            for (Entry<BibtexField, String> data : vals) {
                io.println(data.getKey().getName() + ": " + data.getValue());
            }
            io.println("");
        }
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
                tiedostonGenerointi();
            } else if (komento.equals("3")) {
                break;
            } else if (komento.equals("4")) {
                listaaViitteet();
            } else {
                virheSyote();
            }
        }
        io.println("");
        io.println("Tervetuloa uudelleen!");
    }

    private void tulostaValikko() {
        io.println("");
        io.println("Valitse toiminto:");
        io.println("1. Syötä viite");
        io.println("2. Generoi bibtex-tiedosto");
        io.println("3. Lopeta");
        io.print("-");
    }

    private void tiedostonGenerointi() {
        io.println("Valitse tiedostonimi");
        String nimi = lue();
        if (!nimi.contains(".bib")) {
            nimi = nimi + ".bib";
        }
        if (generoiTiedostoon(nimi)) {
            io.println("Tiedoston " + nimi + " luonti onnistui");
        } else {
            io.println("Virhe tiedoston luonnissa");
        }
    }

    private boolean generoiTiedostoon(String tiednimi) {
        File file = new File(tiednimi);
        try {
            FileOutputStream fout = new FileOutputStream(file);
            generaattori.generate(fout);
            fout.close();
            return true;
        } catch (Exception e) {
            io.println(e.getMessage());
            return false;
        }
    }

    private void lisaaViite() {
        tulostaViiteValikko();
        String komento = lue();
        while (true) {
            if (komento.equals("1")) {

                lisaaViite(BibtexEntryType.ARTICLE);
                break;
            } else if (komento.equals("2")) {

                lisaaViite(BibtexEntryType.BOOK);
                break;
            } else if (komento.equals("3")) {

                lisaaViite(BibtexEntryType.INPROCEEDINGS);
                break;
            } else if (komento.equals("4")) {
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
        io.println("2. Kirja");
        io.println("3. Pöytäkirja-artikla");
        io.println("4. Päävalikkoon");
        io.print("-");
    }

    private void lisaaViite(BibtexEntryType tyyppi) {
        // Required fields: author, title, journal, year
        io.println("");
        BibtexEntry viite = new BibtexEntry(tyyppi);
        for (BibtexField type : tyyppi.getRequiredFields()) {
            boolean validointi = false;
            while (!validointi) {
                io.print(type.getName() + ": ");
                try {
                    viite.setValue(type, lue());
                    validointi = true;
                } catch (ValidationException e) {
                    io.println(e.getMessage());
                }
            }
        }

        lisaaViiteValinnaiset(viite, tyyppi);


        try {
            tietokanta.lisaaArtikkeli(viite);
            tietokanta.tallenna();
            io.println("");
            io.println("Viite lisätty");
            io.println("");
        } catch (ValidationException e) {
            io.println("");
            io.println(e.getMessage());
            io.println("");
        } catch (IOException x) {
            io.println("");
            io.println("Tallennus epäonnistui");
            io.println("");
        }
    }

    private void lisaaViiteValinnaiset(BibtexEntry viite, BibtexEntryType tyyppi) {
        // Optional fields: volume, number, pages, month, note, key
        io.println("");
        io.println("Haluatko lisätä valinnaisia tietoja? (k/e)");
        String komento = lue();
        while (true) {
            if (komento.equals("k")) {
                io.println("");
                io.println("Artikkelin valinnaiset tiedot:");
                io.println("Tyhjä jättää kentän lisäämättä");
                for (BibtexField type : tyyppi.getOptionalFields()) {
                    boolean validointi = false;
                    while (!validointi) {
                        io.print(type.getName() + ": ");
                        try {
                            viite.setValue(type, lue());
                            validointi = true;
                        } catch (ValidationException e) {
                            io.println(e.getMessage());
                        }
                    }
                }
                io.println("Valinnaiset tiedot lisätty");
                break;
            } else if (komento.equals("e")) {
                break;
            } else {
                io.println("Vastaa k tai e");
                komento = lue();
            }
        }
    }

    private String lue() {
        return io.nextLine();
    }

    private void virheSyote() {
        io.println("Virheellinen komento");
        io.println("");
    }
}
