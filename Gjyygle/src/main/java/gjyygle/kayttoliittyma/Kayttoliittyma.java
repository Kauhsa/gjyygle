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
import java.util.List;

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
        io.println("");
        List<BibtexEntry> viitteet = tietokanta.listaaArtikkelit();
        for (BibtexEntry entry : viitteet) {
            io.println(entry.toString());
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
                listaaViitteet();
            } else if (komento.equals("4")) {
                io.print("Lisättävä filtteri: ");
                tietokanta.lisaaFiltteri(lue());            
            } else if (komento.equals("5")) {
                tietokanta.nollaaFiltterit();              
            } else if (komento.equals("x")) {
                break;
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
        io.println("3. Viitteiden listaus");        
        io.println("4. Lisää filtteri "+tietokanta.getFiltterit().toString());        
        io.println("5. Filtterien nollaus");        
        io.println("x. Lopeta");
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
        io.println("3. Konferessijulkaisu");
        io.println("4. Päävalikkoon");
        io.print("-");
    }

    private void lisaaViite(BibtexEntryType tyyppi) {     
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
        lisayksenLopetus(viite);
    }
    private void lisayksenLopetus(BibtexEntry viite) {
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
                valinnaisetLisataan(tyyppi, viite);
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

    private void valinnaisetLisataan(BibtexEntryType tyyppi, BibtexEntry viite) {
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
    }

    
}
