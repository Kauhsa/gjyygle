package gjyygle.bibtex;

import gjyygle.BibtexTietokanta;
import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.BibtexEntryType;
import gjyygle.bibtex.BibtexField;
import java.util.ArrayList;
import java.util.List;

public class BibtexTietokantaMock implements BibtexTietokanta {
    ArrayList<BibtexEntry> defaultEntryt;
    public BibtexTietokantaMock() {
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
        uusEntry.setValue(BibtexField.JOURNAL, "jostain");
        uusEntry.setValue(BibtexField.ID, "c");
        defaultEntryt.add(uusEntry);
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
}
