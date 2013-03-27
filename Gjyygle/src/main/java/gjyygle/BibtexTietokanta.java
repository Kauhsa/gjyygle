package gjyygle;

import Bibtex.BibtexArtikkeli;
import Bibtex.BibtexEntry;
import java.io.IOException;
import java.util.List;

public interface BibtexTietokanta {
    public void lisaaViite(BibtexEntry article) throws IllegalArgumentException;
    public List<BibtexEntry> listaaViite();
    public void tallenna() throws IOException;
}
