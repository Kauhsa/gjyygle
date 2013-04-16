package gjyygle;

import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.ValidationException;
import java.io.IOException;
import java.util.List;

public interface BibtexTietokanta {
    public void lisaaArtikkeli(BibtexEntry article) throws ValidationException;
    public List<BibtexEntry> listaaArtikkelit();
    public void tallenna() throws IOException;
}
