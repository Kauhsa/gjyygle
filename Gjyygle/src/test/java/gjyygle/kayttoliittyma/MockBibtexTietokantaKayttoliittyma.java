/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.kayttoliittyma;

import gjyygle.BibtexTietokanta;
import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.BibtexEntryType;
import gjyygle.bibtex.BibtexField;
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
        ArrayList<BibtexEntry> lista = new ArrayList<BibtexEntry>();
        BibtexEntry uusEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
        uusEntry.setValue(BibtexField.TITLE, "hieno artikkeli");
        uusEntry.setValue(BibtexField.YEAR, "2013");
        uusEntry.setValue(BibtexField.AUTHOR, "joku tyyppi");
        uusEntry.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
        uusEntry.setValue(BibtexField.ID, "aaa");
        uusEntry.setValue(BibtexField.NOTE, "huom!");
        uusEntry.setValue(BibtexField.VOLUME, "1");
        
        lista.add(uusEntry);
        return lista;
    }

    @Override
    public void tallenna() throws IOException {
        
    }
    
}
