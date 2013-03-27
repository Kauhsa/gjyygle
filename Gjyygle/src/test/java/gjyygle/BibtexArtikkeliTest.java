
package gjyygle;

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
}
