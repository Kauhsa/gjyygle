/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.kayttoliittyma;


import gjyygle.kayttoliittyma.Kayttoliittyma;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ivahamaa
 */
public class KayttoliittymaTest {

    private Kayttoliittyma liittyma;
    private IOForTest readerStub;


    public KayttoliittymaTest() {
        this.liittyma = new Kayttoliittyma(readerStub);

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        readerStub = new IOForTest(null);
        this.liittyma = new Kayttoliittyma(readerStub);
        

    }

    @After
    public void tearDown() {
    
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    private void asetaUusiReaderStub(String[] input) {
        this.readerStub.asetaInput(input); 
        
    }

    private void asetaUusiKayttoliittyma(String input) {
        //liittyma = new Kayttoliittyma(new Scanner(input));
    }

    @Test
    public void konstruktoriToimii() {
        assertTrue(liittyma != null);
    }

    @Test
    public void alkuValikkoVirheellinenKomento() {
        String[] input = {"5","3"};
        asetaUusiReaderStub(input);
        liittyma.kaynnista();
        
        assertTrue(readerStub.loytyykoRivi("Virheellinen komento\n"));


    }

    @Test
    public void lisaaViiteValikkoVirheellinenKomento() {
        String[] input = {"1","45" ,"2","3"};
        asetaUusiReaderStub(input);
        liittyma.kaynnista();
        assertTrue(readerStub.loytyykoRivi("Virheellinen komento\n"));
    }

    @Test
    public void lisaaViiteValikkoKayntiJaPoistuminen() {
        String[] input = {"1","2" ,"3"};
        asetaUusiReaderStub(input);
        liittyma.kaynnista();
        assertTrue(!readerStub.loytyykoRivi("Virheellinen komento"));
        assertTrue(readerStub.loytyykoRivi("Tervetuloa uudelleen!\n"));
    }
    @Test
    public void lisaaViiteArtikkeliValideillaArvoilla() {
        String[] input = {"1","1" ,"Kalle","Peruna","Medicus","1999","e","2","3"};
        asetaUusiReaderStub(input);
        liittyma.kaynnista();       
        assertTrue(readerStub.loytyykoRivi("Artikkeli lisätty\n"));       
    }
//    @Test
//    public void lisaaViiteArtikkeliVirheellisellaVuodella() {
//        asetaUusiKayttoliittyma("1\n"
//                + "1\n"
//                + "Kalle\n"
//                + "Peruna\n"
//                + "Medicus\n"
//                + "fdasfda\n"
//                + "1999\n"
//                + "3");
//        liittyma.kaynnista();       
//        assertTrue(outContent.toString().contains("lisätty"));       
//    }
}
