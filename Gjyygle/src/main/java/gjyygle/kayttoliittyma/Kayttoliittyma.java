/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.kayttoliittyma;

import gjyygle.BibtexTietokanta;
import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.BibtexEntryType;
import gjyygle.bibtex.BibtexField;
import gjyygle.bibtex.ValidationException;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author ivahamaa
 */
public class Kayttoliittyma {

    private IO io;
    private BibtexTietokanta tietokanta;
    private HashMap<String,String> komennot;

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

    private boolean generoiTiedostoon(String tiednimi) {
        // Tiedostonluonti poistettu toistaiseksi
        
        return true;
        
//        File file = new File(tiednimi);
//        try {
//            FileOutputStream fout = new FileOutputStream(file);
//            BibtexGen gen = new BibtexGen(tietokanta);
//            gen.generate(fout);
//            fout.close();
//            return true;
//        } catch (Exception e) {
//            //todo
//            return false;
//        }
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
            }  else if (komento.equals("3")) {
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
        io.println("3. INPROCEEDINGS");        
        io.println("4. Päävalikkoon");
        io.print("-");
    }

    private void lisaaViite(BibtexEntryType tyyppi) {
        // Required fields: author, title, journal, year
        BibtexEntry uusi = new BibtexEntry(tyyppi);
        for (BibtexField type : tyyppi.getRequiredFields()) {
            boolean validointi = false;
            while (!validointi) {
                io.print(type.getName() + ": ");
                try {
                    uusi.setValue(type, lue());
                    validointi = true;
                } catch (Exception e) {
                    io.println(e.getMessage());
                }
            }
        }

        lisaaViiteValinnaiset(uusi, tyyppi);

        
        try {
            tietokanta.lisaaArtikkeli(uusi);
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

    private void lisaaViiteValinnaiset(BibtexEntry uusi, BibtexEntryType tyyppi) {
        // Optional fields: volume, number, pages, month, note, key
        io.println("");
        io.println("Haluatko lisätä valinnaisia tietoja? (k/e)");
        String komento = lue();
        while (true) {
            if (komento.equals("k")) {
                io.println("Artikkelin valinnaiset tiedot:");
                for (BibtexField type : tyyppi.getOptionalFields()) {
                    boolean validointi = false;
                    while (!validointi) {
                        io.print(type.getName() + ": ");
                        try {
                            uusi.setValue(type, lue());
                            validointi = true;
                        } catch (Exception e) {
                            io.println(e.toString());
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
