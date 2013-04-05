/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gjyygle.bibtex;

import gjyygle.xml.XmlTietokanta;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author peuranie
 */

//@ARTICLE{aaa,
//ID = "aaa",
//Author = "joku tyyppi",
//Title = "hieno artikkeli",
//Journal = "jostain kirjasta kai",
//Year = "2013",
//Volume = "1",
//Note = "huom!"
//}
//
//@ARTICLE{b,
//ID = "b",
//Author = "joku toinen tyyppi",
//Title = "toinen hieno artikkeli",
//Journal = "jostain kirjasta melko varmasti",
//Year = "2011",
//Pages = "101-103",
//Month = "5",
//Key = "?"
//}
//
//@ARTICLE{c,
//ID = "c",
//Author = "joku",
//Title = "huono artikkeli",
//Journal = "jostain",
//Year = "2010"
//}
public class BibtexGenTest {

    private List<BibtexEntry> lista;
    private BibtexGen gen;
    private BibtexTietokantaMock db;
    private ByteArrayOutputStream foo;

    public BibtexGenTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        db = new BibtexTietokantaMock();
        gen = new BibtexGen(db);
        foo = new ByteArrayOutputStream();
        gen.generate(foo);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generate method, of class BibtexGen.
     */
    @Test
    public void testGenerate() {
        String bar = foo.toByteArray().toString();
        bar.contains("@ARTICLE{");
    }

    @Test
    public void hasAllEntries() {
        String bar = foo.toByteArray().toString();
        bar.contains("@ARTICLE{aaa");
        bar.contains("@ARTICLE{b");
        bar.contains("@ARTICLE{c");
    }
    
    @Test
    public void articleAaaHasAllFields() {
        String bar = foo.toByteArray().toString();
        bar.contains("Author = \"joku tyyppi\"");
        bar.contains("Title = \"hieno artikkeli\"");
        bar.contains("Journal = \"jostain kirjasta kai\"");
        bar.contains("Year = \"2013\"");
        bar.contains("Volume = \"1\"");
        bar.contains("Note = \"huom!\"");
    }
}
