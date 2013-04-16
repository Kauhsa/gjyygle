
package gjyygle.kayttoliittyma;

import gjyygle.BibtexTietokanta;
import gjyygle.bibtex.BibtexGen;
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
    private String[] validiArtikkeliPakolliset;
    private String[] validiArtikkeliValinnaiset;
    

    public KayttoliittymaTest() {
       this.validiArtikkeliPakolliset = new String[] {"Kalle", "Peruna", "Medicus", "1999", "756"};

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
        BibtexTietokanta MockTietokanta = new MockBibtexTietokantaKayttoliittyma();
        BibtexGen gene = new BibtexGen(MockTietokanta);
        this.liittyma = new Kayttoliittyma(readerStub, MockTietokanta, gene);


    }

    @After
    public void tearDown() {
        
        
    }

    private void asetaUusiReaderStubInput(String[] input) {
        this.readerStub.asetaInput(input);

    }

    public String[] generalConcatAll(String[]... jobs) {
        int len = 0;
        for (final String[] job : jobs) {
            len += job.length;
        }

        final String[] result = new String[len];

        int currentPos = 0;
        for (final String[] job : jobs) {
            System.arraycopy(job, 0, result, currentPos, job.length);
            currentPos += job.length;
        }

        return result;
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
        String[] input = {"1", "45", "4", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Virheellinen komento\n"));
    }

    @Test
    public void lisaaViiteValikkoKayntiJaPoistuminen() {
        String[] input = {"1", "4", "3"};
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
        String[] input = generalConcatAll(new String[] {"1", "1"}, validiArtikkeliPakolliset, new String[] {"e", "3"});
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Viite lisätty\n"));
    }

    @Test
    public void lisaaViiteArtikkeliVaarillaArvoillaMeneeLapi() {
        String[] input = {"1", "1", "Kalle123", "Peru321na", "Medicus", "sdfasdfa","1999", "fdasfda", "e", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Viite lisätty\n"));
    }

    @Test
    public void lisaaViiteArtikkeliValideillaArvoillaJaLisatiedoilla() {
        String[] input = {"1", "1", "Kalle", "Peruna", "Medicus", "1999", "756", "k", "14", "15", "99", "100", "1", "moi", "avain", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Viite lisätty\n"));
    }
    
    @Test
    public void lisaaViiteArtikkeliLisatiedotVaaraKomento() {
        String[] input = {"1", "1", "Kalle123", "Peru321na", "Medicus", "1999", "fdasfda", "gepardi","e", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Vastaa k tai e\n"));
    }
    
    
    @Test
    public void generoiBibtexTiedostoOnnistuu() {
        String[] input = {"2", "kallekolamies", "3"};
        asetaUusiReaderStubInput(input);
        liittyma.kaynnista();
        File luotu = new File("kallekolamies.bib");
        luotu.delete();
        assertTrue(readerStub.loytyykoRivi("Tiedoston kallekolamies.bib luonti onnistui\n"));
    }
   
}
