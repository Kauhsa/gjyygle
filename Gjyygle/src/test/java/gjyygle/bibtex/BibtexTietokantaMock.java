package gjyygle.bibtex;

import gjyygle.BibtexTietokanta;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BibtexTietokantaMock implements BibtexTietokanta {

    ArrayList<BibtexEntry> defaultEntryt;

    public BibtexTietokantaMock() {
        try {
            defaultEntryt = new ArrayList<BibtexEntry>();
            BibtexEntry uusEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
            uusEntry.setValue(BibtexField.TITLE, "hieno artikkeli");
            uusEntry.setValue(BibtexField.YEAR, "2013");
            uusEntry.setValue(BibtexField.AUTHOR, "joku tyyppi");
            uusEntry.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
            uusEntry.setValue(BibtexField.ID, "aaa");
            uusEntry.setValue(BibtexField.NOTE, "huom!");
            uusEntry.setValue(BibtexField.VOLUME, "1");
            defaultEntryt.add(uusEntry);
            uusEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
            uusEntry.setValue(BibtexField.TITLE, "toinen hieno artikkeli");
            uusEntry.setValue(BibtexField.YEAR, "2011");
            uusEntry.setValue(BibtexField.AUTHOR, "joku toinen tyyppi");
            uusEntry.setValue(BibtexField.JOURNAL, "jostain kirjasta melko varmasti");
            uusEntry.setValue(BibtexField.MONTH, "5");
            uusEntry.setValue(BibtexField.KEY, "?");
            uusEntry.setValue(BibtexField.ID, "b");
            uusEntry.setValue(BibtexField.PAGES, "101-103");
            defaultEntryt.add(uusEntry);
            uusEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
            uusEntry.setValue(BibtexField.TITLE, "huono artikkeli");
            uusEntry.setValue(BibtexField.YEAR, "2010");
            uusEntry.setValue(BibtexField.AUTHOR, "joku");
            uusEntry.setValue(BibtexField.JOURNAL, "jostÄin");
            uusEntry.setValue(BibtexField.ID, "c");
            defaultEntryt.add(uusEntry);
            uusEntry = new BibtexEntry(BibtexEntryType.INPROCEEDINGS);
            uusEntry.setValue(BibtexField.TITLE, "Skändinåviö");
            uusEntry.setValue(BibtexField.YEAR, "2010");
            uusEntry.setValue(BibtexField.AUTHOR, "");
            uusEntry.setValue(BibtexField.BOOKTITLE, "ÖöpinÅn");
            uusEntry.setValue(BibtexField.ID, "d");
            defaultEntryt.add(uusEntry);
            uusEntry = new BibtexEntry(BibtexEntryType.BOOK);
            uusEntry.setValue(BibtexField.TITLE, "SkändinåviöKIRJA");
            uusEntry.setValue(BibtexField.YEAR, "2010");
            uusEntry.setValue(BibtexField.AUTHOR, "");
            uusEntry.setValue(BibtexField.BOOKTITLE, "ÖöpinÅn");
            uusEntry.setValue(BibtexField.ID, "e");
            defaultEntryt.add(uusEntry);
        } catch (ValidationException ex) {
            Logger.getLogger(BibtexTietokantaMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void lisaaArtikkeli(BibtexEntry article) {
        defaultEntryt.add(article);
    }

    @Override
    public List<BibtexEntry> listaaArtikkelit() {
        return defaultEntryt;
    }

    @Override
    public void tallenna() {
    }

    @Override
    public void lisaaFiltteri(String filtteri) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getFiltterit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void nollaaFiltterit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
