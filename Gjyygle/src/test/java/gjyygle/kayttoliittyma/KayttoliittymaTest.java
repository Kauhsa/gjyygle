
package gjyygle.kayttoliittyma;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ilkka
 */
public class KayttoliittymaTest {

    private Kayttoliittyma liittyma;
    private TestIO readerStub;
    private File tiedosto;

    public KayttoliittymaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        readerStub = new TestIO(null);
        this.liittyma = new Kayttoliittyma(readerStub, new MockBibtexTietokantaKayttoliittyma());


    }

    @After
    public void tearDown() {
    }

    private void asetaUusiReaderStubInput(String[] input) {
        this.readerStub.asetaInput(input);

    }

    @Test
    public void konstruktoriToimii() {
        assertNotNull(liittyma);
    }

    @Test
    public void alkuValikkoVirheellinenKomento() {
        String[] input = {"5", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Virheellinen komento\n"));
    }

    @Test
    public void lisaaViiteValikkoVirheellinenKomento() {
        String[] input = {"1", "45", "2", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Virheellinen komento\n"));
    }

    @Test
    public void lisaaViiteValikkoKayntiJaPoistuminen() {
        String[] input = {"1", "2", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(!readerStub.loytyykoRivi("Virheellinen komento\n"));
        assertTrue(readerStub.loytyykoRivi("Tervetuloa uudelleen!\n"));
    }

    @Test
    public void lisaaViiteArtikkeliKysyyLisatietoja() {
        String[] input = {"1", "1", "Kalle", "Peruna", "Medicus", "1999", "756", "e", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Haluatko lisätä valinnaisia tietoja? (k/e)\n"));
    }
    
    @Test
    public void lisaaViiteArtikkeliValideillaArvoilla() {
        String[] input = {"1", "1", "Kalle", "Peruna", "Medicus", "1999", "756", "e", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Artikkeli lisätty\n"));
    }

    @Test
    public void lisaaViiteArtikkeliVaarillaArvoillaMeneeLapi() {
        String[] input = {"1", "1", "Kalle123", "Peru321na", "Medicus", "sdfasdfa", "fdasfda", "e", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Artikkeli lisätty\n"));
    }

    @Test
    public void lisaaViiteArtikkeliValideillaArvoillaJaLisatiedoilla() {
        String[] input = {"1", "1", "Kalle", "Peruna", "Medicus", "1999", "756", "k", "14", "15", "99", "100", "1", "moi", "avain", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Artikkeli lisätty\n"));
    }
    
    @Test
    public void lisaaViiteArtikkeliLisatiedotVaaraKomento() {
        String[] input = {"1", "1", "Kalle123", "Peru321na", "Medicus", "sdfasdfa", "fdasfda", "gepardi","e", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Vastaa k tai e\n"));
    }
}
