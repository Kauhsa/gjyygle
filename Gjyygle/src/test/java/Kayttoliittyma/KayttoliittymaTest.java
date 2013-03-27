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
        Scanner lukija = new Scanner(input);
        liittyma = new Kayttoliittyma(lukija);
    }
    
    @Test
    public void konstruktoriToimii() {
        assertTrue(liittyma != null);
    }
    
    @Test
    public void alkuValikkoVirheellinenKomento() {
        String input = "5" + "\n" + "3" + "\n";
        Scanner lukija = new Scanner(input);
        
        asetaUusiKayttoliittyma("");
        liittyma.kaynnista();
        
        assertTrue(outContent.toString().contains("Virheellinen"));
        
    }
}
