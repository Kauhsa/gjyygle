
package Bibtex;

import Bibtex.BibtexArtikkeli;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class BibtexArtikkeliTest {
    
    BibtexArtikkeli article;
    
    public BibtexArtikkeliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        article = new BibtexArtikkeli("Arto Vihavainen", "A Software Craftsman's Approach to Data Structures", "A4 Article in conference publication",2011);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
