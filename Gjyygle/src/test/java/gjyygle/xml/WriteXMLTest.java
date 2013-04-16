/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.xml;

import gjyygle.BibtexTietokanta;
import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.BibtexEntryType;
import gjyygle.bibtex.BibtexField;
import gjyygle.bibtex.BibtexTietokantaMock;
import gjyygle.bibtex.ValidationException;
import gjyygle.utils.FileWrite;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;

/**
 *
 * @author mkctammi
 */
public class WriteXMLTest {
    
    public WriteXMLTest() {
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
        } catch (IOException ex) {
            Logger.getLogger(WriteXMLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createDocument method, of class WriteXML.
     */
    @Test
    public void testCreateDocument() {
        ArrayList<HashMap<String, String>> entries = new ArrayList();
        HashMap<String, String> em = new HashMap();
        entries.add(em);
        em.put(BibtexField.AUTHOR.getName(), "test");
        Document result = WriteXML.createDocument(entries);
        assertEquals("test",result.getElementsByTagName("Author").item(0).getFirstChild().getNodeValue());
    }

    /**
     * Test of getString method, of class WriteXML.
     */
    @Test
    public void testGetString() {
        ArrayList<HashMap<String, String>> entries = new ArrayList();
        HashMap<String, String> em = new HashMap();
        entries.add(em);
        em.put(BibtexField.AUTHOR.getName(), "wq");
        em.put(BibtexField.ID.getName(), "es");
        em.put(BibtexField.NUMBER.getName(), "rd");
        String result = WriteXML.getString(entries);
        assertTrue(result.contains("wq"));
        assertTrue(result.contains("es"));
        assertTrue(result.contains("rd"));
    }
}
