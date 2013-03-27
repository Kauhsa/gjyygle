
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
    @Test
    public void canCreateArticle() {
        assertTrue(article != null);
    }
    @Test
    public void articleHasCorrectAuthor() {
        assertEquals("Arto Vihavainen", article.getAuthor());
    }
    @Test
    public void articleHasCorrectTitle() {
        assertEquals("A Software Craftsman's Approach to Data Structures", article.getTitle());
    }
    @Test
    public void articleHasCorrectJournal() {
        assertEquals("A4 Article in conference publication", article.getJournal());
    }
    @Test
    public void articleHasCorrectYear() {
        assertEquals(2011, article.getYear());
    }
    @Test
    public void canSetArticleAuthor() {
        article.setAuthor("plagioija");
        assertEquals("plagioija", article.getAuthor());
    }
    @Test
    public void canSetArticleTitle() {
        article.setTitle("Three years of design-based research to reform a software engineering curriculum");
        assertEquals("Three years of design-based research to reform a software engineering curriculum", article.getTitle());
    }
    @Test
    public void canSetArticleJournal() {
        article.setJournal("arton vihko");
        assertEquals("arton vihko", article.getJournal());
    }
    @Test
    public void canSetArticleYear() {
        article.setYear(2020);
        assertEquals(2020, article.getYear());
    }
    @Test
    public void doesntHaveVolumeBeforeIsSet() {
        assertEquals(-1, article.getVolume());
    }
    @Test
    public void canSetArticleVolume() {
        article.setVolume(11);
        assertEquals(11, article.getVolume());
    }
    @Test
    public void doesntHaveNumberBeforeIsSet() {
        assertEquals(-1, article.getNumber());
    }
    @Test
    public void canSetArticleNumber() {
        article.setNumber(5);
        assertEquals(5, article.getNumber());
    }
    @Test
    public void doesntHavePagesBeforeIsSet() {
        assertEquals(-1, article.getPagesStart());
        assertEquals(-1, article.getPagesEnd());
    }
    @Test
    public void canSetArticlePages() {
        article.setPages(20, 25);
        assertEquals(20, article.getPagesStart());
        assertEquals(25, article.getPagesEnd());
    }
    @Test
    public void doesntHaveMonthBeforeIsSet() {
        assertEquals(-1, article.getMonth());
    }
    @Test
    public void canSetMonthNumber() {
        article.setMonth(9);
        assertEquals(9, article.getMonth());
    }
    @Test
    public void doesntHaveNoteBeforeIsSet() {
        assertEquals(null, article.getNote());
    }
    @Test
    public void canSetArticleNote() {
        article.setNote("löytyi jostain");
        assertEquals("löytyi jostain", article.getNote());
    }

    //keyn arvotyyppiä (int/string/?) ei ole löytynyt mistään, oletetaan string
    @Test
    public void doesntHaveKeyBeforeIsSet() {
        assertEquals(null, article.getKey());
    }
    @Test
    public void canSetArticleKey() {
        article.setKey("??");
        assertEquals("??", article.getKey());
    }
}
