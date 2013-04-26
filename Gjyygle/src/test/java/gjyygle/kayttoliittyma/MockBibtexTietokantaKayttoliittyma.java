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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivahamaa
 */
public class MockBibtexTietokantaKayttoliittyma implements BibtexTietokanta {

    @Override
    public void lisaaArtikkeli(BibtexEntry article) throws IllegalArgumentException {
    }

    @Override
    public List<BibtexEntry> listaaArtikkelit() {
        try {
            ArrayList<BibtexEntry> lista = new ArrayList<BibtexEntry>();
            BibtexEntry uusEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
            uusEntry.setValue(BibtexField.TITLE, "matti");
            uusEntry.setValue(BibtexField.YEAR, "2013");
            uusEntry.setValue(BibtexField.AUTHOR, "teppo");
            uusEntry.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
            uusEntry.setValue(BibtexField.ID, "aa");
            uusEntry.setValue(BibtexField.NOTE, "huom!");
            uusEntry.setValue(BibtexField.VOLUME, "1");

            lista.add(uusEntry);
            
            BibtexEntry tokaEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
            tokaEntry.setValue(BibtexField.TITLE, "esa");
            tokaEntry.setValue(BibtexField.YEAR, "2000");
            tokaEntry.setValue(BibtexField.AUTHOR, "pertti");
            tokaEntry.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
            tokaEntry.setValue(BibtexField.ID, "aaaa");
            tokaEntry.setValue(BibtexField.NOTE, "huom!");
            tokaEntry.setValue(BibtexField.VOLUME, "1");

            lista.add(tokaEntry);
            
            
            return lista;
        } catch (ValidationException ex) {
        }
        return null;
    }

    @Override
    public void tallenna() throws IOException {
    }

    @Override
    public void lisaaFiltteri(String filtteri) {

    }

    @Override
    public List<String> getFiltterit() {
        return new ArrayList<String>();

    }

    @Override
    public void nollaaFiltterit() {

    }
}
