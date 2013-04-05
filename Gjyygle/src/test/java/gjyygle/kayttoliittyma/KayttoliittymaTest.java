/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.kayttoliittyma;

import gjyygle.BibtexTietokanta;
import gjyygle.kayttoliittyma.Kayttoliittyma;
import gjyygle.xml.XmlTietokanta;
import groovy.mock.interceptor.MockFor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        readerStub = new IOForTest(null);
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
        assertTrue(liittyma != null);
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
        assertTrue(!readerStub.loytyykoRivi("Virheellinen komento"));
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
    public void lisaaViiteArtikkeliVaarillaArvoilla() {
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
