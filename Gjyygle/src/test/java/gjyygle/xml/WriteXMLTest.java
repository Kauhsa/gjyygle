/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.xml;

import gjyygle.bibtex.BibtexField;
import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;
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
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createDocument method, of class WriteXML.
     */
    @Test
    public void testCreateDocument() {
        ArrayList<EnumMap<BibtexField, String>> entries = new ArrayList();
        EnumMap<BibtexField, String> em = new EnumMap<BibtexField, String>(BibtexField.class);
        entries.add(em);
        em.put(BibtexField.AUTHOR, "test");
        Document result = WriteXML.createDocument(entries);
        assertEquals("test",result.getElementsByTagName("Author").item(0).getFirstChild().getNodeValue());
    }

    /**
     * Test of getString method, of class WriteXML.
     */
    @Test
    public void testGetString() {
        ArrayList<EnumMap<BibtexField, String>> entries = new ArrayList();
        EnumMap<BibtexField, String> em = new EnumMap<BibtexField, String>(BibtexField.class);
        entries.add(em);
        em.put(BibtexField.AUTHOR, "wq");
        em.put(BibtexField.ID, "es");
        em.put(BibtexField.NUMBER, "rd");
        String result = WriteXML.getString(entries);
        assertTrue(result.contains("wq"));
        assertTrue(result.contains("es"));
        assertTrue(result.contains("rd"));
    }
}
