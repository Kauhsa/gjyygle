
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
    
    HashMap<String, String> kirjanTiedot;
    HashMap<String, String> artonPaperi;
    HashMap<String, String> inproceedingsTiedot;
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
        artonPaperi.put("ID", "arto1");
        kirjanTiedot = new HashMap<String, String>();
        kirjanTiedot.put("Author", "tyyppi");
        kirjanTiedot.put("Editor", "toinen tyyppi");
        kirjanTiedot.put("Title", "kirja");
        kirjanTiedot.put("Publisher", "julkaisu oy");
        kirjanTiedot.put("Year", "2010");
        kirjanTiedot.put("ID", "kirja");
        inproceedingsTiedot = new HashMap<String, String>();
        inproceedingsTiedot.put("Author", "jätkä");
        inproceedingsTiedot.put("Title", "epävalmis");
        inproceedingsTiedot.put("Booktitle", "kirja");
        inproceedingsTiedot.put("Year", "1951");
        inproceedingsTiedot.put("ID", "juttu");
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
    public void canSetField() throws ValidationException {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        artikkeli.setValue(BibtexField.AUTHOR, "plagioija");
        assertEquals("plagioija", artikkeli.getValue(BibtexField.AUTHOR));
    }
    
    @Test(expected=ValidationException.class)
    public void cannotEnterStringYear() throws ValidationException {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        artikkeli.setValue(BibtexField.YEAR, "arton syntymäpäivä");
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
    
    @Test
    public void canCreateBook() {
        BibtexEntry kirja = new BibtexEntry(BibtexEntryType.BOOK);
        assertTrue(kirja != null);
    }

    @Test
    public void emptyBookDoesntHaveFields() {
        BibtexEntry kirja = new BibtexEntry(BibtexEntryType.BOOK);
        assertFalse(kirja.hasFields());
    }
    
    @Test
    public void canGiveBookFields() {
        BibtexEntry kirja = new BibtexEntry(kirjanTiedot, BibtexEntryType.BOOK);
        assertTrue(kirja.hasFields());
    }

    @Test
    public void canSetBookField() {
        BibtexEntry kirja = new BibtexEntry(kirjanTiedot, BibtexEntryType.BOOK);
        try {
            kirja.setValue(BibtexField.ADDRESS, "helsinki");
        }
        catch (Exception e) {
            
        }
        assertTrue(kirja.getValue(BibtexField.ADDRESS).equals("helsinki"));
    }
    
    @Test
    public void canCreateInproceedings() {
        BibtexEntry inproceedings = new BibtexEntry(BibtexEntryType.INPROCEEDINGS);
        assertTrue(inproceedings != null);
    }

    @Test
    public void inproceedingsHasFields() {
        BibtexEntry inproceedings = new BibtexEntry(inproceedingsTiedot, BibtexEntryType.INPROCEEDINGS);
        assertTrue(inproceedings.hasFields());
    }
    @Test
    public void canSetInproceedingsFields() {
        BibtexEntry inproceedings = new BibtexEntry(inproceedingsTiedot, BibtexEntryType.INPROCEEDINGS);
        try {
            inproceedings.setValue(BibtexField.EDITOR, "Arto");
        }
        catch (Exception e) {
            
        }
        assertTrue(inproceedings.getValue(BibtexField.EDITOR).equals("Arto"));
    }
    
    @Test
    public void removesEmptyFields() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        try {
            artikkeli.setValue(BibtexField.MONTH, "");
        }
        catch (Exception e) {
            
        }
        artikkeli.poistaTyhjat();
        assertFalse(artikkeli.getAllValues().containsKey(BibtexField.MONTH));
    }
    @Test
    public void toStringWorks() {
        BibtexEntry artikkeli = new BibtexEntry(artonPaperi, BibtexEntryType.ARTICLE);
        assertEquals("arto1, Arto Vihavainen, A Software Craftsman's Approach to Data Structures, 2012, Article.", artikkeli.toString());
    }
}
