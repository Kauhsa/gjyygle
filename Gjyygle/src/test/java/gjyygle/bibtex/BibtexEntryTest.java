
package gjyygle.bibtex;

import gjyygle.bibtex.BibtexEntry;
import gjyygle.bibtex.BibtexEntryType;
import gjyygle.bibtex.BibtexField;
import java.util.EnumMap;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BibtexEntryTest {
    
    HashMap<String, String> artonPaperi;
    public BibtexEntryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        artonPaperi = new HashMap<String, String>();
        artonPaperi.put("Author", "Arto Vihavainen");
        artonPaperi.put("Title", "A Software Craftsman's Approach to Data Structures");
        artonPaperi.put("Journal", "A4 Article in conference publication");
        artonPaperi.put("Year", "2012");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void canCreateArticle() {
        BibtexEntry artikkeli = new BibtexEntry(BibtexEntryType.ARTICLE);
        assertTrue(artikkeli != null);
    }
    
    @Test
    public void canGiveRequiredValues() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        assertTrue(artikkeli != null);
    }
    
    @Test
    public void getAllValuesWorks() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        EnumMap<BibtexField, String> ret = artikkeli.getAllValues();
        assertEquals("Arto Vihavainen", ret.get(BibtexField.AUTHOR));
        assertEquals("A Software Craftsman's Approach to Data Structures", ret.get(BibtexField.TITLE));
        assertEquals("A4 Article in conference publication", ret.get(BibtexField.JOURNAL));
        assertEquals("2012", ret.get(BibtexField.YEAR));
    }
    
    @Test
    public void hasCorrectInitialValues() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        assertEquals("Arto Vihavainen", artikkeli.getValue(BibtexField.AUTHOR));
    }
    
    @Test
    public void returnsIntCorrectly() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        assertEquals(2012, artikkeli.getValueInt(BibtexField.YEAR));
    }

    @Test
    public void returnsIntAsStringCorrectly() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        assertEquals("2012", artikkeli.getValue(BibtexField.YEAR));
    }
    
    @Test
    public void canSetField() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        artikkeli.setValue(BibtexField.AUTHOR, "plagioija");
        assertEquals("plagioija", artikkeli.getValue(BibtexField.AUTHOR));
    }
    
    @Test
    public void cannotEnterStringYear() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        try {
            artikkeli.setValue(BibtexField.YEAR, "arton syntymäpäivä");
        }
        catch (IllegalArgumentException e) {
            
        }
        assertEquals("2012", artikkeli.getValue(BibtexField.YEAR));
    }
    
    @Test
    public void cannotFindEntryThatDoesntExist() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        assertEquals(null, artikkeli.getValue(BibtexField.MONTH));
    }
    
    @Test
    public void cannotFindNumberThatDoesntExist() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        assertEquals(-1, artikkeli.getValueInt(BibtexField.MONTH));
    }
    
    @Test
    public void emptyArticleDoesntHaveFields() {
        BibtexEntry artikkeli = new BibtexEntry(BibtexEntryType.ARTICLE);
        assertFalse(artikkeli.hasFields());
    }
}
