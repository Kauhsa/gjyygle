/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.xml;

import gjyygle.utils.FileWrite;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.InputSource;

/**
 *
 * @author mkctammi
 */
public class ReadXMLTest {
    
    public ReadXMLTest() {
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
     * Test of read method, of class ReadXML.
     */
    @Test
    public void testRead_File() throws IOException {
        File temp = File.createTempFile("temp",".txt");
        temp.deleteOnExit();
        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root><entry><ID>baka00</ID><Author>A. Baka</Author><Title>Peeling onions</Title><Journal>mkyong</Journal><Year>2000</Year></entry></root>";
        FileWrite.stringToFile(test, temp);
        ArrayList<HashMap<String, String>> res = ReadXML.read(temp);
        HashMap<String,String> e1 = res.get(0);
        assertTrue(e1.containsKey("Author"));
        assertEquals(e1.get("Author"), "A. Baka");
    }
}
