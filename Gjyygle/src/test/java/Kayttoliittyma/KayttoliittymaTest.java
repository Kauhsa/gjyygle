/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    public KayttoliittymaTest() {
        this.liittyma = new Kayttoliittyma();
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.liittyma = new Kayttoliittyma();
        System.setOut(new PrintStream(outContent));
        
    }
    
    @After
    public void tearDown() {
        System.setOut(null);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    private void asetaUusiKayttoliittyma(String input) {
        liittyma = new Kayttoliittyma(new Scanner(input));
    }
    
    @Test
    public void konstruktoriToimii() {
        assertTrue(liittyma != null);
    }
    
    @Test
    public void alkuValikkoVirheellinenKomento() {
        asetaUusiKayttoliittyma("5\n"
                + "3");
        liittyma.kaynnista();       
        assertTrue(outContent.toString().contains("Virheellinen"));
        
    }
        
    @Test
    public void lisaaViiteValikkoVirheellinenKomento() {
        asetaUusiKayttoliittyma("1\n"
                + "45\n"
                + "2\n"
                + "3");
        liittyma.kaynnista();       
        assertTrue(outContent.toString().contains("Virheellinen"));       
    }
    
    @Test
    public void lisaaViiteValikkoKayntiJaPoistuminen() {
        asetaUusiKayttoliittyma("1\n"
                + "2\n"
                + "3");
        liittyma.kaynnista();       
        assertTrue(outContent.toString().contains("Artikkeli") && outContent.toString().contains("uudelleen!"));       
    }
    
    @Test
    public void lisaaViiteArtikkeliValideillaArvoilla() {
        asetaUusiKayttoliittyma("1\n"
                + "1\n"
                + "Kalle\n"
                + "Peruna\n"
                + "Medicus\n"
                + "1999\n"
                + "3");
        liittyma.kaynnista();       
        assertTrue(outContent.toString().contains("lisätty"));       
    }
    
    
    @Test
    public void lisaaViiteArtikkeliVirheellisellaVuodella() {
        asetaUusiKayttoliittyma("1\n"
                + "1\n"
                + "Kalle\n"
                + "Peruna\n"
                + "Medicus\n"
                + "fdasfda\n"
                + "1999\n"
                + "3");
        liittyma.kaynnista();       
        assertTrue(outContent.toString().contains("lisätty"));       
    }
}
