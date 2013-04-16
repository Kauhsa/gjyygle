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
//Author = "joku tyyppi",
//Title = "hieno artikkeli",
//Journal = "jostain kirjasta kai",
//Year = "2013",
//Volume = "1",
//Note = "huom!"
//}
//
//@ARTICLE{b,
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
        String bar = foo.toString();
        assertTrue(bar.contains("@article{"));
    }

    @Test
    public void hasAllEntries() {
        String bar = foo.toString();
        assertTrue(bar.contains("@article{aaa"));
        assertTrue(bar.contains("@article{b"));
        assertTrue(bar.contains("@article{c"));
    }

    @Test
    public void doesntPrintID() {
        String bar = foo.toString();
        assertTrue(!bar.contains("id = \"aaa\""));
        assertTrue(!bar.contains("id = \"b\""));
        assertTrue(!bar.contains("id = \"c\""));
    }

    @Test
    public void articleAaaHasAllFields() {
        String bar = foo.toString();
        assertTrue(bar.contains("author = \"joku tyyppi\""));
        assertTrue(bar.contains("title = \"hieno artikkeli\""));
        assertTrue(bar.contains("journal = \"jostain kirjasta kai\""));
        assertTrue(bar.contains("year = \"2013\""));
        assertTrue(bar.contains("volume = \"1\""));
        assertTrue(bar.contains("note = \"huom!\""));
    }
    
    @Test
    public void removesNullFields() {/*
        BibtexEntry tuhmaEntry = new BibtexEntry(BibtexEntryType.ARTICLE);
        tuhmaEntry.setValue(BibtexField.TITLE, "hmm");
        tuhmaEntry.setValue(BibtexField.YEAR, null);
        db.lisaaArtikkeli(tuhmaEntry);
        String bar = foo.toByteArray().toString();*/
    }
}
