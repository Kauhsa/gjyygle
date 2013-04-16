/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.xml;

import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.BibtexEntryType;
import gjyygle.bibtex.BibtexField;
import gjyygle.bibtex.ValidationException;
import gjyygle.utils.FileWrite;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * @author mkctammi
 */
public class XmlTietokantaTest {
    
    public XmlTietokantaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    File temp;
    XmlTietokanta x;
    @Before
    public void setUp() {
        try {
            temp = File.createTempFile("temp",".txt");
            temp.deleteOnExit();
            String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root><entry><ID>baka00</ID><Author>A. Baka</Author><Title>Peeling onions</Title><Journal>mkyong</Journal><Year>2000</Year></entry></root>";
            FileWrite.stringToFile(test, temp);
        } catch (IOException ex) {
            Logger.getLogger(XmlTietokantaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        x=new XmlTietokanta(temp);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of lisaaArtikkeli method, of class XmlTietokanta.
     */
    @Test
    public void testLisaaArtikkeli() throws ValidationException {
        BibtexEntry uusEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
        uusEntry.setValue(BibtexField.TITLE, "hieno artikkeli");
        uusEntry.setValue(BibtexField.YEAR, "2013");
        uusEntry.setValue(BibtexField.AUTHOR, "joku tyyppi");
        uusEntry.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
        uusEntry.setValue(BibtexField.ID, "aaa");
        uusEntry.setValue(BibtexField.NOTE, "huom!");
        uusEntry.setValue(BibtexField.VOLUME, "1");
        

        x.lisaaArtikkeli(uusEntry);
        
        assertEquals(2,x.listaaArtikkelit().size());
    }   
    @Test(expected=ValidationException.class)
    public void testLisaaDuplicateArtikkeli() throws ValidationException {
        BibtexEntry uusEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
        uusEntry.setValue(BibtexField.TITLE, "hieno artikkeli");
        uusEntry.setValue(BibtexField.YEAR, "2013");
        uusEntry.setValue(BibtexField.AUTHOR, "joku tyyppi");
        uusEntry.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
        uusEntry.setValue(BibtexField.ID, "aaa");
        uusEntry.setValue(BibtexField.NOTE, "huom!");
        uusEntry.setValue(BibtexField.VOLUME, "1");
        
        BibtexEntry uusEntry2 = new BibtexEntry(BibtexEntryType.ARTICLE);
        uusEntry.setValue(BibtexField.TITLE, "hieno artikkeli");
        uusEntry.setValue(BibtexField.YEAR, "2013");
        uusEntry.setValue(BibtexField.AUTHOR, "joku tyyppi");
        uusEntry.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
        uusEntry.setValue(BibtexField.ID, "aaa");
        uusEntry.setValue(BibtexField.NOTE, "huom!");
        uusEntry.setValue(BibtexField.VOLUME, "1");
        

        x.lisaaArtikkeli(uusEntry);
        x.lisaaArtikkeli(uusEntry2);
        
        assertEquals(2,x.listaaArtikkelit().size());
    }

    /**
     * Test of listaaArtikkelit method, of class XmlTietokanta.
     */
    @Test
    public void testListaaArtikkelit() {
        assertEquals(1,x.listaaArtikkelit().size());
    }
    @Test 
    public void testTallenna() throws ValidationException {
        BibtexEntry uusEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
        uusEntry.setValue(BibtexField.TITLE, "hieno artikkeli");
        uusEntry.setValue(BibtexField.YEAR, "2013");
        uusEntry.setValue(BibtexField.AUTHOR, "joku tyyppi");
        uusEntry.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
        uusEntry.setValue(BibtexField.ID, "aaa");
        uusEntry.setValue(BibtexField.NOTE, "huom!");
        uusEntry.setValue(BibtexField.VOLUME, "1");
        x.lisaaArtikkeli(uusEntry);
        x.tallenna();
        x=new XmlTietokanta(temp);
        assertEquals(2,x.listaaArtikkelit().size());
    }
}
