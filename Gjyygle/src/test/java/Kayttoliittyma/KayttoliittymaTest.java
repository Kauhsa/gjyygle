/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

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
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test
    public void konstruktoriToimii() {
        assertTrue(liittyma != null);
    }
    
    @Test
    public void alkuValikkoVirheellinenKomento() {
        
        
    }
}
