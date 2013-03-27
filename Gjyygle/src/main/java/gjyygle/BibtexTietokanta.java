package gjyygle;

import Bibtex.BibtexArtikkeli;
import Bibtex.BibtexEntry;
import java.io.IOException;
import java.util.List;

public interface BibtexTietokanta {
    public void lisaaArtikkeli(BibtexEntry article) throws IllegalArgumentException;
    public List<BibtexArtikkeli> listaaArtikkelit();
    public void tallenna() throws IOException;
}
