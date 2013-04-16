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
    public void setUp() throws ValidationException {
        try {
            temp = File.createTempFile("temp",".txt");
            temp.deleteOnExit();
            String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
"<root>\n" +
"    <entry>\n" +
"        <Type>Article</Type>\n" +
"        <Author>Pekko</Author>\n" +
"        <Title>plaa plaa</Title>\n" +
"        <Year>2001</Year>\n" +
"        <Journal>jäbä</Journal>\n" +
"        <ID>p1</ID>\n" +
"    </entry>\n" +
"    <entry>\n" +
"        <Year>55</Year>\n" +
"        <Journal>mama</Journal>\n" +
"        <Type>Article</Type>\n" +
"        <Author>Plee</Author>\n" +
"        <ID>ooo11</ID>\n" +
"        <Title>Pi</Title>\n" +
"    </entry>\n" +
"    <entry>\n" +
"        <Booktitle>hihi</Booktitle>\n" +
"        <Year>99</Year>\n" +
"        <Type>inproceedings</Type>\n" +
"        <Author>wat</Author>\n" +
"        <ID>KK11</ID>\n" +
"        <Title>de</Title>\n" +
"    </entry>\n" +
"</root>";
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
        
        assertEquals(4,x.listaaArtikkelit().size());
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
        uusEntry2.setValue(BibtexField.TITLE, "hieno artikkeli");
        uusEntry2.setValue(BibtexField.YEAR, "2013");
        uusEntry2.setValue(BibtexField.AUTHOR, "joku tyyppi");
        uusEntry2.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
        uusEntry2.setValue(BibtexField.ID, "aaa");
        uusEntry2.setValue(BibtexField.NOTE, "huom!");
        uusEntry2.setValue(BibtexField.VOLUME, "1");
        

        x.lisaaArtikkeli(uusEntry);
        x.lisaaArtikkeli(uusEntry2);
    }    
    
    @Test(expected=ValidationException.class)
    public void testLisaaDuplicateArtikkeliCaseInsensitive() throws ValidationException {
        BibtexEntry uusEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
        uusEntry.setValue(BibtexField.TITLE, "hieno artikkeli");
        uusEntry.setValue(BibtexField.YEAR, "2013");
        uusEntry.setValue(BibtexField.AUTHOR, "joku tyyppi");
        uusEntry.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
        uusEntry.setValue(BibtexField.ID, "aaa035");
        uusEntry.setValue(BibtexField.NOTE, "huom!");
        uusEntry.setValue(BibtexField.VOLUME, "1");
        
        BibtexEntry uusEntry2 = new BibtexEntry(BibtexEntryType.ARTICLE);
        uusEntry2.setValue(BibtexField.TITLE, "hieno artikkeli");
        uusEntry2.setValue(BibtexField.YEAR, "2013");
        uusEntry2.setValue(BibtexField.AUTHOR, "joku tyyppi");
        uusEntry2.setValue(BibtexField.JOURNAL, "jostain kirjasta kai");
        uusEntry2.setValue(BibtexField.ID, "AAa035");
        uusEntry2.setValue(BibtexField.NOTE, "huom!");
        uusEntry2.setValue(BibtexField.VOLUME, "1");
        

        x.lisaaArtikkeli(uusEntry);
        x.lisaaArtikkeli(uusEntry2);
    }

    /**
     * Test of listaaArtikkelit method, of class XmlTietokanta.
     */
    @Test
    public void testListaaArtikkelit() {
        assertEquals(3,x.listaaArtikkelit().size());
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
        assertEquals(4,x.listaaArtikkelit().size());
    }
}
