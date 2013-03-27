package gjyygle;

import Bibtex.BibtexArtikkeli;
import java.util.List;

public interface BibtexTietokanta {
    public void lisaaArtikkeli(BibtexArtikkeli article);
    public List<BibtexArtikkeli> listaaArtikkelit();
    public void tallenna();
}
