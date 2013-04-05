/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.kayttoliittyma;

import gjyygle.BibtexTietokanta;
import gjyygle.bibtex.BibtexEntry;
import java.io.IOException;
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
        return null;
    }

    @Override
    public void tallenna() throws IOException {
        
    }
    
}
